package org.mb4j.example.servlet.event.edit;

import org.junit.Test;
import static org.mb4j.example.servlet.ServletSampleTestApplication.inject;
import static org.mb4j.example.servlet.ServletSampleTestApplication.renderToString;
import static org.mb4j.example.servlet.event.TypicalEvents.fishingEventId;

public class EventEditPageTest {

    private final EventEditPage page = inject(EventEditPage.class);

    @Test
    public void renders_fishing_event() {
        System.out.println(renderToString(page, EventEditPage.url(fishingEventId())));
        System.out.println("\n\n" + page.componentTreeToString("    ") + "\n");
    }
}
