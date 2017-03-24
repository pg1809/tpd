package ftims.tpd.zad2;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by grzelak on 23-3-17.
 */
public class Game {

    private final int firstPlayerStrategiesNum;

    private final int secondPlayerStrategiesNum;

    private final double[][] payOffMatrix;

    public Game(double[][] payOffMatrix) {
        this.payOffMatrix = payOffMatrix;
        this.firstPlayerStrategiesNum = payOffMatrix.length;
        this.secondPlayerStrategiesNum = payOffMatrix[0].length;
    }

    public GameSolution getSolution() {
        double smallestPayoffAbs = findSmallestPayoffAbs(payOffMatrix);
        double[][] nonNegativePayOffs = createNonNegativePayOffMatrix(payOffMatrix, smallestPayoffAbs);

        double[] firstPlayerLpSolution = solveLpProblemForFirstPlayer(nonNegativePayOffs);

        double[] secondPlayerLpSolution = solveLpProblemForSecondPlayer(nonNegativePayOffs);

        double v = 1 / Arrays.stream(firstPlayerLpSolution).sum();

        double gameValue = v - smallestPayoffAbs;
        double[] firstPlayerStrategiesFrequencies = Arrays.stream(firstPlayerLpSolution).map(f -> f * v).toArray();
        double[] secondPlayerStrategiesFrequencies = Arrays.stream(secondPlayerLpSolution).map(f -> f * v).toArray();
        return new GameSolution(gameValue, firstPlayerStrategiesFrequencies, secondPlayerStrategiesFrequencies);
    }

    private double[] solveLpProblemForFirstPlayer(double[][] payOffs) {
        LinearObjectiveFunction firstPlayerObjectiveFunction = createObjectiveFunction(firstPlayerStrategiesNum);

        LinearConstraintSet firstPlayerConstraintSet = createConstraintsForFirstPlayer(payOffs);

        SimplexSolver firstPlayerSimplexSolver = new SimplexSolver();
        PointValuePair firstPlayerSolution = firstPlayerSimplexSolver.optimize(firstPlayerObjectiveFunction, firstPlayerConstraintSet,
                GoalType.MINIMIZE);

        return firstPlayerSolution.getPoint();
    }

    private double[] solveLpProblemForSecondPlayer(double[][] payOffs) {
        LinearObjectiveFunction secondPlayerObjectiveFunction = createObjectiveFunction(secondPlayerStrategiesNum);

        LinearConstraintSet secondPlayerConstraintSet = createConstraintsForSecondPlayer(payOffs);

        SimplexSolver secondPlayerSolver = new SimplexSolver();
        PointValuePair secondPlayerSolution = secondPlayerSolver.optimize(secondPlayerObjectiveFunction, secondPlayerConstraintSet,
                GoalType.MAXIMIZE);

        return secondPlayerSolution.getPoint();
    }

    private LinearObjectiveFunction createObjectiveFunction(int numberOfVariables) {
        double[] objectiveFunctionCoefficients = new double[numberOfVariables];
        for (int i = 0; i < numberOfVariables; i++) {
            objectiveFunctionCoefficients[i] = 1;
        }
        return new LinearObjectiveFunction(objectiveFunctionCoefficients, 0);
    }

    private LinearConstraintSet createConstraintsForFirstPlayer(double[][] payOffs) {
        List<LinearConstraint> firstPlayerConstraints = new ArrayList<>();
        for (int i = 0; i < secondPlayerStrategiesNum; i++) {
            double[] lhs = new double[firstPlayerStrategiesNum];
            for (int j = 0; j < lhs.length; j++) {
                lhs[j] = payOffs[j][i];
            }

            firstPlayerConstraints.add(new LinearConstraint(lhs, Relationship.GEQ, 1));
        }

        firstPlayerConstraints.addAll(createNonNegativeConstraintsForEachVariable(firstPlayerStrategiesNum));
        return new LinearConstraintSet(firstPlayerConstraints);
    }

    private LinearConstraintSet createConstraintsForSecondPlayer(double[][] payOffs) {
        List<LinearConstraint> secondPlayerConstraints = new ArrayList<>();
        for (int i = 0; i < firstPlayerStrategiesNum; i++) {
            double[] lhs = Arrays.copyOf(payOffs[i], secondPlayerStrategiesNum);
            secondPlayerConstraints.add(new LinearConstraint(lhs, Relationship.LEQ, 1));
        }
        secondPlayerConstraints.addAll(createNonNegativeConstraintsForEachVariable(secondPlayerStrategiesNum));
        return new LinearConstraintSet(secondPlayerConstraints);
    }

    private List<LinearConstraint> createNonNegativeConstraintsForEachVariable(int variablesNum) {
        List<LinearConstraint> constraints = new ArrayList<>();
        for (int i = 0; i < variablesNum; i++) {
            double[] lhs = new double[firstPlayerStrategiesNum];
            lhs[i] = 1;

            constraints.add(new LinearConstraint(lhs, Relationship.GEQ, 0));
        }
        return constraints;
    }

    private double[][] createNonNegativePayOffMatrix(double[][] payOffMatrix, double smallestPayoffAbs) {
        return Arrays.stream(payOffMatrix)
                .map(row -> Arrays.stream(row).map(payOff -> payOff + smallestPayoffAbs).toArray())
                .toArray(double[][]::new);
    }

    private double findSmallestPayoffAbs(double[][] payOffMatrix) {
        return Math.abs(Arrays.stream(payOffMatrix)
                .flatMapToDouble(Arrays::stream).min().getAsDouble());
    }
}
