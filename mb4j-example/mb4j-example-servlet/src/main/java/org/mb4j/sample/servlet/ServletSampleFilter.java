package org.mb4j.sample.servlet;

import com.google.inject.Inject;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.controller.sitemap.SiteMap;
import org.mb4j.servlet.BrickServletFilter;

public class ServletSampleFilter extends BrickServletFilter {
  @Inject
  public ServletSampleFilter(BrickRenderer renderer, SiteMap siteMap) {
    super(renderer, siteMap);
  }
}
