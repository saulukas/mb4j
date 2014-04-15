package org.mb4j.samples;

import java.util.ArrayList;
import java.util.List;
import org.mb4j.Brick;
import org.mb4j.bricks.BrickList;
import org.mb4j.template.TemplateType;

@TemplateType(".mustache")
public class ListOfBrickItems extends Brick {

  BrickList verticalList;
  BrickList horizontalList;

  public ListOfBrickItems() {
    List<SimpleBrick> items = new ArrayList<SimpleBrick>();
    items.add(new SimpleBrick("water2"));
    items.add(new SimpleBrick("bread2"));
    items.add(new SimpleBrick("onion2"));
    this.verticalList = new BrickList(items);
    this.horizontalList = new BrickList(items);
  }

}
