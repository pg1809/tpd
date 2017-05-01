package ftims.tpd.problem;

import java.util.List;

public class Solution {

    public final int minTotalTime;

    public final List<Integer> criticalPath;

    public final List<Integer> totalReserves;

    public final List<Integer> conditionalReserves;

    public final List<Integer> independentReserves;

    public final List<Integer> freeReserves;

    public Solution(int minTotalTime,
                    List<Integer> criticalPath, List<Integer> totalReserves,
                    List<Integer> conditionalReserves,
                    List<Integer> independentReserves,
                    List<Integer> freeReserves) {
        this.minTotalTime = minTotalTime;
        this.criticalPath = criticalPath;
        this.totalReserves = totalReserves;
        this.conditionalReserves = conditionalReserves;
        this.independentReserves = independentReserves;
        this.freeReserves = freeReserves;
    }
}
