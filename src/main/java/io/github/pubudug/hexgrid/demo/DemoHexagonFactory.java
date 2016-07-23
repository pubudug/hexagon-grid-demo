package io.github.pubudug.hexgrid.demo;

import io.github.pubudug.hexgrid.Coordinate;
import io.github.pubudug.hexgrid.HexagonFactory;
import io.github.pubudug.hexgrid.TerrainMap;

public class DemoHexagonFactory implements HexagonFactory<DemoHexagon> {

    private TerrainMap map;

    public DemoHexagonFactory(TerrainMap map) {
        this.map = map;
    }

    @Override
    public DemoHexagon[][] createArray(int columns, int rows) {
        return new DemoHexagon[columns][rows];
    }

    @Override
    public DemoHexagon createHexagon(Coordinate c, int size) {
        return new DemoHexagon(c, size, map.getTerrain(c.getOffsetCoordinateColumn(), c.getOffsetCoordinateRow()));
    }

}
