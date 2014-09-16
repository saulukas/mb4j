package org.mb4j.component;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mb4j.component.TypicalComponents.BaseComponent;
import org.mb4j.component.TypicalComponents.ExtendedComponent;
import org.mb4j.component.TypicalComponents.NonVoidResourceMethod;

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

    @Test(expected = NonVoidResourceMethodException.class)
    public void return_type_of_ResourceMethod_must_by_void() {
        new NonVoidResourceMethod();
    }

}
