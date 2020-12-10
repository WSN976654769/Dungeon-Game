package unsw.dungeon.goal;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entity.Exit;

public class ExitGoal implements Goal{

    private Dungeon dungeon;

    public ExitGoal(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    @Override
    public boolean achieveGoal() {
        if (dungeon.getEntity(dungeon.getPlayer().getX(), dungeon.getPlayer().getY()) instanceof Exit)
            return true;
        else
            return false;
    }
}
