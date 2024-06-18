package org.example.amazonwordcloud.words;

public class ProbabilisticBreakCondition implements BreakCondition {
    private final double zScore;
    private final double requiredDistance;
    private final int verifyAfterWordAmount;

    public ProbabilisticBreakCondition(double zScore, double requiredDistance, int verifyAfterWordAmount) {
        this.zScore = zScore;
        this.requiredDistance = requiredDistance;
        this.verifyAfterWordAmount = verifyAfterWordAmount;
    }

    @Override
    public boolean isMet(int frequency, int start, int end, int length) {
        if (end % verifyAfterWordAmount != 0) return false;

        double p = (double) frequency / end;
        double distance = 2 * zScore * Math.sqrt(p*(1-p)/end);
        return distance <= requiredDistance;
    }
}
