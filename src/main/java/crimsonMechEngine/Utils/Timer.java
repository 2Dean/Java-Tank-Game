package crimsonMechEngine.Utils;

public class Timer {

    private double lastLoopTime;

    public void init() {
        lastLoopTime = getTime();
    }


    // 1000_000_000.0
    public static double getTime() {
        return System.nanoTime() / 1000_000_000.0;
    }

    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    public double getLastLoopTime() {
        return lastLoopTime;
    }
}