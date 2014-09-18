package org.mb4j.example.liferay.event.edit;

import static com.google.common.base.Strings.isNullOrEmpty;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.component.Request;
import org.mb4j.component.form.FormOldVersion;
import org.mb4j.component.form.FormHandler;
import org.mb4j.component.form.action.FormActionMethod;
import org.mb4j.component.form.data.FormData;
import org.mb4j.component.form.data.FormField;
import static org.mb4j.component.form.data.FormField.optionalField;
import static org.mb4j.component.form.data.FormField.requiredField;
import org.mb4j.component.form.response.FormResponse;
import static org.mb4j.component.form.response.FormResponseRedirectToView.redirectTo;
import static org.mb4j.component.form.response.FormResponseRenderCurrentPage.renderCurrentPage;
import org.mb4j.component.utils.AttributeKey;
import org.mb4j.example.domain.commands.EventSaveCommand;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.domain.queries.EventByIdQuery;
import org.mb4j.example.liferay.event.list.EventListView;
import org.mb4j.example.liferay.util.FormFieldWithLabel;
import static org.mb4j.example.liferay.util.FormFieldWithLabel.optionalFieldWithLabel;
import static org.mb4j.example.liferay.util.FormFieldWithLabel.requiredFieldWithLabel;

@Singleton
public class EventEditFormHandler extends FormHandler<EventEditFormHandler.Data> {

    private static final AttributeKey<Data> DATA_KEY = new AttributeKey<Data>() {
    };
    @Inject
    EventByIdQuery eventByIdQuery;
    @Inject
    EventSaveCommand eventSaveCommand;

    static class Data extends FormData {

        FormField id = requiredField();
        FormFieldWithLabel title = requiredFieldWithLabel("Title:");
        FormFieldWithLabel summary = optionalFieldWithLabel("Summary:");
        FormField imageUrl = optionalField();
    }

    private Data createData(Event event) {
        Data data = createEmptyFields();
        data.id.value = Integer.toString(event.id);
        data.title.value = event.title;
        data.summary.value = event.summary;
        data.imageUrl.value = event.imageUrl;
        return data;
    }

    private Event createEvent(Data data) {
        return new Event(
                Integer.parseInt(data.id.value),
                data.imageUrl.value,
                data.title.value,
                data.summary.value);
    }

    FormOldVersion<Data> fillForm(Request request, int eventId) {
        Data data = request.attributes().valueOf(DATA_KEY).orNull();
        if (data == null) {
            Event event = eventByIdQuery.result(eventId).get();
            data = createData(event);
        }
        return formWith(data);
    }

    @FormActionMethod
    FormResponse save(Request request, Data data) {
        if (validated(data).hasErrors()) {
            return renderCurrentPage(request).with(DATA_KEY, data);
        }
        eventSaveCommand.execute(createEvent(data));
        return redirectTo(EventListView.url());
    }

    @FormActionMethod
    FormResponse reset(Request request, Data data) {
        data.summary.value = "";
        return renderCurrentPage(request).with(DATA_KEY, data);
    }

    @FormActionMethod
    FormResponse goToEventList(Request request, Data data) {
        return redirectTo(EventListView.url());
    }

    private Data validated(Data data) {
        data.title.setErrorIf(isNullOrEmpty(data.title.value), "Title may not be empty.");
        return data;
    }
}
