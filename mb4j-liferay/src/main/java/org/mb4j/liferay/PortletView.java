package org.mb4j.liferay;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.EmptyBrick;
import org.mb4j.component.Component;
import org.mb4j.component.View;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.ViewResponse;
import org.mb4j.component.page.BrickBaker;

public class PortletView extends Component implements View, BrickBaker {
  @Override
  public void handle(ViewRequest request, ViewResponse response) {
    response.render(bakeBrickFrom(request));
  }

  @Override
  public MustacheBrick bakeBrickFrom(ViewRequest request) {
    return EmptyBrick.emptyBrick();
  }
}
