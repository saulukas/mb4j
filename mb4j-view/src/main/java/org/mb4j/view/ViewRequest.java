package org.mb4j.view;

import static org.mb4j.view.path.BufferedViewPathReader.bufferedReaderOf;
import org.mb4j.view.path.ViewPathReader;
import org.mb4j.view.url.StaticResourceUrlResolver;
import org.mb4j.view.url.ViewUrl;
import org.mb4j.view.url.ViewUrlStringResolver;
import org.mb4j.view.form.Form;

public class ViewRequest {
  public final ViewUrl url;
  public final ViewPathReader pathParamsReader;
  private final StaticResourceUrlResolver staticResourceUrlResolver;
  private final ViewUrlStringResolver viewUrlResolver;
  public final Form.NameResolver actionParamNameResolver;

  public ViewRequest(
      ViewUrl url,
      StaticResourceUrlResolver staticUrlResolver,
      ViewUrlStringResolver viewUrlResolver,
      Form.NameResolver actionParamNameResolver) {
    this.url = url;
    this.pathParamsReader = bufferedReaderOf(url.params.path);
    this.staticResourceUrlResolver = staticUrlResolver;
    this.viewUrlResolver = viewUrlResolver;
    this.actionParamNameResolver = actionParamNameResolver;
  }

  public String stringOf(ViewUrl url) {
    return viewUrlResolver.urlStringOf(url);
  }

  public String staticUrl(String urlFromHome) {
    return staticResourceUrlResolver.urlForStaticResource(urlFromHome);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "[" + pathParamsReader + "]";
  }
}
