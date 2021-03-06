package org.mb4j.component.form.data;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.component.form.data.binding.FormFieldValueNode;

public abstract class AbstractFormData {

    public Map<String, FormField> asFieldMap() {
        Map<String, FormField> formFields = new HashMap<>();
        collectFields("", formFields);
        return formFields;
    }

    abstract void collectFields(String nameInParent, Map<String, FormField> fieldMap);

    abstract void setValuesFrom(FormFieldValueNode node);

    public abstract String toString(String margin);

    public abstract boolean hasErrors();

    @Override
    public String toString() {
        return toString("");
    }
}
