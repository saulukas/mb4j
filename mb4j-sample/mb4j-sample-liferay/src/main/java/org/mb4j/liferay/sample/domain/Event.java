package org.mb4j.liferay.sample.domain;

public class Event {
    public final int id;
    public final String imageUrl;
    public final String title;
    public final String summary;

    public Event(int id, String imageUrl, String title, String summary) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.summary = summary;
    }
}
