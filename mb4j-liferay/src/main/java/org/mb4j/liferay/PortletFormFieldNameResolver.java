package org.mb4j.liferay;

import org.mb4j.controller.form1.Form1;

public class PortletFormFieldNameResolver implements Form1.NameResolver {
    @Override
    public String resolvedName(String fieldName) {
        return fieldName;
    }
}
