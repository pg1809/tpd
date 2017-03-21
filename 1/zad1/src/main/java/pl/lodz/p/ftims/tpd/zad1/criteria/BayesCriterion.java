package pl.lodz.p.ftims.tpd.zad1.criteria;

import java.util.Arrays;

public class BayesCriterion implements Criterion {

    private final double[] natureStatesProbabilities;

    public BayesCriterion(double[] natureStatesProbabilities) {
        this.natureStatesProbabilities = Arrays.copyOf(natureStatesProbabilities, natureStatesProbabilities.length);
    }

    @Override
    public int selectDecision(double[][] problemMatrix) {
        double[] averageWins = Arrays.stream(problemMatrix).mapToDouble(this::averageWinForDecision).toArray();
        return ArraysFunctions.indexOfMaxElement(averageWins);
    }

    @Override
    public String toString() {
        return "Kryterium Bayesa, p-stwa stanów natury: " + Arrays.toString(natureStatesProbabilities);
    }

    private double averageWinForDecision(double[] decision) {
        double averageWin = 0;
        for (int i = 0; i < decision.length; i++) {
            averageWin += natureStatesProbabilities[i] * decision[i];
        }

        return averageWin;
    }
}
