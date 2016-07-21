package io.github.pubudug.hexgrid.demo;

public abstract class GameLoop implements Runnable {

    private static final int NO_DELAYS_PER_YIELD = 16;
    private static int MAX_FRAME_SKIPS = 5;

    private long gameStartTime;
    private boolean running;
    private int period;

    private Stats stats;

    public GameLoop(int period, Stats stats) {
        this.period = period;
        this.stats = stats;
    }

    @Override
    public void run()
    /* The frames of the animation are drawn inside the while loop. */
    {
        long beforeTime, afterTime, timeDiff, sleepTime;
        long overSleepTime = 0L;
        int noDelays = 0;
        long excess = 0L;

        this.gameStartTime = System.nanoTime();
        stats.start(this.gameStartTime);
        beforeTime = gameStartTime;

        this.running = true;

        while (running) {
            update();
            render();
            paintScreen();

            afterTime = System.nanoTime();
            timeDiff = afterTime - beforeTime;
            sleepTime = (period - timeDiff) - overSleepTime;

            if (sleepTime > 0) { // some time left in this cycle
                try {
                    Thread.sleep(sleepTime / 1000000L); // nano -> ms
                } catch (InterruptedException ex) {
                }
                overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
            } else { // sleepTime <= 0; the frame took longer than the period
                excess -= sleepTime; // store excess time value
                overSleepTime = 0L;

                if (++noDelays >= NO_DELAYS_PER_YIELD) {
                    Thread.yield(); // give another thread a chance to run
                    noDelays = 0;
                }
            }

            beforeTime = System.nanoTime();

            /*
             * If frame animation is taking too long, update the game state
             * without rendering it, to get the updates/sec nearer to the
             * required FPS.
             */
            int skips = 0;
            while ((excess > period) && (skips < MAX_FRAME_SKIPS)) {
                excess -= period;
                update(); // update state but don't render
                skips++;
            }
            stats.frameEnd(skips);
        }

        System.exit(0); // so window disappears
    }

    abstract protected void update();

    abstract protected void render();

    abstract protected void paintScreen();

}
