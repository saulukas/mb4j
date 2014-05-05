package org.mb4j.sample.liferay.event.edit;

import static com.google.common.base.Strings.isNullOrEmpty;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.form.FormActionMethod;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormResponse;
import static org.mb4j.controller.form.FormResponseRedirectToController.redirectTo;
import static org.mb4j.controller.form.FormResponseRenderCurrentPage.renderCurrentPage;
import org.mb4j.controller.form.field.FormField;
import static org.mb4j.controller.form.field.FormField.createOptionalField;
import static org.mb4j.controller.form.field.FormField.createRequiredField;
import org.mb4j.controller.form.field.FormFieldRecord;
import org.mb4j.controller.utils.AttributeKey;
import org.mb4j.sample.domain.commands.EventSaveCommand;
import org.mb4j.sample.domain.data.Event;
import org.mb4j.sample.domain.queries.EventByIdQuery;
import org.mb4j.sample.liferay.event.list.EventListPage;
import org.mb4j.sample.liferay.util.FormFieldWithLabel;
import static org.mb4j.sample.liferay.util.FormFieldWithLabel.optionalFieldWithLabel;
import static org.mb4j.sample.liferay.util.FormFieldWithLabel.requiredFieldWithLabel;

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

  FormData<Fields> dataFor(ControllerRequest request, int eventId) {
    Fields fields = request.attributes().valueOf(FIELDS_KEY).orNull();
    if (fields == null) {
      Event event = eventByIdQuery.result(eventId).get();
      fields = createFieldsFrom(event);
    }
    return dataWith(fields);
  }

  @FormActionMethod
  FormResponse save(ControllerRequest request, Fields fields) {
    if (errorsFoundIn(fields)) {
      return renderCurrentPage().with(FIELDS_KEY, fields);
    }
    eventSaveCommand.execute(createEventFrom(fields));
    return redirectTo(EventListPage.url());
  }

  @FormActionMethod
  FormResponse reset(ControllerRequest request, Fields fields) {
    fields.summary.value = "";
    return renderCurrentPage().with(FIELDS_KEY, fields);
  }

  @FormActionMethod
  FormResponse goToEventList(ControllerRequest request, Fields fields) {
    return redirectTo(EventListPage.url());
  }

  private boolean errorsFoundIn(Fields fields) {
    fields.title.setErrorIf(isNullOrEmpty(fields.title.value), "Title may not be empty.");
    return fields.hasErrors();
  }
}
