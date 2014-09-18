package org.mb4j.component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.mb4j.component.form.ActionResponse;
import org.mb4j.component.form.FormHandler;
import org.mb4j.component.url.NamedParams;

public interface Component {

    Map<String, Component> getNamedChildren();

    Set<String> getResourceNames();

    Set<String> getActionNames();

    void serveResource(String name, Request request, Response response);

    ActionResponse processAction(String name, Request request, NamedParams formInput);

    void addFormsRecursively(Collection<FormHandler> result);

}
