package org.mb4j.controller.page;

import org.mb4j.controller.Request;
import org.mb4j.controller.Response;

public abstract class BrickBakerPage extends Page implements BrickBaker {
  @Override
  public void handle(Request request, Response response) {
    response.render(bakeBrickFrom(request));
  }
}
