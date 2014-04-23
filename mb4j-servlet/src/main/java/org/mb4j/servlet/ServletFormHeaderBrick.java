package org.mb4j.servlet;

import org.mb4j.brick.Brick;

public class ServletFormHeaderBrick extends Brick {
  public static final ServletFormHeaderBrick INSTANCE = new ServletFormHeaderBrick();
  public static final String FORM_MARKER_PARAM = "mb(f)";
  final String formMarker = FORM_MARKER_PARAM;
}
