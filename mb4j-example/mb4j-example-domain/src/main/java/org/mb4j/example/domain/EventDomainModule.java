package org.mb4j.example.domain;

import org.mb4j.example.domain.commands.EventSaveCommand;
import org.mb4j.example.domain.queries.EventListQuery;
import org.mb4j.example.domain.queries.EventByIdQuery;
import com.google.inject.AbstractModule;

public class EventDomainModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventListQuery.class);
    bind(EventByIdQuery.class);
    bind(EventSaveCommand.class);
  }
}
