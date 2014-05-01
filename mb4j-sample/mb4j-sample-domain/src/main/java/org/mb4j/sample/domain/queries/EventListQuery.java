package org.mb4j.sample.domain.queries;

import org.mb4j.sample.domain.data.Event;
import com.google.inject.Singleton;
import java.util.List;
import static org.mb4j.sample.domain.EventDatabase.EVENT_LIST;

@Singleton
public class EventListQuery {
  public List<Event> resultFor(int maxResultCount) {
    synchronized (EVENT_LIST) {
      if (maxResultCount < 0 || maxResultCount > EVENT_LIST.size()) {
        maxResultCount = EVENT_LIST.size();
      }
      return EVENT_LIST.subList(0, maxResultCount);
    }
  }
}
