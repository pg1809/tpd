package pl.lodz.p.ftims.tpd.zad1.criteria;

import java.util.Arrays;

public class HurwiczCriterion implements Criterion {

    private final double cautionCoefficient;

    public HurwiczCriterion(double cautionCoefficient) {
        this.cautionCoefficient = cautionCoefficient;
    }

    @Override
    public int selectDecision(double[][] problemMatrix) {
        double[] averageWins = Arrays.stream(problemMatrix).mapToDouble(decision ->
                cautionCoefficient * worstStateOfNature(decision)
                        + (1 - cautionCoefficient) * bestStateOfNature(decision)).toArray();

        return ArraysFunctions.indexOfMaxElement(averageWins);
    }

    @Override
    public String toString() {
        return "Kryterium Hurwicza, współczynnik ostrożności: " + cautionCoefficient;
    }

    private double worstStateOfNature(double[] statesPerDecision) {
        return Arrays.stream(statesPerDecision).min().getAsDouble();
    }

    private double bestStateOfNature(double[] statesPerDecision) {
        return Arrays.stream(statesPerDecision).max().getAsDouble();
    }
}
