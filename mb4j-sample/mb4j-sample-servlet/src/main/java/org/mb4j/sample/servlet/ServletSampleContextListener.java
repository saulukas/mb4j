package org.mb4j.sample.servlet;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.mb4j.controller.sitemap.SiteMap;

public class ServletSampleContextListener extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    Injector injector = ServletSampleModule.injector();
    System.out.println(""
        + "\n"
        + "" + injector.getInstance(SiteMap.class)
        + "\n");
    return injector;
  }
}
