package org.mb4j.example.servlet.master;

import com.google.inject.Singleton;
import java.io.IOException;
import java.util.Date;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewResponse;
import org.mb4j.component.page.Panel;
import org.mb4j.component.resource.ResourceMethod;
import org.mb4j.component.resource.Resources4Response;
import org.mb4j.component.view.ViewUrl4Response;
import org.mb4j.example.servlet.services.TimeService;

@Singleton
public class FooterPanel extends Panel {
  public static final FooterPanel INSTANCE = new FooterPanel();

  private FooterPanel() {
  }

  public static class Brick extends MustacheBrick {
    ViewUrl4Response timeServiceUrl;
    Resources4Response resources;
  }

  Brick bakeBrick(ViewRequest request) {
    Brick brick = new Brick();
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
