package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.ControllerResponse;
import static org.mb4j.controller.form.FormActionResponse.redirectTo;
import org.mb4j.controller.form1.Form1;
import org.mb4j.controller.form1.FormAction1;
import org.mb4j.controller.form1.FormField1;
import org.mb4j.controller.form1.FormFiller1;
import org.mb4j.servlet.sample.domain.Event;
import org.mb4j.servlet.sample.domain.EventSaveCommand;
import org.mb4j.servlet.sample.event.list.EventListPage;

public class EventEditForm1 extends Form1 {
  final FormField1 id = FormField1.requiredField();
  final FormField1 title = FormField1.requiredField();
  final FormField1 summary = FormField1.optionalField();
  final FormField1 imageUrl = FormField1.optionalField();

  @Singleton
  public static class Filler extends FormFiller1<Filler.Params, EventEditForm1> {
    public static class Params {
      final Event event;

      public Params(Event event) {
        this.event = event;
      }
    }

    @Override
    protected EventEditForm1 createFormFrom(Params params) {
      EventEditForm1 form = new EventEditForm1();
      form.id.setValue(Integer.toString(params.event.id));
      form.title.setValue(params.event.title);
      form.summary.setValue(params.event.summary);
      form.imageUrl.setValue(params.event.imageUrl);
      return form;
    }
  }

  @Singleton
  public static class SaveAction extends FormAction1<EventEditForm1> {
    @Inject
    EventSaveCommand saveCommand;

    @Override
    protected ControllerResponse doHandle(ControllerRequest request, EventEditForm1 form) {
      Event event = createEventFrom(form);
      saveCommand.save(event);
      return redirectTo(EventListPage.url());
    }

    private Event createEventFrom(EventEditForm1 form) throws NumberFormatException {
      Event event = new Event(
          Integer.parseInt(form.id.value()),
          form.imageUrl.value(),
          form.title.value(),
          form.summary.value());
      return event;
    }
  }
}
