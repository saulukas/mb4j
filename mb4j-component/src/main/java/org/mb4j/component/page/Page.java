package org.mb4j.component.page;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.EmptyBrick;
import org.mb4j.component.Component;
import org.mb4j.component.View;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.ViewResponse;

public class Page extends Component implements View, BrickBaker {
  @Override
  public void handle(ViewRequest request, ViewResponse response) {
    response.render(bakeBrickFrom(request));
  }

  @Override
  public MustacheBrick bakeBrickFrom(ViewRequest request) {
    return EmptyBrick.emptyBrick();
  }
}
