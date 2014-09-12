package org.mb4j.component.utils;

import java.io.OutputStream;
import java.io.Writer;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.component.Response;

public class ResponseOnWriter extends Response {
  private final Writer writer;
  public String contentType = null;
  public int contentLength = 0;
  public String characterEncoding = null;

  public ResponseOnWriter(BrickRenderer renderer, Writer writer) {
    super(renderer);
    this.writer = writer;
  }

  @Override
  public void setContentType(String type) {
    this.contentType = type;
  }

  @Override
  public void setContentLength(int len) {
    this.contentLength = len;
  }

  @Override
  public void setCharacterEncoding(String encoding) {
    this.characterEncoding = encoding;
  }

  @Override
  public Writer getWriter() {
    return writer;
  }

  @Override
  public OutputStream getOutputStream() {
    throw new UnsupportedOperationException("getOutputStream() not supported. Use getWriter().");
  }
}
