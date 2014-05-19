package org.mb4j.servlet;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.form.FormData4ResponseResolver;
import org.mb4j.controller.sitemap.MapFormClass2Name;

public class ServletFormData4ResponseResolver extends FormData4ResponseResolver {
  public ServletFormData4ResponseResolver(MapFormClass2Name formClass2name) {
    super(formClass2name);
  }

  @Override
  protected MustacheBrick createHeaderBrick(String formName) {
    return new ServletFormHeaderBrick(FORM_PARAM, formName);
  }
}
