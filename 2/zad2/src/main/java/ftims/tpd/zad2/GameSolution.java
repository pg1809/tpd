package ftims.tpd.zad2;

/**
 * Created by grzelak on 23-3-17.
 */
public class GameSolution {

    public final double gameValue;

    public final double[] firstPlayerStrategyFrequencies;

    public final double[] secondPlayerStrategyFrequencies;

    public GameSolution(double gameValue, double[] firstPlayerStrategyFrequencies,
            double[] secondPlayerStrategyFrequencies) {
        this.gameValue = gameValue;
        this.firstPlayerStrategyFrequencies = firstPlayerStrategyFrequencies;
        this.secondPlayerStrategyFrequencies = secondPlayerStrategyFrequencies;
    }
}
