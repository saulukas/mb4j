package org.mb4j.controller.baker;

import org.mb4j.controller.ViewRequest;

public class ParameterlessBakerView<B extends ViewBrickBaker<Void>> extends BakerView<Void> {
  public ParameterlessBakerView(B baker) {
    super(baker);
  }

  @Override
  protected Void bakerParamsFrom(ViewRequest request) {
    return null;
  }
}
