package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Singleton;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormField;
import static org.mb4j.controller.form.FormField.createOptionalField;
import static org.mb4j.controller.form.FormField.createRequiredField;
import org.mb4j.servlet.sample.domain.Event;

@Singleton
public class EventEditForm {
  public static class Data extends FormData {
    final FormField id = createRequiredField();
    final FormField title = createRequiredField();
    final FormField summary = createOptionalField();
    final FormField imageUrl = createOptionalField();
  }

  public Data dataFrom(Event event) {
    Data data = new Data();
    data.id.value = Integer.toString(event.id);
    data.title.value = event.title;
    data.summary.value = event.summary;
    data.imageUrl.value = event.imageUrl;
    return data;
  }
}
