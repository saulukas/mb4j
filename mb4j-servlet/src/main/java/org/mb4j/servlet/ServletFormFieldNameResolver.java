package org.mb4j.servlet;

import org.mb4j.controller.form1.Form1;

public class ServletFormFieldNameResolver implements Form1.NameResolver {
  public static final ServletFormFieldNameResolver INSTANCE = new ServletFormFieldNameResolver();

  @Override
  public String resolvedName(String fieldName) {
    return fieldName;
  }
}
