/**
 *
 */
package unsw.dungeon;

import unsw.dungeon.entity.*;
import unsw.dungeon.entity.thing.Treasure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public Entity getEntity(int x, int y) {
        //one coordinate may has two entities, ignore the useless one
        List<Entity> result = new ArrayList<>();
        for (Entity e : entities) {
            if (e.getX() == x && e.getY() == y) {
                result.add(e);
            }
        }
        if (result.size() == 1) {
            return result.get(0);
        }
        else if (result.size() == 0) {
            return null;
        }
        else {
            for (Entity r : result) {
                if (r instanceof Boulder || r instanceof Exit )
                    return r;
            }
            
            // player priority is lowest
            for(Entity r : result) {
            	if(r instanceof Switch)
            		return r;
            }
            
            //player and bomb return player
            for(Entity r : result) {
            	if(r instanceof Player)
            		return r;
            }
            return null;
        }
    }

    public void removeEntity(Entity e) {
            entities.remove(e);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getEntitiesAround(int x, int y) {
        List<Entity> result = new ArrayList<>();
        result.add(getEntity(x-1, y));
        result.add(getEntity(x, y-1));
        result.add(getEntity(x+1, y));
        result.add(getEntity(x, y+1));
        result.add(getEntity(x, y));
        return result;
    }
}
