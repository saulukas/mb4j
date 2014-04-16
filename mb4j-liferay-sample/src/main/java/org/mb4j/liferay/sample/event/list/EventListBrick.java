package org.mb4j.liferay.sample.event.list;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.LinkedList;
import java.util.List;
import org.mb4j.brick.Brick;
import org.mb4j.liferay.sample.domain.Event;
import org.mb4j.liferay.sample.domain.EventListQuery;
import org.mb4j.controller.ViewParams;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.baker.BakerView;
import org.mb4j.controller.baker.ViewBrickBaker;
import org.mb4j.controller.path.ViewPathBuilder;
import static org.mb4j.controller.path.ViewPathBuilder.viewPath;
import org.mb4j.controller.url.ViewUrl;

public class EventListBrick extends Brick {
    List<DecoratedListItem> list;
    String reverseOrderUrl;

    static class DecoratedListItem {
        EventListItemBrick item;

        DecoratedListItem(EventListItemBrick item) {
            this.item = item;
        }
    }

    @Singleton
    public static class Baker<P extends Baker.Params> implements ViewBrickBaker<P> {
        @Inject
        EventListQuery eventListQuery;
        @Inject
        EventListItemBrick.Baker itemBaker;

        @Override
        public EventListBrick bakeBrick(ViewRequest request, P params) {
            EventListBrick brick = new EventListBrick();
            brick.list = initDecoratedList(params, request);
            brick.reverseOrderUrl = request.stringOf(initReverseOrderUrl(params, request));
            return brick;
        }

        private List<DecoratedListItem> initDecoratedList(P params, ViewRequest request) {
            LinkedList<DecoratedListItem> list = new LinkedList<DecoratedListItem>();
            List<Event> events = eventListQuery.resultFor(params.maxResultCount);
            for (Event event : events) {
                EventListItemBrick.Baker.Params itemParams = new EventListItemBrick.Baker.Params(event);
                DecoratedListItem item = new DecoratedListItem(itemBaker.bakeBrick(request, itemParams));
                if (params.reverseOrder) {
                    list.addFirst(item);
                } else {
                    list.addLast(item);
                }
            }
            return list;
        }

        private ViewUrl initReverseOrderUrl(P params, ViewRequest request) {
            boolean newReverseOrder = !params.reverseOrder;
            return (newReverseOrder
                    ? request.url.withReplacedParam(Params.REVERSE_ORDER, "")
                    : request.url.withDeletedParam(Params.REVERSE_ORDER));
        }

        public static class Params {
            public static final int SHOW_ALL = -1;
            static final String REVERSE_ORDER = "reverseOrder";
            final int maxResultCount;
            final boolean reverseOrder;

            public Params(int maxResultCount, boolean reverseOrder) {
                this.maxResultCount = maxResultCount;
                this.reverseOrder = reverseOrder;
            }

            public static Params from(ViewRequest request) {
                return new Params(
                        readMaxEventCount(request),
                        readReverseOrderFlag(request));
            }

            public ViewParams toViewParams() {
                ViewPathBuilder pathBuilder = viewPath();
                if (maxResultCount != SHOW_ALL) {
                    pathBuilder = pathBuilder.with(String.valueOf(maxResultCount));
                }
                return ViewParams.of(pathBuilder.instance());
            }

            private static int readMaxEventCount(ViewRequest request) {
                int maxEventCount = SHOW_ALL;
                try {
                    if (request.pathParamsReader.hasMoreSegments()) {
                        maxEventCount = Integer.parseInt(request.pathParamsReader.readSegment());
                    }
                } catch (RuntimeException ex) {
                    // use initial value
                }
                return maxEventCount;
            }

            private static boolean readReverseOrderFlag(ViewRequest request) {
                return request.url.params.named.valueOf(REVERSE_ORDER) != null;
            }
        }
    }
}
