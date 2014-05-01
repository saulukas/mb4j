package org.mb4j.sample.domain;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import org.mb4j.sample.domain.data.Event;

public class EventDatabase {
  public final static List<Event> EVENT_LIST = new ArrayList<>(asList(
      new Event(1, "img/glyphicons_254_fishes.png", "Fishing", "Let's go fishing on saturday."),
      new Event(2, "img/glyphicons_314_table_tennis.png", "Ping pong", "Ping pong is on fridays."),
      new Event(3, "img/glyphicons_338_turtle.png", "Slow mondays", "Mondays are fow slow motion.")));
}
