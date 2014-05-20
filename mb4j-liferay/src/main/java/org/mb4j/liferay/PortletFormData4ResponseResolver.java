package org.mb4j.liferay;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.form.FormData4ResponseResolver;
import org.mb4j.component.sitemap.MapFormClass2Name;

public class PortletFormData4ResponseResolver extends FormData4ResponseResolver {
  private final String portletNamespace;
  private final String pauthParamOrNull;

  public PortletFormData4ResponseResolver(
      String portletNamespace,
      String pauthParamOrNull,
      MapFormClass2Name formClass2name) {
    super(formClass2name);
    this.portletNamespace = portletNamespace;
    this.pauthParamOrNull = pauthParamOrNull;
  }

  @Override
  protected String fieldName4Response(String name) {
    return portletNamespace + name;
  }

  @Override
  protected MustacheBrick createHeaderBrick(String formName) {
    return new PortletFormHeaderBrick(pauthParamOrNull, FORM_PARAM, formName);
  }
}
