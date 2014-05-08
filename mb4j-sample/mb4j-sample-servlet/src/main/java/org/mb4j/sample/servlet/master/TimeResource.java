package org.mb4j.sample.servlet.master;

import com.google.inject.Singleton;
import java.util.Date;
import org.mb4j.controller.Request;
import org.mb4j.controller.Response;
import org.mb4j.controller.resource.Resource;
import org.mb4j.controller.url.ControllerUrl;

@Singleton
public class TimeResource extends Resource {
  public static ControllerUrl url() {
    return ControllerUrl.of(TimeResource.class);
  }

  @Override
  protected void serve(Request request, Response response) throws Exception {
    response.getWriter().write("" + new Date());
  }
}
