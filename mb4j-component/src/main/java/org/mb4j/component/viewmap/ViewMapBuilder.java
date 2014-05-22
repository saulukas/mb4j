package org.mb4j.component.viewmap;

import java.util.Collection;
import org.mb4j.component.url.BufferedUrlPathReader;
import org.mb4j.component.url.UrlPath;
import org.mb4j.component.url.UrlPathString;
import org.mb4j.component.view.View;

public class ViewMapBuilder {
  private static final UrlPath HOME_VIEW_PATH = UrlPath.empty();
  private static final UrlPath DEFAULT_HOME_VIEW_PATH = UrlPathString.urlPathOf("*");
  private final ViewMapNode root = ViewMapNode.createRoot();
  private final ViewMapClasses viewClasses = new ViewMapClasses();

  private ViewMapBuilder() {
  }

  public static ViewMapBuilder withHomeView(View homeView) {
    return new ViewMapBuilder().mount(HOME_VIEW_PATH, homeView);
  }

  public static ViewMapBuilder withDefaultHomeView(View homeView) {
    return new ViewMapBuilder().mount(DEFAULT_HOME_VIEW_PATH, homeView);
  }

  public ViewMapBuilder mount(UrlPath path, View view) {
    BufferedUrlPathReader pathReader = BufferedUrlPathReader.of(path);
    root.mount(pathReader, view);
    viewClasses.mount(pathReader.processedPath(), view.getClass());
    return this;
  }

  MapUrlPath2View urlPath2View() {
    return root;
  }

  MapViewClass2UrlPath viewClass2UrlPath() {
    return viewClasses;
  }

  void collectViews(Collection<View> result) {
    root.collectViews(result);
  }

  @Override
  public String toString() {
    return root.toString();
  }

  public String toString(String margin) {
    return root.toString(margin);
  }
}
