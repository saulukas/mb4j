package org.mb4j.sample.servlet.event.list;

import java.util.List;
import org.mb4j.brick.Brick;
import org.mb4j.controller.url.ControllerUrl4Request;

public class EventListPanelBrick extends Brick {
  List<DecoratedListItem> list;
  ControllerUrl4Request reverseOrderUrl;

  static class DecoratedListItem {
    EventListItemPanelBrick item;

    DecoratedListItem(EventListItemPanelBrick item) {
      this.item = item;
    }
  }
}
