package unsw.dungeon.entity;

import javafx.beans.property.IntegerProperty;

public class Door extends Entity {
	
	private int id;
	private boolean state; //open or close
	
	public Door(int x, int y , int id,boolean set) {
	     super(x, y);
	     this.id = id;
	     this.state = set;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
   

	
}
