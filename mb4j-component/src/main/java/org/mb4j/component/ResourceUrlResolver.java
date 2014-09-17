package org.mb4j.component;

import org.mb4j.component.viewmap.ComponentNameResolver;

public abstract class ResourceUrlResolver {

    public static final String RESOURCE_PARAM_NAME = "mb4r";
    private final ComponentNameResolver componentNames;

    public ResourceUrlResolver(ComponentNameResolver componentNames) {
        this.componentNames = componentNames;
    }

    public ResourceUrl resourceUrl(Component component, String resourceName) {
        String componentName = componentNames.componentNameOf(component.getClass());
        if (!component.getResourceNames().contains(resourceName)) {
            throw new RuntimeException("Component " + component + " does not have resource"
                    + " with name " + resourceName + "."
                    + " Available resources: " + component.getResourceNames());
        }
        ParamValue paramValue = new ParamValue(componentName, resourceName);
        return new ResourceUrl(resolveResourceUrl(RESOURCE_PARAM_NAME, paramValue.toString()));
    }

    protected abstract String resolveResourceUrl(String resourceParamName, String resourceParamValue);

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

}
