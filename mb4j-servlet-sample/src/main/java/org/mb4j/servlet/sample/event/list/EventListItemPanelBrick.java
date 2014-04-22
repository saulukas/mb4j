package org.mb4j.servlet.sample.event.list;

import org.mb4j.brick.Brick;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.servlet.sample.domain.Event;

public class EventListItemPanelBrick extends Brick {
  Event event;
  String eventImageUrl;
  ControllerUrl4Request eventEditUrl;
}
