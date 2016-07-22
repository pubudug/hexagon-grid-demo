package io.github.pubudug.hexgrid.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DemoStats extends Stats {

    DemoStats(long period) {
        super(period);
    }

    void draw(Graphics dbg) {
        dbg.setColor(Color.blue);
        Font font = new Font("SansSerif", Font.BOLD, 12);
        dbg.setFont(font);
        dbg.drawString("Average FPS/UPS: " + getAverageFPS() + ", " + getAverageUPS(), 20, 25);
        dbg.drawString("Frame Count/Loss: " + getFrameCount() + " / " + getTotalFramesSkipped(), 20, 40);

    }

}
