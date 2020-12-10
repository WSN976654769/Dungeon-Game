package unsw.dungeon.entity;

public class Pit extends Entity{
	
	public Pit(int x, int y) {
	     super(x, y);    
	}
	
   public int getY() {
       return y().get();
   }

   public int getX() {
       return x().get();
   }
}
