package pl.lodz.p.ftims.tpd.zad1;

import pl.lodz.p.ftims.tpd.zad1.criteria.*;

import java.util.*;

public class Program {

    private static final double DEFAULT_CAUTION_COEFF = 0.5;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.getDefault());

        int decisionsNum = scanner.nextInt();
        int natureStatesNum = scanner.nextInt();

        double[][] problemMatrix = new double[decisionsNum][natureStatesNum];
        for (int i = 0; i < problemMatrix.length; i++) {
            for (int j = 0; j < problemMatrix[0].length; j++) {
                problemMatrix[i][j] = scanner.nextDouble();
            }
        }

        String criterionName = scanner.next();

        Criterion criterion = null;
        switch (criterionName) {
        case "wald":
            criterion = new WaldCriterion();
            break;
        case "optimistic":
            criterion = new OptimisticCriterion();
            break;
        case "hurwicz":
            double cautionCoefficient;
            if (scanner.hasNext()) {
                cautionCoefficient = scanner.nextDouble();
            } else {
                cautionCoefficient = DEFAULT_CAUTION_COEFF;
            }
            criterion = new HurwiczCriterion(cautionCoefficient);
            break;
        case "bayes":
            double[] natureStatesProbabilities = new double[natureStatesNum];
            if (scanner.hasNext()) {
                for (int i = 0; i < natureStatesNum; i++) {
                    natureStatesProbabilities[i] = scanner.nextDouble();
                }
            } else {
                Arrays.fill(natureStatesProbabilities, 1.0 / natureStatesNum);
            }
            criterion = new BayesCriterion(natureStatesProbabilities);
            break;
        case "savage":
            criterion = new SavageCriterion();
            break;
        default:
            System.err.println("Unknown criterion name. Exiting with error");
            System.exit(1);
        }

        int decisionIdx = criterion.selectDecision(problemMatrix);
        System.out.println(criterion + "\nNr decyzji: " + (decisionIdx + 1) + "\n");
    }
}
