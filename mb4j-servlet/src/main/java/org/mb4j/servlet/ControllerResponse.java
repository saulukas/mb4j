package org.mb4j.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.servlet.http.HttpServletResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.component.view.ViewResponse;

public class ControllerResponse extends ViewResponse {
  private final HttpServletResponse httpResponse;

  public ControllerResponse(BrickRenderer renderer, HttpServletResponse httpResponse) {
    super(renderer);
    this.httpResponse = httpResponse;
  }

  @Override
  public void setContentType(String type) {
    httpResponse.setContentType(type);
  }

  @Override
  public void setContentLength(int len) {
    httpResponse.setContentLength(len);
  }

  @Override
  public void setCharacterEncoding(String encoding) {
    httpResponse.setCharacterEncoding(encoding);
  }

  @Override
  public Writer getWriter() {
    try {
      return httpResponse.getWriter();
    } catch (IOException ex) {
      throw new RuntimeException("Failed to getWriter from HTTP response: " + ex, ex);
    }
  }

  @Override
  public OutputStream getOutputStream() {
    try {
      return httpResponse.getOutputStream();
    } catch (IOException ex) {
      throw new RuntimeException("Failed to getOutputStream from HTTP response: " + ex, ex);
    }
  }
}
