package org.mb4j.sample.servlet.domain;

import com.google.common.base.Optional;
import com.google.inject.Singleton;
import static org.mb4j.sample.servlet.domain.EventDatabase.EVENT_LIST;

@Singleton
public class EventByIdQuery {
  public Optional<Event> result(int eventId) {
    synchronized (EVENT_LIST) {
      for (Event event : EVENT_LIST) {
        if (event.id == eventId) {
          return Optional.of(event);
        }
      }
      return Optional.absent();
    }
  }
}
