package org.mb4j.liferay.adapters;

import org.mb4j.brick.MustacheBrick;

public class PortletFormHeaderBrick extends MustacheBrick {

    final boolean pauthParamEnabled;
    final String pauthParamValue;
    final String formParamName;
    final String formParamValue;

    public PortletFormHeaderBrick(String pauthParamOrNull, String formParamName, String formParamValue) {
        this.pauthParamEnabled = (pauthParamOrNull != null);
        this.pauthParamValue = pauthParamOrNull;
        this.formParamName = formParamName;
        this.formParamValue = formParamValue;
    }
}
