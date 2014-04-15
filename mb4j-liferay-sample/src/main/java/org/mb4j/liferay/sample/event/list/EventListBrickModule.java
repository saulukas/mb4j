package org.mb4j.liferay.sample.event.list;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

@Singleton
public class EventListBrickModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EventListBrick.Baker.class);
        bind(EventListBrickView.class);
        bind(EventListItemBrick.Baker.class);
    }
}
