package io.github.pubudug.hexgrid.demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import io.github.pubudug.hexgrid.CoordinateGrid;
import io.github.pubudug.hexgrid.ShortestPathCalculator;
import io.github.pubudug.hexgrid.VisibilityCalculator;

public class GamePanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -6069849266154183365L;

    private Image dbImage;

    private Graphics2D dbg;
    private int width;
    private int height;

    private DemoStats stats;

    private DemoHexagonGrid grid;

    private Unit unit;

    private CoordinateGrid<DemoCoordinate> cg;

    public GamePanel(int width, int height, DemoStats stats, DemoHexagonGrid grid, CoordinateGrid<DemoCoordinate> cg,
            Unit unit) {
        this.width = width;
        this.height = height;
        this.grid = grid;
        this.unit = unit;
        this.cg = cg;
        setPreferredSize(new Dimension(width, height));
        this.stats = stats;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (unit.isMoveActoinComplete()) {
                    DemoHexagon to = grid.getHexagonContainingPixel(e.getX(), e.getY());
                    ShortestPathCalculator<DemoCoordinate> calculator = new ShortestPathCalculator<DemoCoordinate>(cg,
                            unit.getHexagonAttributes());
                    List<DemoCoordinate> path = calculator.findShortestPath(unit.getCoordinate(), to.getCoordinate());
                    unit.setMoveAction(path);
                }
            }
        });
    }

    void update() {
        unit.update();
        if (unit.isUpdateVisibility()) {
            VisibilityCalculator<DemoCoordinate> v = new VisibilityCalculator<DemoCoordinate>(unit.getCoordinate(),
                    unit.getVisiblityRange(), cg, unit.getHexagonAttributes());
            grid.setVisible(v.getVisibleHexagons());
            unit.setUpdateVisibility(false);
        }
    }

    void render() {
        if (dbImage == null) {
            this.dbImage = createImage(width, height);
            if (dbImage == null) {
                System.out.println("dbImage is  null");
                return;
            } else {
                this.dbg = (Graphics2D) dbImage.getGraphics();
                dbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
        }

        // clear the background
        dbg.setColor(Color.white);
        dbg.fillRect(0, 0, width, height);

        grid.draw(dbg);

        unit.draw(dbg);

        stats.draw(dbg);

    }

    void paintScreen() {
        // use active rendering to put the buffered image on-screen
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null))
                g.drawImage(dbImage, 0, 0, null);
            g.dispose();
        } catch (Exception e) {
            System.out.println("Graphics context error: " + e);
        }
    }

}
