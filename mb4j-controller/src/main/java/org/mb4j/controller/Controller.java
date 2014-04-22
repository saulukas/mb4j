package org.mb4j.controller;

public interface Controller {
  ControllerResponse handle(ControllerRequest request);
}
