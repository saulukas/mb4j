package org.mb4j.sample.liferay;

import com.google.inject.Injector;

public class LiferaySampleTestApplication {
  static Injector injector = LiferaySampleModule.injector();

  public static <T> T inject(Class<T> klass) {
    return injector.getInstance(klass);
  }
}
