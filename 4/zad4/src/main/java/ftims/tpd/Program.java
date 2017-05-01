package ftims.tpd;

import ftims.tpd.problem.Action;
import ftims.tpd.problem.Problem;
import ftims.tpd.problem.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int eventsNum = scanner.nextInt();
        int actionsNum = scanner.nextInt();

        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < actionsNum; i++) {
            int startingEvent = scanner.nextInt();
            int finalEvent = scanner.nextInt();
            int time = scanner.nextInt();
            actions.add(new Action(startingEvent, finalEvent, time));
        }

        int[][] process = new int[eventsNum][eventsNum];
        for (Action action: actions) {
            int i = action.startingEvent - 1;
            int j = action.finalEvent - 1;
            process[i][j] = action.time;
        }

        Solution problemSolution = new Problem(process, actions).solution();

        System.out.println("Minimal time to complete: " + problemSolution.minTotalTime);
        System.out.print("Critical path: ");
        for (int event : problemSolution.criticalPath) {
            System.out.print(event + " ");
        }
        System.out.println("\n");
        for (int i = 0; i < actionsNum; i++) {
            Action action = actions.get(i);
            int totalReserve = problemSolution.totalReserves.get(i);
            int conditionalReserve = problemSolution.conditionalReserves.get(i);
            int freeReserve = problemSolution.freeReserves.get(i);
            int independentReserve = problemSolution.independentReserves.get(i);

            System.out.println("Time reserves for action: " + action.startingEvent + " -> " + action.finalEvent);
            System.out.println("Total reserve: " + totalReserve);
            System.out.println("Conditional reserve: " + conditionalReserve);
            System.out.println("Free reserve: " + freeReserve);
            System.out.println("Independent reserve: " + independentReserve);
            System.out.println();
        }
    }
}
