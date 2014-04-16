package org.mb4j.servlet.sample;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.brick.renderer.RendererUtils;
import org.mb4j.servlet.sample.domain.EventModule;
import org.mb4j.servlet.sample.event.edit.EventEditPageModule;
import org.mb4j.servlet.sample.event.list.EventListPageModule;
import org.mb4j.servlet.sample.home.HomePageModule;
import org.mb4j.servlet.sample.util.ModuleWithExplicitBindings;
import org.mb4j.controller.ViewMap;

public class ServletSampleModule extends AbstractModule {
    public static Injector createInjector() {
        return Guice.createInjector(new ModuleWithExplicitBindings(new ServletSampleModule()));
    }

    @Override
    protected void configure() {
        install(new EventModule());
        install(new SampleServletHttpModule());
        bindPages();
        bind(ViewMap.class).to(ServletSampleViewMap.class);
        bind(BrickRenderer.class).toInstance(RendererUtils.renderer4Development());
    }

    private void bindPages() {
        install(new HomePageModule());
        install(new EventListPageModule());
        install(new EventEditPageModule());
    }
}
