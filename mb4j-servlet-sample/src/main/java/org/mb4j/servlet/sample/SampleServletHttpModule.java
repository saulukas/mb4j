package org.mb4j.servlet.sample;

import org.mb4j.servlet.sample.util.DummyServlet2;
import org.mb4j.servlet.sample.util.DummyServlet;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

public class SampleServletHttpModule extends ServletModule {
  @Override
  protected void configureServlets() {
    bugfixGuicServlet30MissingBinding();
    bind(DummyServlet.class).in(Singleton.class);
    bind(DummyServlet2.class).in(Singleton.class);
    bind(ServletSampleFilter.class).in(Singleton.class);
    filter("/*").through(ServletSampleFilter.class);
    serve("/hello/aha/*").with(DummyServlet.class);
    serve("/hello/oho/*").with(DummyServlet2.class);
  }

  private void bugfixGuicServlet30MissingBinding() {
    String className = "com.google.inject.servlet.InternalServletModule$BackwardsCompatibleServletContextProvider";
    try {
      bind(Class.forName(className));
    } catch (ClassNotFoundException ex) {
      throw new TypeNotPresentException(className, ex);
    }
  }
}
