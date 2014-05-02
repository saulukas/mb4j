package org.mb4j.servlet;

import org.mb4j.brick.Brick;

public class ServletFormHeaderBrick extends Brick {
  final String formParamName;
  final String formParamValue;

  public ServletFormHeaderBrick(String formParamName, String formParamValue) {
    this.formParamName = formParamName;
    this.formParamValue = formParamValue;
  }
}
