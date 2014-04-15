package org.mb4j.servlet.sample;

import com.google.inject.Injector;

public class ServletSampleTestApplication {
    static Injector injector = ServletSampleModule.createInjector();

    public static <T> T inject(Class<T> klass) {
        return injector.getInstance(klass);
    }
}
