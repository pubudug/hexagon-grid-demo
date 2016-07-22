package io.github.pubudug.hexgrid.demo;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {
        int frameRate = 60;
        int period = 1000000000 / frameRate;
        DemoStats stats = new DemoStats(period);
        DemoHexagonGrid grid = new DemoHexagonGrid(new DemoHexagonFactory(), 10, 10, 30);
        GamePanel gamePanel = new GamePanel(500, 400, stats, grid);
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
