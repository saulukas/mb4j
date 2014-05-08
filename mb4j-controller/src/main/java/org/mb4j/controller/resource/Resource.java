package org.mb4j.controller.resource;

import org.mb4j.controller.Controller;
import org.mb4j.controller.Request;

public interface Resource extends Controller {
  @Override
  ResourceResponse handle(Request request);
}
