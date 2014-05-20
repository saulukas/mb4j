package org.mb4j.component.service;

import org.mb4j.component.View;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.ViewResponse;

public abstract class Service implements View {
  @Override
  public void handle(ViewRequest request, ViewResponse response) {
    try {
      serve(request, response);
    } catch (Exception ex) {
      throw new RuntimeException("Failed to serve resource: " + ex, ex);
    }
  }

  protected abstract void serve(ViewRequest request, ViewResponse response) throws Exception;
}
