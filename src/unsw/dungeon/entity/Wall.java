package unsw.dungeon.entity;

import javafx.beans.property.IntegerProperty;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }

//    public IntegerProperty x() {
//        return x();
//    }
//
//    public IntegerProperty y() {
//        return y();
//    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
}
