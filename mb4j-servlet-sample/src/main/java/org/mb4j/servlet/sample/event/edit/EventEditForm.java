package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.servlet.sample.domain.Event;
import org.mb4j.servlet.sample.domain.EventSaveCommand;
import org.mb4j.servlet.sample.event.list.EventListPage;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.ViewResponse;
import static org.mb4j.controller.ViewResponse.redirectTo;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.form.FormAction;
import org.mb4j.controller.form.FormField;
import org.mb4j.controller.form.FormFiller;

public class EventEditForm extends Form {
  final FormField id = FormField.requiredField();
  final FormField title = FormField.requiredField();
  final FormField summary = FormField.optionalField();
  final FormField imageUrl = FormField.optionalField();

  @Singleton
  public static class Filler extends FormFiller<Filler.Params, EventEditForm> {
    public static class Params {
      final Event event;

      public Params(Event event) {
        this.event = event;
      }
    }

    @Override
    protected EventEditForm createFormFrom(Params params) {
      EventEditForm form = new EventEditForm();
      form.id.setValue(Integer.toString(params.event.id));
      form.title.setValue(params.event.title);
      form.summary.setValue(params.event.summary);
      form.imageUrl.setValue(params.event.imageUrl);
      return form;
    }
  }

  @Singleton
  public static class SaveAction extends FormAction<EventEditForm> {
    @Inject
    EventSaveCommand saveCommand;

    @Override
    protected ViewResponse doHandle(ControllerRequest request, EventEditForm form) {
      Event event = createEventFrom(form);
      saveCommand.save(event);
      return redirectTo(EventListPage.url());
    }

    private Event createEventFrom(EventEditForm form) throws NumberFormatException {
      Event event = new Event(
          Integer.parseInt(form.id.value()),
          form.imageUrl.value(),
          form.title.value(),
          form.summary.value());
      return event;
    }
  }
}
