package org.mb4j.liferay.sample.event.list;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

@Singleton
public class EventListPageModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventListPage.class);
    bind(EventListItemPanel.class);
  }
}
