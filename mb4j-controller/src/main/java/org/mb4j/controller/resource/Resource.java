package org.mb4j.controller.resource;

import org.mb4j.controller.Controller;
import org.mb4j.controller.ControllerRequest;

public abstract class Resource extends Controller {
  @Override
  public abstract ResourceResponse handle(ControllerRequest request);
}
