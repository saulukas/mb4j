package org.mb4j.example.servlet.event.list;

import org.junit.Test;
import static org.mb4j.example.servlet.ServletSampleTestApplication.inject;
import static org.mb4j.example.servlet.ServletSampleTestApplication.renderToString;

public class EventListPageTest {

    private final EventListPage page = inject(EventListPage.class);

    @Test
    public void renders_with_zero_events() {
        int eventCount = 0;
        System.out.println(renderToString(page, EventListPage.locator(eventCount)));
    }

    @Test
    public void renders_with_one_event() {
        int eventCount = 1;
        System.out.println(renderToString(page, EventListPage.locator(eventCount)));
    }

    @Test
    public void renders_with_all_events() {
        System.out.println(renderToString(page, EventListPage.locatorShowAll()));
        System.out.println("\n\n" + page.componentTreeToString("    ") + "\n");
    }
}
