package ftims.tpd.zad2;

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
