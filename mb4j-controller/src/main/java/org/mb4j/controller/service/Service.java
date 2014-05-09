package org.mb4j.controller.service;

import org.mb4j.controller.Controller;
import org.mb4j.controller.Request;
import org.mb4j.controller.Response;

public abstract class Service implements Controller {
  @Override
  public void handle(Request request, Response response) {
    try {
      serve(request, response);
    } catch (Exception ex) {
      throw new RuntimeException("Failed to serve resource: " + ex, ex);
    }
  }

  protected abstract void serve(Request request, Response response) throws Exception;
}
