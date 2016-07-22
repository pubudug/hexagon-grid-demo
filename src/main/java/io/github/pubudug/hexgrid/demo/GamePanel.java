package io.github.pubudug.hexgrid.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -6069849266154183365L;

    private Image dbImage;

    private Graphics2D dbg;
    private int width;
    private int height;

    private DemoStats stats;

    private DemoHexagonGrid grid;

    public GamePanel(int width, int height, DemoStats stats, DemoHexagonGrid grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
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
                this.dbg = (Graphics2D) dbImage.getGraphics();
                dbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
        }

        // clear the background
        dbg.setColor(Color.white);
        dbg.fillRect(0, 0, width, height);

        stats.draw(dbg);

        grid.draw(dbg);

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
