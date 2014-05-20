package org.mb4j.example.liferay.event.edit;

import static com.google.common.base.Strings.isNullOrEmpty;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.form.Form;
import org.mb4j.component.form.FormActionMethod;
import org.mb4j.component.form.FormData;
import org.mb4j.component.form.FormResponse;
import static org.mb4j.component.form.FormResponseRedirectToController.redirectTo;
import static org.mb4j.component.form.FormResponseRenderCurrentPage.renderCurrentPage;
import org.mb4j.component.form.field.FormField;
import static org.mb4j.component.form.field.FormField.createOptionalField;
import static org.mb4j.component.form.field.FormField.createRequiredField;
import org.mb4j.component.form.field.FormFieldRecord;
import org.mb4j.component.utils.AttributeKey;
import org.mb4j.example.domain.commands.EventSaveCommand;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.domain.queries.EventByIdQuery;
import org.mb4j.example.liferay.event.list.EventListView;
import org.mb4j.example.liferay.util.FormFieldWithLabel;
import static org.mb4j.example.liferay.util.FormFieldWithLabel.optionalFieldWithLabel;
import static org.mb4j.example.liferay.util.FormFieldWithLabel.requiredFieldWithLabel;

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

  FormData<Fields> dataFor(ViewRequest request, int eventId) {
    Fields fields = request.attributes().valueOf(FIELDS_KEY).orNull();
    if (fields == null) {
      Event event = eventByIdQuery.result(eventId).get();
      fields = createFieldsFrom(event);
    }
    return dataWith(fields);
  }

  @FormActionMethod
  FormResponse save(ViewRequest request, Fields fields) {
    if (errorsFoundIn(fields)) {
      return renderCurrentPage(request).with(FIELDS_KEY, fields);
    }
    eventSaveCommand.execute(createEventFrom(fields));
    return redirectTo(EventListView.url());
  }

  @FormActionMethod
  FormResponse reset(ViewRequest request, Fields fields) {
    fields.summary.value = "";
    return renderCurrentPage(request).with(FIELDS_KEY, fields);
  }

  @FormActionMethod
  FormResponse goToEventList(ViewRequest request, Fields fields) {
    return redirectTo(EventListView.url());
  }

  private boolean errorsFoundIn(Fields fields) {
    fields.title.setErrorIf(isNullOrEmpty(fields.title.value), "Title may not be empty.");
    return fields.hasErrors();
  }
}
