package org.mb4j.servlet.sample.event.list;

import com.google.inject.Singleton;
import org.mb4j.Brick;
import org.mb4j.servlet.sample.domain.Event;
import org.mb4j.servlet.sample.event.edit.EventEditPage;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.baker.ViewBrickBaker;

public class EventListItemBrick extends Brick {
  Event event;
  String eventImageUrl;
  String eventEditUrl;

  @Singleton
  public static class Baker implements ViewBrickBaker<Baker.Params> {
    @Override
    public EventListItemBrick bakeBrick(ViewRequest request, Params params) {
      EventListItemBrick brick = new EventListItemBrick();
      brick.event = params.event;
      brick.eventImageUrl = request.staticUrl(params.event.imageUrl);
      brick.eventEditUrl = request.stringOf(EventEditPage.url(params.event.id));
      return brick;
    }

    public static class Params {
      final Event event;

      public Params(Event event) {
        this.event = event;
      }
    }
  }
}
