package pl.lodz.p.ftims.tpd.zad1;

import pl.lodz.p.ftims.tpd.zad1.criteria.*;

import java.util.*;

public class Program {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.err.println("Criterion name unspecified. Exiting with error");
            System.exit(1);
        }

        String criterionArg = args[0];

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

        Criterion criterion = null;
        switch (criterionArg) {
        case "wald":
            criterion = new WaldCriterion();
            break;
        case "optimistic":
            criterion = new OptimisticCriterion();
            break;
        case "hurwicz":
            double cautionCoefficient = scanner.nextDouble();
            criterion = new HurwiczCriterion(cautionCoefficient);
            break;
        case "bayes":
            double[] natureStatesProbabilities = new double[natureStatesNum];
            for (int i = 0; i < natureStatesNum; i++) {
                natureStatesProbabilities[i] = scanner.nextDouble();
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
