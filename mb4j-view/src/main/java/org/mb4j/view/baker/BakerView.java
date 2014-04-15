package org.mb4j.view.baker;

import org.mb4j.view.View;
import org.mb4j.view.ViewRequest;
import org.mb4j.view.ViewResponse;
import static org.mb4j.view.ViewResponse.responseWith;

public abstract class BakerView<P> implements View {
  protected final ViewBrickBaker<P> baker;

  public BakerView(ViewBrickBaker<P> baker) {
    this.baker = baker;
  }

  protected abstract P bakerParamsFrom(ViewRequest request);

  @Override
  public ViewResponse handle(ViewRequest request) {
    return responseWith(baker.bakeBrick(request, bakerParamsFrom(request)));
  }
}
