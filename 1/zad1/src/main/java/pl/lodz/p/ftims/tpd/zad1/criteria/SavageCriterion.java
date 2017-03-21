package pl.lodz.p.ftims.tpd.zad1.criteria;

import java.util.Arrays;

public class SavageCriterion implements Criterion {
    @Override
    public int selectDecision(double[][] problemMatrix) {

        double[][] relativeLossMatrix = relativeLossMatrix(problemMatrix);
        double[] maxLossPerDecision = Arrays.stream(relativeLossMatrix)
                .mapToDouble(decision -> Arrays.stream(decision).max().getAsDouble()).toArray();

        return ArraysFunctions.indexOfMinElement(maxLossPerDecision);
    }

    @Override
    public String toString() {
        return "Kryterium Savage'a";
    }

    private double[][] relativeLossMatrix(double[][] problemMatrix) {
        double[] maxReturnsPerNatureState = maxReturnsPerNatureState(problemMatrix);
        double[][] relativeLossMatrix = new double[problemMatrix.length][problemMatrix[0].length];
        for (int i = 0; i < problemMatrix.length; i++) {
            for (int j = 0; j < problemMatrix[i].length; j++) {
                relativeLossMatrix[i][j] = maxReturnsPerNatureState[j] - problemMatrix[i][j];
            }
        }

        return relativeLossMatrix;
    }

    private double[] maxReturnsPerNatureState(double[][] problemMatrix) {
        double[] maxReturnsPerNatureState = new double[problemMatrix[0].length];

        for (int i = 0; i < maxReturnsPerNatureState.length; i++) {
            double max = problemMatrix[0][i];
            for (int j = 1; j < problemMatrix.length; j++) {
                if (max < problemMatrix[j][i]) {
                    max = problemMatrix[j][i];
                }
            }
            maxReturnsPerNatureState[i] = max;
        }

        return maxReturnsPerNatureState;
    }
}
