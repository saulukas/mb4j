package org.mb4j.controller.form.field;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mb4j.controller.utils.ReflectionUtils;
import org.mb4j.controller.utils.SimpleClassName;

public class FormFieldRecord extends FormFieldBase {
  public Map<String, FormField> asMap() {
    Map<String, FormField> formFields = new HashMap<>();
    collectFields("", formFields);
    return formFields;
  }

  private Map<String, FormFieldBase> childrenMap() {
    return ReflectionUtils.getFieldsOf(this, FormFieldBase.class, FormFieldBase.class);
  }

  @Override
  void collectFields(String nameInParent, Map<String, FormField> fieldMap) {
    String namePrefix = (nameInParent.isEmpty() ? "" : nameInParent + ".");
    Class superClass = getClass();
    while (superClass != null && FormFieldRecord.class.isAssignableFrom(superClass)) {
      Field[] declaredFields = superClass.getDeclaredFields();
      for (Field declaredField : declaredFields) {
        Object fieldValue = ReflectionUtils.valueOf(declaredField, this);
        String fieldName = declaredField.getName();
        if (fieldValue instanceof FormFieldBase) {
          FormFieldBase member = (FormFieldBase) fieldValue;
          member.collectFields(namePrefix + fieldName, fieldMap);
        } else {
          throw new RuntimeException("Illegal attribute '" + fieldName + "' of type "
              + SimpleClassName.of(declaredField.getType()) + " found in " + getClass() + ".\n"
              + FormFieldRecord.class.getSimpleName() + " must containt only non-null attributes of types: "
              + FormField.class.getSimpleName() + ", "
              + FormFieldRecord.class.getSimpleName() + " or "
              + FormFieldList.class.getSimpleName() + ".");
        }
      }
      superClass = superClass.getSuperclass();
    }
  }

  public void setValuesFrom(FormFieldValueTree valueTree) {
    setValuesFrom(valueTree.root);
  }

  @Override
  void setValuesFrom(FormFieldValueNode node) {
    Map<String, FormFieldBase> childrenMap = childrenMap();
    for (Map.Entry<String, FormFieldBase> entry : childrenMap.entrySet()) {
      String name = entry.getKey();
      FormFieldValueNode childNode = node.children.get(name);
      if (childNode != null) {
        FormFieldBase child = entry.getValue();
        child.setValuesFrom(childNode);
      }
    }
  }

  @Override
  public String toString(String margin) {
    String result = SimpleClassName.of(getClass());
    Map<String, FormFieldBase> children = childrenMap();
    Iterator<String> namesIterator = children.keySet().iterator();
    while (namesIterator.hasNext()) {
      String childName = namesIterator.next();
      FormFieldBase child = children.get(childName);
      result += "\n" + margin + "    " + childName + " = " + child.toString(margin + "    ");
    }
    return result;
  }
}
