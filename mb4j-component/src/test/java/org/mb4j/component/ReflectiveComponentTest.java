package org.mb4j.component;

import com.google.common.base.Objects;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.component.TypicalComponents.BaseComponent;
import org.mb4j.component.TypicalComponents.ComponentWithInvalidResourceMethods;
import org.mb4j.component.TypicalComponents.ExtendedComponent;

public class ReflectiveComponentTest {

    @Test
    public void finds_ResourceMethods() {
        BaseComponent component = new BaseComponent();
        assertThat(component.getResourceNames(),
                containsInAnyOrder("resource1", "resource2"));
    }

    @Test
    public void finds_ResourceMethods_from_base_class() {
        ExtendedComponent component = new ExtendedComponent();
        assertThat(component.getResourceNames(),
                containsInAnyOrder("resource1", "resource2", "resource3"));
    }

    @Test(expected = ResourceMethodSignatureException.class)
    public void validates_ResourceMethods_in_constructor() {
        new ComponentWithInvalidResourceMethods();
    }

    @Test
    public void ResourceMethods_must_be_void_and_have_two_params__Request_and_Response() {
        assertFalse(ReflectiveComponent.isValid(methodByName(
                ComponentWithInvalidResourceMethods.class, "nonVoidResourceMethod")));
        assertFalse(ReflectiveComponent.isValid(methodByName(
                ComponentWithInvalidResourceMethods.class, "wrongParameterOrder")));
    }

    static Method methodByName(Class<?> klass, String name) {
        List<Method> methods = Arrays.asList(klass.getMethods());
        for (Method method : methods) {
            if (Objects.equal(method.getName(), name)) {
                return method;
            }
        }
        throw new RuntimeException("Method " + name + " not found in " + klass + ".");
    }
}
