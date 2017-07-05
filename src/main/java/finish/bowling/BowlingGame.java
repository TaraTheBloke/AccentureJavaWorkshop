package finish.bowling;

public class BowlingGame {

    public static int score(int... pins) {
        int score = 0;
        int frameIndex = 0;
        for (int frameCount = 0; frameCount < 10; frameCount++) {
            score += frameScore(frameIndex, pins);
            frameIndex += frameSize(pins, frameIndex);
        }
        return score;
    }

    private static int frameSize(int[] pins, int frameIndex) {
        return pins[frameIndex] == 10 ? 1 : 2;
    }

    private static int frameScore(int frameIndex, int[] pins) {
        int frameScore = pins[frameIndex] + pins[frameIndex + 1];
        if (frameScore >= 10) {
            frameScore += pins[frameIndex + 2];
        }
        return frameScore;
    }
}