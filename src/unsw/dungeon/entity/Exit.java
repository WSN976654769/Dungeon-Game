package unsw.dungeon.entity;

import javafx.beans.property.IntegerProperty;

public class Exit extends Entity {
	
	public Exit(int x, int y) {
	     super(x, y);
	     
	}
	
    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
}
