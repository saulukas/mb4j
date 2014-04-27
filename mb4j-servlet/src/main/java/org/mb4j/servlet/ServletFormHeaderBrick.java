package org.mb4j.servlet;

import org.mb4j.brick.Brick;

public class ServletFormHeaderBrick extends Brick {
  public static final String FORM_MARKER_PARAM = "mb(f)";
  final String formParamName = FORM_MARKER_PARAM;
  final String formParamValue;

  public ServletFormHeaderBrick(String formParamValue) {
    this.formParamValue = formParamValue;
  }
}
