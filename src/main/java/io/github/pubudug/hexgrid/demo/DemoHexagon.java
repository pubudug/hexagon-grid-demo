package io.github.pubudug.hexgrid.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.List;
import java.util.Optional;

import io.github.pubudug.hexgrid.Coordinate;
import io.github.pubudug.hexgrid.Point;
import io.github.pubudug.hexgrid.TerrainType;
import io.github.pubudug.hexgrid.TestHexagon;

public class DemoHexagon extends TestHexagon {
    private Optional<Unit> unit;

    protected DemoHexagon(Coordinate coordinate, int size, TerrainType terrainType) {
        super(coordinate, size, terrainType);
        unit = Optional.empty();
    }

    public void draw(Graphics dbg) {
        Polygon p = new Polygon();
        List<Point> corners = getCorners();
        for (Point corner : corners) {
            p.addPoint((int) corner.getX(), (int) corner.getY());
        }
        dbg.setColor(getTerrainType().getColor());
        dbg.fillPolygon(p);
    }

    public void drawCubeCoordinates(Graphics2D dbg) {
        dbg.setColor(Color.black);
        dbg.drawString(getCubeX() + "," + getCubeY() + "," + getCubeZ(), (int) getCenter().getX() - getSize() / 2,
                (int) getCenter().getY());
    }

    public void drawOffsetCoordinates(Graphics2D dbg) {
        dbg.setColor(Color.black);
        dbg.drawString(getOffsetCoordinateColumn() + "," + getOffsetCoordinateRow(),
                (int) getCenter().getX() - getSize() / 3, (int) getCenter().getY());
    }

    public void drawTerrainType(Graphics2D dbg) {
        dbg.setColor(Color.black);
        dbg.drawString(getTerrainType().name(), (int) getCenter().getX() - getSize() / 3, (int) getCenter().getY());
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
