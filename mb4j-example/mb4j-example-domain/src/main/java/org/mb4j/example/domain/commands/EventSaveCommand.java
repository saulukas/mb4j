package org.mb4j.example.domain.commands;

import org.mb4j.example.domain.data.Event;
import com.google.inject.Singleton;
import static org.mb4j.example.domain.EventDatabase.EVENT_LIST;

@Singleton
public class EventSaveCommand {
  public void execute(Event event) {
    synchronized (EVENT_LIST) {
      for (int i = 0; i < EVENT_LIST.size(); i++) {
        if (EVENT_LIST.get(i).id == event.id) {
          EVENT_LIST.set(i, event);
          return;
        }
      }
    }
  }
}
