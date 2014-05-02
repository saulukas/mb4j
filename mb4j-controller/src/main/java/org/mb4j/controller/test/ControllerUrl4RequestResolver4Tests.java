package org.mb4j.controller.test;

import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.ControllerUrl4RequestResolver;

public class ControllerUrl4RequestResolver4Tests implements ControllerUrl4RequestResolver {
  public static final ControllerUrl4RequestResolver4Tests INSTANCE = new ControllerUrl4RequestResolver4Tests();

  @Override
  public ControllerUrl4Request resolve(ControllerUrl url) {
    return new ControllerUrl4Request("../url4tests/../" + url);
  }
}
