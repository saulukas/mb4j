package org.mb4j.example.liferay.event.list;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.LinkedList;
import java.util.List;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.url.UrlParams;
import org.mb4j.component.url.UrlPathBuilder;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.component.view.ViewUrl4Response;
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

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(EventListView.class);
      bind(EventListItemPanel.class);
    }
  }

  public static ViewUrl url() {
    return url(Params.SHOW_ALL);
  }

  public static ViewUrl url(int maxEventCount) {
    return ViewUrl.of(EventListView.class, new Params(maxEventCount, false).toUrlParams());
  }

  static class Brick extends MustacheBrick {
    List<DecoratedListItem> list;
    ViewUrl4Response reverseOrderUrl;

    static class DecoratedListItem {
      EventListItemPanel.Brick item;

      DecoratedListItem(EventListItemPanel.Brick item) {
        this.item = item;
      }
    }
  }

  @Override
  public MustacheBrick bakeBrickFrom(ViewRequest request) {
    Params params = Params.from(request);
    Brick brick = new Brick();
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

  private ViewUrl initReverseOrderUrl(Params params, ViewRequest request) {
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
      return request.viewUrl().params.named.valueOrNullOf(PARAM_REVERSE_ORDER) != null;
    }
  }
}
