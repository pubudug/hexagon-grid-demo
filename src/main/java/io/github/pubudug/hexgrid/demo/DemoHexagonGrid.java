package io.github.pubudug.hexgrid.demo;

import java.awt.Graphics;
import java.util.Collection;

import io.github.pubudug.hexgrid.HexagonGrid;
import io.github.pubudug.hexgrid.HexagonFactory;

public class DemoHexagonGrid extends HexagonGrid<DemoHexagon> {

    DemoHexagonGrid(HexagonFactory<DemoHexagon> hexagonFactory, int columns, int rows, int size) {
        super(hexagonFactory, columns, rows, size);
    }

    void draw(Graphics dbg) {
        Collection<DemoHexagon> hexagons = getHexagons();
        for (DemoHexagon demoHexagon : hexagons) {
            demoHexagon.draw(dbg);
        }
    }

}
