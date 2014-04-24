package org.mb4j.controller;

public abstract class Controller extends StatelessComponent {
  public abstract ControllerResponse handle(ControllerRequest request);
}
