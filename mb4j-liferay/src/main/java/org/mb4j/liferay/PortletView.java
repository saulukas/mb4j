package org.mb4j.liferay;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.EmptyBrick;
import org.mb4j.component.Component;
import org.mb4j.component.ComponentUsingReflection;
import org.mb4j.component.view.View;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewResponse;
import org.mb4j.component.view.BrickBaker;

public class PortletView extends ComponentUsingReflection implements View, BrickBaker {
  @Override
  public void handle(ViewRequest request, ViewResponse response) {
    response.render(bakeBrick(request));
  }

  @Override
  public MustacheBrick bakeBrick(ViewRequest request) {
    return EmptyBrick.emptyBrick();
  }
}
