package org.mb4j.sample.servlet.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.Request;
import org.mb4j.controller.form.FormData4Response;
import org.mb4j.controller.page.Panel;

@Singleton
public class EventEditPanel extends Panel {
  @Inject
  EventEditForm form;

  public static class View extends MustacheBrick {
    FormData4Response fd;
  }

  public View bakeBrick(Request request, int eventId) {
    View brick = new View();
    brick.fd = request.resolve(form.data(request, eventId));
    return brick;
  }
}
