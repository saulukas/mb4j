package org.mb4j.example.servlet.util;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class ModuleWithExplicitBindings extends AbstractModule {
  private final Module[] modules;

  public ModuleWithExplicitBindings(Module... modules) {
    this.modules = modules;
  }
  public static Injector injectorWithExplicitBindings(Module... modules) {
    return Guice.createInjector(new ModuleWithExplicitBindings(modules));
  }

  @Override
  protected void configure() {
    binder().requireExplicitBindings();
    for (Module module : modules) {
      install(module);
    }
  }
}
