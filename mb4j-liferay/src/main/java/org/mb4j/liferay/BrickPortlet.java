package org.mb4j.liferay;

import java.io.IOException;
import java.net.URI;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.controller.NamedParams;
import org.mb4j.controller.ViewMap;
import org.mb4j.controller.ViewParams;
import org.mb4j.controller.ViewRequest;
import org.mb4j.controller.ViewResponse;
import org.mb4j.controller.mount.ViewFromPathResolver;
import org.mb4j.controller.path.ViewPath;
import static org.mb4j.controller.path.ViewPathString.pathStringOf;
import org.mb4j.controller.url.ViewUrl;
import static org.mb4j.controller.http.HttpNamedParams.namedParametersFromRawQueryString;
import static org.mb4j.liferay.PortletPathToHome.pathStringToHomeFrom;
import static org.mb4j.liferay.PortletViewPathUtils.currentURI;
import static org.mb4j.liferay.PortletViewPathUtils.viewPathFrom;

public class BrickPortlet extends GenericPortlet {
  private final BrickRenderer renderer;
  private final ViewMap views;

  public BrickPortlet(BrickRenderer renderer, ViewMap views) {
    this.renderer = renderer;
    this.views = views;
  }

  @Override
  protected void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
    URI currentURI = currentURI(request);
    ViewPath path = viewPathFrom(request);
    String path2home = pathStringToHomeFrom(request, currentURI.getRawPath());
    System.out.println("viewPath {" + pathStringOf(path) + "}");
    System.out.println("currentURI {" + currentURI + "}{" + path2home + "}");
    ViewFromPathResolver.Result resolverResult = views.viewFromPathResolver().resolve(path);
    if (!resolverResult.hasView()) {
      throw new PortletException("No view found for path [" + pathStringOf(path) + "]");
    }
    NamedParams namedParams = namedParametersFromRawQueryString(currentURI.getRawQuery());
    ViewUrl url = ViewUrl.of(
        resolverResult.view.getClass(),
        ViewParams.of(resolverResult.paramsPath, namedParams));
    ViewRequest viewReq = createViewRequest(url, path2home, response);
    ViewResponse viewResp = resolverResult.view.handle(viewReq);
    handle(viewReq, viewResp, response);
  }

  private ViewRequest createViewRequest(ViewUrl url, String path2home, MimeResponse response) {
    return new ViewRequest(
        url,
        new PortletStaticResourceUrlResolver(path2home),
        new PortletViewUrlStringResolver(response, views.pathFromViewClassResolver()),
        new PortletFormFieldNameResolver());
  }

  private void handle(ViewRequest viewReq, ViewResponse viewResp, RenderResponse response)
      throws IOException {
    switch (viewResp.type) {
    case BRICK:
      renderer.render(viewResp.brick, response.getWriter());
      break;
    case REDIRECT_TO_VIEW:
    default:
      throw new RuntimeException("Unsupported " + ViewResponse.class.getSimpleName()
          + " type: " + viewResp.type);
    }
  }

  @Override
  public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
    super.processAction(request, response);
  }
}
