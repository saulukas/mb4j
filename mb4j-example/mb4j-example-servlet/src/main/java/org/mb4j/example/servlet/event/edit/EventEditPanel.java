package org.mb4j.example.servlet.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.Component;
import org.mb4j.component.form.FormData4Response;
import org.mb4j.component.view.ViewRequest;

@Singleton
public class EventEditPanel extends Component {
  @Inject
  EventEditForm form;

  public static class Brick extends MustacheBrick {
    FormData4Response fd;
  }

  public Brick bakeBrick(ViewRequest request, int eventId) {
    Brick brick = new Brick();
    brick.fd = request.resolve(form.data(request, eventId));
    return brick;
  }
}
