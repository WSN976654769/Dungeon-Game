package unsw;

import org.junit.*;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.thing.Bomb;
import unsw.dungeon.entity.thing.Invincibility;
import unsw.dungeon.entity.thing.Key;
import unsw.dungeon.entity.thing.Sword;
import unsw.dungeon.entity.thing.Treasure;
import unsw.dungeon.goal.EnemyGoal;

public class TestCollectPotion {

	private Dungeon dungeon;
	private Player player;

    @Before
    public void init() {
        dungeon = new Dungeon(10,10);
        dungeon.addEntity(new Invincibility(6,6));  
    }
    @Test
    public void testLoading() {
    	
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Invincibility);
    	
    }
    
    @Test
    public void testMoveUpPickupPotion() {
    	player = new Player(dungeon,6,7);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveUp();
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Player);
    	player.moveUp();
    	Assert.assertNull(dungeon.getEntity(6,6));
    	Assert.assertTrue(player.getBag().containPotion());	
    }
    
    @Test
    public void testMoveDownPickupPotion() {
    	player = new Player(dungeon,6,5);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveDown();
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Player);
    	player.moveDown();
    	Assert.assertNull(dungeon.getEntity(6,6));
    	Assert.assertTrue(player.getBag().containPotion());	
    }
    
    @Test 
    public void testMoveRightPickupPotion() {
    	player = new Player(dungeon,5,6);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveRight();
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Player);
    	player.moveRight();
    	Assert.assertNull(dungeon.getEntity(6,6));
    	Assert.assertTrue(player.getBag().containPotion());	
    }
    
    @Test 
    public void testMoveLeftPickupPotion() {
    	player = new Player(dungeon,7,6);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveLeft();
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Player);
    	player.moveLeft();
    	Assert.assertNull(dungeon.getEntity(6,6));
    	Assert.assertTrue(player.getBag().containPotion());	
    }

}
