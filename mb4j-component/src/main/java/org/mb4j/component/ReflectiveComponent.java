package org.mb4j.component;

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
import org.mb4j.component.form.ActionResponse;
import org.mb4j.component.form.FormHandler;
import org.mb4j.component.url.NamedParams;
import org.mb4j.component.utils.ReflectionUtils;
import org.mb4j.component.utils.SimpleClassName;

public class ReflectiveComponent implements Component {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ResourceMethod {
    }

    public static class ResourceMethodSignatureException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public ResourceMethodSignatureException(Method method) {
            super(ResourceMethod.class.getSimpleName() + " " + method + " must have signature:\n  "
                    + "void " + method.getName() + "("
                    + Request.class.getSimpleName()
                    + " request, "
                    + Response.class.getSimpleName()
                    + "response).");
        }

    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ActionMethod {
    }

    public static class ActionMethodSignatureException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public ActionMethodSignatureException(Method method) {
            super(ActionMethod.class.getSimpleName() + " " + method + " must have signature\n  "
                    + ActionResponse.class.getSimpleName() + " " + method.getName() + "("
                    + Request.class.getSimpleName() + " request, "
                    + NamedParams.class.getSimpleName() + " formInput)");
        }

    }
    private final Map<String, Method> resourceMethodMap = new HashMap<>();
    private final Map<String, Method> actionMethodMap = new HashMap<>();

    public ReflectiveComponent() {
        initResourceMethodMap();
        initActionMethodMap();
    }

    private void initResourceMethodMap() {
        List<Method> resourceMethods = ReflectionUtils.getAnnotatedMethodsOf(
                getClass(), ReflectiveComponent.class, ResourceMethod.class);
        for (Method method : resourceMethods) {
            if (!isValidResourceMethod(method)) {
                throw new ResourceMethodSignatureException(method);
            }
            method.setAccessible(true);
            resourceMethodMap.put(method.getName(), method);
        }
    }

    static boolean isValidResourceMethod(Method method) {
        return method.getReturnType().equals(void.class)
                && method.getParameterTypes().length == 2
                && method.getParameterTypes()[0].equals(Request.class)
                && method.getParameterTypes()[1].equals(Response.class);
    }

    private void initActionMethodMap() {
        List<Method> actionMethods = ReflectionUtils.getAnnotatedMethodsOf(
                getClass(), ReflectiveComponent.class, ActionMethod.class);
        for (Method method : actionMethods) {
            if (!isValidActionMethod(method)) {
                throw new ActionMethodSignatureException(method);
            }
            method.setAccessible(true);
            actionMethodMap.put(method.getName(), method);
        }
    }

    static boolean isValidActionMethod(Method method) {
        return method.getReturnType().equals(ActionResponse.class)
                && method.getParameterTypes().length == 2
                && method.getParameterTypes()[0].equals(Request.class)
                && method.getParameterTypes()[1].equals(NamedParams.class);
    }

    @Override
    public Map<String, Component> getNamedChildren() {
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
    public Set<String> getActionNames() {
        return actionMethodMap.keySet();
    }

    @Override
    public void serveResource(String name, Request request, Response response) {
        Method method = resourceMethodMap.get(name);
        try {
            method.invoke(this, request, response);
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException ex) {
            throw new RuntimeException("Failed to serve resource '" + name + "'"
                    + " by invoking method " + method + ": " + ex, ex);
        }
    }

    @Override
    public ActionResponse processAction(String name, Request request, NamedParams formInput) {
        Method method = actionMethodMap.get(name);
        try {
            return (ActionResponse) method.invoke(this, request, formInput);
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException ex) {
            throw new RuntimeException("Failed serve to process action '" + name + "'"
                    + " by invoking method " + method + ": " + ex, ex);
        }
    }

    public String componentTreeToString(String margin) {
        String result = SimpleClassName.of(getClass());
        Map<String, Component> children = getNamedChildren();
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
