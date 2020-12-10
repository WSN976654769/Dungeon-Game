package unsw;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Player;
import unsw.dungeon.entity.thing.Bomb;
import unsw.dungeon.entity.thing.Key;
import unsw.dungeon.entity.thing.Sword;
import unsw.dungeon.entity.thing.Treasure;
import unsw.dungeon.goal.EnemyGoal;
import unsw.dungeon.goal.ExitGoal;

public class TestUseSword {
		private Dungeon dungeon;
		private Player player;
		
		@Before
		public void init() {
			dungeon = new Dungeon(10,10);
		    player = new Player(dungeon,5,5);
	        player.setGoal(new EnemyGoal(dungeon));   
	        dungeon.addEntity(player);
	        dungeon.addEntity(new Sword(1,1)); 
		}
		
		@Test
		public void testLoading() {
		
			Assert.assertTrue(dungeon.getEntity(1,1) instanceof Sword);
			Assert.assertTrue(dungeon.getEntity(5,5) instanceof Player);

		}
		@Test
		public void testUpUseSword(){	
	        Enemy e = new Enemy(dungeon,5,4);
	        player.registerEnemy(e);
	        dungeon.addEntity(e);
	    	player.pickup(1,1);
	    	player.moveUp();
	    	player.moveUp();
	    	Assert.assertNull(dungeon.getEntity(5,4));
		}
		
		@Test
		public void testDownUseSword(){	
	        Enemy e = new Enemy(dungeon,5,6);
	        player.registerEnemy(e);
	        dungeon.addEntity(e);
	    	player.pickup(1,1);
	    	player.moveDown();
	    	player.moveDown();
	    	Assert.assertNull(dungeon.getEntity(5,6));
		}
		
		@Test
		public void testLeftUseSword(){	
	        Enemy e = new Enemy(dungeon,4,5);
	        player.registerEnemy(e);
	        dungeon.addEntity(e);
	    	player.pickup(1,1);
	    	player.moveLeft();
	    	player.moveLeft();
	    	Assert.assertNull(dungeon.getEntity(4,5));
		}
		
		@Test
		public void testRightUseSword(){	
	        Enemy e = new Enemy(dungeon,6,5);
	        player.registerEnemy(e);
	        dungeon.addEntity(e);
	    	player.pickup(1,1);
	    	player.moveRight();
	    	player.moveRight();
	    	Assert.assertNull(dungeon.getEntity(6,5));
		}
		
		@Test
		public void testReduceHitSword(){	
	        Enemy e1 = new Enemy(dungeon,6,5);
	        Enemy e2 = new Enemy(dungeon,4,5);  
	        player.registerEnemy(e1);
	        player.registerEnemy(e2);
	        dungeon.addEntity(e1);
	        dungeon.addEntity(e2);
	        
	        Sword s = new Sword(2,2);
	        dungeon.addEntity(s);
	    	player.pickup(2,2);
	    	player.moveRight();
	    	player.moveLeft();
	    	player.moveLeft();
	    	player.moveLeft();
	    	Assert.assertNull(dungeon.getEntity(6,5));
	    	Assert.assertNull(dungeon.getEntity(4,5));
	    	Assert.assertTrue(s.getHit()==3);
		}
		
		@Test
		public void testDispearSword(){	
	        Enemy e1 = new Enemy(dungeon,6,5);
	        Enemy e2 = new Enemy(dungeon,7,5);  
	        Enemy e3 = new Enemy(dungeon,8,5);  
	        Enemy e4 = new Enemy(dungeon,8,10);
	        Enemy e5 = new Enemy(dungeon,9,9);
	        dungeon.addEntity(e1);
	        dungeon.addEntity(e2);
	        dungeon.addEntity(e3);
	        dungeon.addEntity(e4);
	        dungeon.addEntity(e5);
	        
	        Sword s = new Sword(2,2);
	        dungeon.addEntity(s);
	    	player.pickup(2,2);
	    	
	    	player.moveRight(); //4
	    	Assert.assertTrue(s.getHit()==4);
	    	player.moveRight(); //3	
	    	Assert.assertTrue(s.getHit()==3);
	    	player.moveRight(); //2
	    	Assert.assertTrue(s.getHit()==2);
	    	
	    	player.moveDown(); //(8,6)
	    	player.moveDown(); //(8,7)
	    	player.moveDown(); //(8,8)
	    	player.moveDown(); //(8,9)
	    	Assert.assertTrue(dungeon.getEntity(8,9) instanceof Player); 
	    	
	    	player.moveDown();///10 kill  (8,10)
	    	Assert.assertTrue(s.getHit()==1);
	    	
	    	Assert.assertTrue(dungeon.getEntity(8,9) instanceof Player); 	
	    	player.moveRight();  
	    	Assert.assertTrue(dungeon.getEntity(9,9) instanceof Player); 
	    	player.moveRight(); //0 disappear
	    	
	    	Assert.assertTrue(dungeon.getEntity(9,9) instanceof Player);
	    	Assert.assertTrue(s.getHit()==0); 
	    	player.moveDown(); 
	    	Assert.assertFalse(player.getBag().containSword());
	    	
	    	Assert.assertNull(dungeon.getEntity(6,5));
	    	Assert.assertNull(dungeon.getEntity(7,5));
	    	Assert.assertNull(dungeon.getEntity(8,5));	
	    	Assert.assertNull(dungeon.getEntity(8,10));
	    	
	    	
	    	Assert.assertNull(dungeon.getEntity(9,10));
	    	
	    	
	    	player.pickup(1,1);
	    	Assert.assertTrue(player.getBag().containSword());
	    	
	    	
		}
}