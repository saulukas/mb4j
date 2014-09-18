package org.mb4j.example.servlet.event.list;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.LinkedList;
import java.util.List;
import org.mb4j.brick.BrickList;
import org.mb4j.component.ReflectiveComponent;
import org.mb4j.component.Request;
import org.mb4j.component.ViewLocator;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.domain.queries.EventListQuery;

@Singleton
public class EventListPanel extends ReflectiveComponent {

    @Inject
    EventListQuery eventListQuery;
    @Inject
    EventListItemPanel itemPanel;

    public EventListPanelBrick bakeBrick(Request request) {
        EventListPanelParams params = EventListPanelParams.from(request);
        EventListPanelBrick brick = new EventListPanelBrick();
        brick.list = initList(params, request);
        brick.reverseOrderUrl = request.resolve(initReverseOrderUrl(params, request));
        return brick;
    }

    private BrickList<EventListItemBrick> initList(EventListPanelParams params, Request request) {
        LinkedList<EventListItemBrick> list = new LinkedList<>();
        List<Event> events = eventListQuery.resultFor(params.maxResultCount);
        for (Event event : events) {
            EventListItemBrick item = itemPanel.bakeBrick(request, event);
            if (params.reverseOrder) {
                list.addFirst(item);
            } else {
                list.addLast(item);
            }
        }
        return new BrickList(list);
    }

    private ViewLocator initReverseOrderUrl(EventListPanelParams params, Request request) {
        boolean newReverseOrder = !params.reverseOrder;
        return newReverseOrder
                ? request.viewLocator().withReplacedParam(EventListPanelParams.PARAM_REVERSE_ORDER, "")
                : request.viewLocator().withDeletedParam(EventListPanelParams.PARAM_REVERSE_ORDER);
    }

}
