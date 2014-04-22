package org.mb4j.controller.mapping;

import org.mb4j.controller.mapping.PathFromViewClassResolver;
import org.mb4j.controller.mapping.ViewFromPathResolver;
import org.mb4j.controller.mapping.ViewMounter;

public class ViewMap {
    private final ViewMounter mounter;

    public ViewMap(ViewMounter mounter) {
        this.mounter = mounter;
    }

    public ViewFromPathResolver viewFromPathResolver() {
        return mounter.viewFromPathResolver();
    }

    public PathFromViewClassResolver pathFromViewClassResolver() {
        return mounter.pathFromViewClassResolver();
    }

    @Override
    public String toString() {
        return mounter.toString();
    }
}
