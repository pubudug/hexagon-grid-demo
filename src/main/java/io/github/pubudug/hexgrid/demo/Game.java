package io.github.pubudug.hexgrid.demo;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import io.github.pubudug.hexgrid.CoordinateGrid;
import io.github.pubudug.hexgrid.HardCodedTerrainMapReader;
import io.github.pubudug.hexgrid.TerrainMap;

public class Game {
    public static void main(String[] args) {
        int frameRate = 60;
        int period = 1000000000 / frameRate;
        DemoStats stats = new DemoStats(period);
        int size = 40;

        TerrainMap map = new TerrainMap(new HardCodedTerrainMapReader());
        DemoCoordinateFactory coordinateFactory = new DemoCoordinateFactory(map);
        CoordinateGrid<DemoCoordinate> cg = new CoordinateGrid<DemoCoordinate>(map.getColumns(), map.getRows(),
                coordinateFactory);
        DemoHexagonFactory hexagonFactory = new DemoHexagonFactory(size);
        DemoHexagonGrid grid = new DemoHexagonGrid(cg, hexagonFactory, size);

        DemoHexagon hex = hexagonFactory.create(cg.getCoordinate(1, 1));
        Unit unit = new Unit((int) hex.getCenter().getX(), (int) hex.getCenter().getY(), hexagonFactory);
        cg.getCoordinate(1, 1).setUnit(unit);

        GamePanel gamePanel = new GamePanel(
                (int) hexagonFactory.create(cg.getCoordinate(map.getColumns() - 1, 0)).getCenter().getX(),
                (int) hexagonFactory.create(cg.getCoordinate(0, map.getRows() - 1)).getCenter().getY(), stats, grid, cg,
                unit);
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
        ControlPanel controlPanel = new ControlPanel(grid);

        JFrame frame = new JFrame("Hexagon grid demo");
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread animator = new Thread(loop);
        animator.start();
    }
}
