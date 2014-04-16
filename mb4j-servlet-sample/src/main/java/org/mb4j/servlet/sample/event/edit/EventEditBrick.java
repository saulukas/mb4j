package org.mb4j.servlet.sample.event.edit;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.brick.Brick;
import org.mb4j.servlet.sample.domain.Event;
import org.mb4j.servlet.sample.domain.EventQuery;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.baker.ViewBrickBaker;
import org.mb4j.controller.url.ViewUrl;

public class EventEditBrick extends Brick {
  String actionSaveUrl;
  EventEditForm form;

  @Singleton
  public static class Baker<P extends Baker.Params> implements ViewBrickBaker<P> {
    @Inject
    EventQuery eventQuery;
    @Inject
    EventEditForm.Filler formFiller;

    public static class Params {
      public final int eventId;

      public Params(int eventId) {
        this.eventId = eventId;
      }
    }

    @Override
    public EventEditBrick bakeBrick(ViewRequest request, P params) {
      Event event = eventQuery.eventOrNullFor(params.eventId);
      EventEditBrick brick = new EventEditBrick();
      brick.actionSaveUrl = request.stringOf(ViewUrl.of(EventEditForm.SaveAction.class));
      brick.form = formFiller.filledForm(request, new EventEditForm.Filler.Params(event));
      return brick;
    }
  }
}
