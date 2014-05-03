package org.mb4j.controller.test;

import org.mb4j.controller.ControllerRequest;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.Url4RequestResolver;
import org.mb4j.controller.utils.AttributesMap;

public class ControllerTesting {
  public static ControllerRequest request4Tests(ControllerUrl url) {
    return new ControllerRequest(
        url,
        new AttributesMap(),
        new Url4RequestResolver("../path2home/../"),
        ControllerUrl4RequestResolver4Tests.INSTANCE,
        FormData4RequestResolver4Tests.INSTANCE
    );
  }
}
