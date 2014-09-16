package org.mb4j.component;

import com.google.common.base.Objects;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.mb4j.component.form.FormHandler;
import org.mb4j.component.utils.ReflectionUtils;
import org.mb4j.component.utils.SimpleClassName;

public class ReflectiveComponent implements Component {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ResourceMethod {
    }

    private final Map<String, Method> resourceMethodMap = new HashMap<>();

    public ReflectiveComponent() {
        initResourceMehodMap();
    }

    private void initResourceMehodMap() {
        List<Method> resourceMethods = ReflectionUtils.getAnnotatedMethodsOf(
                getClass(), ReflectiveComponent.class, ResourceMethod.class);
        for (Method method : resourceMethods) {
            if (!Objects.equal(method.getReturnType(), void.class)) {
                throw new NonVoidResourceMethodException(method);
            }
            resourceMethodMap.put(method.getName(), method);
        }
    }

    @Override
    public void addFormsRecursively(Collection<FormHandler> result) {
        ReflectionUtils.collectRecursivelyNonStaticFieldsOf(this, ReflectiveComponent.class, FormHandler.class, result);
    }

    @Override
    public void addSubtree(Collection<Component> result) {
        result.add(this);
        ReflectionUtils.collectRecursivelyNonStaticFieldsOf(this, Component.class, Component.class, result);
    }

    @Override
    public Set<String> getResourceNames() {
        return resourceMethodMap.keySet();
    }

    @Override
    public void serveResource(String name, Request request, Response response) throws IOException {
        Method method = resourceMethodMap.get(name);
        try {
            method.setAccessible(true);
            method.invoke(this, request, response);
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException ex) {
            throw new RuntimeException("Failed to invoke resource method " + method + ": " + ex, ex);
        }
    }

    public String componentTreeToString(String margin) {
        String result = SimpleClassName.of(getClass());
        Map<String, ReflectiveComponent> children = getChildren();
        for (FormHandler form : getForms().values()) {
            String formMargin = margin + (children.isEmpty() ? "    " : "|   ");
            result += "\n" + formMargin + "form: " + SimpleClassName.of(form.getClass())
                    + " " + form.getActionNames();
        }
        Set<String> resourceNames = getResourceNames();
        if (!resourceNames.isEmpty()) {
            String resourceMargin = margin + (children.isEmpty() ? "    " : "|   ");
            result += "\n" + resourceMargin + "resources: " + resourceNames;
        }
        if (children.isEmpty()) {
            return result;
        }
        Iterator<String> namesIterator = children.keySet().iterator();
        while (namesIterator.hasNext()) {
            String childName = namesIterator.next();
            ReflectiveComponent child = children.get(childName);
            result += "\n" + margin + "|";
            result += "\n" + margin + "+-- " + childName + " = "
                    + child.componentTreeToString(margin + (namesIterator.hasNext() ? "|   " : "    "));
        }
        return result;
    }

    private Map<String, FormHandler> getForms() {
        return ReflectionUtils.getNonStaticFieldsOf(this, ReflectiveComponent.class, FormHandler.class);
    }

    private Map<String, ReflectiveComponent> getChildren() {
        return ReflectionUtils.getNonStaticFieldsOf(this, ReflectiveComponent.class, ReflectiveComponent.class);
    }
}
