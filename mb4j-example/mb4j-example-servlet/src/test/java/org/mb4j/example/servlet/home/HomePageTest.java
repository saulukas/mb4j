package org.mb4j.example.servlet.home;

import org.mb4j.example.servlet.home.HomePage;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.Test;
import static org.mb4j.example.servlet.ServletSampleTestApplication.inject;
import static org.mb4j.example.servlet.ServletSampleTestApplication.requestFor;
import static org.mb4j.example.servlet.ServletSampleTestApplication.responseOn;

public class HomePageTest {
  private final HomePage page = inject(HomePage.class);

  @Test
  public void renders_successfully() {
    Writer writer = new StringWriter();
    page.handle(requestFor(HomePage.url()), responseOn(writer));
    System.out.println(writer.toString());
    System.out.println("\n\n" + page.componentTreeToString("    ") + "\n");
  }
}
