package unsw;

import org.junit.Assert;
import org.junit.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.*;
import unsw.dungeon.entity.thing.Treasure;
import unsw.dungeon.goal.*;
public class TestCombinationGoal {
	
	private Dungeon dungeon;
	private Player player;
	
    @Test
    public void testExitAchieveGoal () {
        CombinationGoal combinationGoal = new CombinationGoal();
        Dungeon dungeon = new Dungeon(2, 2);
        dungeon.setPlayer(new Player(dungeon,2, 2));
        dungeon.addEntity(new Exit(2,2));
        combinationGoal.addGoal(new ExitGoal(dungeon));
        Assert.assertTrue(combinationGoal.achieveGoal());
    }

    @Test
    public void testBoulderAchieveGoal () {
        CombinationGoal combinationGoal = new CombinationGoal();
        Dungeon dungeon = new Dungeon(2, 2);
        dungeon.setPlayer(new Player(dungeon,2, 2));
        dungeon.addEntity(new Boulder(2,2));
        dungeon.addEntity(new Switch(2,2));
        dungeon.addEntity(new Boulder(1,2));
        dungeon.addEntity(new Switch(1,2));
        combinationGoal.addGoal(new BoulderGoal(dungeon));
        Assert.assertTrue(combinationGoal.achieveGoal());
    }


    @Test
    public void testEnemyAchieveGoal () {
    	 CombinationGoal combinationGoal = new CombinationGoal();
         Dungeon dungeon = new Dungeon(2, 2);
         dungeon.setPlayer(new Player(dungeon,1, 2));
         dungeon.addEntity(new Enemy(dungeon,1,2));
         dungeon.removeEntity(dungeon.getEntity(1,2));
         combinationGoal.addGoal(new EnemyGoal(dungeon));
         Assert.assertTrue(combinationGoal.achieveGoal());

    }

    @Test
    public void testTreasureAchieveGoal () {
        CombinationGoal combinationGoal = new CombinationGoal();
        Dungeon dungeon = new Dungeon(4, 4);
        dungeon.setPlayer(new Player(dungeon,2, 2));
        dungeon.addEntity(new Treasure(2,2));
        dungeon.removeEntity(dungeon.getEntity(2,2));
        combinationGoal.addGoal(new TreasureGoal(dungeon));
        Assert.assertTrue(combinationGoal.achieveGoal());
    }

    @Test
    public void testAdvancedAchieveGoal () {
        CombinationGoal combinationGoal = new CombinationGoal();
        Dungeon dungeon = new Dungeon(2, 2);
        dungeon.setPlayer(new Player(dungeon,2, 2));
        dungeon.addEntity(new Treasure(1,2));
        dungeon.addEntity(new Enemy(dungeon,2,2));
        dungeon.removeEntity(dungeon.getEntity(1,2));
        dungeon.removeEntity(dungeon.getEntity(2,2));
        combinationGoal.addGoal(new TreasureGoal(dungeon));
        combinationGoal.addGoal(new EnemyGoal(dungeon));
        combinationGoal.setRule("AND");
        Assert.assertTrue(combinationGoal.achieveGoal());
    }
}
