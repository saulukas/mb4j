package org.mb4j.servlet;

import org.mb4j.component.view.View;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewResponse;

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
