package org.mb4j.example.liferay.time;

import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Date;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.resource.ResourceMethod;
import org.mb4j.component.resource.Resources4Response;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewResponse;
import org.mb4j.liferay.PortletView;

@Singleton
public class TimeView extends PortletView {
  static class Brick extends MustacheBrick {
    Resources4Response resources;
  }

  @Override
  public MustacheBrick bakeBrick(ViewRequest request) {
    Brick brick = new Brick();
    brick.resources = request.resolveResourcesOf(this);
    return brick;
  }

  @ResourceMethod
  public void time(ViewRequest request, ViewResponse response) throws IOException {
    response.setCharacterEncoding("utf-8");
    response.getWriter().write("" + new Date());
  }
}
