package org.mb4j.example.servlet.services;

import com.google.inject.Singleton;
import java.util.Date;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewResponse;
import org.mb4j.servlet.Service;
import org.mb4j.component.view.ViewUrl;

@Singleton
public class TimeService extends Service {
  public static ViewUrl url() {
    return ViewUrl.of(TimeService.class);
  }

  @Override
  protected void serve(ViewRequest request, ViewResponse response) throws Exception {
    response.setCharacterEncoding("utf-8");
    response.getWriter().write("" + new Date());
  }
}
