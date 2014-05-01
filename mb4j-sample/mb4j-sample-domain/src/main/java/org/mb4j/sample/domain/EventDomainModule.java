package org.mb4j.sample.domain;

import org.mb4j.sample.domain.commands.EventSaveCommand;
import org.mb4j.sample.domain.queries.EventListQuery;
import org.mb4j.sample.domain.queries.EventByIdQuery;
import com.google.inject.AbstractModule;

public class EventDomainModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(EventListQuery.class);
    bind(EventByIdQuery.class);
    bind(EventSaveCommand.class);
  }
}
