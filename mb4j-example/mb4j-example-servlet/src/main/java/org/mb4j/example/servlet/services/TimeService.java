package org.mb4j.example.servlet.services;

import com.google.inject.Singleton;
import java.util.Date;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.ViewResponse;
import org.mb4j.component.service.Service;
import org.mb4j.component.url.ControllerUrl;

@Singleton
public class TimeService extends Service {
  public static ControllerUrl url() {
    return ControllerUrl.of(TimeService.class);
  }

  @Override
  protected void serve(ViewRequest request, ViewResponse response) throws Exception {
    response.setCharacterEncoding("utf-8");
    response.getWriter().write("" + new Date());
  }
}
