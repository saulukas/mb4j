package org.mb4j.brick.samples;

import java.util.ArrayList;
import java.util.List;
import org.mb4j.brick.Brick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class ListOfCustomItems extends Brick {

  final List<ListItem> list;

  public static class ListItem {

    final int no;
    final SimpleBrick simpleBrick;

    public ListItem(int no, SimpleBrick simpleBrick) {
      this.no = no;
      this.simpleBrick = simpleBrick;
    }

  }

  public ListOfCustomItems() {
    this.list = new ArrayList<ListItem>();
    this.list.add(new ListItem(1, new SimpleBrick("water")));
    this.list.add(new ListItem(2, new SimpleBrick("bread")));
    this.list.add(new ListItem(3, new SimpleBrick("onion")));
  }

}
