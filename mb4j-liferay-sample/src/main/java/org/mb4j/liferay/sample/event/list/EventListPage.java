package org.mb4j.liferay.sample.event.list;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.LinkedList;
import java.util.List;
import org.mb4j.controller.BrickBakerPage;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.url.ControllerUrl;
import org.mb4j.controller.url.UrlParams;
import org.mb4j.controller.url.UrlPathBuilder;
import org.mb4j.liferay.sample.domain.Event;
import org.mb4j.liferay.sample.domain.EventListQuery;
import org.mb4j.liferay.sample.event.list.EventListPageBrick.DecoratedListItem;

@Singleton
public class EventListPage extends BrickBakerPage {
  @Inject
  EventListQuery eventListQuery;
  @Inject
  EventListItemPanel itemPanel;

  public static ControllerUrl url() {
    return url(Params.SHOW_ALL);
  }

  public static ControllerUrl url(int maxEventCount) {
    return ControllerUrl.of(EventListPage.class, new Params(maxEventCount, false).toViewParams());
  }

  @Override
  public EventListPageBrick bakeBrickFrom(ViewRequest request) {
    return bakeBrick(request, Params.from(request));
  }

  EventListPageBrick bakeBrick(ViewRequest request, Params params) {
    EventListPageBrick brick = new EventListPageBrick();
    brick.list = initDecoratedList(params, request);
    brick.reverseOrderUrl = request.stringOf(initReverseOrderUrl(params, request));
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
        ? request.url().withReplacedParam(Params.REVERSE_ORDER, "")
        : request.url().withDeletedParam(Params.REVERSE_ORDER);
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
      return new Params(readMaxEventCount(request), readReverseOrderFlag(request));
    }

    public UrlParams toViewParams() {
      UrlPathBuilder pathBuilder = UrlPathBuilder.urlPath();
      if (maxResultCount != SHOW_ALL) {
        pathBuilder = pathBuilder.with(String.valueOf(maxResultCount));
      }
      return UrlParams.of(pathBuilder.instance());
    }

    private static int readMaxEventCount(ViewRequest request) {
      int maxEventCount = SHOW_ALL;
      if (request.pathParamsReader.hasMoreSegments()) {
        maxEventCount = Integer.parseInt(request.pathParamsReader.readSegment());
      }
      return maxEventCount;
    }

    private static boolean readReverseOrderFlag(ViewRequest request) {
      return request.url().params.named.valueOf(REVERSE_ORDER) != null;
    }
  }
}
