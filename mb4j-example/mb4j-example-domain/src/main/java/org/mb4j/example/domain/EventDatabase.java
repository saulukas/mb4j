package org.mb4j.example.domain;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import org.mb4j.example.domain.data.Event;

public class EventDatabase {

    public final static List<Event> EVENT_LIST = new ArrayList<>(asList(
            new Event(1, "img/glyphicons_254_fishes.png", "Fishing", "Let's go fishing on saturday."),
            new Event(2, "img/glyphicons_314_table_tennis.png", "Ping pong", "Ping pong is on fridays."),
            new Event(3, "img/glyphicons_338_turtle.png", "Slow mondays", "Mondays are for slow motion."),
            new Event(4, "img/glyphicons_262_spade.png", "Healthy sundays", "Gardening with beer."),
            new Event(5, "img/glyphicons_326_piano.png", "Fly away", "Jamming with friends.")
    ));

}
