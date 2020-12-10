package unsw.dungeon.entity.thing;

import javafx.application.Platform;
import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonController;
import unsw.dungeon.entity.Boulder;
import unsw.dungeon.entity.Enemy;
import unsw.dungeon.entity.Entity;
import unsw.dungeon.entity.Player;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity implements Item{
	
	private boolean state; 
	private int lastTime = 5;


    public Bomb(int x, int y) {
	     super(x, y);
	     this.state = false;
	}

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }
    /**
     * record time of fire bomb
     * @param dungeonController: update to map
     * @param dungeon : game 
     * @param x
     * @param y
     */
    public void fire(DungeonController dungeonController, Dungeon dungeon, int x, int y) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int tmpTime = lastTime -2;
            public void run() {
                if (tmpTime < 0) {
                    Platform.runLater(() -> {
                        dungeonController.refreshUI();
                        destroyAround(dungeonController,dungeon, x, y);
                        
                        //destroyAround(dungeon, x, y);
                    });
                 // destroy around enemies and player
                    state = true;
                    timer.cancel();

                }
                tmpTime--;
                lastTime = tmpTime + 2;
                // refreshUI
                Platform.runLater(dungeonController::refreshUI);
            }
        }, 0, 1000);
    }
    /**
     * destroy around entity enemy and boulder by explode bomb
     * @param dungeonController update map and show the explode
     * @param dungeon : game 
     * @param x
     * @param y
     */
    public void destroyAround(DungeonController dungeonController,Dungeon dungeon, int x, int y) {
        for (Entity entity : dungeon.getEntitiesAround(x, y)) {
            if (entity instanceof Enemy || entity instanceof Boulder) {
                dungeon.removeEntity(entity);
            }
            if (entity instanceof Player) {
                dungeon.getPlayer().setisAlive(false);
                dungeonController.checkAlive();
            }
        }
    }
    
	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

}
