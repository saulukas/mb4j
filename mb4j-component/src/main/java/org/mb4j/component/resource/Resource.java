package org.mb4j.component.resource;

public class Resource {

    public final String name;
    public boolean enabled = true;
    public boolean visible = true;

    public Resource(String name) {
        this.name = name;
    }
}
