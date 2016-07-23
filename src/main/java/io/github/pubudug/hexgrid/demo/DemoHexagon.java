package io.github.pubudug.hexgrid.demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.List;

import io.github.pubudug.hexgrid.Coordinate;
import io.github.pubudug.hexgrid.Hexagon;
import io.github.pubudug.hexgrid.Point;

public class DemoHexagon extends Hexagon {

    private TerrainType terrainType;

    DemoHexagon(Coordinate coordinate, int size, TerrainType terrainType) {
        super(coordinate, size);
        this.terrainType = terrainType;
    }

    public void draw(Graphics dbg) {
        Polygon p = new Polygon();
        List<Point> corners = getCorners();
        for (Point corner : corners) {
            p.addPoint((int) corner.getX(), (int) corner.getY());
        }
        dbg.setColor(terrainType.getColor());
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
        dbg.drawString(terrainType.name(), (int) getCenter().getX() - getSize() / 3, (int) getCenter().getY());
    }

}
