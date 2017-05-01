package ftims.tpd.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Problem {

    private final int[][] process;

    private final List<Action> actions;

    public Problem(int[][] process, List<Action> actions) {
        this.process = process;
        this.actions = actions;
    }

    public Solution solution() {

        int[] eventsEarliestOccurrences = calculateEventsEarliestOccurrences();

        int lastStepEarliestOccurrence = eventsEarliestOccurrences[process.length - 1];
        int[] eventsLatestOccurrences = calculateEventsLatestOccurrences(lastStepEarliestOccurrence);

        List<Integer> criticalPath = retrieveCriticalPath(eventsEarliestOccurrences, eventsLatestOccurrences);

        int minTotalTime = eventsEarliestOccurrences[process.length - 1];

        List<Integer> totalReserves = actions.stream()
                .map(action -> totalReserveForAction(action, eventsEarliestOccurrences, eventsLatestOccurrences))
                .collect(Collectors.toList());

        List<Integer> freeReserves = actions.stream()
                .map(action -> freeReserveForAction(action, eventsEarliestOccurrences))
                .collect(Collectors.toList());

        List<Integer> conditionalReserves = actions.stream()
                .map(action -> conditionalReserveForAction(action, eventsLatestOccurrences))
                .collect(Collectors.toList());

        List<Integer> independentReserves = actions.stream()
                .map(action -> independentReserveForAction(action, eventsEarliestOccurrences, eventsLatestOccurrences))
                .collect(Collectors.toList());

        return new Solution(minTotalTime, criticalPath,
                totalReserves,
                conditionalReserves,
                independentReserves,
                freeReserves);
    }

    private int[] calculateEventsLatestOccurrences(int finalEventEarliestOccurrence) {
        int[] latestOccurrences = new int[process.length];
        int lastEventIdx = process.length - 1;
        latestOccurrences[lastEventIdx] = finalEventEarliestOccurrence;
        for (int i = lastEventIdx - 1; i >= 0 ; i--) {
            int latestOccurrence = Integer.MAX_VALUE;
            for (int j = 0; j < process.length; j++) {
                if (process[i][j] != 0) {
                    latestOccurrence = Math.min(latestOccurrence, latestOccurrences[j] - process[i][j]);
                }
            }
            latestOccurrences[i] = latestOccurrence;
        }
        return latestOccurrences;
    }

    private int[] calculateEventsEarliestOccurrences() {
        int[] earliestOccurrences = new int[process.length];
        for (int i = 0; i < process.length; i++) {
            int earliestOccurrence = 0;
            for (int j = 0; j < process.length; j++) {
                if (process[j][i] != 0) {
                    earliestOccurrence = Math.max(earliestOccurrence, earliestOccurrences[j] + process[j][i]);
                }
            }
            earliestOccurrences[i] = earliestOccurrence;
        }

        return earliestOccurrences;
    }

    private List<Integer> retrieveCriticalPath(int[] t0, int[] t1) {
        List<Integer> criticalPath = new ArrayList<>();
        for (int i = 0; i < t0.length; i++) {
            if (t0[i] == t1[i]) {
                criticalPath.add(i + 1);
            }
        }
        return criticalPath;
    }

    private int independentReserveForAction(Action action, int[] earliestOccurrences, int[] latestOccurrences) {
        int i = action.startingEvent - 1;
        int j = action.finalEvent - 1;
        return earliestOccurrences[j] - latestOccurrences[i] - process[i][j];
    }

    private int conditionalReserveForAction(Action action, int[] latestOccurrences) {
        int i = action.startingEvent - 1;
        int j = action.finalEvent - 1;
        return latestOccurrences[j] - latestOccurrences[i] - process[i][j];
    }

    private int totalReserveForAction(Action action, int[] earliestOccurrences, int[] latestOccurrences) {
        int i = action.startingEvent - 1;
        int j = action.finalEvent - 1;
        return latestOccurrences[j] - earliestOccurrences[i] - process[i][j];
    }

    private int freeReserveForAction(Action action, int[] earliestOccurrences) {
        int i = action.startingEvent - 1;
        int j = action.finalEvent - 1;
        return earliestOccurrences[j] - earliestOccurrences[i] - process[i][j];
    }
}
