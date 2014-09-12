package org.mb4j.component.utils;

import com.google.common.base.Optional;
import java.util.HashMap;
import java.util.Map;

public class AttributesMap extends Attributes {

    private final Map<AttributeKey, Object> map = new HashMap<>();

    @Override
    public <T> void setValueOf(AttributeKey<T> key, T value) {
        map.put(key, value);
    }

    @Override
    public <T> Optional<T> valueOf(AttributeKey<T> key) {
        return Optional.fromNullable((T) map.get(key));
    }

    public Map<AttributeKey, Object> asMap() {
        return map;
    }
}
