package org.mb4j.sample.liferay.time;

import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Date;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.Request;
import org.mb4j.controller.Response;
import org.mb4j.controller.resource.ResourceMethod;
import org.mb4j.liferay.PortletView;

@Singleton
public class TimeView extends PortletView {
  @Override
  public MustacheBrick bakeBrickFrom(Request request) {
    TimeViewBrick brick = new TimeViewBrick();
    brick.timeServiceUrl = request.resolve(TimeService.url());
    brick.resources = request.resolveResourcesOf(this);
    return brick;
  }

  @ResourceMethod
  public void time(Request request, Response response) throws IOException {
    response.setCharacterEncoding("utf-8");
    response.getWriter().write("" + new Date());
  }
}
