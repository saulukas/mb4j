package org.mb4j.example.servlet;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.brick.renderer.RendererUtils;
import org.mb4j.component.viewmap.ViewMap;
import org.mb4j.example.domain.EventDomainModule;
import org.mb4j.example.servlet.event.edit.EventEditPageModule;
import org.mb4j.example.servlet.event.list.EventListPageModule;
import org.mb4j.example.servlet.home.HomePageModule;
import static org.mb4j.example.servlet.util.ModuleWithExplicitBindings.injectorWithExplicitBindings;

public class ServletSampleModule extends AbstractModule {

    public static final Injector injector = injectorWithExplicitBindings(new ServletSampleModule());

    @Override
    protected void configure() {
        install(new EventDomainModule());
        install(new SampleServletHttpModule());
        bindPages();
        bind(ViewMap.class).to(ServletSampleSiteMap.class);
        bind(BrickRenderer.class).toInstance(RendererUtils.renderer4Development());
    }

    private void bindPages() {
        install(new HomePageModule());
        install(new EventListPageModule());
        install(new EventEditPageModule());
    }
}
