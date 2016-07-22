package io.github.pubudug.hexgrid.demo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collection;
import java.util.Optional;

import io.github.pubudug.hexgrid.HexagonFactory;
import io.github.pubudug.hexgrid.HexagonGrid;

public class DemoHexagonGrid extends HexagonGrid<DemoHexagon> {

    private Optional<DemoHexagon> mouseOverHexagon;

    DemoHexagonGrid(HexagonFactory<DemoHexagon> hexagonFactory, int columns, int rows, int size) {
        super(hexagonFactory, columns, rows, size);
        mouseOverHexagon = Optional.empty();
    }

    void draw(Graphics dbg) {
        Collection<DemoHexagon> hexagons = getHexagons();
        for (DemoHexagon demoHexagon : hexagons) {
            demoHexagon.draw(dbg);
        }
    }

    void mouseOverHexagon(Point point) {
        DemoHexagon hexagon = getHexagonContainingPixel(point.x, point.y);
        this.mouseOverHexagon = Optional.ofNullable(hexagon);
    }

    public void drawMouseOverCoordinates(Graphics2D dbg) {
        mouseOverHexagon.ifPresent((hex) -> hex.drawCoordinates(dbg));
    }

}
