package org.mb4j.example.servlet.event.list;

import com.google.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import org.mb4j.brick.BrickList;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.Request;
import org.mb4j.component.ViewLocator;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.domain.queries.EventListQuery;
import org.mb4j.example.servlet.master.MasterLayoutPage;

public class EventListPage extends MasterLayoutPage {

    @Inject
    EventListQuery eventListQuery;
    @Inject
    EventListItemPanel itemPanel;

    public static ViewLocator locatorShowAll() {
        return locator(EventListParams.SHOW_ALL);
    }

    public static ViewLocator locator(int maxEventCount) {
        return ViewLocator.of(EventListPage.class,
                new EventListParams(maxEventCount, false).toUrlParams());
    }

    @Override
    protected MustacheBrick bakeContentBrick(Request request) {
        EventListParams params = EventListParams.from(request);
        EventListBrick brick = new EventListBrick();
        brick.list = initList(params, request);
        brick.reverseOrderUrl = request.resolve(initReverseOrderUrl(params, request));
        return brick;
    }

    private BrickList<EventListItemBrick> initList(EventListParams params, Request request) {
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

    private ViewLocator initReverseOrderUrl(EventListParams params, Request request) {
        boolean newReverseOrder = !params.reverseOrder;
        return newReverseOrder
                ? request.viewLocator().withReplacedParam(EventListParams.PARAM_REVERSE_ORDER, "")
                : request.viewLocator().withDeletedParam(EventListParams.PARAM_REVERSE_ORDER);
    }
}
