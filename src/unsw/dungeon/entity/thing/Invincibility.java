package unsw.dungeon.entity.thing;

import unsw.dungeon.entity.Entity;

public class Invincibility extends Entity implements Item{
    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public Invincibility(int x, int y) {
        super(x, y);
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
}
