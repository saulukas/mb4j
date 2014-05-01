package org.mb4j.liferay.sample;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.brick.renderer.RendererUtils;
import org.mb4j.liferay.sample.domain.EventDomainModule;
import org.mb4j.liferay.sample.event.EventPortletModule;
import org.mb4j.liferay.sample.offer.PersonalOfferPortletModule;
import org.mb4j.liferay.sample.util.ModuleWithExplicitBindings;

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
    install(new EventPortletModule());
    install(new PersonalOfferPortletModule());
    bind(BrickRenderer.class).toInstance(RendererUtils.renderer4Development());
  }
}
