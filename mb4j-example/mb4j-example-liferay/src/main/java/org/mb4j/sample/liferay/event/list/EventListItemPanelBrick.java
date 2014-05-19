package org.mb4j.sample.liferay.event.list;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.controller.url.ControllerUrl4Response;
import org.mb4j.controller.url.AssetUrl4Response;
import org.mb4j.sample.domain.data.Event;

public class EventListItemPanelBrick extends MustacheBrick {
  Event event;
  AssetUrl4Response eventImageUrl;
  ControllerUrl4Response eventEditUrl;
}
