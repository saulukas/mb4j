package org.mb4j.example.servlet.event.edit;

import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import static org.mb4j.example.servlet.ServletSampleTestApplication.inject;
import static org.mb4j.example.servlet.ServletSampleTestApplication.requestFor;
import static org.mb4j.example.servlet.ServletSampleTestApplication.responseOn;
import static org.mb4j.example.servlet.event.TypicalEvents.fishingEventId;

public class EventEditPageTest {

    private final EventEditPage page = inject(EventEditPage.class);

    @Test
    public void renders_fishing_event() {
        Writer writer = new StringWriter();
        page.handle(requestFor(EventEditPage.url(fishingEventId())), responseOn(writer));
        System.out.println(writer.toString());
        System.out.println("\n\n" + page.componentTreeToString("    ") + "\n");
    }
}
