package org.mb4j.example.servlet.event.list;

import org.mb4j.brick.BrickList;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ViewUrl;

public class EventListPanelBrick extends MustacheBrick {

    BrickList<EventListItemBrick> list;
    ViewUrl reverseOrderUrl;

}
