package org.mb4j.sample.servlet.services;

import com.google.inject.Singleton;
import java.util.Date;
import org.mb4j.controller.Request;
import org.mb4j.controller.Response;
import org.mb4j.controller.service.Service;
import org.mb4j.controller.url.ControllerUrl;

@Singleton
public class TimeService extends Service {
  public static ControllerUrl url() {
    return ControllerUrl.of(TimeService.class);
  }

  @Override
  protected void serve(Request request, Response response) throws Exception {
    response.setCharacterEncoding("utf-8");
    response.getWriter().write("" + new Date());
  }
}
