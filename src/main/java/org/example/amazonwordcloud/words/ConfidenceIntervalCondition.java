package org.example.amazonwordcloud.words;

public class ConfidenceIntervalCondition implements BreakCondition {
    private final double zScore;
    private final double requiredIntervalDistance;
    private final int verifyAfterWordCount;
    private int maxFrequency = 1;

    public ConfidenceIntervalCondition(
            double zScore,
            double requiredIntervalDistance,
            int verifyAfterWordCount
    ) {
        this.zScore = zScore;
        this.requiredIntervalDistance = requiredIntervalDistance;
        this.verifyAfterWordCount = verifyAfterWordCount;
    }

    @Override
    public boolean isMet(int frequency, int start, int end, int length) {
        if (frequency > maxFrequency) maxFrequency = frequency;
        if (end % verifyAfterWordCount != 0) return false;

        double p = (double) maxFrequency / end;
        double distance = 2 * zScore * Math.sqrt(p*(1-p)/end);
        return distance <= requiredIntervalDistance;
    }
}
