package org.mb4j.example.domain.queries;

import com.google.common.base.Optional;
import com.google.inject.Singleton;
import static org.mb4j.example.domain.EventDatabase.EVENT_LIST;
import org.mb4j.example.domain.data.Event;

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
