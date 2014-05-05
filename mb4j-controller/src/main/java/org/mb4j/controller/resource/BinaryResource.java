package org.mb4j.controller.resource;

import java.io.OutputStream;
import org.mb4j.controller.ControllerRequest;

public abstract class BinaryResource extends Resource {
  public interface Output {
    Output withHeader(String name, String value);

    OutputStream outputStreamForMimeType(String mimeType);
  }

  @Override
  public final ResourceResponse handle(ControllerRequest request) {
    writeResourceTo(request.binaryOutput());
    return new ResourceResponse();
  }

  protected abstract void writeResourceTo(Output output);
}
