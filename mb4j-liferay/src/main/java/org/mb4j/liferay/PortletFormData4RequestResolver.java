package org.mb4j.liferay;

import org.mb4j.brick.Brick;
import org.mb4j.controller.form.FormData4RequestResolver;
import org.mb4j.controller.mapping.FormClass2NameResolver;

public class PortletFormData4RequestResolver extends FormData4RequestResolver {
  private final String portletNamespace;
  private final String pauthParamOrNull;

  public PortletFormData4RequestResolver(
      String portletNamespace,
      String pauthParamOrNull,
      FormClass2NameResolver formClass2name) {
    super(formClass2name);
    this.portletNamespace = portletNamespace;
    this.pauthParamOrNull = pauthParamOrNull;
  }

  @Override
  protected String resolveFieldName(String name) {
    return portletNamespace + name;
  }

  @Override
  protected Brick createHeaderBrick(String formName) {
    return new PortletFormHeaderBrick(pauthParamOrNull, FORM_PARAM, formName);
  }
}
