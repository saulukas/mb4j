package org.mb4j.liferay;

import org.mb4j.brick.Brick;
import org.mb4j.brick.EmptyBrick;
import org.mb4j.controller.Component;
import org.mb4j.controller.Controller;
import org.mb4j.controller.Request;
import org.mb4j.controller.Response;
import org.mb4j.controller.page.BrickBaker;

public class PortletView extends Component implements Controller, BrickBaker {
  @Override
  public void handle(Request request, Response response) {
    response.render(bakeBrickFrom(request));
  }

  @Override
  public Brick bakeBrickFrom(Request request) {
    return EmptyBrick.emptyBrick();
  }
}
