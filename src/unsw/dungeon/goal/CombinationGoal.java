package unsw.dungeon.goal;

import java.util.ArrayList;
import java.util.List;

public class CombinationGoal implements Goal{

    private String rule = "";

    private List<Goal> goals = new ArrayList<>();

    public CombinationGoal(){
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    @Override
    public boolean achieveGoal() {

        if (rule.equals("AND")) {
            for (Goal g : goals) {
                if (!g.achieveGoal())
                    return false;
            }

            return true;
        }

        else if (rule.equals("OR")) {
            for (Goal g : goals) {
                if (g.achieveGoal())
                    return true;
            }

            return false;
        }
        else {
            return goals.get(0).achieveGoal();
        }
    }
}
