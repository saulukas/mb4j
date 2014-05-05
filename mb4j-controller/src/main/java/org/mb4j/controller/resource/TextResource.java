package org.mb4j.controller.resource;

import java.io.Writer;
import org.mb4j.controller.ControllerRequest;

public abstract class TextResource extends Resource {
  public interface Output {
    Output withHeader(String name, String value);

    Writer writerForMimeType(String mimeType);
  }

  @Override
  public final ResourceResponse handle(ControllerRequest request) {
    writeResourceTo(request.textOutput());
    return new ResourceResponse();
  }

  protected abstract void writeResourceTo(Output output);
}
