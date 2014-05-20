package org.mb4j.example.servlet;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import org.mb4j.component.sitemap.SiteMap;

public class ServletSampleContextListener extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    Injector injector = ServletSampleModule.injector();
    System.out.println(""
        + "\n"
        + "" + injector.getInstance(SiteMap.class).toString()
        + "\n");
    return injector;
  }

  @Override
  public void contextInitialized(ServletContextEvent contextEvent) {
    super.contextInitialized(contextEvent);
    ServletContext context = contextEvent.getServletContext();
    System.out.println("\n  contextPath=[" + context.getContextPath() + "]\n");
  }
}
