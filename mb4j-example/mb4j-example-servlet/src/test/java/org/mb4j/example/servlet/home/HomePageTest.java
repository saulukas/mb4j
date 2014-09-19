package org.mb4j.example.servlet.home;

import org.junit.Test;
import static org.mb4j.example.servlet.ServletSampleTestApplication.inject;
import static org.mb4j.example.servlet.ServletSampleTestApplication.renderToString;

public class HomePageTest {

    private final HomePage page = inject(HomePage.class);

    @Test
    public void renders_successfully() {
        System.out.println(renderToString(page, HomePage.url()));
        System.out.println("\n\n" + page.componentTreeToString("    ") + "\n");
    }
}
