package org.mb4j.servlet.sample.event.list;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

@Singleton
public class EventListPageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventListPanel.class);
    bind(EventListItemPanel.class);
    bind(EventListPage.class);
  }
}
