package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import unsw.dungeon.entity.*;
import unsw.dungeon.entity.thing.Bomb;
import unsw.dungeon.entity.thing.Hover;
import unsw.dungeon.entity.thing.Invincibility;
import unsw.dungeon.entity.thing.Key;
import unsw.dungeon.entity.thing.Sword;
import unsw.dungeon.entity.thing.Treasure;
import unsw.dungeon.goal.*;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader() {

    }

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        JSONObject goal = json.getJSONObject("goal-condition");
        dungeon.getPlayer().setGoal(loadGoal(dungeon, goal));
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id = 0;

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "boulder":
            Boulder boulder = new Boulder(x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "switch":
            Switch sw = new Switch(x, y);
            onLoad(sw);
            entity = sw;
            break;
        case "bomb":
            Bomb bomb = new Bomb(x, y);
            onLoad(bomb);
            entity = bomb;
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon,x, y);
            enemy.addEnemy();
            onLoad(enemy);
            entity = enemy;
            break;
        case "invincibility":
            Invincibility invincibility = new Invincibility(x, y);
            onLoad(invincibility);
            entity = invincibility;
            break;
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            break;         
		case "key":
			id = json.getInt("id");
			Key key = new Key(x, y,id);
			onLoad(key);
			entity = key;
			break;		  
		case "door":
			id = json.getInt("id");
			boolean set = json.getBoolean("state");
			Door door = new Door(x, y,id,set);
			onLoad(door);
			entity = door;
			break;			 
		case "pit":
			Pit pit = new Pit( x, y);
			onLoad(pit);
			entity = pit;
			break;	
        case "hover":
            Hover hover = new Hover(x, y);
            onLoad(hover);
            entity = hover;
            break;
        }
        dungeon.addEntity(entity);
    }

    // load goal according to different input
    private CombinationGoal loadGoal(Dungeon dungeon, JSONObject json) {
        String goal = json.getString("goal");
        CombinationGoal combinationGoal = new CombinationGoal();
        switch (goal) {
        case "exit":
            combinationGoal.addGoal(new ExitGoal(dungeon));
            return combinationGoal;
        case "boulders":
            combinationGoal.addGoal(new BoulderGoal(dungeon));
            return combinationGoal;
        case "treasure":
            combinationGoal.addGoal(new TreasureGoal(dungeon));
            return combinationGoal;
        case "enemies":
            combinationGoal.addGoal(new EnemyGoal(dungeon));
            return combinationGoal;
        case "AND":
            JSONArray subGoals = json.getJSONArray("subgoals");
            combinationGoal.setRule(goal);
            for (int i = 0; i < subGoals.length(); i++) {
                combinationGoal.addGoal(loadGoal(dungeon, subGoals.getJSONObject(i)));
            }
            return combinationGoal;
        default:
            return null;
        }
    }

    public abstract void onLoad(Player player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Boulder wall);

    public abstract void onLoad(Switch exit);

    public abstract void onLoad(Bomb bomb);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Invincibility invincibility);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Pit pit );
    
    public abstract void onLoad(Hover hover);

}
