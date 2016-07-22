package io.github.pubudug.hexgrid.demo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.List;

import io.github.pubudug.hexgrid.Coordinate;
import io.github.pubudug.hexgrid.Hexagon;
import io.github.pubudug.hexgrid.Point;

public class DemoHexagon extends Hexagon {

    DemoHexagon(Coordinate coordinate, int size) {
        super(coordinate, size);
    }

    public void draw(Graphics dbg) {
        Polygon p = new Polygon();
        List<Point> corners = getCorners();
        for (Point corner : corners) {
            p.addPoint((int) corner.getX(), (int) corner.getY());
        }
        dbg.drawPolygon(p);
    }

    public void drawCubeCoordinates(Graphics2D dbg) {
        dbg.drawString(getCubeX() + "," + getCubeY() + "," + getCubeZ(), (int) getCenter().getX() - getSize() / 2,
                (int) getCenter().getY());
    }

    public void drawOffsetCoordinates(Graphics2D dbg) {
        dbg.drawString(getOffsetCoordinateColumn() + "," + getOffsetCoordinateRow(),
                (int) getCenter().getX() - getSize() / 3, (int) getCenter().getY());
    }

}
