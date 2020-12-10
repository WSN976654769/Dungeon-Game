package unsw;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.thing.Bomb;
import unsw.dungeon.entity.thing.Key;
import unsw.dungeon.entity.thing.Sword;
import unsw.dungeon.entity.thing.Treasure;
import unsw.dungeon.goal.EnemyGoal;

public class TestCollectSword {
		private Dungeon dungeon;
		private Player player;
		
		@Before
		public void init() {
		    dungeon = new Dungeon(5,5);
		    dungeon.addEntity(new Sword(3,3)); 
		}
		
		@Test
		public void testLoading() {
		
			Assert.assertTrue(dungeon.getEntity(3,3) instanceof Sword);

		}
			
	   @Test
	    public void testMoveUpPickupSword() {
	    	player = new Player(dungeon,3,4);
	    	player.setGoal(new EnemyGoal(dungeon));
	    	dungeon.addEntity(player);
	    	player.moveUp();
	    	Assert.assertTrue(dungeon.getEntity(3,3) instanceof Player);
	    	player.moveUp();
	    	Assert.assertNull(dungeon.getEntity(3,3));
	    	Assert.assertTrue(player.getBag().containSword());	
	    }
	    
	    @Test
	    public void testMoveDownPickSword() {
	    	player = new Player(dungeon,3,2);
	    	player.setGoal(new EnemyGoal(dungeon));
	    	dungeon.addEntity(player);
	    	player.moveDown();
	    	Assert.assertTrue(dungeon.getEntity(3,3) instanceof Player);
	    	player.moveDown();
	    	Assert.assertNull(dungeon.getEntity(3,3));
	    	Assert.assertTrue(player.getBag().containSword());	
	    }
	    
	    @Test 
	    public void testMoveRightPickupSword() {
	    	player = new Player(dungeon,2,3);
	    	player.setGoal(new EnemyGoal(dungeon));
	    	dungeon.addEntity(player);
	    	player.moveRight();
	    	Assert.assertTrue(dungeon.getEntity(3,3) instanceof Player);
	    	player.moveRight();
	    	Assert.assertNull(dungeon.getEntity(3,3));
	    	Assert.assertTrue(player.getBag().containSword());	
	    }
	    
	    @Test 
	    public void testMoveLeftPickupSword() {
	    	player = new Player(dungeon,4,3);
	    	player.setGoal(new EnemyGoal(dungeon));
	    	dungeon.addEntity(player);
	    	player.moveLeft();
	    	Assert.assertTrue(dungeon.getEntity(3,3) instanceof Player);
	    	player.moveLeft();
	    	Assert.assertNull(dungeon.getEntity(3,3));
	    	Assert.assertTrue(player.getBag().containSword());		
	    }
	    @Test 
	    public void testOnlyCanCarryOneSword() {
	    	player = new Player(dungeon,4,3);
	    	player.setGoal(new EnemyGoal(dungeon));
	    	dungeon.addEntity(new Sword(2,2));   	
	    	player.pickup(3, 3);
	    	Assert.assertNull(dungeon.getEntity(3,3));
	    	Assert.assertTrue(player.getBag().containSword());	
	    	
	    	player.pickup(2, 2);
	    	Assert.assertTrue(dungeon.getEntity(2,2) instanceof Sword); 	
	    }
	    
}
