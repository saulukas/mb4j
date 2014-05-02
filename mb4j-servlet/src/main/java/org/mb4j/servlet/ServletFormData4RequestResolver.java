package org.mb4j.servlet;

import org.mb4j.brick.Brick;
import org.mb4j.controller.form.FormData;
import org.mb4j.controller.form.FormData4RequestResolver;
import org.mb4j.controller.mapping.FormClass2NameResolver;

public class ServletFormData4RequestResolver extends FormData4RequestResolver {
  public static final ServletFormData4RequestResolver INSTANCE = new ServletFormData4RequestResolver();

  @Override
  protected Brick createHeaderBrick(FormClass2NameResolver formResolver, FormData<?> formData) {
    return new ServletFormHeaderBrick(FORM_PARAM, formResolver.formNameOf(formData.formClass));
  }
}
