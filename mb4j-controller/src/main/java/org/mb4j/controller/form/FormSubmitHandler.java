package org.mb4j.controller.form;

import com.google.common.base.Optional;
import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.form.field.FormFieldRecord;
import static org.mb4j.controller.form.field.FormFieldValueTree.fieldValueTreeOf;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.controller.url.NamedParams;

public class FormSubmitHandler {
  public static Optional<FormResponse> formResponseFor(
      ControllerRequest request,
      NamedParams postParams,
      ControllerMappings mappings) {
    String formName = postParams.valueOrNullOf(FormData4ResponseResolver.FORM_PARAM);
    if (formName == null) {
      return Optional.absent();
    }
    Form form = mappings.formName2FormResolver().resolveFormName(formName);
    String actionName = getActionNameFrom(postParams, form);
    FormFieldRecord fields = form.createEmptyFields();
    fields.setValuesFrom(fieldValueTreeOf(postParams.asMap()));
    return Optional.of(form.handle(request, actionName, fields));
  }

  private static String getActionNameFrom(NamedParams postParams, Form form) {
    for (String paramName : postParams.names()) {
      if (paramName.startsWith(FormData4ResponseResolver.ACTION_PARAM_PREFIX)) {
        return paramName.substring(FormData4ResponseResolver.ACTION_PARAM_PREFIX.length());
      }
    }
    throw new RuntimeException("No action name found for form " + form + " in postParams: " + postParams);
  }
}
