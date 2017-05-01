package ftims.tpd.problem;

public class Action {

    public final int startingEvent;

    public final int finalEvent;

    public final int time;

    public Action(int startingEvent, int finalEvent, int time) {
        this.startingEvent = startingEvent;
        this.finalEvent = finalEvent;
        this.time = time;
    }
}
