package org.mb4j.sample.liferay.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.form.FormAction;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormResponse;
import org.mb4j.controller.form.field.FormField;
import static org.mb4j.controller.form.field.FormField.createOptionalField;
import static org.mb4j.controller.form.field.FormField.createRequiredField;
import org.mb4j.controller.form.field.FormFieldRecord;
import org.mb4j.sample.domain.commands.EventSaveCommand;
import org.mb4j.sample.domain.data.Event;

@Singleton
public class EventEditForm extends Form<EventEditForm.Fields> {
  @Inject
  EventSaveCommand saveCommand;

  public static class Fields extends FormFieldRecord {
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
  FormResponse save(ControllerRequest request, Fields fields) {
    System.out.println("save: " + fields);
    saveCommand.execute(createEventFrom(fields));
    return null;
  }

  @FormAction
  FormResponse reset(ControllerRequest request, Fields fields) {
    System.out.println("reset: " + fields);
    return null;
  }

  private Event createEventFrom(Fields fields) {
    return new Event(
        Integer.parseInt(fields.id.value),
        fields.imageUrl.value,
        fields.title.value,
        fields.summary.value);
  }
}
