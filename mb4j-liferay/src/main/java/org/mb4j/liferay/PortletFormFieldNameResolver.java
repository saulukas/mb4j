package org.mb4j.liferay;

import org.mb4j.controller.form1.Form;

public class PortletFormFieldNameResolver implements Form.NameResolver {
    @Override
    public String resolvedName(String fieldName) {
        return fieldName;
    }
}
