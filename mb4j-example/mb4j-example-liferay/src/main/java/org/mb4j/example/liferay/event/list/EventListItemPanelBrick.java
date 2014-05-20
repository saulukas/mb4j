package org.mb4j.example.liferay.event.list;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.url.ControllerUrl4Response;
import org.mb4j.component.url.AssetUrl4Response;
import org.mb4j.example.domain.data.Event;

public class EventListItemPanelBrick extends MustacheBrick {
  Event event;
  AssetUrl4Response eventImageUrl;
  ControllerUrl4Response eventEditUrl;
}
