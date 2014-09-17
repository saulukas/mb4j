package org.mb4j.example.servlet.event.list;

import java.util.List;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewUrl;

public class EventListPanelBrick extends MustacheBrick {

    List<DecoratedListItem> list;
    ViewUrl reverseOrderUrl;

    static class DecoratedListItem {

        EventListItemPanelBrick item;

        DecoratedListItem(EventListItemPanelBrick item) {
            this.item = item;
        }
    }

}
