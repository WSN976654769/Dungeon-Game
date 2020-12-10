package unsw;

import org.junit.*;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.thing.Bomb;
import unsw.dungeon.entity.thing.Key;
import unsw.dungeon.entity.thing.Sword;
import unsw.dungeon.entity.thing.Treasure;
import unsw.dungeon.goal.EnemyGoal;

public class TestCollectKey {

	private Dungeon dungeon;
	private Player player;

    @Before
    public void init() {
        dungeon = new Dungeon(10,10);
        dungeon.addEntity(new Key(6,6,0));
    }
    @Test
    public void testLoading() {
    	
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Key);
    	
    }
    
    @Test
    public void testMoveUpPickupKey() {
    	player = new Player(dungeon,6,7);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveUp();
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Player);
    	player.moveUp();
    	Assert.assertNull(dungeon.getEntity(6,6));
    	Assert.assertTrue(player.getBag().containKey());	
    }
    
    @Test
    public void testMoveDownPickupKey() {
    	player = new Player(dungeon,6,5);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveDown();
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Player);
    	player.moveDown();
    	Assert.assertNull(dungeon.getEntity(6,6));
    	Assert.assertTrue(player.getBag().containKey());	
    }
    
    @Test 
    public void testMoveRightPickupKey() {
    	player = new Player(dungeon,5,6);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveRight();
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Player);
    	player.moveRight();
    	Assert.assertNull(dungeon.getEntity(6,6));
    	Assert.assertTrue(player.getBag().containKey());	
    }
    
    @Test 
    public void testMoveLeftPickupKey() {
    	player = new Player(dungeon,7,6);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveLeft();
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Player);
    	player.moveLeft();
    	Assert.assertNull(dungeon.getEntity(6,6));
    	Assert.assertTrue(player.getBag().containKey());	
    }
    
    @Test 
    public void testOnlyCanCarryOneKey() {
    	player = new Player(dungeon,4,3);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(new Key(2,2,3));   	
    	player.pickup(2, 2);
    	Assert.assertNull(dungeon.getEntity(2,2));
    	Assert.assertTrue(player.getBag().containKey());	
    	
    	player.pickup(6, 6);
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Key); 	
    	Assert.assertTrue(player.getBag().checkIdKey(3));
    }
}
