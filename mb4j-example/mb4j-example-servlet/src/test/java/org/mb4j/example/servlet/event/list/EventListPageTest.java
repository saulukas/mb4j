package org.mb4j.example.servlet.event.list;

import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import static org.mb4j.example.servlet.ServletSampleTestApplication.inject;
import static org.mb4j.example.servlet.ServletSampleTestApplication.requestFor;
import static org.mb4j.example.servlet.ServletSampleTestApplication.responseOn;

public class EventListPageTest {

    private final EventListPage page = inject(EventListPage.class);

    @Test
    public void renders_with_zero_events() {
        int eventCount = 0;
        Writer writer = new StringWriter();
        page.handle(requestFor(EventListPage.url(eventCount)), responseOn(writer));
        System.out.println(writer.toString());
    }

    @Test
    public void renders_with_one_event() {
        int eventCount = 1;
        Writer writer = new StringWriter();
        page.handle(requestFor(EventListPage.url(eventCount)), responseOn(writer));
        System.out.println(writer.toString());
    }

    @Test
    public void renders_with_all_events() {
        Writer writer = new StringWriter();
        page.handle(requestFor(EventListPage.url()), responseOn(writer));
        System.out.println(writer.toString());
        System.out.println("\n\n" + page.componentTreeToString("    ") + "\n");
    }
}
