package org.mb4j.example.servlet.event.list;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ControllerUrl4Response;
import org.mb4j.component.asset.AssetUrl4Response;
import org.mb4j.example.domain.data.Event;

public class EventListItemPanelBrick extends MustacheBrick {

    Event event;
    AssetUrl4Response eventImageUrl;
    ControllerUrl4Response eventEditUrl;

}
