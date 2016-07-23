package io.github.pubudug.hexgrid.demo;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collection;
import java.util.Optional;

import io.github.pubudug.hexgrid.HexagonFactory;
import io.github.pubudug.hexgrid.HexagonGrid;
import lombok.Setter;

@Setter
public class DemoHexagonGrid extends HexagonGrid<DemoHexagon> {

    private Optional<DemoHexagon> mouseOverHexagon;

    private volatile boolean drawCubeCoordinates;

    private volatile boolean drawOffsetCoordinates;

    private volatile boolean drawTerrainType;

    DemoHexagonGrid(HexagonFactory<DemoHexagon> hexagonFactory, int columns, int rows, int size) {
        super(hexagonFactory, columns, rows, size);
        mouseOverHexagon = Optional.empty();
    }

    void draw(Graphics2D dbg) {
        Collection<DemoHexagon> hexagons = getHexagons();
        for (DemoHexagon demoHexagon : hexagons) {
            demoHexagon.draw(dbg);
            if (drawCubeCoordinates) {
                demoHexagon.drawCubeCoordinates(dbg);
            }
            if (drawOffsetCoordinates) {
                demoHexagon.drawOffsetCoordinates(dbg);
            }
            if(drawTerrainType){
                demoHexagon.drawTerrainType(dbg);
            }
        }
    }

    void mouseOverHexagon(Point point) {
        DemoHexagon hexagon = getHexagonContainingPixel(point.x, point.y);
        this.mouseOverHexagon = Optional.ofNullable(hexagon);
    }

    public void drawMouseOverDetails(Graphics2D dbg) {
    }

}
