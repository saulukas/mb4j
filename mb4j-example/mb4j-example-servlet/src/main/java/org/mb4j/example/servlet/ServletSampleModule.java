package org.mb4j.example.servlet;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.brick.renderer.RendererUtils;
import org.mb4j.component.viewmap.SiteMap;
import org.mb4j.example.domain.EventDomainModule;
import org.mb4j.example.servlet.event.edit.EventEditPage;
import org.mb4j.example.servlet.event.list.EventListPage;
import org.mb4j.example.servlet.home.HomePage;
import org.mb4j.example.servlet.util.ModuleWithExplicitBindings;

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
    install(new EventDomainModule());
    install(new SampleServletHttpModule());
    bindPages();
    bind(SiteMap.class).to(ServletSampleSiteMap.class);
    bind(BrickRenderer.class).toInstance(RendererUtils.renderer4Development());
  }

  private void bindPages() {
    install(new HomePage.Module());
    install(new EventListPage.Module());
    install(new EventEditPage.Module());
  }
}
