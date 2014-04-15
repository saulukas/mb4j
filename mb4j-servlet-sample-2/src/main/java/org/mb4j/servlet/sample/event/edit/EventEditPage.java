package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import org.mb4j.Brick;
import static org.mb4j.view.path.ViewPathBuilder.viewPath;
import org.mb4j.servlet.sample.master.MasterLayoutPage;
import org.mb4j.view.url.ViewUrl;
import org.mb4j.view.ViewRequest;

public class EventEditPage extends MasterLayoutPage {
  @Inject
  EventEditPanel contentPanel;

  public static ViewUrl url(int eventId) {
    return ViewUrl.of(EventEditPage.class, viewPath().with(String.valueOf(eventId)).instance());
  }

  @Override
  protected Brick bakeContentBrick(ViewRequest request) {
    return contentPanel.bakeBrick(request, readEventIdFrom(request));
  }

  private int readEventIdFrom(ViewRequest request) {
    return Integer.parseInt(request.pathParamsReader.readSegment());
  }
}
