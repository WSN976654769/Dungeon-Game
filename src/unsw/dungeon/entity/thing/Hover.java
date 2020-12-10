package unsw.dungeon.entity.thing;

import unsw.dungeon.entity.Entity;

public class Hover extends  Entity implements Item{
	
	public Hover(int x, int y) {
	     super(x, y);    
	}
	
	public int getY() {
      return y().get();
	}

	public int getX() {
      return x().get();
    }
}
