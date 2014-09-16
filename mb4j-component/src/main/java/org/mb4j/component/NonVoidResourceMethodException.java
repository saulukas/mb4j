package org.mb4j.component;

import java.lang.reflect.Method;

public class NonVoidResourceMethodException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NonVoidResourceMethodException(Method method) {
        super("Return type of ResourceMethod " + method + " must be void.");
    }

}
