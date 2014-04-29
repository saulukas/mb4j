package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Singleton;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.form.FormAction;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormField;
import static org.mb4j.controller.form.FormField.createOptionalField;
import static org.mb4j.controller.form.FormField.createRequiredField;
import org.mb4j.controller.form.FormFields;
import org.mb4j.servlet.sample.domain.Event;

@Singleton
public class EventEditForm extends Form<EventEditForm.Fields> {
  public static class Fields extends FormFields {
    FormField id = createRequiredField();
    FormField title = createRequiredField();
    FormField summary = createOptionalField();
    FormField imageUrl = createOptionalField();
  }

  public FormData<Fields> dataFrom(Event event) {
    Fields data = createEmptyFields();
    data.id.value = Integer.toString(event.id);
    data.title.value = event.title;
    data.summary.value = event.summary;
    data.imageUrl.value = event.imageUrl;
    return dataWith(data);
  }

  @FormAction
  void save() {
  }

  @FormAction
  void reset() {
  }
}
