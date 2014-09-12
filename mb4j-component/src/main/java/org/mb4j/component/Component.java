package org.mb4j.component;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import org.mb4j.component.form.FormHandler;
import org.mb4j.component.resource.Resource;
import org.mb4j.component.view.ViewRequest;
import org.mb4j.component.view.ViewResponse;

public interface Component {

    void addFormsRecursively(Collection<FormHandler> result);

    void addSubtree(Collection<Component> result);

    boolean hasResources();

    Set<String> getResourceNames();

    Collection<Resource> getResources();

    void serveResource(String resourceName, ViewRequest request, ViewResponse response) throws IOException;
}
