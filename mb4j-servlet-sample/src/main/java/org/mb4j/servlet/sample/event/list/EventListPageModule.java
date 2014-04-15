package org.mb4j.servlet.sample.event.list;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

@Singleton
public class EventListPageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventListBrick.Baker.class);
    bind(EventListItemBrick.Baker.class);
    bind(EventListPage.Baker.class);
    bind(EventListPage.View.class);
  }
}
