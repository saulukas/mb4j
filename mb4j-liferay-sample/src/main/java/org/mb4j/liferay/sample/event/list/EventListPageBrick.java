package org.mb4j.liferay.sample.event.list;

import java.util.List;
import org.mb4j.brick.Brick;

public class EventListPageBrick extends Brick {
  List<DecoratedListItem> list;
  String reverseOrderUrl;

  static class DecoratedListItem {
    EventListItemPanelBrick item;

    DecoratedListItem(EventListItemPanelBrick item) {
      this.item = item;
    }
  }
}
