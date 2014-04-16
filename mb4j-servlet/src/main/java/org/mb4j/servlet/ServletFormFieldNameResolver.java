package org.mb4j.servlet;

import org.mb4j.controller.form.Form;

public class ServletFormFieldNameResolver implements Form.NameResolver {
  public static final ServletFormFieldNameResolver INSTANCE = new ServletFormFieldNameResolver();

  @Override
  public String resolvedName(String fieldName) {
    return fieldName;
  }
}
