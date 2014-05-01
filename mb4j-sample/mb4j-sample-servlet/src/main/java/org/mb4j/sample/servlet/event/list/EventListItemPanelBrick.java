package org.mb4j.sample.servlet.event.list;

import org.mb4j.brick.Brick;
import org.mb4j.controller.url.ControllerUrl4Request;
import org.mb4j.controller.url.Url4Request;
import org.mb4j.sample.servlet.domain.Event;

public class EventListItemPanelBrick extends Brick {
  Event event;
  Url4Request eventImageUrl;
  ControllerUrl4Request eventEditUrl;
}
