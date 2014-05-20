package org.mb4j.servlet.adapters;

import org.mb4j.brick.MustacheBrick;

public class ServletFormHeaderBrick extends MustacheBrick {
  final String formParamName;
  final String formParamValue;

  public ServletFormHeaderBrick(String formParamName, String formParamValue) {
    this.formParamName = formParamName;
    this.formParamValue = formParamValue;
  }
}
