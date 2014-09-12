package org.mb4j.example.servlet.event.edit;

import org.mb4j.component.form.data.FormData;
import org.mb4j.component.form.data.FormField;
import org.mb4j.example.servlet.util.FormFieldWithLabel;

class EventEditFormData extends FormData {

    FormField id = FormField.requiredField();
    FormFieldWithLabel title = FormFieldWithLabel.requiredFieldWithLabel("Title:");
    FormFieldWithLabel summary = FormFieldWithLabel.optionalFieldWithLabel("Summary:");
    FormField imageUrl = FormField.optionalField();

}
