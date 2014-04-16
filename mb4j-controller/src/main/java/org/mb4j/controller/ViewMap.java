package org.mb4j.controller;

import org.mb4j.controller.mount.PathFromViewClassResolver;
import org.mb4j.controller.mount.ViewFromPathResolver;
import org.mb4j.controller.mount.ViewMounter;

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
