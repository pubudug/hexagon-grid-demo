package io.github.pubudug.hexgrid.demo;

import java.util.Optional;

import io.github.pubudug.hexgrid.Coordinate;
import io.github.pubudug.hexgrid.TerrainType;
import io.github.pubudug.hexgrid.TestCoordinate;

public class DemoCoordinate extends TestCoordinate {


    private Optional<Unit> unit;

    public DemoCoordinate(Coordinate coordinate, TerrainType terrainType) {
        super(coordinate, terrainType);
    }

    void setUnit(Unit unit) {
        if (unit != null) {
            unit.setHexagon(null);
        }
        this.unit = Optional.ofNullable(unit);
        if (unit != null) {
            unit.setHexagon(this);
        }
    }

}
