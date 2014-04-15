package org.mb4j.servlet.sample;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.mb4j.view.ViewMap;

public class ServletSampleContextListener extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    Injector injector = ServletSampleModule.createInjector();
    System.out.println(""
        + "\n"
        + "" + injector.getInstance(ViewMap.class)
        + "\n");
    return injector;
  }
}
