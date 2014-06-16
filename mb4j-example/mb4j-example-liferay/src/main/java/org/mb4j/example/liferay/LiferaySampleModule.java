package org.mb4j.example.liferay;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.brick.renderer.RendererUtils;
import org.mb4j.example.domain.EventDomainModule;
import org.mb4j.example.liferay.event.EventPortlet;
import org.mb4j.example.liferay.offer.PersonalOfferPortlet;
import org.mb4j.example.liferay.time.TimePortlet;
import org.mb4j.example.liferay.util.ModuleWithExplicitBindings;

public class LiferaySampleModule extends AbstractModule {
  public static Injector injector() {
    return Holder.injector;
  }

  private static class Holder { // Thread-safe Lazy initialization, Effective Java 2nd, Item 71.
    static final Injector injector = createInjector();
  }

  private static Injector createInjector() {
    return Guice.createInjector(new ModuleWithExplicitBindings(new LiferaySampleModule()));
  }

  @Override
  protected void configure() {
    install(new EventDomainModule());
    install(new EventPortlet.Module());
    install(new PersonalOfferPortlet.Module());
    install(new TimePortlet.Module());
    bind(BrickRenderer.class).toInstance(RendererUtils.renderer4Development());
  }
}
