package unsw.dungeon.entity.thing;

import javafx.beans.property.IntegerProperty;
import unsw.dungeon.entity.Entity;

public class Key extends Entity implements Item{
	
	private int id;
	
	public Key(int x, int y,int id) {
	     super(x, y);
	     this.id  = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

}
