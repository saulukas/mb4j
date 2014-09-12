package org.mb4j.example.servlet.master;

import org.mb4j.brick.MustacheBrick;
import org.mb4j.component.asset.AssetUrl4Response;

public class MasterLayoutPageBrick extends MustacheBrick {

    MustacheBrick header;
    MustacheBrick content;
    MustacheBrick footer;
    String title = "Events";
    AssetUrl4Response jquery_js;
}
