package org.mb4j.servlet.sample.domain;

import com.google.inject.Singleton;
import static org.mb4j.servlet.sample.domain.EventDatabase.EVENT_LIST;

@Singleton
public class EventQuery {
  public Event eventOrNullFor(int eventId) {
    synchronized (EVENT_LIST) {
      for (Event event : EVENT_LIST) {
        if (event.id == eventId) {
          return event;
        }
      }
      return null;
    }
  }
}
