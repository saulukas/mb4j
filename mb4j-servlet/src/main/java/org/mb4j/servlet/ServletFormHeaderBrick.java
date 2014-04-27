package org.mb4j.servlet;

import org.mb4j.brick.Brick;

public class ServletFormHeaderBrick extends Brick {
  public static final String FORM_NAME_PARAM = "mb(f)";
  final String formParamName = FORM_NAME_PARAM;
  final String formParamValue;

  public ServletFormHeaderBrick(String formParamValue) {
    this.formParamValue = formParamValue;
  }
}
