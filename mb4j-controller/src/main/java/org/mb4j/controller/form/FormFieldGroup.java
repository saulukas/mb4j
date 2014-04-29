package org.mb4j.controller.form;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mb4j.controller.url.NamedParams;
import org.mb4j.controller.utils.SimpleClassName;

public class FormFieldGroup extends FormFieldBase {
  public Map<String, FormField> asMap() {
    Map<String, FormField> formFields = new HashMap<>();
    collectFields("", formFields);
    return formFields;
  }

  @Override
  void collectFields(String nameInParent, Map<String, FormField> fieldMap) {
    String namePrefix = (nameInParent.isEmpty() ? "" : nameInParent + ".");
    Class superClass = getClass();
    while (superClass != null && FormFieldGroup.class.isAssignableFrom(superClass)) {
      Field[] declaredFields = superClass.getDeclaredFields();
      for (Field declaredField : declaredFields) {
        Object fieldValue;
        try {
          declaredField.setAccessible(true);
          fieldValue = declaredField.get(this);
        } catch (Exception ex) {
          throw new RuntimeException("Failed to access field: " + declaredField);
        }
        String fieldName = declaredField.getName();
        if (fieldValue instanceof FormFieldBase) {
          FormFieldBase member = (FormFieldBase) fieldValue;
          member.collectFields(namePrefix + fieldName, fieldMap);
        } else {
          throw new RuntimeException("Illegal attribute '" + fieldName + "' found in " + getClass() + ".\n"
              + FormFieldGroup.class.getSimpleName() + " must containt only non-null attributes of types: "
              + FormField.class.getSimpleName() + ", "
              + FormFieldGroup.class.getSimpleName() + " or "
              + FormFieldList.class.getSimpleName() + ".");
        }
      }
      superClass = superClass.getSuperclass();
    }
  }

  public void setValuesFrom(NamedParams params) {
  }

  @Override
  public String toString(String margin) {
    String result = SimpleClassName.of(getClass());
    Map<String, FormFieldBase> children = getChildren();
    Iterator<String> namesIterator = children.keySet().iterator();
    while (namesIterator.hasNext()) {
      String childName = namesIterator.next();
      FormFieldBase child = children.get(childName);
      result += "\n" + margin + "    " + childName + " = " + child.toString(margin + "    ");
    }
    return result;
  }
}
