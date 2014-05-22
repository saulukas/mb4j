package org.mb4j.example.liferay.event.edit;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.form.FormData4Response;
import static org.mb4j.component.url.UrlPathBuilder.urlPath;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewUrl;
import org.mb4j.liferay.PortletView;

@Singleton
public class EventEditView extends PortletView {
  @Inject
  EventEditForm form;

  public static class Module extends AbstractModule {
    @Override
    protected void configure() {
      bind(EventEditView.class);
      bind(EventEditForm.class);
    }
  }

  public static ViewUrl url(int eventId) {
    return ViewUrl.of(EventEditView.class, urlPath().with(String.valueOf(eventId)));
  }

  static class Brick extends MustacheBrick {
    FormData4Response form;
  }

  @Override
  public MustacheBrick bakeBrickFrom(ViewRequest request) {
    int eventId = Integer.parseInt(request.readPathSegment());
    Brick brick = new Brick();
    brick.form = request.resolve(form.dataFor(request, eventId));
    return brick;
  }
}
