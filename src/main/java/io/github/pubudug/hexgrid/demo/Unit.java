package io.github.pubudug.hexgrid.demo;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import io.github.pubudug.hexgrid.HexagonAttributes;
import io.github.pubudug.hexgrid.HumanHexagonAttributes;
import io.github.pubudug.hexgrid.Point;

public class Unit {

    private DemoHexagon hexagon;

    private boolean moveActionComplete;

    private Queue<DemoHexagon> moveList;

    private int x, y;

    private int visiblityRange = 2;

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

            double xDiff = nextCenter.getX() - hexagon.getCenter().getX();
            double yDiff = nextCenter.getY() - hexagon.getCenter().getY();

            x = (int) (x + xDiff * 0.1);
            y = (int) (y + yDiff * 0.1);

            int allowedError = 10;
            double error = sqrt(pow(abs(nextCenter.getX() - x), 2) + pow(abs(nextCenter.getY() - y), 2));
            if (error < allowedError) {
                moveList.poll().setUnit(this);
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

    public int getVisiblityRange() {
        return visiblityRange;
    }

    public HexagonAttributes<DemoHexagon> getHexagonAttributes() {
        return new HumanHexagonAttributes<DemoHexagon>();
    }

}
