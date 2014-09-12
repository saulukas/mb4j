package org.mb4j.component.utils;

public abstract class AttributeKey<T> {

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass().equals(obj.getClass());
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
