package org.mb4j.example.liferay.event.list;

import java.util.List;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.view.ViewUrl4Response;

public class EventListViewBrick extends MustacheBrick {
  List<DecoratedListItem> list;
  ViewUrl4Response reverseOrderUrl;

  static class DecoratedListItem {
    EventListItemPanelBrick item;

    DecoratedListItem(EventListItemPanelBrick item) {
      this.item = item;
    }
  }
}
