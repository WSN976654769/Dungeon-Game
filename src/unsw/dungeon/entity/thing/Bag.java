package unsw.dungeon.entity.thing;
import java.util.ArrayList;

public class Bag{
	private Key key = null;
	private Sword sword = null;
	private Invincibility potion;
	private ArrayList<Bomb> bombs;
	private ArrayList<Treasure> treasures;
	private Hover fly = null;
	
	public Bag() {		 	
	     key = null;
	     sword = null;
	     potion=null;
	     treasures = new ArrayList<Treasure>();
	     bombs = new ArrayList<Bomb>();
	}	
	
	public Sword getSword() {
		return sword;
	}


	public Key getKey() {
		return key;
	}

	public Invincibility getPoition() {
		return potion;
	}

	public void add(Invincibility p) {
		this.potion = p;
		System.out.println("get Potion !");
	}

	public void add(Bomb b) {
		
		bombs.add(b);
		System.out.println("get Bomb !");
	}
	
	public void add(Treasure t) {
		treasures.add(t);
		System.out.println("get Treasure !");
	}
	
	public void add(Sword s) {
		this.sword = s;
		System.out.println("get Sword !");
	}
	
	public void add(Key k) {
		this.key = k;
		System.out.println("get Key !");
		System.out.println("Key id : " + getKey().getId());
	}
	public void add(Hover f) {
		this.fly = f;
		System.out.println("can Flay !");
	}

	public void removeBomb() {
		bombs.remove(bombs.size()-1);
	}
	
	public void removeSword() {
		this.sword = null;
	}
	
	public void removePotion() {
		this.potion = null;
		System.out.println("Invincibiility disappear!");
	}
	
	public void removeKey() {
		this.key = null;
		System.out.println("Key disappear");
	}
	
	public boolean containKey() {
		
		if (this.key!=null)
			return true;
		return false;
	}
	
	public boolean containSword() {
		
		if (this.sword!=null)
			return true;
		return false;
	}
	
	public boolean containPotion() {
		
		if (this.potion!=null)
			return true;
		return false;
	}
	
	
	public boolean containBomb() {
		
		if(bombs.isEmpty())
			return false;
		return true;
	}
	
	public boolean containTreasure() {
		
		if(treasures.isEmpty())
			return false;
		return true;
	}
	
	public boolean containHover() {
			
			if(this.fly==null)
				return false;
			return true;
		}

	public boolean checkIdKey(int id ) {
		
		if(key.getId()==id) {
			return true;
		}
		return false;
	}
	
	public boolean checkNumTreasure(int num) {
		
		if(treasures.size()==num)
			return true;
		return false;
	}
	
	public boolean checkNumBomb(int num) {
		
		if(bombs.size()==num)
			return true;
		return false;
	}
	
	/**
	 * reduce one hit when player used sword
	 * 
	 */
	public void reduceHit() {
		getSword().reduceHit();
		System.out.println(getSword().getHit());
		if(getSword().getHit()==0) {
			removeSword();
			System.out.println("Sword dispear");
		}	
	}
	
	
}