package org.mb4j.servlet.adapters;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.form.FormData4ResponseResolver;
import org.mb4j.component.viewmap.MapFormClass2Name;

public class ServletFormData4ResponseResolver extends FormData4ResponseResolver {
  public ServletFormData4ResponseResolver(MapFormClass2Name formClass2name) {
    super(formClass2name);
  }

  @Override
  protected MustacheBrick createHeaderBrick(String formName) {
    return new ServletFormHeaderBrick(FORM_PARAM, formName);
  }
}
