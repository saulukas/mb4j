package org.mb4j.component.form;

import com.google.common.base.Optional;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.form.field.FormFieldRecord;
import static org.mb4j.component.form.field.FormFieldValueTree.fieldValueTreeOf;
import org.mb4j.component.sitemap.MapFormName2Form;
import org.mb4j.component.url.ControllerUrl;
import org.mb4j.component.url.NamedParams;

public class FormSubmitHandler {
  public static Optional<FormResponse> formResponseFor(
      ViewRequest request,
      NamedParams postParams,
      MapFormName2Form formName2Form) {
    String formName = postParams.valueOrNullOf(FormData4ResponseResolver.FORM_PARAM);
    if (formName == null) {
      return Optional.absent();
    }
    Form form = formName2Form.formFor(formName);
    String actionName = getActionNameFrom(postParams, form);
    FormFieldRecord fields = form.createEmptyFields();
    fields.setValuesFrom(fieldValueTreeOf(postParams.asMap()));
    FormResponse formResponse = form.handle(actionName, request, fields);
    if (formResponse instanceof FormResponseRedirectToController) {
      ControllerUrl controllerUrl = ((FormResponseRedirectToController) formResponse).controllerUrl;
      formResponse = FormResponseRedirectToUrlString.redirectTo(request.resolve(controllerUrl));
    }
    return Optional.of(formResponse);
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
