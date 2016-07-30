package io.github.pubudug.hexgrid.demo;

import io.github.pubudug.hexgrid.Coordinate;
import io.github.pubudug.hexgrid.CoordinateFactory;
import io.github.pubudug.hexgrid.TerrainMap;

public class DemoCoordinateFactory implements CoordinateFactory<DemoCoordinate> {

    private TerrainMap map;

    public DemoCoordinateFactory(TerrainMap map) {
        this.map = map;
    }

    @Override
    public DemoCoordinate[][] createArray(int columns, int rows) {
        return new DemoCoordinate[columns][rows];
    }

    @Override
    public DemoCoordinate create(Coordinate c) {
        return new DemoCoordinate(c, map.getTerrain(c.getOffsetCoordinateColumn(), c.getOffsetCoordinateRow()));
    }

}
