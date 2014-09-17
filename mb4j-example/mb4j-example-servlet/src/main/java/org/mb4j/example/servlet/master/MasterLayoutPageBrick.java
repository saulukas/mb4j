package org.mb4j.example.servlet.master;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.AssetUrl;

public class MasterLayoutPageBrick extends MustacheBrick {

    MustacheBrick header;
    MustacheBrick content;
    MustacheBrick footer;
    String title = "Events";
    AssetUrl jquery_js;
}
