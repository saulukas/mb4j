package org.mb4j.example.servlet.event.list;

import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.Component;
import org.mb4j.component.asset.AssetUrl4Response;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl4Response;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.servlet.event.edit.EventEditPage;

@Singleton
public class EventListItemPanel extends Component {
  public static class Brick extends MustacheBrick {
    Event event;
    AssetUrl4Response eventImageUrl;
    ViewUrl4Response eventEditUrl;
  }

  public Brick bakeBrick(ViewRequest request, Event event) {
    Brick brick = new Brick();
    brick.event = event;
    brick.eventImageUrl = request.resolveUrl(event.imageUrl);
    brick.eventEditUrl = request.resolve(EventEditPage.url(event.id));
    return brick;
  }
}
