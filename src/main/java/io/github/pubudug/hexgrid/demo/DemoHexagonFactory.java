package io.github.pubudug.hexgrid.demo;

import io.github.pubudug.hexgrid.Coordinate;
import io.github.pubudug.hexgrid.HexagonFactory;

public class DemoHexagonFactory implements HexagonFactory<DemoHexagon> {

    @Override
    public DemoHexagon[][] createArray(int columns, int rows) {
        return new DemoHexagon[columns][rows];
    }

    @Override
    public DemoHexagon createHexagon(Coordinate c, int size) {
        return new DemoHexagon(c, size);
    }

}
