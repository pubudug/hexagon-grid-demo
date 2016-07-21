package io.github.pubudug.hexgrid.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -6069849266154183365L;

    private Image dbImage;

    private Graphics dbg;
    private int width;
    private int height;

    private Stats stats;

    public GamePanel(int width, int height, Stats stats) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        this.stats = stats;
    }

    void update() {

    }

    void render() {
        if (dbImage == null) {
            this.dbImage = createImage(width, height);
            if (dbImage == null) {
                System.out.println("dbImage is  null");
                return;
            } else {
                this.dbg = dbImage.getGraphics();
            }
        }

        // clear the background
        dbg.setColor(Color.red);
        dbg.fillRect(0, 0, width, height);

        dbg.setColor(Color.blue);
        Font font = new Font("SansSerif", Font.BOLD, 12);
        dbg.setFont(font);

        dbg.drawString("Average FPS/UPS: " + stats.getAverageFPS() + ", " + stats.getAverageUPS(), 20, 25);
        dbg.drawString("Frame Count/Loss: " + stats.getFrameCount() + " / " + stats.getTotalFramesSkipped(), 20, 40);
    }

    void paintScreen() {
        // use active rendering to put the buffered image on-screen
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null))
                g.drawImage(dbImage, 0, 0, null);
            g.dispose();
        } catch (Exception e) {
            System.out.println("Graphics context error: " + e);
        }
    }

}
