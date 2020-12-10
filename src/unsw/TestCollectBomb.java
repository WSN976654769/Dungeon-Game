package unsw;
import org.junit.*;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.thing.Bomb;
import unsw.dungeon.entity.thing.Key;
import unsw.dungeon.entity.thing.Sword;
import unsw.dungeon.entity.thing.Treasure;
import unsw.dungeon.goal.EnemyGoal;

public class TestCollectBomb {
	private Dungeon dungeon;
	private Player player;

    @Before
    public void init() {
        dungeon = new Dungeon(10,10);
        dungeon.addEntity(new Bomb(5,5));
    }
    @Test
    public void testLoading() {
    	
    	Assert.assertTrue(dungeon.getEntity(5,5) instanceof Bomb);
    	
    }
    
    @Test
    public void testMoveUpPickupBomb() {
    	player = new Player(dungeon,5,6);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveUp();
    	Assert.assertTrue(dungeon.getEntity(5,5) instanceof Player);
    	player.moveUp();
    	Assert.assertNull(dungeon.getEntity(5,5));
    	Assert.assertTrue(player.getBag().containBomb());	
    }
    
    @Test
    public void testMoveDownPickupBomb() {
    	player = new Player(dungeon,5,4);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveDown();
    	Assert.assertTrue(dungeon.getEntity(5,5) instanceof Player);
    	player.moveDown();
    	Assert.assertNull(dungeon.getEntity(5,5));
    	Assert.assertTrue(player.getBag().containBomb());	
    }
    
    @Test 
    public void testMoveRightPickupBomb() {
    	player = new Player(dungeon,4,5);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveRight();
    	Assert.assertTrue(dungeon.getEntity(5,5) instanceof Player);
    	player.moveRight();
    	Assert.assertNull(dungeon.getEntity(5,5));
    	Assert.assertTrue(player.getBag().containBomb());	
    }
    
    @Test 
    public void testMoveLeftPickupBomb() {
    	player = new Player(dungeon,6,5);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	player.moveLeft();
    	Assert.assertTrue(dungeon.getEntity(5,5) instanceof Player);
    	player.moveLeft();
    	Assert.assertNull(dungeon.getEntity(5,5));
    	Assert.assertTrue(player.getBag().containBomb());	
    	
    }
    
    @Test 
    public void testNotPickupLitBomb() {
    	player = new Player(dungeon,6,5);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	
    	Bomb Litbomb =new Bomb(6,6);
    	Litbomb.setState(true);
    	dungeon.addEntity(Litbomb);
    	player.pickup(6,6);
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Bomb);
    	Assert.assertFalse(player.getBag().containBomb());	
    }
    
    @Test 
    public void testPickupManyBombs() {
    	player = new Player(dungeon,6,5);
    	player.setGoal(new EnemyGoal(dungeon));
    	dungeon.addEntity(player);
    	dungeon.addEntity(new Bomb(3,3));
    	dungeon.addEntity(new Bomb(7,7));
    	dungeon.addEntity(new Bomb(8,8));    	
    	Bomb Litbomb =new Bomb(6,6);
    	Litbomb.setState(true);
    	dungeon.addEntity(Litbomb);
    	
    	player.pickup(3,3);
    	player.pickup(5,5);
    	player.pickup(6,6);
    	player.pickup(7,7);
    	player.pickup(8,8);
    	
    	Assert.assertNull(dungeon.getEntity(3,3));
    	Assert.assertNull(dungeon.getEntity(5,5));
    	Assert.assertNull(dungeon.getEntity(7,7));
    	Assert.assertNull(dungeon.getEntity(8,8));
    	Assert.assertTrue(dungeon.getEntity(6,6) instanceof Bomb);
    	Assert.assertTrue(player.getBag().checkNumBomb(4));	
    }
}
