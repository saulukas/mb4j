package org.mb4j.component;

import org.mb4j.component.Component;
import org.mb4j.component.viewmap.MapComponentClass2Name;

public abstract class ResourceUrlResolver {

    public static final String RESOURCE_PARAM_NAME = "mb(r)";
    private final MapComponentClass2Name componentWithResourcesClass2Name;

    public static class ParamValue {

        public static final String DELIMITER = "-";
        public final String componentName;
        public final String resourceName;

        public ParamValue(String componentName, String resourceName) {
            this.componentName = componentName;
            this.resourceName = resourceName;
        }

        public static ParamValue from(String string) {
            int index = string.indexOf(DELIMITER);
            if (index <= 0) {
                throw new RuntimeException("No delimiter '" + DELIMITER + "' found in [" + string + "].");
            }
            return new ParamValue(string.substring(0, index), string.substring(index + 1));
        }

        @Override
        public String toString() {
            return componentName + DELIMITER + resourceName;
        }
    }

    public ResourceUrlResolver(MapComponentClass2Name mapper) {
        this.componentWithResourcesClass2Name = mapper;
    }

    public String resolveResourceUrl(Component component, String resourceName) {
        String componentName = componentWithResourcesClass2Name.componentNameOf(component.getClass());
        ParamValue paramValue = new ParamValue(componentName, resourceName);
        return resolveResourceUrl(RESOURCE_PARAM_NAME, paramValue.toString());
    }

    protected abstract String resolveResourceUrl(String resourceParamName, String resourceParamValue);
}
