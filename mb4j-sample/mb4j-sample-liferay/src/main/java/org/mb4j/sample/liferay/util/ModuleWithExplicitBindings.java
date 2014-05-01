package org.mb4j.sample.liferay.util;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

public class ModuleWithExplicitBindings extends AbstractModule {
  private final Module[] modules;

  public ModuleWithExplicitBindings(Module... modules) {
    this.modules = modules;
  }

  @Override
  protected void configure() {
    binder().requireExplicitBindings();
    for (Module module : modules) {
      install(module);
    }
  }
}
