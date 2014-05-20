package org.mb4j.example.liferay.time;

import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Date;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.ViewResponse;
import org.mb4j.component.resource.ResourceMethod;
import org.mb4j.liferay.PortletView;

@Singleton
public class TimeView extends PortletView {
  @Override
  public MustacheBrick bakeBrickFrom(ViewRequest request) {
    TimeViewBrick brick = new TimeViewBrick();
    brick.timeServiceUrl = request.resolve(TimeService.url());
    brick.resources = request.resolveResourcesOf(this);
    return brick;
  }

  @ResourceMethod
  public void time(ViewRequest request, ViewResponse response) throws IOException {
    response.setCharacterEncoding("utf-8");
    response.getWriter().write("" + new Date());
  }
}
