package org.mb4j.liferay;

import org.mb4j.brick.Brick;

public class PortletFormHeaderBrick extends Brick {
  final boolean pauthParamEnabled;
  final String pauthParamValue;
  final String formParamName;
  final String formParamValue;

  public PortletFormHeaderBrick(String pauthParamOrNull, String formParamName, String formParamValue) {
    this.pauthParamEnabled = (pauthParamOrNull != null);
    this.pauthParamValue = pauthParamOrNull;
    this.formParamName = formParamName;
    this.formParamValue = formParamValue;
  }
}
