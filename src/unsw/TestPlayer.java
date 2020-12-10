package unsw;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Boulder;
import unsw.dungeon.entity.Door;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.Wall;
import unsw.dungeon.goal.EnemyGoal;

public class TestPlayer {

    private Dungeon dungeon;

    private Player player;

    @Before
    public void init() {
        dungeon = new Dungeon(5,5);
        player = new Player(dungeon,2,2);
        player.setGoal(new EnemyGoal(dungeon));
        dungeon.addEntity(player);
    }

    @Test
    public void testBasicMoveUp() {
        player.moveUp();
        Assert.assertTrue(dungeon.getEntity(2,1) instanceof Player);
    }

    @Test
    public void testMoveUpTowardsWall () {
        dungeon.addEntity(new Wall(2,1));
        player.moveUp();
        Assert.assertTrue(dungeon.getEntity(2,1) instanceof Wall);
        Assert.assertTrue(dungeon.getEntity(2,2) instanceof Player);
    }

    @Test
    public void testMoveUpTowardsBoulder () {
        dungeon.addEntity(new Boulder(2,1));
        player.moveUp();
        Assert.assertTrue(dungeon.getEntity(2,1) instanceof Player);
        Assert.assertTrue(dungeon.getEntity(2,0) instanceof Boulder);
    }
    @Test
    public void testMoveUpTowardsClosedDoor() {
        dungeon.addEntity(new Door(2,1,1,false));
        player.moveUp();
        Assert.assertTrue(dungeon.getEntity(2,1) instanceof Door);
        Assert.assertTrue(dungeon.getEntity(2,2) instanceof Player);
    }
    
    @Test
    public void testMoveUpTowardsOpenedDoor() {
        dungeon.addEntity(new Door(2,1,1,true));
        player.moveUp();
        player.moveUp();
        Assert.assertTrue(dungeon.getEntity(2,1) instanceof Door);
        Door closedDoor = (Door)dungeon.getEntity(2,1);
		Assert.assertTrue(closedDoor.isState());	
		Assert.assertTrue(dungeon.getEntity(2,0) instanceof Player);
    }
   
    @Test
    public void testBasicMoveDown() {
        player.moveDown();
        Assert.assertTrue(dungeon.getEntity(2,3) instanceof Player);
    }

    @Test
    public void testMoveDownTowardsWall () {
        dungeon.addEntity(new Wall(2,3));
        player.moveDown();
        Assert.assertTrue(dungeon.getEntity(2,3) instanceof Wall);
        Assert.assertTrue(dungeon.getEntity(2,2) instanceof Player);
    }
    @Test
    public void testMoveDownTowardsClosedDoor() {
        dungeon.addEntity(new Door(2,3,1,false));
        player.moveUp();
        Assert.assertTrue(dungeon.getEntity(2,3) instanceof Door);
    }

    @Test
    public void testMoveDownTowardsOpenedDoor() {
        dungeon.addEntity(new Door(2,3,1,true));
        player.moveDown();
        player.moveDown();
        Assert.assertTrue(dungeon.getEntity(2,3) instanceof Door);
        Door closedDoor = (Door)dungeon.getEntity(2,3);
		Assert.assertTrue(closedDoor.isState());	
		Assert.assertTrue(dungeon.getEntity(2,4) instanceof Player);
    }
    
    @Test
    public void testMoveDownTowardsBoulder () {
        dungeon.addEntity(new Boulder(2,3));
        player.moveDown();
        Assert.assertTrue(dungeon.getEntity(2,3) instanceof Player);
        Assert.assertTrue(dungeon.getEntity(2,4) instanceof Boulder);
    }

    @Test
    public void testBasicMoveLeft() {
        player.moveLeft();
        Assert.assertTrue(dungeon.getEntity(1,2) instanceof Player);
    }

    @Test
    public void testMoveLeftTowardsWall () {
        dungeon.addEntity(new Wall(1,2));
        player.moveLeft();
        Assert.assertTrue(dungeon.getEntity(1,2) instanceof Wall);
        Assert.assertTrue(dungeon.getEntity(2,2) instanceof Player);
    }
    
    @Test
    public void testMoveLeftTowardsClosedDoor() {
        dungeon.addEntity(new Door(1,2,1,false));
        player.moveLeft();
        Assert.assertTrue(dungeon.getEntity(1,2) instanceof Door);
        Assert.assertTrue(dungeon.getEntity(2,2) instanceof Player);
    }
    
    @Test
    public void testMoveLeftTowardsOpenedDoor() {
        dungeon.addEntity(new Door(1,2,1,true));
        player.moveLeft();
        player.moveLeft();
        Assert.assertTrue(dungeon.getEntity(1,2) instanceof Door);
        Door closedDoor = (Door)dungeon.getEntity(1,2);
		Assert.assertTrue(closedDoor.isState());	
		Assert.assertTrue(dungeon.getEntity(0,2) instanceof Player);
    }

    @Test
    public void testMoveLeftTowardsBoulder () {
        dungeon.addEntity(new Boulder(1,2));
        player.moveLeft();
        Assert.assertTrue(dungeon.getEntity(1,2) instanceof Player);
        Assert.assertTrue(dungeon.getEntity(0,2) instanceof Boulder);
    }

    @Test
    public void testBasicMoveRight() {
        player.moveRight();
        Assert.assertTrue(dungeon.getEntity(3,2) instanceof Player);
    }

    @Test
    public void testMoveRightTowardsWall () {
        dungeon.addEntity(new Wall(3,2));
        player.moveRight();
        Assert.assertTrue(dungeon.getEntity(3,2) instanceof Wall);
        Assert.assertTrue(dungeon.getEntity(2,2) instanceof Player);
    }

    @Test
    public void testMoveRightTowardsBoulder () {
        dungeon.addEntity(new Boulder(3,2));
        player.moveRight();
        Assert.assertTrue(dungeon.getEntity(3,2) instanceof Player);
        Assert.assertTrue(dungeon.getEntity(4,2) instanceof Boulder);
    }
    @Test
    public void testMoveRightTowardsClosedDoor () {
        dungeon.addEntity(new Door(3,2,1,false));
        player.moveRight();
        Assert.assertTrue(dungeon.getEntity(3,2) instanceof Door);
        Assert.assertTrue(dungeon.getEntity(2,2) instanceof Player);
    }
    
    @Test
    public void testMoveRightTowardsOpenedDoor() {
        dungeon.addEntity(new Door(3,2,1,true));
        player.moveRight();
        player.moveRight();
        Assert.assertTrue(dungeon.getEntity(3,2) instanceof Door);
        Door closedDoor = (Door)dungeon.getEntity(3,2);
		Assert.assertTrue(closedDoor.isState());	
		Assert.assertTrue(dungeon.getEntity(4,2) instanceof Player);
    }
    
}
