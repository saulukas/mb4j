package org.mb4j.example.domain;

import com.google.inject.AbstractModule;
import org.mb4j.example.domain.commands.EventSaveCommand;
import org.mb4j.example.domain.queries.EventByIdQuery;
import org.mb4j.example.domain.queries.EventListQuery;

public class EventDomainModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EventListQuery.class);
        bind(EventByIdQuery.class);
        bind(EventSaveCommand.class);
    }
}
