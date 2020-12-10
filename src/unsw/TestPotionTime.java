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

public class TestPotionTime {

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
    public void testTimeLimted(){
    	player = new Player(dungeon,3,2);
		player.setGoal(new EnemyGoal(dungeon));
		dungeon.addEntity(player);
		player.pickup(6,6);
		Assert.assertTrue(player.getBag().containPotion());
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		Assert.assertNull(dungeon.getEntity(6,6));
		Assert.assertFalse(player.getBag().containPotion());

    }
    
}