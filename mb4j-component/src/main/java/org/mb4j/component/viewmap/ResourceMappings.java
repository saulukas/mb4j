package org.mb4j.component.viewmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.mb4j.component.Component;
import org.mb4j.component.View;
import org.mb4j.component.utils.SimpleClassName;

public class ResourceMappings implements MapComponentClass2Name, MapComponentName2Component {

    private final Map<Class<? extends Component>, String> class2name = new HashMap<>();
    private final Map<String, Component> name2component = new TreeMap<>();

    public ResourceMappings(Set<View> views) {
        Set<Component> components = new HashSet<>();
        for (View view : views) {
            if (view instanceof Component) {
                Component component = (Component) view;
                component.addSubtree(components);
            }
        }
        Set<Component> processedComponents = new HashSet<>();
        for (Component component : components) {
            if (processedComponents.contains(component) || !hasResources(component)) {
                continue;
            }
            processedComponents.add(component);
            String name = SimpleClassName.of(component.getClass());
            String postfix = "";
            int counter = 0;
            while (name2component.containsKey(name + postfix)) {
                counter += 1;
                postfix = "" + counter;
            }
            name += postfix;
            name2component.put(name, component);
            class2name.put(component.getClass(), name);
        }
    }

    public boolean isEmpty() {
        return class2name.isEmpty();
    }

    @Override
    public String componentNameOf(Class<? extends Component> componentClass) {
        String name = class2name.get(componentClass);
        if (name == null) {
            throw new RuntimeException("No mapping found for Component class: " + componentClass);
        }
        return name;
    }

    @Override
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

    private boolean hasResources(Component component) {
        return !component.getResourceNames().isEmpty();
    }
}
