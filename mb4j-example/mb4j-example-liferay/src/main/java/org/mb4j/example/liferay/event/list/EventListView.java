package org.mb4j.example.liferay.event.list;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.LinkedList;
import java.util.List;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.ControllerUrl;
import org.mb4j.component.ControllerUrl4Response;
import org.mb4j.component.Request;
import org.mb4j.component.url.UrlParams;
import org.mb4j.component.url.UrlPathBuilder;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.domain.queries.EventListQuery;
import org.mb4j.example.liferay.event.list.EventListView.Brick.DecoratedListItem;
import org.mb4j.liferay.PortletView;

@Singleton
public class EventListView extends PortletView {

    @Inject
    EventListQuery eventListQuery;
    @Inject
    EventListItemPanel itemPanel;

    public static ControllerUrl url() {
        return url(Params.SHOW_ALL);
    }

    public static ControllerUrl url(int maxEventCount) {
        return ControllerUrl.of(EventListView.class, new Params(maxEventCount, false).toUrlParams());
    }

    static class Brick extends MustacheBrick {

        List<DecoratedListItem> list;
        ControllerUrl4Response reverseOrderUrl;

        static class DecoratedListItem {

            EventListItemPanel.Brick item;

            DecoratedListItem(EventListItemPanel.Brick item) {
                this.item = item;
            }
        }
    }

    @Override
    public MustacheBrick bakeBrick(Request request) {
        Params params = Params.from(request);
        Brick brick = new Brick();
        brick.list = initDecoratedList(params, request);
        brick.reverseOrderUrl = request.resolve(initReverseOrderUrl(params, request));
        return brick;
    }

    private List<DecoratedListItem> initDecoratedList(Params params, Request request) {
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

    private ControllerUrl initReverseOrderUrl(Params params, Request request) {
        boolean newReverseOrder = !params.reverseOrder;
        return newReverseOrder
                ? request.viewUrl().withReplacedParam(Params.PARAM_REVERSE_ORDER, "")
                : request.viewUrl().withDeletedParam(Params.PARAM_REVERSE_ORDER);
    }

    public static class Params {

        public static final int SHOW_ALL = -1;
        static final String PARAM_REVERSE_ORDER = "reverseOrder";
        final int maxResultCount;
        final boolean reverseOrder;

        public Params(int maxResultCount, boolean reverseOrder) {
            this.maxResultCount = maxResultCount;
            this.reverseOrder = reverseOrder;
        }

        public static Params from(Request request) {
            return new Params(readMaxEventCount(request), readReverseOrderFlag(request));
        }

        public UrlParams toUrlParams() {
            UrlPathBuilder pathBuilder = UrlPathBuilder.urlPath();
            if (maxResultCount != SHOW_ALL) {
                pathBuilder = pathBuilder.with(String.valueOf(maxResultCount));
            }
            return UrlParams.of(pathBuilder.instance());
        }

        private static int readMaxEventCount(Request request) {
            int maxEventCount = SHOW_ALL;
            if (request.hasMorePathSegments()) {
                maxEventCount = Integer.parseInt(request.readPathSegment());
            }
            return maxEventCount;
        }

        private static boolean readReverseOrderFlag(Request request) {
            return request.viewUrl().params.named.valueOrNullOf(PARAM_REVERSE_ORDER) != null;
        }
    }
}
