package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Enemy;

public class EnemyGoal implements Goal{

    private Dungeon dungeon;

    public EnemyGoal(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    @Override
    public boolean achieveGoal() {
        for (int i = 0; i <= dungeon.getHeight(); i++) {
            for (int j = 0; j <= dungeon.getWidth(); j++) {
                if (dungeon.getEntity(i, j) instanceof Enemy) {
                    return false;
                }
            }
        }
        return true;
    }
}
