package org.mb4j.component.viewmap;

import java.util.HashSet;
import java.util.Set;
import org.mb4j.component.Controller;
import org.mb4j.component.utils.SimpleClassName;

public class ViewMap {

    private final ViewMapBuilder builder;
    private final FormMappings formMappings;
    private final ResourceMappings resourceMappings;

    public ViewMap(ViewMapBuilder builder) {
        this.builder = builder;
        Set<Controller> views = new HashSet<>();
        builder.collectViews(views);
        this.formMappings = new FormMappings(views);
        this.resourceMappings = new ResourceMappings(views);
    }

    public MapUrlPath2View urlPath2View() {
        return builder.urlPath2View();
    }

    public MapViewClass2UrlPath viewClass2UrlPath() {
        return builder.viewClass2UrlPath();
    }

    public MapFormClass2Name formClass2Name() {
        return formMappings;
    }

    public MapFormName2Form formName2Form() {
        return formMappings;
    }

    public MapComponentClass2Name componentWithResourcesClass2Name() {
        return resourceMappings;
    }

    public MapComponentName2Component componentWithResourcesName2Component() {
        return resourceMappings;
    }

    @Override
    public String toString() {
        return SimpleClassName.of(getClass()) + ":"
                + "\n    " + builder.toString("    ")
                + (formMappings.isEmpty() ? "" : "\n" + formMappings)
                + (resourceMappings.isEmpty() ? "" : "\n" + resourceMappings);
    }
}
