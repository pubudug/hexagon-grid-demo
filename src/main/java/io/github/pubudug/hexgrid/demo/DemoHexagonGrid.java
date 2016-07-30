package io.github.pubudug.hexgrid.demo;

import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import io.github.pubudug.hexgrid.CoordinateGrid;
import io.github.pubudug.hexgrid.HexagonFactory;
import io.github.pubudug.hexgrid.HexagonGrid;
import lombok.Setter;

@Setter
public class DemoHexagonGrid extends HexagonGrid<DemoHexagon, DemoCoordinate> {

    private volatile boolean drawCubeCoordinates;

    private volatile boolean drawOffsetCoordinates;

    private volatile boolean drawTerrainType;

    private volatile boolean drawVisibility;

    private Set<DemoCoordinate> visible;

    DemoHexagonGrid(CoordinateGrid<DemoCoordinate> coordinateGrid,
            HexagonFactory<DemoHexagon, DemoCoordinate> hexagonFactory, int size) {
        super(coordinateGrid, hexagonFactory, size);
        visible = new HashSet<>();
    }

    void draw(Graphics2D dbg) {
        Stream<DemoHexagon> hexagons = getHexagons();
        hexagons.forEach(demoHexagon -> {
            demoHexagon.draw(dbg);
            if (drawVisibility && !this.visible.contains(demoHexagon.getCoordinate())) {
                return;
            }
            if (drawCubeCoordinates) {
                demoHexagon.drawCubeCoordinates(dbg);
            }
            if (drawOffsetCoordinates) {
                demoHexagon.drawOffsetCoordinates(dbg);
            }
            if (drawTerrainType) {
                demoHexagon.drawTerrainType(dbg);
            }
        });
    }

}
