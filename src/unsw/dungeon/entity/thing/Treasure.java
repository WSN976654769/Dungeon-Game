package unsw.dungeon.entity.thing;

import javafx.beans.property.IntegerProperty;
import unsw.dungeon.entity.Entity;

public class Treasure extends Entity implements Item {
	
	public Treasure(int x, int y) {
	     super(x, y);
	}

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
}
