package org.mb4j.brick.template;

import java.util.HashMap;
import java.util.Map;
import org.mb4j.brick.MustacheBrick;

public class CachedTemplateProvider implements TemplateProvider {

    private final TemplateProvider source;
    private final Map<Class<? extends MustacheBrick>, BrickTemplate> class2template = new HashMap<>();

    public CachedTemplateProvider(TemplateProvider source) {
        this.source = source;
    }

    @Override
    public synchronized BrickTemplate templateFor(Class<? extends MustacheBrick> brickClass) {
        BrickTemplate result = class2template.get(brickClass);
        if (result == null) {
            result = source.templateFor(brickClass);
            class2template.put(brickClass, result);
        }
        return result;
    }
}
