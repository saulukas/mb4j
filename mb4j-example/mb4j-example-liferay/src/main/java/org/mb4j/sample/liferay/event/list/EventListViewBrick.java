package org.mb4j.sample.liferay.event.list;

import java.util.List;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.url.ControllerUrl4Response;

public class EventListViewBrick extends MustacheBrick {
  List<DecoratedListItem> list;
  ControllerUrl4Response reverseOrderUrl;

  static class DecoratedListItem {
    EventListItemPanelBrick item;

    DecoratedListItem(EventListItemPanelBrick item) {
      this.item = item;
    }
  }
}
