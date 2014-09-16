package org.mb4j.component.viewmap;

import org.mb4j.component.View;
import org.mb4j.component.url.UrlPath;

public interface MapUrlPath2View {

    Result viewAt(UrlPath path);

    class Result {

        public final View view;
        public final UrlPath mappedPath;
        public final UrlPath paramsPath;

        public Result(View view, UrlPath mappedPath, UrlPath paramsPath) {
            this.view = view;
            this.mappedPath = mappedPath;
            this.paramsPath = paramsPath;
        }

        public boolean resultIsEmpty() {
            return view == null;
        }
    }
}
