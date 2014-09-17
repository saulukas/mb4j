package org.mb4j.component;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.mb4j.component.form.FormHandler;

public interface Component {

    Map<String, Component> getChildren();

    void addFormsRecursively(Collection<FormHandler> result);

    void addSubtree(Collection<Component> result);

    Set<String> getResourceNames();

    void serveResource(String name, Request request, Response response) throws IOException;
}
