package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.Form;
import org.mb4j.controller.form.FormAction;
import org.mb4j.controller.form.FormActionResponse;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormField;
import static org.mb4j.controller.form.FormField.createOptionalField;
import static org.mb4j.controller.form.FormField.createRequiredField;
import org.mb4j.servlet.sample.domain.Event;

@Singleton
public class EventEditForm extends Form {
  @Inject
  SaveAction saveAction;
  @Inject
  ResetAction resetAction;

  public static class Data extends FormData {
    FormField id = createRequiredField();
    FormField title = createRequiredField();
    FormField summary = createOptionalField();
    FormField imageUrl = createOptionalField();
    SaveAction save;
    ResetAction reset;
  }

  public Data dataFrom(Event event) {
    Data data = new Data();
    data.id.value = Integer.toString(event.id);
    data.title.value = event.title;
    data.summary.value = event.summary;
    data.imageUrl.value = event.imageUrl;
    data.save = saveAction;
    data.reset = resetAction;
    return data;
  }

  static class SaveAction extends FormAction<Data> {
    @Override
    protected FormActionResponse handle(ControllerRequest request, Data formData) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }

  static class ResetAction extends FormAction<Data> {
    @Override
    protected FormActionResponse handle(ControllerRequest request, Data formData) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
  }
}
