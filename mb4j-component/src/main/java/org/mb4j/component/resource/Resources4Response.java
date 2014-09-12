package org.mb4j.component.resource;

import java.util.Collection;
import java.util.HashMap;

public class Resources4Response extends HashMap<String, Resource4Response> {

    private static final long serialVersionUID = 1L;

    public Resources4Response(Collection<Resource4Response> resources) {
        for (Resource4Response resource : resources) {
            put(resource.name(), resource);
        }
    }
}
