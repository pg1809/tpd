package pl.lodz.p.ftims.tpd.zad1.criteria;

import java.util.Arrays;

public class WaldCriterion implements Criterion {

    @Override
    public int selectDecision(double[][] problemMatrix) {
        double[] worstNatureStates =  Arrays.stream(problemMatrix)
                .mapToDouble(decision -> Arrays.stream(decision).min().getAsDouble())
                .toArray();

        return ArraysFunctions.indexOfMaxElement(worstNatureStates);
    }

    @Override
    public String toString() {
        return "Kryterium Walda";
    }
}
