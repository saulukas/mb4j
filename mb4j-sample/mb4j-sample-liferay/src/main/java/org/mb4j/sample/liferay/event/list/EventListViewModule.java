package org.mb4j.sample.liferay.event.list;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

@Singleton
public class EventListViewModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventListView.class);
    bind(EventListItemPanel.class);
  }
}
