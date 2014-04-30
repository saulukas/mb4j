package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import static org.mb4j.controller.ControllerRequestAttribute.requestAttribute;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.form.FormAction;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormResponse;
import static org.mb4j.controller.form.FormResponse.Redirect.redirectTo;
import static org.mb4j.controller.form.FormResponse.RepeatRerendering.repeatRendering;
import org.mb4j.controller.form.field.FormField;
import static org.mb4j.controller.form.field.FormField.createOptionalField;
import static org.mb4j.controller.form.field.FormField.createRequiredField;
import org.mb4j.controller.form.field.FormFieldRecord;
import org.mb4j.servlet.sample.domain.Event;
import org.mb4j.servlet.sample.domain.EventByIdQuery;
import org.mb4j.servlet.sample.domain.EventSaveCommand;
import org.mb4j.servlet.sample.event.list.EventListPage;

@Singleton
public class EventEditForm extends Form<EventEditForm.Fields> {
  @Inject
  EventByIdQuery eventByIdQuery;
  @Inject
  EventSaveCommand eventSaveCommand;

  static class Fields extends FormFieldRecord {
    FormField id = createRequiredField();
    FormField title = createRequiredField();
    FormField summary = createOptionalField();
    FormField imageUrl = createOptionalField();
  }

  FormData<Fields> data(ControllerRequest request, int eventId) {
    Event event = eventByIdQuery.result(eventId).get();
    Fields fields = createEmptyFields();
    fields.id.value = Integer.toString(event.id);
    fields.title.value = event.title;
    fields.summary.value = event.summary;
    fields.imageUrl.value = event.imageUrl;
    return dataWith(fields);
  }

  @FormAction
  FormResponse save(ControllerRequest request, Fields fields) {
    System.out.println("save: " + fields);
    eventSaveCommand.execute(createEventFrom(fields));
    return repeatRendering().with(requestAttribute(fields.id.value, fields));
  }

  @FormAction
  FormResponse reset(ControllerRequest request, Fields fields) {
    System.out.println("reset: " + fields);
    fields.summary.value = null;
    return repeatRendering().with(requestAttribute(fields.id.value, fields));
  }

  @FormAction
  FormResponse goToEventList(ControllerRequest request, Fields fields) {
    System.out.println("goToEventList: " + fields);
    return redirectTo(request.resolve(EventListPage.url()));
  }

  private Event createEventFrom(Fields fields) {
    return new Event(
        Integer.parseInt(fields.id.value),
        fields.imageUrl.value,
        fields.title.value,
        fields.summary.value);
  }
}
