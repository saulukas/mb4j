package org.mb4j.servlet;

import com.google.common.base.Optional;
import javax.servlet.http.HttpServletRequest;
import org.mb4j.component.utils.AttributeKey;
import org.mb4j.component.utils.Attributes;

public class ServletRequestAttributes extends Attributes {
  private final HttpServletRequest request;

  public ServletRequestAttributes(HttpServletRequest request) {
    this.request = request;
  }

  @Override
  public <T> void setValueOf(AttributeKey<T> key, T value) {
    request.setAttribute(key.toString(), value);
  }

  @Override
  public <T> Optional<T> valueOf(AttributeKey<T> key) {
    return Optional.fromNullable((T) request.getAttribute(key.toString()));
  }
}
