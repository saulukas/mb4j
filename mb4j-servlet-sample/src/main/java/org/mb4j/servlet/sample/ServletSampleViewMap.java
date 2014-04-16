package org.mb4j.servlet.sample;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.mb4j.servlet.sample.event.edit.EventEditForm;
import org.mb4j.servlet.sample.event.edit.EventEditPage;
import org.mb4j.servlet.sample.event.list.EventListPage;
import org.mb4j.servlet.sample.home.HomePage;
import org.mb4j.controller.ViewMap;
import org.mb4j.controller.mount.ViewMounter;
import static org.mb4j.controller.path.ViewPathString.viewPath;

@Singleton
public class ServletSampleViewMap extends ViewMap {
    @Inject
    public ServletSampleViewMap(
            HomePage.View home,
            EventListPage.View eventList,
            EventEditPage.View eventEdit,
            EventEditForm.SaveAction eventSave) {
        super(ViewMounter.withHomeView(home)
                .mount(viewPath("event/*"), eventList)
                .mount(viewPath("event/edit/*"), eventEdit)
                .mount(viewPath("event/save"), eventSave));
    }
}
