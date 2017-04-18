package ftims.tpd.zad3;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int farmsNum = scanner.nextInt();
        int[][] farmsGraph = new int[farmsNum][farmsNum];

        for (int i = 0; i < farmsNum; i++) {
            for (int j = 0; j < farmsNum; j++) {
                farmsGraph[i][j] = scanner.nextInt();
            }
        }

        int startingPoint = scanner.nextInt();
        Problem problem = new Problem();

        Path[] shortestPaths = problem.solve(farmsGraph, startingPoint - 1);

        for (int i = 0; i < shortestPaths.length; i++) {
            if (i + 1 == startingPoint) {
                continue;
            }
            System.out.println("Path from point nr: " + startingPoint + " to point nr: " + (i + 1) + ": " + shortestPaths[i]);
        }
    }
}
