package org.mb4j.liferay;

import com.google.common.base.Optional;
import javax.portlet.PortletRequest;
import org.mb4j.controller.utils.AttributeKey;
import org.mb4j.controller.utils.Attributes;

public class PortletRequestAttributes extends Attributes {
  private final PortletRequest request;

  public PortletRequestAttributes(PortletRequest request) {
    this.request = request;
  }

  @Override
  public <T> void setValueOf(AttributeKey<T> key, T value) {
    setAttribute(request, key, value);
  }

  @Override
  public <T> Optional<T> valueOf(AttributeKey<T> key) {
    return getAttribute(request, key);
  }

  public static <T> void setAttribute(PortletRequest request, AttributeKey<T> key, T value) {
    request.setAttribute(key.toString(), value);
  }

  public static <T> Optional<T> getAttribute(PortletRequest request, AttributeKey<T> key) {
    return Optional.fromNullable((T) request.getAttribute(key.toString()));
  }
}
