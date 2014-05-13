package org.mb4j.sample.servlet.master;

import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Date;
import org.mb4j.brick.Brick;
import org.mb4j.controller.Request;
import org.mb4j.controller.Response;
import org.mb4j.controller.page.Panel;
import org.mb4j.controller.resource.ResourceMethod;
import org.mb4j.sample.servlet.services.TimeService;

@Singleton
public class FooterPanel extends Panel {
  public static final FooterPanel INSTANCE = new FooterPanel();

  private FooterPanel() {
  }

  Brick bakeBrickFrom(Request request) {
    FooterPanelBrick brick = new FooterPanelBrick();
    brick.timeServiceUrl = request.resolve(TimeService.url());
//    brick.resources = request.resolveResourcesOf(this);
    return brick;
  }

  @ResourceMethod
  public void time(Request request, Response response) throws IOException {
    response.setCharacterEncoding("utf-8");
    response.getWriter().write("" + new Date());
  }
}
