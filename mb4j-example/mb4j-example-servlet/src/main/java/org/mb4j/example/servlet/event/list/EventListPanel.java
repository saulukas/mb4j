package org.mb4j.example.servlet.event.list;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.LinkedList;
import java.util.List;
import org.mb4j.component.ComponentUsingReflection;
import org.mb4j.component.ControllerUrl;
import org.mb4j.component.Request;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.domain.queries.EventListQuery;
import org.mb4j.example.servlet.event.list.EventListPanelBrick.DecoratedListItem;

@Singleton
public class EventListPanel extends ComponentUsingReflection {

    @Inject
    EventListQuery eventListQuery;
    @Inject
    EventListItemPanel itemPanel;

    public EventListPanelBrick bakeBrick(Request request) {
        EventListPanelParams params = EventListPanelParams.from(request);
        EventListPanelBrick brick = new EventListPanelBrick();
        brick.list = initDecoratedList(params, request);
        brick.reverseOrderUrl = request.resolve(initReverseOrderUrl(params, request));
        return brick;
    }

    private List<DecoratedListItem> initDecoratedList(EventListPanelParams params, Request request) {
        LinkedList<DecoratedListItem> list = new LinkedList<>();
        List<Event> events = eventListQuery.resultFor(params.maxResultCount);
        for (Event event : events) {
            DecoratedListItem item = new DecoratedListItem(itemPanel.bakeBrick(request, event));
            if (params.reverseOrder) {
                list.addFirst(item);
            } else {
                list.addLast(item);
            }
        }
        return list;
    }

    private ControllerUrl initReverseOrderUrl(EventListPanelParams params, Request request) {
        boolean newReverseOrder = !params.reverseOrder;
        return newReverseOrder
                ? request.viewUrl().withReplacedParam(EventListPanelParams.PARAM_REVERSE_ORDER, "")
                : request.viewUrl().withDeletedParam(EventListPanelParams.PARAM_REVERSE_ORDER);
    }

}
