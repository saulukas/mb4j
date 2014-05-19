package org.mb4j.sample.servlet.event.list;

import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.Request;
import org.mb4j.controller.page.Panel;
import org.mb4j.controller.url.AssetUrl4Response;
import org.mb4j.controller.url.ControllerUrl4Response;
import org.mb4j.sample.domain.data.Event;
import org.mb4j.sample.servlet.event.edit.EventEditPage;

@Singleton
public class EventListItemPanel extends Panel {
  public static class Brick extends MustacheBrick {
    Event event;
    AssetUrl4Response eventImageUrl;
    ControllerUrl4Response eventEditUrl;
  }

  public Brick bakeBrick(Request request, Event event) {
    Brick brick = new Brick();
    brick.event = event;
    brick.eventImageUrl = request.resolveUrl(event.imageUrl);
    brick.eventEditUrl = request.resolve(EventEditPage.url(event.id));
    return brick;
  }
}
