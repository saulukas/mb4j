package org.mb4j.component.viewmap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.mb4j.component.Component;
import org.mb4j.component.View;
import org.mb4j.component.utils.SimpleClassName;

public class ComponentNameResolverBuilder {

    private final Collection<View> views;
    private final Map<Class<? extends Component>, String> class2name = new HashMap<>();
    private final Map<String, Component> name2component = new HashMap<>();

    private ComponentNameResolverBuilder(Collection<View> views) {
        this.views = views;
    }

    public static ComponentNameResolver resolverFor(Collection<View> views) {
        return new ComponentNameResolverBuilder(views).build();
    }

    private ComponentNameResolver build() {
        for (View view : views) {
            if (view instanceof Component) {
                process((Component) view);
            }
        }
        return new ComponentNameResolver(class2name, name2component);
    }

    private void process(Component component) {
        if (class2name.containsKey(component.getClass())) {
            String processedName = class2name.get(component.getClass());
            Component processedComponent = name2component.get(processedName);
            if (component != processedComponent) {
                throw new RuntimeException("Components must be singletons."
                        + " Encountered component of class " + component.getClass() + " twice:"
                        + "\n    " + component
                        + "\n    " + processedComponent);
            }
            return;
        }
        String name = selectNameFor(component);
        name2component.put(name, component);
        class2name.put(component.getClass(), name);
        for (Component child : component.getNamedChildren().values()) {
            process(child);
        }
    }

    private String selectNameFor(Component component) {
        String name = SimpleClassName.of(component.getClass());
        String postfix = "";
        int counter = 0;
        while (name2component.containsKey(name + postfix)) {
            counter += 1;
            postfix = "" + counter;
        }
        name += postfix;
        return name;
    }
}
