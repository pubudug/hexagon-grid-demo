package io.github.pubudug.hexgrid.demo;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import io.github.pubudug.hexgrid.CoordinateAttributes;
import io.github.pubudug.hexgrid.HumanCoordinateAttributes;
import io.github.pubudug.hexgrid.Point;

public class Unit {

    private DemoCoordinate coordinate;

    private boolean moveActionComplete;

    private Queue<DemoCoordinate> moveList;

    private int x, y;

    private int visiblityRange = 3;

    private boolean updateVisibility;

    private DemoHexagonFactory hexagonFactory;

    public Unit(int x, int y, DemoHexagonFactory hexagonFactory) {
        moveActionComplete = true;
        setUpdateVisibility(true);
        this.hexagonFactory = hexagonFactory;
    }

    void setHexagon(DemoCoordinate coordinate) {
        if (this.coordinate != null) {
            this.coordinate.setUnit(null);
        }
        this.coordinate = coordinate;
        if (this.coordinate != null) {
            Point center = hexagonFactory.create(coordinate).getCenter();
            this.x = (int) center.getX();
            this.y = (int) center.getY();
            setUpdateVisibility(true);
        }

    }

    boolean isMoveActoinComplete() {
        return moveActionComplete;
    }

    void update() {
        if (!moveActionComplete) {
            Point nextCenter = hexagonFactory.create(moveList.peek()).getCenter();

            double xDiff = nextCenter.getX() - hexagonFactory.create(coordinate).getCenter().getX();
            double yDiff = nextCenter.getY() - hexagonFactory.create(coordinate).getCenter().getY();

            x = (int) (x + xDiff * 0.1);
            y = (int) (y + yDiff * 0.1);

            int allowedError = 10;
            double error = sqrt(pow(abs(nextCenter.getX() - x), 2) + pow(abs(nextCenter.getY() - y), 2));
            if (error < allowedError) {
                hexagonFactory.create(moveList.poll()).getCoordinate().setUnit(this);
            }
            if (moveList.isEmpty()) {
                moveActionComplete = true;
            }
        }
    }

    void setMoveAction(List<DemoCoordinate> path) {
        if (moveActionComplete) {
            this.moveList = new LinkedList<>(path);
            moveActionComplete = false;
        }
    }

    public DemoCoordinate getCoordinate() {
        return coordinate;
    }

    public void draw(Graphics2D dbg) {
        dbg.setColor(Color.red);
        dbg.drawString("Unit", x, y);
    }

    public int getVisiblityRange() {
        return visiblityRange;
    }

    public CoordinateAttributes<DemoCoordinate> getHexagonAttributes() {
        return new HumanCoordinateAttributes<DemoCoordinate>();
    }

    public boolean isUpdateVisibility() {
        return updateVisibility;
    }

    public void setUpdateVisibility(boolean updateVisibility) {
        this.updateVisibility = updateVisibility;
    }

}
