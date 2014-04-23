package org.mb4j.controller.page;

import org.mb4j.controller.Controller;
import org.mb4j.controller.ControllerRequest;

public interface Page extends Controller {
  @Override
  public PageResponse handle(ControllerRequest request);
}
