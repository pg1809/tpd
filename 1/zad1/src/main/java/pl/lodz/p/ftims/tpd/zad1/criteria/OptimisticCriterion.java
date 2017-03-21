package pl.lodz.p.ftims.tpd.zad1.criteria;

import java.util.Arrays;

public class OptimisticCriterion implements Criterion {

    @Override
    public int selectDecision(double[][] problemMatrix) {
        double[] bestNatureStates = Arrays.stream(problemMatrix)
                .mapToDouble(decision -> Arrays.stream(decision).max().getAsDouble())
                .toArray();

        return ArraysFunctions.indexOfMaxElement(bestNatureStates);
    }

    @Override
    public String toString() {
        return "Kryterium optymistyczne";
    }
}
