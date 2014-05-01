package org.mb4j.servlet.sample.event.edit;

import static com.google.common.base.Strings.isNullOrEmpty;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.form.FormAction;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormResponse;
import static org.mb4j.controller.form.FormResponseRedirect.redirectTo;
import static org.mb4j.controller.form.FormResponseRenderCurrentPage.renderCurrentPage;
import org.mb4j.controller.form.field.FormField;
import static org.mb4j.controller.form.field.FormField.createOptionalField;
import static org.mb4j.controller.form.field.FormField.createRequiredField;
import org.mb4j.controller.form.field.FormFieldRecord;
import org.mb4j.controller.utils.AttributeKey;
import org.mb4j.servlet.sample.domain.Event;
import org.mb4j.servlet.sample.domain.EventByIdQuery;
import org.mb4j.servlet.sample.domain.EventSaveCommand;
import org.mb4j.servlet.sample.event.list.EventListPage;
import org.mb4j.servlet.sample.util.FormFieldWithLabel;
import static org.mb4j.servlet.sample.util.FormFieldWithLabel.optionalFieldWithLabel;
import static org.mb4j.servlet.sample.util.FormFieldWithLabel.requiredFieldWithLabel;

@Singleton
public class EventEditForm extends Form<EventEditForm.Fields> {
  private static final AttributeKey<Fields> FIELDS_KEY = new AttributeKey<Fields>() {
  };
  @Inject
  EventByIdQuery eventByIdQuery;
  @Inject
  EventSaveCommand eventSaveCommand;

  static class Fields extends FormFieldRecord {
    FormField id = createRequiredField();
    FormFieldWithLabel title = requiredFieldWithLabel("Title:");
    FormFieldWithLabel summary = optionalFieldWithLabel("Summary:");
    FormField imageUrl = createOptionalField();
  }

  private Fields createFieldsFrom(Event event) {
    Fields fields = createEmptyFields();
    fields.id.value = Integer.toString(event.id);
    fields.title.value = event.title;
    fields.summary.value = event.summary;
    fields.imageUrl.value = event.imageUrl;
    return fields;
  }

  private Event createEventFrom(Fields fields) {
    return new Event(
        Integer.parseInt(fields.id.value),
        fields.imageUrl.value,
        fields.title.value,
        fields.summary.value);
  }

  FormData<Fields> data(ControllerRequest request, int eventId) {
    Fields fields = request.attributeFor(FIELDS_KEY).orNull();
    if (fields == null) {
      Event event = eventByIdQuery.result(eventId).get();
      fields = createFieldsFrom(event);
    }
    return dataWith(fields);
  }

  @FormAction
  FormResponse save(ControllerRequest request, Fields fields) {
    System.out.println("save: " + fields);
    if (validate(fields)) {
      eventSaveCommand.execute(createEventFrom(fields));
    }
    return renderCurrentPage().with(FIELDS_KEY, fields);
  }

  @FormAction
  FormResponse reset(ControllerRequest request, Fields fields) {
    System.out.println("reset: " + fields);
    fields.summary.value = "";
    return renderCurrentPage().with(FIELDS_KEY, fields);
  }

  @FormAction
  FormResponse goToEventList(ControllerRequest request, Fields fields) {
    System.out.println("goToEventList: " + fields);
    return redirectTo(request.resolve(EventListPage.url()));
  }

  private boolean validate(Fields fields) {
    fields.title.setErrorIf(isNullOrEmpty(fields.title.value), "Title may not be empty.");
    return !fields.hasErrors();
  }
}
