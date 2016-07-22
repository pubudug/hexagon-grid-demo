package io.github.pubudug.hexgrid.demo;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {
        int frameRate = 60;
        int period = 1000000000 / frameRate;
        DemoStats stats = new DemoStats(period);
        int size = 40;
        int columns = 10;
        int rows = 10;
        DemoHexagonGrid grid = new DemoHexagonGrid(new DemoHexagonFactory(), columns, rows, size);
        GamePanel gamePanel = new GamePanel((int) grid.getHexagon(columns - 1, 0).getCenter().getX(),
                (int) grid.getHexagon(0, rows - 1).getCenter().getY(), stats, grid);
        GameLoop loop = new GameLoop(period, stats) {

            @Override
            protected void update() {
                gamePanel.update();
            }

            @Override
            protected void render() {
                gamePanel.render();
            }

            @Override
            protected void paintScreen() {
                gamePanel.paintScreen();
            }
        };

        JFrame frame = new JFrame("Hexagon grid demo");
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread animator = new Thread(loop);
        animator.start();
    }
}
