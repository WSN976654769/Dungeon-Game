package unsw;
import org.junit.*;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Door;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.thing.Key;
import unsw.dungeon.goal.EnemyGoal;

public class TestOpenDoor {

	private Dungeon dungeon;
	private Player player;

    @Before
    public void init() {
        dungeon = new Dungeon(10,10);
       
        dungeon.addEntity(new Key(6,6,0));
        dungeon.addEntity(new Key(8,8,1));   
        dungeon.addEntity(new Door(3,3,0,false)); //closedDoor
    }
    
    @Test
    public void testLoading() {
    	
    	//Assert.assertTrue(dungeon.getEntity(2,2) instanceof Player);
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Key);
    	Assert.assertTrue(dungeon.getEntity(8,8) instanceof Key);
    	Assert.assertTrue(dungeon.getEntity(3,3) instanceof Door);
    	
    }
    
    @Test
    public void testUpOpenDoorWithWrongKey() {
		player = new Player(dungeon,3,4);
		player.setGoal(new EnemyGoal(dungeon));
		dungeon.addEntity(player);
    	
		player.pickup(8,8);
		Assert.assertTrue(player.getBag().containKey());
		Assert.assertTrue(player.getBag().checkIdKey(1));
		player.moveUp();
		Door closedDoor = (Door)dungeon.getEntity(3,3);
		Assert.assertFalse(closedDoor.isState());
		Assert.assertTrue(dungeon.getEntity(3,3) instanceof Door);
		Assert.assertTrue(dungeon.getEntity(3,4) instanceof Player);
    }
    
    @Test
    public void testUpOpenDoorWithFitKey() {
		player = new Player(dungeon,3,4);
		player.setGoal(new EnemyGoal(dungeon));
		dungeon.addEntity(player);
    	
		player.pickup(6,6);
		Assert.assertTrue(player.getBag().containKey());
		Assert.assertTrue(player.getBag().checkIdKey(0));
		player.moveUp();
		player.moveUp();
		Door closedDoor = (Door)dungeon.getEntity(3,3);
		Assert.assertTrue(closedDoor.isState());	
		Assert.assertTrue(dungeon.getEntity(3,2) instanceof Player);
    }
    public void testDownOpenDoorWithWrongKey() {
		player = new Player(dungeon,3,2);
		player.setGoal(new EnemyGoal(dungeon));
		dungeon.addEntity(player);
    	
		player.pickup(8,8);
		Assert.assertTrue(player.getBag().containKey());
		Assert.assertTrue(player.getBag().checkIdKey(1));
		player.moveDown();
		Door closedDoor = (Door)dungeon.getEntity(3,3);
		Assert.assertFalse(closedDoor.isState());
		Assert.assertTrue(dungeon.getEntity(3,3) instanceof Door);
		Assert.assertTrue(dungeon.getEntity(3,2) instanceof Player);
    }
    
    @Test
    public void testDownOpenDoorWithFitKey() {
		player = new Player(dungeon,3,2);
		player.setGoal(new EnemyGoal(dungeon));
		dungeon.addEntity(player);
    	
		player.pickup(6,6);
		Assert.assertTrue(player.getBag().containKey());
		Assert.assertTrue(player.getBag().checkIdKey(0));
		player.moveDown();
		player.moveDown();
		Door closedDoor = (Door)dungeon.getEntity(3,3);
		Assert.assertTrue(closedDoor.isState());	
		Assert.assertTrue(dungeon.getEntity(3,4) instanceof Player);
    }
    
    @Test
    public void testLeftOpenDoorWithWrongKey() {
		player = new Player(dungeon,4,3);
		player.setGoal(new EnemyGoal(dungeon));
		dungeon.addEntity(player);
    	
		player.pickup(8,8);
		Assert.assertTrue(player.getBag().containKey());
		Assert.assertTrue(player.getBag().checkIdKey(1));
		player.moveLeft();
		Door closedDoor = (Door)dungeon.getEntity(3,3);
		Assert.assertFalse(closedDoor.isState());
		Assert.assertTrue(dungeon.getEntity(3,3) instanceof Door);
		Assert.assertTrue(dungeon.getEntity(4,3) instanceof Player);
    }
    
    @Test
    public void testLeftOpenDoorWithFitKey() {
		player = new Player(dungeon,4,3);
		player.setGoal(new EnemyGoal(dungeon));
		dungeon.addEntity(player);
    	
		player.pickup(6,6);
		Assert.assertTrue(player.getBag().containKey());
		Assert.assertTrue(player.getBag().checkIdKey(0));
		player.moveLeft();
		player.moveLeft();
		Door closedDoor = (Door)dungeon.getEntity(3,3);
		Assert.assertTrue(closedDoor.isState());	
		Assert.assertTrue(dungeon.getEntity(2,3) instanceof Player);
    }
    
    
    
    @Test
    public void testRightOpenDoorWithWrongKey() {
		player = new Player(dungeon,2,3);
		player.setGoal(new EnemyGoal(dungeon));
		dungeon.addEntity(player);
    	
		player.pickup(8,8);
		Assert.assertTrue(player.getBag().containKey());
		Assert.assertTrue(player.getBag().checkIdKey(1));
		player.moveRight();
		Door closedDoor = (Door)dungeon.getEntity(3,3);
		Assert.assertFalse(closedDoor.isState());
		Assert.assertTrue(dungeon.getEntity(3,3) instanceof Door);
		Assert.assertTrue(dungeon.getEntity(2,3) instanceof Player);
    }
    
    @Test
    public void testRightOpenDoorWithFitKey() {
		player = new Player(dungeon,2,3);
		player.setGoal(new EnemyGoal(dungeon));
		dungeon.addEntity(player);
    	
		player.pickup(6,6);
		Assert.assertTrue(player.getBag().containKey());
		Assert.assertTrue(player.getBag().checkIdKey(0));
		player.moveRight();
		player.moveRight();
		Door closedDoor = (Door)dungeon.getEntity(3,3);
		Assert.assertTrue(closedDoor.isState());	
		Assert.assertTrue(dungeon.getEntity(4,3) instanceof Player);
    }
    
   
}
