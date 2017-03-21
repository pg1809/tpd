package pl.lodz.p.ftims.tpd.zad1;

import pl.lodz.p.ftims.tpd.zad1.criteria.*;

import java.util.*;

public class Program {

    private static final double DEFAULT_CAUTION_COEFFICIENT = 0.5;

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            scanner.useLocale(Locale.getDefault());
            while (scanner.hasNext()) {
                int decisionsNum = scanner.nextInt();
                int natureStatesNum = scanner.nextInt();

                double[][] problemMatrix = new double[decisionsNum][natureStatesNum];
                for (int i = 0; i < problemMatrix.length; i++) {
                    for (int j = 0; j < problemMatrix[0].length; j++) {
                        problemMatrix[i][j] = scanner.nextDouble();
                    }
                }

                String nextToken = scanner.next();
                double cautionCoefficient;
                if ("c".equals(nextToken)) {
                    cautionCoefficient = scanner.nextDouble();
                } else {
                    cautionCoefficient = DEFAULT_CAUTION_COEFFICIENT;
                }

                nextToken = scanner.next();
                double[] natureStatesProbabilities = new double[natureStatesNum];
                if ("p".equals(nextToken)) {
                    for (int i = 0; i < natureStatesNum; i++) {
                        natureStatesProbabilities[i] = scanner.nextDouble();
                    }
                } else {
                    Arrays.fill(natureStatesProbabilities, 1.0 / natureStatesNum);
                }

                List<Criterion> criteria = new ArrayList<>();
                criteria.add(new WaldCriterion());
                criteria.add(new OptimisticCriterion());
                criteria.add(new HurwiczCriterion(cautionCoefficient));
                criteria.add(new BayesCriterion(natureStatesProbabilities));
                criteria.add(new SavageCriterion());

                for (Criterion criterion : criteria) {
                    int decisionIdx = criterion.selectDecision(problemMatrix);
                    System.out.println(criterion + "\nNr decyzji: " + (decisionIdx + 1) + "\n");
                }
            }
        }
    }
}
