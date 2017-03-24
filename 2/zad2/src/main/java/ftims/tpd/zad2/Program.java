package ftims.tpd.zad2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by grzelak on 23-3-17.
 */
public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int firstPlayerStrategiesNum = scanner.nextInt();
        int secondPlayerStrategiesNum = scanner.nextInt();

        double[][] payOffMatrix = new double[firstPlayerStrategiesNum][secondPlayerStrategiesNum];
        for (int i = 0; i < firstPlayerStrategiesNum; i++) {
            for (int j = 0; j < secondPlayerStrategiesNum; j++) {
                payOffMatrix[i][j] = scanner.nextDouble();
            }
        }

        Game game = new Game(payOffMatrix);
        GameSolution solution = game.getSolution();
        System.out.println("Game value: " + solution.gameValue);
        System.out.println("First player strategies' frequencies: " + Arrays.toString(solution.firstPlayerStrategyFrequencies));
        System.out.println("Second player strategies' frequencies: " + Arrays.toString(solution.secondPlayerStrategyFrequencies));
    }
}
