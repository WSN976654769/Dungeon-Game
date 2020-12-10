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

public class TestCollectTeasure {

	private Dungeon dungeon;
	private Player player;

    @Before
    public void init() {
        dungeon = new Dungeon(10,10);
        dungeon.addEntity(new Treasure(4,4));      
    }
    @Test
    public void testLoading() {
    	
    	Assert.assertTrue(dungeon.getEntity(4,4) instanceof Treasure);
    	
    }
    
    @Test
    public void testMoveUpPickupTeasure() {
    	player = new Player(dungeon,4,5);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveUp();
    	Assert.assertTrue(dungeon.getEntity(4,4) instanceof Player);
    	player.moveUp();
    	Assert.assertNull(dungeon.getEntity(4,4));
    	Assert.assertTrue(player.getBag().containTreasure());	
    }
    
    @Test
    public void testMoveDownPickupTeasure() {
    	player = new Player(dungeon,4,3);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveDown();
    	Assert.assertTrue(dungeon.getEntity(4,4) instanceof Player);
    	player.moveDown();
    	Assert.assertNull(dungeon.getEntity(4,4));
    	Assert.assertTrue(player.getBag().containTreasure());	
    }
    
    @Test 
    public void testMoveRightPickupTeasure() {
    	player = new Player(dungeon,3,4);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveRight();
    	Assert.assertTrue(dungeon.getEntity(4,4) instanceof Player);
    	player.moveRight();
    	Assert.assertNull(dungeon.getEntity(4,4));
    	Assert.assertTrue(player.getBag().containTreasure());	
    }
    
    @Test 
    public void testMoveLeftPickupTeasure() {
    	player = new Player(dungeon,5,4);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveLeft();
    	Assert.assertTrue(dungeon.getEntity(4,4) instanceof Player);
    	player.moveLeft();
    	Assert.assertNull(dungeon.getEntity(4,4));
    	Assert.assertTrue(player.getBag().containTreasure());	
    }
    
    @Test 
    public void testPickupManyTreasures() {
    	player = new Player(dungeon,6,5);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	
    	dungeon.addEntity(new Treasure(3,3));
    	dungeon.addEntity(new Treasure(6,6));
    	player.pickup(3,3);
    	player.pickup(4,4);
    	player.pickup(6,6);
     	
    	Assert.assertNull(dungeon.getEntity(3,3));
    	Assert.assertNull(dungeon.getEntity(4,4));
    	Assert.assertNull(dungeon.getEntity(6,6));
    	Assert.assertTrue(player.getBag().checkNumTreasure(3));	
    }
    

}
