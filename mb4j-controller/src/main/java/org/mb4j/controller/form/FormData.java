package org.mb4j.controller.form;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FormData {
  public Map<String, FormField> getFields() {
    Map<String, FormField> formFields = new HashMap<>();
    collectFieldsFrom(this, "", formFields);
    return formFields;
  }

  public Map<String, FormAction2> getActions() {
    Map<String, FormAction2> formActions = new HashMap<>();
    collectActionsFrom(this, "", formActions);
    return formActions;
  }

  public static void collectFieldsFrom(FormData data, String namePrefix, Map<String, FormField> formFields) {
    Class dataClass = data.getClass();
    while (dataClass != null && FormData.class.isAssignableFrom(dataClass)) {
      Field[] declaredFields = dataClass.getDeclaredFields();
      for (Field declaredField : declaredFields) {
        Object fieldValue;
        try {
          declaredField.setAccessible(true);
          fieldValue = declaredField.get(data);
        } catch (Exception ex) {
          throw new RuntimeException("Failed to access field: " + declaredField);
        }
        if (fieldValue instanceof FormField) {
          formFields.put(namePrefix + declaredField.getName(), (FormField) fieldValue);
        } else if (fieldValue instanceof FormData) {
          collectFieldsFrom((FormData) fieldValue, namePrefix + declaredField.getName() + ".", formFields);
        }
      }
      dataClass = dataClass.getSuperclass();
    }
  }

  public static void collectActionsFrom(Object data, String namePrefix, Map<String, FormAction2> formActions) {
    Class dataClass = data.getClass();
    while (dataClass != null && FormData.class.isAssignableFrom(dataClass)) {
      Field[] declaredFields = dataClass.getDeclaredFields();
      for (Field declaredField : declaredFields) {
        Object fieldValue;
        try {
          declaredField.setAccessible(true);
          fieldValue = declaredField.get(data);
        } catch (Exception ex) {
          throw new RuntimeException("Failed to access field: " + declaredField);
        }
        if (fieldValue instanceof FormAction2) {
          formActions.put(namePrefix + declaredField.getName(), (FormAction2) fieldValue);
        }
      }
      dataClass = dataClass.getSuperclass();
    }
  }
}
