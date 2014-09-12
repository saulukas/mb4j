package org.mb4j.component.form.data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mb4j.component.form.data.binding.FormFieldValueNode;
import org.mb4j.component.form.data.binding.FormFieldValueTree;
import org.mb4j.component.utils.ReflectionUtils;
import org.mb4j.component.utils.SimpleClassName;

public class FormData extends AbstractFormData {

    public Map<String, FormField> asMap() {
        Map<String, FormField> formFields = new HashMap<>();
        collectFields("", formFields);
        return formFields;
    }

    @Override
    public boolean hasErrors() {
        for (AbstractFormData child : childrenMap().values()) {
            if (child.hasErrors()) {
                return true;
            }
        }
        return false;
    }

    private Map<String, AbstractFormData> childrenMap() {
        return ReflectionUtils.getNonStaticFieldsOf(this, AbstractFormData.class, AbstractFormData.class);
    }

    @Override
    void collectFields(String nameInParent, Map<String, FormField> fieldMap) {
        String namePrefix = (nameInParent.isEmpty() ? "" : nameInParent + ".");
        Class superClass = getClass();
        while (superClass != null && FormData.class.isAssignableFrom(superClass)) {
            Field[] declaredFields = superClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Object fieldValue = ReflectionUtils.valueOf(declaredField, this);
                String fieldName = declaredField.getName();
                if (fieldValue instanceof AbstractFormData) {
                    AbstractFormData member = (AbstractFormData) fieldValue;
                    member.collectFields(namePrefix + fieldName, fieldMap);
                } else {
                    throw new RuntimeException("Illegal attribute '" + fieldName + "' of type "
                            + SimpleClassName.of(declaredField.getType()) + " found in " + getClass() + ".\n"
                            + FormData.class.getSimpleName() + " must containt only non-null attributes of types: "
                            + FormField.class.getSimpleName() + ", "
                            + FormData.class.getSimpleName() + " or "
                            + FormDataList.class.getSimpleName() + ".");
                }
            }
            superClass = superClass.getSuperclass();
        }
    }

    public void setValuesFrom(Map<String, String> name2valueMap) {
        setValuesFrom(FormFieldValueTree.fieldValueTreeOf(name2valueMap));
    }

    public void setValuesFrom(FormFieldValueTree valueTree) {
        setValuesFrom(valueTree.root);
    }

    @Override
    void setValuesFrom(FormFieldValueNode node) {
        Map<String, AbstractFormData> childrenMap = childrenMap();
        for (Map.Entry<String, AbstractFormData> entry : childrenMap.entrySet()) {
            String name = entry.getKey();
            FormFieldValueNode childNode = node.children.get(name);
            if (childNode != null) {
                AbstractFormData child = entry.getValue();
                child.setValuesFrom(childNode);
            }
        }
    }

    @Override
    public String toString(String margin) {
        String result = SimpleClassName.of(getClass());
        Map<String, AbstractFormData> children = childrenMap();
        Iterator<String> namesIterator = children.keySet().iterator();
        while (namesIterator.hasNext()) {
            String childName = namesIterator.next();
            AbstractFormData child = children.get(childName);
            result += "\n" + margin + "    " + childName + " = " + child.toString(margin + "    ");
        }
        return result;
    }
}
