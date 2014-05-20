package org.mb4j.example.liferay.event.list;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.LinkedList;
import java.util.List;
import org.mb4j.component.ViewRequest;
import org.mb4j.component.url.ControllerUrl;
import org.mb4j.component.url.UrlParams;
import org.mb4j.component.url.UrlPathBuilder;
import org.mb4j.liferay.PortletView;
import org.mb4j.example.domain.data.Event;
import org.mb4j.example.domain.queries.EventListQuery;
import org.mb4j.example.liferay.event.list.EventListViewBrick.DecoratedListItem;

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

  @Override
  public EventListViewBrick bakeBrickFrom(ViewRequest request) {
    return bakeBrick(request, Params.from(request));
  }

  EventListViewBrick bakeBrick(ViewRequest request, Params params) {
    EventListViewBrick brick = new EventListViewBrick();
    brick.list = initDecoratedList(params, request);
    brick.reverseOrderUrl = request.resolve(initReverseOrderUrl(params, request));
    return brick;
  }

  private List<DecoratedListItem> initDecoratedList(Params params, ViewRequest request) {
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

  private ControllerUrl initReverseOrderUrl(Params params, ViewRequest request) {
    boolean newReverseOrder = !params.reverseOrder;
    return newReverseOrder
        ? request.url().withReplacedParam(Params.PARAM_REVERSE_ORDER, "")
        : request.url().withDeletedParam(Params.PARAM_REVERSE_ORDER);
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

    public static Params from(ViewRequest request) {
      return new Params(readMaxEventCount(request), readReverseOrderFlag(request));
    }

    public UrlParams toUrlParams() {
      UrlPathBuilder pathBuilder = UrlPathBuilder.urlPath();
      if (maxResultCount != SHOW_ALL) {
        pathBuilder = pathBuilder.with(String.valueOf(maxResultCount));
      }
      return UrlParams.of(pathBuilder.instance());
    }

    private static int readMaxEventCount(ViewRequest request) {
      int maxEventCount = SHOW_ALL;
      if (request.hasMorePathSegments()) {
        maxEventCount = Integer.parseInt(request.readPathSegment());
      }
      return maxEventCount;
    }

    private static boolean readReverseOrderFlag(ViewRequest request) {
      return request.url().params.named.valueOrNullOf(PARAM_REVERSE_ORDER) != null;
    }
  }
}
