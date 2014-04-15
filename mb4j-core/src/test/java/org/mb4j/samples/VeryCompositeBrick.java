package org.mb4j.samples;

import org.mb4j.Brick;
import org.mb4j.BrickBaker;
import org.mb4j.template.TemplateType;

@TemplateType(".mustache")
public class VeryCompositeBrick extends Brick {
  final Brick moreComposite = new MoreCompositeBrick();
  final Brick composite = new CompositeBrick();
  final Brick simple = new SimpleBrick("<Hello>");
  final Brick listOfBricks = new ListOfBrickItems();
  final Brick listOfCustomItems = new ListOfCustomItems();
  String message;

  VeryCompositeBrick() {
  }

  public static class Baker implements BrickBaker<Baker.Params> {
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
