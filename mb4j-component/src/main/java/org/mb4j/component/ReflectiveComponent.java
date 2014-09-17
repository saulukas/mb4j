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
            if (!isValid(method)) {
                throw new ResourceMethodSignatureException(method);
            }
            resourceMethodMap.put(method.getName(), method);
        }
    }

    static boolean isValid(Method method) {
        return Objects.equal(method.getReturnType(), void.class)
                && method.getParameterTypes().length == 2
                && method.getParameterTypes()[0].equals(Request.class)
                && method.getParameterTypes()[1].equals(Response.class);
    }

    @Override
    public Map<String, Component> getChildren() {
        return ReflectionUtils.getNonStaticFieldsOf(this, ReflectiveComponent.class, Component.class);
    }

    @Override
    public void addFormsRecursively(Collection<FormHandler> result) {
        ReflectionUtils.collectRecursivelyNonStaticFieldsOf(this, ReflectiveComponent.class, FormHandler.class, result);
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
        Map<String, Component> children = getChildren();
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
            Component child = children.get(childName);
            String childString = (child instanceof ReflectiveComponent)
                    ? ((ReflectiveComponent) child).componentTreeToString(
                            margin + (namesIterator.hasNext() ? "|   " : "    "))
                    : child.toString();
            result += "\n" + margin + "|";
            result += "\n" + margin + "+-- " + childName + " = " + childString;
        }
        return result;
    }

    private Map<String, FormHandler> getForms() {
        return ReflectionUtils.getNonStaticFieldsOf(this, ReflectiveComponent.class, FormHandler.class);
    }

}
