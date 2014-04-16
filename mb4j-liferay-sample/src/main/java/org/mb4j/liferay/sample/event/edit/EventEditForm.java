package org.mb4j.liferay.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.liferay.sample.domain.Event;
import org.mb4j.liferay.sample.domain.EventSaveCommand;
import org.mb4j.liferay.sample.event.list.EventListBrickView;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.ViewResponse;
import static org.mb4j.controller.ViewResponse.redirectTo;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.form.FormAction;
import org.mb4j.controller.form.FormField;
import org.mb4j.controller.form.FormFiller;
import org.mb4j.controller.url.ViewUrl;

public class EventEditForm extends Form {
  final FormField id = FormField.requiredField();
  final FormField title = FormField.requiredField();
  final FormField summary = FormField.optionalField();
  final FormField imageUrl = FormField.optionalField();

  @Singleton
  public static class Filler extends FormFiller<Filler.Params, EventEditForm> {
    @Override
    protected EventEditForm createFormFrom(Params params) {
      EventEditForm form = new EventEditForm();
      form.id.setValue(Integer.toString(params.event.id));
      form.title.setValue(params.event.title);
      form.summary.setValue(params.event.summary);
      form.imageUrl.setValue(params.event.imageUrl);
      return form;
    }

    public static class Params {
      final Event event;

      public Params(Event event) {
        this.event = event;
      }
    }
  }

  @Singleton
  public static class SaveAction extends FormAction<EventEditForm> {
    @Inject
    EventSaveCommand saveCommand;

    public static ViewUrl url() {
      return ViewUrl.of(SaveAction.class);
    }

    @Override
    protected ViewResponse doHandle(ViewRequest request, EventEditForm form) {
      System.out.println("save event: " + form);
      Event event = createEventFrom(form);
      saveCommand.save(event);
      return redirectTo(EventListBrickView.url());
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
