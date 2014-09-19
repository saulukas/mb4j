package org.mb4j.example.servlet.event.list;

import org.mb4j.component.Request;
import org.mb4j.component.url.UrlParams;
import org.mb4j.component.url.UrlPathBuilder;

public class EventListParams {

    public static final int SHOW_ALL = -1;
    static final String PARAM_REVERSE_ORDER = "reverseOrder";
    final int maxResultCount;
    final boolean reverseOrder;

    public EventListParams(int maxResultCount, boolean reverseOrder) {
        this.maxResultCount = maxResultCount;
        this.reverseOrder = reverseOrder;
    }

    public static EventListParams from(Request request) {
        return new EventListParams(readMaxEventCount(request), readReverseOrderFlag(request));
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
        return request.viewLocator().params.named.valueOrNullOf(PARAM_REVERSE_ORDER) != null;
    }

}
