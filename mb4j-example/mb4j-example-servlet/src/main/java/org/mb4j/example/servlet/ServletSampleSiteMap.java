package org.mb4j.example.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import static org.mb4j.component.url.UrlPathString.urlPathOf;
import org.mb4j.component.viewmap.ViewMap;
import static org.mb4j.component.viewmap.ViewMapBuilder.routeHomeTo;
import org.mb4j.example.servlet.event.edit.EventEditPage;
import org.mb4j.example.servlet.event.list.EventListPage;
import org.mb4j.example.servlet.form2v.FormV2Page;
import org.mb4j.example.servlet.home.HomePage;
import org.mb4j.example.servlet.services.TimeService;

@Singleton
public class ServletSampleSiteMap extends ViewMap {

    @Inject
    public ServletSampleSiteMap(
            HomePage home,
            EventListPage eventList,
            EventEditPage eventEdit) {
        super(routeHomeTo(home)
                .route(urlPathOf("event/*"), eventList)
                .route(urlPathOf("event/edit/*"), eventEdit)
                .route(urlPathOf("time/*"), new TimeService())
                .route(urlPathOf("form2v/*"), new FormV2Page())
        );
    }
}
