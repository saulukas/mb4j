package org.mb4j.servlet;

import org.mb4j.brick.Brick;
import org.mb4j.controller.form.FormData4ResponseResolver;
import org.mb4j.controller.mapping.MapFormClass2Name;

public class ServletFormData4ResponseResolver extends FormData4ResponseResolver {
  public ServletFormData4ResponseResolver(MapFormClass2Name formClass2name) {
    super(formClass2name);
  }

  @Override
  protected Brick createHeaderBrick(String formName) {
    return new ServletFormHeaderBrick(FORM_PARAM, formName);
  }
}
