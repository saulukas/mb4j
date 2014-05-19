package org.mb4j.sample.servlet.master;

import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Date;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.Request;
import org.mb4j.controller.Response;
import org.mb4j.controller.page.Panel;
import org.mb4j.controller.resource.ResourceMethod;
import org.mb4j.controller.resource.Resources4Response;
import org.mb4j.controller.url.ControllerUrl4Response;
import org.mb4j.sample.servlet.services.TimeService;

@Singleton
public class FooterPanel extends Panel {
  public static final FooterPanel INSTANCE = new FooterPanel();

  private FooterPanel() {
  }

  public static class View extends MustacheBrick {
    ControllerUrl4Response timeServiceUrl;
    Resources4Response resources;
  }

  View bakeBrick(Request request) {
    View brick = new View();
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
