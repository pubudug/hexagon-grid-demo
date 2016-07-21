package io.github.pubudug.hexgrid.demo;

import java.text.DecimalFormat;

public class Stats {
    private long frameCount;
    private long statsInterval;
    private long period;
    private static long MAX_STATS_INTERVAL = 1000000000L;
    private long prevStatsTime;
    private long totalElapsedTime = 0L;
    private long totalFramesSkipped = 0L;
    private long framesSkipped = 0L;
    private static int NUM_FPS = 10;

    private double fpsStore[];
    private double upsStore[];
    private long statsCount = 0;

    private double averageFPS = 0.0;
    private double averageUPS = 0.0;
    private DecimalFormat df = new DecimalFormat("0.##"); // 2 dp

    Stats(long period) {
        super();
        this.period = period;
        fpsStore = new double[NUM_FPS];
        upsStore = new double[NUM_FPS];
    }

    void start(long gameStartTime) {
        prevStatsTime = gameStartTime;
    }

    private void storeStats()
    /*
     * The statistics: - the summed periods for all the iterations in this
     * interval (period is the amount of time a single frame iteration should
     * take), the actual elapsed time in this interval, the error between these
     * two numbers;
     * 
     * - the total frame count, which is the total number of calls to run();
     * 
     * - the frames skipped in this interval, the total number of frames
     * skipped. A frame skip is a game update without a corresponding render;
     * 
     * - the FPS (frames/sec) and UPS (updates/sec) for this interval, the
     * average FPS & UPS over the last NUM_FPSs intervals.
     * 
     * The data is collected every MAX_STATS_INTERVAL (1 sec).
     */
    {
        this.frameCount++;
        this.statsInterval += period;

        if (statsInterval >= MAX_STATS_INTERVAL) { // record stats every
                                                   // MAX_STATS_INTERVAL
            long timeNow = System.nanoTime();

            // time since last stats collection
            long realElapsedTime = timeNow - prevStatsTime;
            totalElapsedTime += realElapsedTime;

            totalFramesSkipped += framesSkipped;

            double actualFPS = 0; // calculate the latest FPS and UPS
            double actualUPS = 0;
            if (totalElapsedTime > 0) {
                actualFPS = (((double) frameCount / totalElapsedTime) * 1000000000L);
                actualUPS = (((double) (frameCount + totalFramesSkipped) / totalElapsedTime) * 1000000000L);
            }

            // store the latest FPS and UPS
            fpsStore[(int) statsCount % NUM_FPS] = actualFPS;
            upsStore[(int) statsCount % NUM_FPS] = actualUPS;
            statsCount = statsCount + 1;

            double totalFPS = 0.0; // total the stored FPSs and UPSs
            double totalUPS = 0.0;
            for (int i = 0; i < NUM_FPS; i++) {
                totalFPS += fpsStore[i];
                totalUPS += upsStore[i];
            }

            if (statsCount < NUM_FPS) { // obtain the average FPS and UPS
                averageFPS = totalFPS / statsCount;
                averageUPS = totalUPS / statsCount;
            } else {
                averageFPS = totalFPS / NUM_FPS;
                averageUPS = totalUPS / NUM_FPS;
            }
            framesSkipped = 0;
            prevStatsTime = timeNow;
            statsInterval = 0L; // reset
        }
    }

    long getFrameCount() {
        return frameCount;
    }

    long getTotalFramesSkipped() {
        return totalFramesSkipped;
    }

    void frameEnd(int skips) {
        totalFramesSkipped += skips;
        storeStats();
    }

    String getAverageFPS() {
        return df.format(averageFPS);
    }

    String getAverageUPS() {
        return df.format(averageUPS);
    }
}
