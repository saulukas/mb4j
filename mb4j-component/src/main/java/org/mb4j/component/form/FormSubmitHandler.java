package org.mb4j.component.form;

import org.mb4j.component.form.response.FormResponseRedirectToView;
import org.mb4j.component.form.response.FormResponseRedirectToUrlString;
import org.mb4j.component.form.response.FormResponse;
import com.google.common.base.Optional;
import org.mb4j.component.form.data.FormData;
import org.mb4j.component.url.NamedParams;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.component.viewmap.MapFormName2Form;

public class FormSubmitHandler {
  public static Optional<FormResponse> formResponseFor(
      ViewRequest request,
      NamedParams postParams,
      MapFormName2Form formName2Form) {
    String formName = postParams.valueOrNullOf(Form4ResponseResolver.FORM_PARAM);
    if (formName == null) {
      return Optional.absent();
    }
    FormHandler handler = formName2Form.formFor(formName);
    String actionName = getActionNameFrom(postParams, handler);
    FormData data = handler.createEmptyFields();
    data.setValuesFrom(postParams.asMap());
    FormResponse formResponse = handler.handle(actionName, request, data);
    if (formResponse instanceof FormResponseRedirectToView) {
      ViewUrl viewUrl = ((FormResponseRedirectToView) formResponse).viewUrl;
      formResponse = FormResponseRedirectToUrlString.redirectTo(request.resolve(viewUrl));
    }
    return Optional.of(formResponse);
  }

  private static String getActionNameFrom(NamedParams postParams, FormHandler form) {
    for (String paramName : postParams.names()) {
      if (paramName.startsWith(Form4ResponseResolver.ACTION_PARAM_PREFIX)) {
        return paramName.substring(Form4ResponseResolver.ACTION_PARAM_PREFIX.length());
      }
    }
    throw new RuntimeException("No action name found for form " + form + " in postParams: " + postParams);
  }
}
