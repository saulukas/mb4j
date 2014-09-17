package org.mb4j.component;

import java.lang.reflect.Method;

public class ResourceMethodSignatureException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceMethodSignatureException(Method method) {
        super("Return type of ResourceMethod " + method + " must be void.");
    }

}
