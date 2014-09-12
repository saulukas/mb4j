package org.mb4j.example.servlet.event.edit;

import com.google.common.base.Objects;
import static com.google.common.base.Strings.isNullOrEmpty;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.component.Request;
import org.mb4j.component.form.Form;
import org.mb4j.component.form.FormHandler;
import org.mb4j.component.form.action.FormActionMethod;
import org.mb4j.component.form.response.FormResponse;
import static org.mb4j.component.form.response.FormResponseRedirectToView.redirectTo;
import static org.mb4j.component.form.response.FormResponseRenderCurrentPage.renderCurrentPage;
import org.mb4j.component.utils.AttributeKey;
import org.mb4j.example.domain.commands.EventSaveCommand;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.domain.queries.EventByIdQuery;
import org.mb4j.example.servlet.event.list.EventListPage;

@Singleton
public class EventEditFormHandler extends FormHandler<EventEditFormData> {

    private static final AttributeKey<EventEditFormData> DATA_KEY = new AttributeKey<EventEditFormData>() {
    };
    @Inject
    EventByIdQuery eventByIdQuery;
    @Inject
    EventSaveCommand eventSaveCommand;

    private EventEditFormData createData(Event event) {
        EventEditFormData data = createEmptyFields();
        data.id.value = Integer.toString(event.id);
        data.title.value = event.title;
        data.summary.value = event.summary;
        data.imageUrl.value = event.imageUrl;
        return data;
    }

    private Event createEvent(EventEditFormData data) {
        return new Event(
                Integer.parseInt(data.id.value),
                data.imageUrl.value,
                data.title.value,
                data.summary.value);
    }

    Form<EventEditFormData> fillForm(Request request, int eventId) {
        EventEditFormData data = request.attributes().valueOf(DATA_KEY).orNull();
        if (data == null) {
            Event event = eventByIdQuery.result(eventId).get();
            data = createData(event);
        }
        return formWith(data);
    }

    @FormActionMethod
    FormResponse save(Request request, EventEditFormData data) {
        if (errorsFoundIn(data)) {
            return renderCurrentPage(request).with(DATA_KEY, data);
        }
        eventSaveCommand.execute(createEvent(data));
        return redirectTo(EventListPage.url());
    }

    @FormActionMethod
    FormResponse reset(Request request, EventEditFormData data) {
        data.summary.value = "";
        return renderCurrentPage(request).with(DATA_KEY, data);
    }

    @FormActionMethod
    FormResponse goToEventList(Request request, EventEditFormData data) {
        return redirectTo(EventListPage.url());
    }

    private boolean errorsFoundIn(EventEditFormData data) {
        data.title.setErrorIf(isNullOrEmpty(data.title.value), "Title may not be empty.");
        return data.hasErrors();
    }

    @Override
    protected boolean isActionEnabled(String name) {
        return !Objects.equal(name, "save");
    }
}
