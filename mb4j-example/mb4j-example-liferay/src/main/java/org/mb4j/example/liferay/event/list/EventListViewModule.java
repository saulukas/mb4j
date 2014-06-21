package org.mb4j.example.liferay.event.list;

import com.google.inject.AbstractModule;

public class EventListViewModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventListView.class);
    bind(EventListItemPanel.class);
  }
}
