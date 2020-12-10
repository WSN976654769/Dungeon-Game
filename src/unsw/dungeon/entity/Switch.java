package unsw.dungeon.entity;

import javafx.beans.property.IntegerProperty;

public class Switch extends Entity{
	
	public Switch(int x, int y) {
	     super(x, y);
	}
	
    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
}
