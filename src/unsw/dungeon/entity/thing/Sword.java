package unsw.dungeon.entity.thing;

import javafx.beans.property.IntegerProperty;
import unsw.dungeon.entity.Entity;

public class Sword extends Entity implements Item{
	
	private int hit;
	
	public Sword(int x, int y) {
	     super(x, y);
	     this.hit = 5;
	}

	public void reduceHit() {
		this.hit --;
		assert(this.hit>=0);
		
	}
	
	public int getHit() {
		return hit;
	}

	public void setHint(int hit) {
		this.hit = hit;
	}
	
    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
	
}
