package org.mb4j.servlet.sample.domain;

import com.google.inject.AbstractModule;

public class EventModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventListQuery.class);
    bind(EventQuery.class);
    bind(EventSaveCommand.class);
  }
}