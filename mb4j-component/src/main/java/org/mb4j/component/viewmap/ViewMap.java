package org.mb4j.component.viewmap;

import java.util.HashSet;
import java.util.Set;
import org.mb4j.component.Component;
import org.mb4j.component.View;
import org.mb4j.component.utils.SimpleClassName;

public class ViewMap {

    private final ViewMapBuilder builder;
    public final ComponentNameResolver componentNameResolver;
    private final FormMappings formMappings;

    public ViewMap(ViewMapBuilder builder) {
        this.builder = builder;
        Set<View> views = new HashSet<>();
        builder.collectViews(views);
        this.componentNameResolver = ComponentNameResolverBuilder.resolverFor(views);
        this.formMappings = new FormMappings(views);
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

    public Component componentByName(String name) {
        return componentNameResolver.componentByName(name);
    }

    @Override
    public String toString() {
        return SimpleClassName.of(getClass()) + ":"
                + "\n    " + builder.toString("    ")
                + (formMappings.isEmpty() ? "" : "\n" + formMappings);
    }
}
