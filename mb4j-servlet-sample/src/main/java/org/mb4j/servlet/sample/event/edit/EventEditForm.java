package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.form.FormAction;
import org.mb4j.controller.form.FormAction2;
import org.mb4j.controller.form.FormActionResponse;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormField;
import static org.mb4j.controller.form.FormField.createOptionalField;
import static org.mb4j.controller.form.FormField.createRequiredField;
import org.mb4j.servlet.sample.domain.Event;

@Singleton
public class EventEditForm extends Form<EventEditForm.Data> {
  public static class Data extends FormData {
    FormField id = createRequiredField();
    FormField title = createRequiredField();
    FormField summary = createOptionalField();
    FormField imageUrl = createOptionalField();
  }

  public Data dataFrom(Event event) {
    Data data = createEmptyData();
    data.id.value = Integer.toString(event.id);
    data.title.value = event.title;
    data.summary.value = event.summary;
    data.imageUrl.value = event.imageUrl;
    return data;
  }

  @FormAction
  void save() {
  }

  @FormAction
  void reset() {
  }

  static class SaveAction extends FormAction2<Data> {
    @Override
    protected FormActionResponse handle(ControllerRequest request, Data formData) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  static class ResetAction extends FormAction2<Data> {
    @Override
    protected FormActionResponse handle(ControllerRequest request, Data formData) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }
}
