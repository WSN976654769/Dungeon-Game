package unsw.dungeon.entity;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonController;
import unsw.dungeon.entity.thing.*;
import unsw.dungeon.goal.Goal;

import java.util.ArrayList;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

	private Dungeon dungeon;
	private boolean success;
	private Goal goal;

	private boolean alive;
	// bag of player
	private Bag bag;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int time_I = 10;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Player(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.bag = new Bag();
		this.alive = true;
		this.time_I = time_I;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setisAlive(boolean flag) {
		this.alive = flag;
	}

	public boolean isSuccess() {
		return success;
	}

	public int getTime_I() {
		return time_I;
	}

	public void setTime_I(int time_I) {
		this.time_I = time_I;
	}
	
	/**
	 * Use fireBomb if bag have
	 * 
	 * @param dungeonController
	 * 
	 */
	public void fireBomb(DungeonController dungeonController) {
		if (bag.containBomb()) {
			bag.removeBomb();
			Bomb newBomb = new Bomb(getX(), getY());
			dungeon.addEntity(newBomb);
			newBomb.fire(dungeonController, dungeon, getX(), getY());
		}
	}

	public Bag getBag() {
		return this.bag;
	}
	/**
	 * update player location to enemies by observe pattern
	 * first determined player's alive status if they meet enemy or pit
	 * potion time used to recored invincibility time 
	 * push boulder if it can be moveable
	 * if entity is item which can be picked up, save to bag
	 * if meet door, check door's status and use fitted key if have 
	 * keep check goal whether or not achieve
	 */
	public void moveUp() {
		notifyObservers();
		if (isAlive(getX(), getY() - 1))
			alive = false;
		// movement meets boulder
		if (isBoulder(getX(), getY() - 1)) {
			if (canPush(getX(), getY(), "up")) {
				if (dungeon.getEntity(getX(), getY()-2) instanceof Pit) 
					dungeon.removeEntity(dungeon.getEntity(getX(), getY()-1));
				else 
					dungeon.getEntity(getX(), getY() - 1).y().set(getY() - 2);
				y().set(getY() - 1);
			}
			if (goal.achieveGoal())
				success = true;
			return;
		}
		potionTime();
		// movement meets thing
		if (isItem(getX(), getY() - 1)) {
			pickup(getX(), getY() - 1);
			potionTime();
		}
		if (isDoor(getX(), getY() - 1)) {
			openDoor(getX(), getY() - 1);
		}
		// just move
		if (getY() > 0 && isMoveAble(getX(), getY() - 1))
			y().set(getY() - 1);

		// reach goal
		if (goal.achieveGoal())
			success = true;
	}

	public void moveDown() {
		notifyObservers();
		if (isAlive(getX(), getY() + 1))
			alive = false;
		if (isBoulder(getX(), getY() + 1)) {
			if (canPush(getX(), getY(), "down")) {
				if (dungeon.getEntity(getX(), getY()+2) instanceof Pit) 
					dungeon.removeEntity(dungeon.getEntity(getX(), getY()+1));
				else 
					dungeon.getEntity(getX(), getY() + 1).y().set(getY() + 2);
				y().set(getY() + 1);
			}
			if (goal.achieveGoal())
				success = true;
			return;
		}
		potionTime();
		if (isItem(getX(), getY() + 1)) {
			pickup(getX(), getY() + 1);
			potionTime();
		}
		if (isDoor(getX(), getY() + 1)) {		
			openDoor(getX(), getY() + 1);
		}
		if (getY() < dungeon.getHeight() - 1 && isMoveAble(getX(), getY() + 1))
			y().set(getY() + 1);

		if (goal.achieveGoal())
			success = true;
	}

	public void moveLeft() {
		notifyObservers();
		if (isAlive(getX() - 1, getY()))
			alive = false;
		if (isBoulder(getX() - 1, getY())) {
			if (canPush(getX(), getY(), "left")) {
				if (dungeon.getEntity(getX()-2, getY()) instanceof Pit) 
					dungeon.removeEntity(dungeon.getEntity(getX() - 1, getY()));
				else 
					dungeon.getEntity(getX() - 1, getY()).x().set(getX() - 2);
				x().set(getX() - 1);
			}
			if (goal.achieveGoal())
				success = true;
			return;
		}
		potionTime();
		if (isItem(getX() - 1, getY())) {
			pickup(getX() - 1, getY());
			potionTime();
		}
		if (isDoor(getX() - 1, getY())) {		
			openDoor(getX() - 1, getY());
		}
		if (getX() > 0 && isMoveAble(getX() - 1, getY()))
			x().set(getX() - 1);

		if (goal.achieveGoal())
			success = true;

	}

	public void moveRight() {
		notifyObservers();
		if (isAlive(getX() + 1, getY()))
			alive = false;
		if (isBoulder(getX() + 1, getY())) {
			if (canPush(getX(), getY(), "right")) {
				if (dungeon.getEntity(getX()+2, getY()) instanceof Pit)  
					dungeon.removeEntity(dungeon.getEntity(getX() + 1, getY()));
				else 
					dungeon.getEntity(getX() + 1, getY()).x().set(getX() + 2);
				x().set(getX() + 1);
			}
			if (goal.achieveGoal())
				success = true;
			return;
		}
		potionTime();
		if (isItem(getX() + 1, getY())) {
			pickup(getX() + 1, getY());
			potionTime();
		}
		if (isDoor(getX() + 1, getY())) {
			openDoor(getX() + 1, getY());
		}
		if (getX() < dungeon.getWidth() - 1 && isMoveAble(getX() + 1, getY()))
			x().set(getX() + 1);

		if (goal.achieveGoal())
			success = true;
	}
	
	/**
	 * add enemy to list
	 * @param e: enmey
	 */
	public void registerEnemy(Enemy e) {
		enemies.add(e);
	}
	
	public void removeEnemy(Enemy e) {
		enemies.remove(e);
	}

	 
	/**
	 * transfer information of player location to enemies
	 * 
	 */
	private void notifyObservers() {
		for (Enemy e : enemies) {
			e.update(this); // send it
		}
	}

	/**
	 * pick up item such as sword, key .. put these into bag
	 * @param x
	 * @param y
	 */
	public void pickup(int x, int y) {
		if (dungeon.getEntity(x, y) instanceof Sword) {
			if (!bag.containSword()) {
				Sword s = (Sword) dungeon.getEntity(x, y);
				bag.add(s);
			}
		} 
		else if (dungeon.getEntity(x, y) instanceof Invincibility) {
			Invincibility p = (Invincibility) dungeon.getEntity(x, y);
			bag.add(p);
		} 
		else if (dungeon.getEntity(x, y) instanceof Hover) {
			System.out.println("hover");
			Hover h = (Hover) dungeon.getEntity(x, y);
			bag.add(h);
		}
		else if (dungeon.getEntity(x, y) instanceof Bomb) {
			Bomb b = (Bomb) dungeon.getEntity(x, y);
			if (!b.isState()) {
				bag.add(b);	
			}
		}
		else if (dungeon.getEntity(x, y) instanceof Treasure) {
			Treasure t = (Treasure) dungeon.getEntity(x, y);
			bag.add(t);
		}
		else if (dungeon.getEntity(x, y) instanceof Key) {
			if (!bag.containKey()) {
				Key k = (Key) dungeon.getEntity(x, y);
				bag.add(k);
			}
		}
		dungeon.removeEntity(dungeon.getEntity(x, y));
	}

	/**
	 * open closed door if bag contain key with fitted id,
	 * 
	 * @param x
	 * @param y
	 */
	private void openDoor(int x, int y) {
		Door d = (Door) dungeon.getEntity(x,y);
		System.out.println("door id: " + d.getId());
		if (!d.isState()) {
			if (bag.containKey() && bag.checkIdKey(d.getId())) {
				d.setState(true);
				System.out.println("open !");
				bag.removeKey();
			}
		}
	}
	/**check player's moveable
	 * check next square is wall or not
	 * if it is wall, return false
	 * if next square is closed door, cannot move into, return false
	 * otherwise return true
	 * @param x
	 * @param y
	 * @return boolean
	 */
	private boolean isMoveAble(int x, int y) {
		if (dungeon.getEntity(x, y) instanceof Wall) {
			return false;
		} else if (dungeon.getEntity(x, y) instanceof Door) {
			Door d = (Door) dungeon.getEntity(x, y);
			if (!d.isState())
				return false;
			System.out.println("entry");
			return true;
		} else
			return true;
	}

	/**
	 *  determine next square is item which can be picked up or not
	 *  item include sword,treasure,key,bomb,hover,invincibiliy and hover potion
	 * @param x
	 * @param y
	 * @return true if can pick up
	 */
	private boolean isItem(int x, int y) {
		if (dungeon.getEntity(x, y) instanceof Item)
			return true;
		else
			return false;
	}

	// decide next square is boulder or not
	private boolean isBoulder(int x, int y) {
		if (dungeon.getEntity(x, y) instanceof Boulder)
			return true;
		else
			return false;
	}
	
	/**
	 * if it has a sword in bag, use it to kill enemies
	 * @param x
	 * @param y
	 */
	private void useSword(int x, int y) {
		Enemy e = (Enemy) dungeon.getEntity(x, y);
		dungeon.removeEntity(e);
		System.out.println("use sword to kill enemys");
		bag.reduceHit();
		removeEnemy(e); // reduce notify target
	}

	/**
	 * check adjacent square whether a door
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isDoor(int x, int y) {
		if (dungeon.getEntity(x, y) instanceof Door)
			return true;
		else
			return false;
	}

	/**
	 * if player get potion, then record their limited time
 	 */
	private void potionTime() {
		if (bag.containPotion()) {
			if (getTime_I() >= 0) {
				System.out.println("remain " + getTime_I() + " seconds");
				setTime_I(getTime_I() - 1);
			}
			if (getTime_I() <= 0) {
				bag.removePotion();
			}
		}
	}

	
	/**
	 * determine alive for player
	 * if meet enemy, use sword to kill it if have
	 * or if have in potion time, invincibility
	 * if meet a pet, check whether has hover potion so that can fly
	 * @param x
	 * @param y
	 * @return false if have sword, invincibility and hover potions
	 * otherwise return true which means player die 
	 */
	private boolean isAlive(int x, int y) {
		if (dungeon.getEntity(x, y) instanceof Enemy) {
			System.out.println("Enemy");
			if (bag.containSword()) {
				useSword(x,y);
				return false;
			}
			if (bag.containPotion()) {
				System.out.println("Invincibility !");
				return false;
			}
			return true;
		}
		if (dungeon.getEntity(x, y) instanceof Pit) {
			if (bag.containHover()) {
				System.out.println("Flay !");
				return false;
			}
			return true;
		}
		return false;
	}

	/** can play push boulder
	 * player can push boulder to switch if there not wall 
	 * if player push boulder to pit, boulder would disappear
	 * @param x
	 * @param y
	 * @param orient
	 * @return
	 */
	private boolean canPush(int x, int y, String orient) {
		switch (orient) {
		case "up":
			if (dungeon.getEntity(x, y - 2) instanceof Switch || dungeon.getEntity(x, y - 2) instanceof Pit|| dungeon.getEntity(x, y - 2) == null)
				return true;
			else
				return false;
		case "down":
			if (dungeon.getEntity(x, y + 2) instanceof Switch || dungeon.getEntity(x, y + 2) instanceof Pit|| dungeon.getEntity(x, y + 2) == null)
				return true;
			else
				return false;
		case "left":
			if (dungeon.getEntity(x - 2, y) instanceof Switch || dungeon.getEntity(x-2, y) instanceof Pit||dungeon.getEntity(x - 2, y) == null)
				return true;
			else
				return false;
		case "right":	
			if (dungeon.getEntity(x + 2, y) instanceof Switch || dungeon.getEntity(x+2, y) instanceof Pit ||  dungeon.getEntity(x + 2, y) == null)
				return true;
			else
				return false;
		default:
			return false;
		}

	}

}
