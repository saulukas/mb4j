package org.mb4j.brick.samples.composition;

import org.mb4j.brick.samples.composition.MoreCompositeBrick;
import org.mb4j.brick.samples.composition.CompositeBrick;
import org.mb4j.brick.samples.lists.ListOfBrickItems;
import org.mb4j.brick.samples.lists.ListOfCustomItems;
import org.mb4j.brick.MustacheBrick;
import org.mb4j.brick.samples.SimpleBrick;
import org.mb4j.brick.template.TemplateType;

@TemplateType(".mustache")
public class VeryCompositeBrick extends MustacheBrick {
  final MustacheBrick moreComposite = new MoreCompositeBrick();
  final MustacheBrick composite = new CompositeBrick();
  final MustacheBrick simple = new SimpleBrick("<Hello>");
  final MustacheBrick listOfBricks = new ListOfBrickItems();
  final MustacheBrick listOfCustomItems = new ListOfCustomItems();
  String message;

  VeryCompositeBrick() {
  }

  public static class Baker {
    public VeryCompositeBrick bakeBrick(Params params) {
      VeryCompositeBrick brick = new VeryCompositeBrick();
      brick.message = params.message;
      return brick;
    }

    public static class Params {
      final String message;

      public Params(String message) {
        this.message = message;
      }
    }
  }
}
