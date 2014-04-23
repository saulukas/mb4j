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

  public Map<String, FormAction> getActions() {
    Map<String, FormAction> formActions = new HashMap<>();
    collectActionsFrom(this, "", formActions);
    return formActions;
  }

  public static void collectFieldsFrom(Object data, String namePrefix, Map<String, FormField> formFields) {
    Class dataClass = data.getClass();
    while (dataClass != null) {
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
        } else {
          collectFieldsFrom(fieldValue, namePrefix + declaredField.getName() + ".", formFields);
        }
      }
      dataClass = dataClass.getSuperclass();
    }
  }

  public static void collectActionsFrom(Object data, String namePrefix, Map<String, FormAction> formActions) {
    Class dataClass = data.getClass();
    while (dataClass != null) {
      Field[] declaredFields = dataClass.getDeclaredFields();
      for (Field declaredField : declaredFields) {
        Object fieldValue;
        try {
          declaredField.setAccessible(true);
          fieldValue = declaredField.get(data);
        } catch (Exception ex) {
          throw new RuntimeException("Failed to access field: " + declaredField);
        }
        if (fieldValue instanceof FormAction) {
          formActions.put(namePrefix + declaredField.getName(), (FormAction) fieldValue);
        }
      }
      dataClass = dataClass.getSuperclass();
    }
  }
}
