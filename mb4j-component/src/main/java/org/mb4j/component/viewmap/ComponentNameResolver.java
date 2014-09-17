package org.mb4j.component.viewmap;

import java.util.Map;
import org.mb4j.component.Component;

public class ComponentNameResolver {

    private final Map<Class<? extends Component>, String> class2name;
    private final Map<String, Component> name2component;

    public ComponentNameResolver(
            Map<Class<? extends Component>, String> class2name,
            Map<String, Component> name2component
    ) {
        this.class2name = class2name;
        this.name2component = name2component;
    }

    public String componentNameOf(Class<? extends Component> componentClass) {
        String name = class2name.get(componentClass);
        if (name == null) {
            throw new RuntimeException("No mapping found for Component class: " + componentClass);
        }
        return name;
    }

    public Component componentFor(String componentName) {
        Component component = name2component.get(componentName);
        if (component == null) {
            throw new RuntimeException("No mapping found for Component name: " + componentName);
        }
        return component;
    }

    @Override
    public String toString() {
        String result = "Components with resources: " + name2component.size();
        for (Map.Entry<String, Component> entry : name2component.entrySet()) {
            Component component = entry.getValue();
            result += "\n    " + entry.getKey() + " -> " + component.getClass().getName()
                    + " " + component.getResourceNames();
        }
        return result;
    }
}
