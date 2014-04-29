package org.mb4j.servlet.sample;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.brick.renderer.RendererUtils;
import org.mb4j.controller.mapping.ControllerMappings;
import org.mb4j.servlet.sample.domain.EventModule;
import org.mb4j.servlet.sample.event.edit.EventEditPageModule;
import org.mb4j.servlet.sample.event.list.EventListPageModule;
import org.mb4j.servlet.sample.home.HomePageModule;
import org.mb4j.servlet.sample.util.ModuleWithExplicitBindings;

public class ServletSampleModule extends AbstractModule {
  public static Injector injector() {
    return Holder.injector;
  }

  private static class Holder { // Thread-safe Lazy initialization, Effective Java 2nd, Item 71.
    static final Injector injector = createInjector();
  }

  private static Injector createInjector() {
    return Guice.createInjector(new ModuleWithExplicitBindings(new ServletSampleModule()));
  }

  @Override
  protected void configure() {
    install(new EventModule());
    install(new SampleServletHttpModule());
    bindPages();
    bind(ControllerMappings.class).to(ServletSampleViewMap.class);
    bind(BrickRenderer.class).toInstance(RendererUtils.renderer4Development());
  }

  private void bindPages() {
    install(new HomePageModule());
    install(new EventListPageModule());
    install(new EventEditPageModule());
  }
}
