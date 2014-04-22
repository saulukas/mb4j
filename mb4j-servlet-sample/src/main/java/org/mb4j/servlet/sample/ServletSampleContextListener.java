package org.mb4j.servlet.sample;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.mb4j.controller.mapping.ControllerMappings;

public class ServletSampleContextListener extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    Injector injector = ServletSampleModule.createInjector();
    System.out.println(""
        + "\n"
        + "" + injector.getInstance(ControllerMappings.class)
        + "\n");
    return injector;
  }
}
