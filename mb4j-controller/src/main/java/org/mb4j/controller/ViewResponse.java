package org.mb4j.controller;

import org.mb4j.brick.Brick;
import org.mb4j.controller.url.ViewUrl;

public class ViewResponse {
  public final Type type;
  public final Brick brick;
  public final ViewUrl viewUrl;

  public enum Type {
    BRICK,
    REDIRECT_TO_VIEW
  }

  ViewResponse(Type type, Brick brick, ViewUrl viewUrl) {
    this.type = type;
    this.brick = brick;
    this.viewUrl = viewUrl;
  }

  public static ViewResponse responseWith(Brick brick) {
    return new ViewResponse(Type.BRICK, brick, null);
  }

  public static ViewResponse redirectTo(ViewUrl viewUrl) {
    return new ViewResponse(Type.REDIRECT_TO_VIEW, null, viewUrl);
  }
}
