package org.mb4j.view;

import org.mb4j.view.mount.PathFromViewClassResolver;
import org.mb4j.view.mount.ViewFromPathResolver;
import org.mb4j.view.mount.ViewMounter;

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
