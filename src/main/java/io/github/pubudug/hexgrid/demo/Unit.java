package io.github.pubudug.hexgrid.demo;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import io.github.pubudug.hexgrid.Point;

public class Unit {

    private DemoHexagon hexagon;

    private boolean moveActionComplete;

    private Queue<DemoHexagon> moveList;

    private int x, y;

    public Unit() {
        moveActionComplete = true;
    }

    void setHexagon(DemoHexagon hexagon) {
        if (this.hexagon != null) {
            this.hexagon.setUnit(null);
        }
        this.hexagon = hexagon;
        if (this.hexagon != null) {
            Point center = hexagon.getCenter();
            this.x = (int) center.getX();
            this.y = (int) center.getY();
        }

    }

    boolean isMoveActoinComplete() {
        return moveActionComplete;
    }

    void update() {
        if (!moveActionComplete) {
            Point nextCenter = moveList.peek().getCenter();

            double xDiff = nextCenter.getX() - x;
            double yDiff = nextCenter.getY() - y;

            x = (int) (x + xDiff * 0.1);
            y = (int) (y + yDiff * 0.1);

            int allowedError = 15;
            double error = sqrt(pow(abs(nextCenter.getX() - x), 2) + pow(abs(nextCenter.getY() - y), 2));
            if (error < allowedError) {
                setHexagon(moveList.poll());
            }
            if (moveList.isEmpty()) {
                moveActionComplete = true;
            }
        }
    }

    void setMoveAction(List<DemoHexagon> moveList) {
        if (moveActionComplete) {
            this.moveList = new LinkedList<>(moveList);
            moveActionComplete = false;
        }
    }

    public DemoHexagon getHexagon() {
        return hexagon;
    }

    public void draw(Graphics2D dbg) {
        dbg.setColor(Color.red);
        dbg.drawString("Unit", x, y);
    }

}
