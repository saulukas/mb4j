package org.mb4j.controller;

public interface Controller {
  ViewResponse handle(ControllerRequest request);
}
