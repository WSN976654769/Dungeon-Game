package unsw.dungeon.entity;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.thing.*;
import java.util.ArrayList;

import javafx.scene.control.Alert;

public class Enemy extends Entity {
	
	private Dungeon dungeon;
	private Player player;

	public Enemy(Dungeon dungeon, int x, int y) {
	     super(x, y);
	     this.dungeon = dungeon;
	     this.player = dungeon.getPlayer();
	}
	/**
	 * add new enemy in list 
	 */
	public void addEnemy() {
		player.registerEnemy(this);
	}
	
	/**
	 * determine whether enemy can move such as wall, boulder..
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
    private boolean isMoveAble(int x, int y) {
	    if (dungeon.getEntity(x,y) instanceof Wall || dungeon.getEntity(x,y) instanceof Boulder 
	    		||dungeon.getEntity(x,y) instanceof Enemy || dungeon.getEntity(x,y) instanceof Item) {
	    	return false;
	    }
	    else if(dungeon.getEntity(x,y) instanceof Pit) {
	    	dungeon.removeEntity(this);
	    	return false;
	    }
	    else if (dungeon.getEntity(x,y) instanceof Door) {
	    	Door d = (Door)dungeon.getEntity(x,y);
	    	if(!d.isState()) return false;
	    	System.out.println("entry");
	    	return true;
	    }  	
	    else
	        return true;
    }
	
    /**
     * received information of player, get location so that move it.
     * @param p = player 
     */
   
    public void update(Player p) {
    	Bag b = p.getBag();
		int playerX = p.getX();
		int playerY = p.getY();
		boolean flag =b.containPotion();	
		moveEnemy(playerX,playerY,flag);
    }
    
    /**
     * analyze location of player, then make move  
     * @param playerX  location x
     * @param playerY  location y
     * @param flag     used to determine the status of player 
     * if they has invincibility potion return flag=true, 
     * enemy would escape and keep away from player
     * otherwise, keep close to player
     */
    public void moveEnemy(int playerX,int playerY,boolean flag) {	
		int enemyX = getX();
		int enemyY = getY();
		int x, y;
	
		if(enemyX == playerX) {
			if(enemyY == playerY+1 || enemyY == playerY-1)
				return;
			else if(enemyY < playerY) {
				if(flag== false) moveDown();
				else moveUp();
			}
			else if(enemyY > playerY) {
				if(flag== false) moveUp();
				else moveDown();
			}
		}
		else if(enemyY == playerY) {
			if(enemyX == playerX+1 || enemyX == playerX-1)
				return;
			else if(enemyX < playerX) {
				if(flag== false) moveRight();
				else moveLeft();
			}
			else if(enemyX > playerX) {
				if(flag== false) moveLeft();
				else moveRight();
			}
		}
		
		//if enemy at top right corner
		else if(enemyX > playerX && enemyY < playerY) {//
		     x = enemyX - playerX;
			 y = playerY - enemyY;
			if(x>y) {
				if(flag== false) moveLeft();
				else moveUp();
			}
			else {
				if(flag== false) moveDown();
				else moveRight();
			}
		}
		//if enemy at top left corner
		else if(enemyX < playerX && enemyY < playerY) {
			 x = playerX - enemyX;
			 y = playerY - enemyY;
			if(x>y) {
				if(flag== false) moveRight();
				else moveUp();
			}
			else {
				if(flag== false) moveDown();
				else moveLeft();
			}
		}
		//if enemy at bottom left corner
		else if(enemyX < playerX && enemyY > playerY) {
			 x = playerX - enemyX;
			 y = enemyY - playerY;
			 if(x>y) {
				 if(flag== false) moveRight();
				 else moveDown();
			 }
			 else {
				 if(flag== false) moveUp();
				 else moveLeft();
			 }
		}
		//if enemy at bottom right corner
		else if(enemyX > playerX && enemyY > playerY) {
			 x =  enemyX -playerX;
			 y = enemyY - playerY;
			 if(x>y) {
				 if(flag== false) moveLeft();
				 else moveDown();
			 }
			 else {
				 if(flag== false) moveUp();
				 else moveRight();
			 }
		}		
    }

	public boolean moveUp() {
	    
		if (getY() > 0 && isMoveAble(getX(), getY() - 1)) {
	        y().set(getY() - 1);
	        return true;
		}
		return false;
	}
	public boolean moveRight() {
	
		 if (getX() < dungeon.getWidth() - 1 && isMoveAble(getX() + 1, getY())) {
	            x().set(getX() + 1);
	            return true;
		 }
		 return false;
	}
	public boolean moveLeft() {
		
		if (getX() > 0 && isMoveAble(getX() - 1, getY())) {
            x().set(getX() - 1);
            return true;
		}
		return false;
	}
	public boolean moveDown() {
		
		if (getY() < dungeon.getHeight() - 1 && isMoveAble(getX(), getY() + 1)) {
            y().set(getY() + 1);
			return true;
		}
		return false;
	}
}
