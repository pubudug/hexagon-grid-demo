package io.github.pubudug.hexgrid.demo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

    private static final long serialVersionUID = -2230134238764365927L;

    private DemoHexagonGrid grid;

    public ControlPanel(DemoHexagonGrid grid) {
        super();
        this.grid = grid;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initialize();
    }

    private void initialize() {
        JPanel coordinatesPanel = getCoordinatesPanel();
        add(coordinatesPanel);

        JCheckBox drawVisibility = new JCheckBox("Draw metadata on visible hexes only");
        add(drawVisibility);

        drawVisibility.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                grid.setDrawVisibility(drawVisibility.isSelected());
            }
        });

        JLabel description = new JLabel();
        description.setText("<html>The movement costs are as follows. Trees-2, Water-4, Hills-3, Flat-1. "
                + "Click on a hex to move the 'unit' to that hex via the shortest path.</html>");
        description.setPreferredSize(coordinatesPanel.getPreferredSize());
        add(description);

        description = new JLabel();
        description.setText(
                "<html>The unit has a visible range of 3 hexagons." + "The vision is blocked by hills and trees.</html>");
        description.setPreferredSize(coordinatesPanel.getPreferredSize());
        add(description);

    }

    private JPanel getCoordinatesPanel() {
        JPanel coordinatesPanel = new JPanel();
        JCheckBox cubeCoordinateButton = new JCheckBox("Draw cube coordinates");
        JCheckBox offsetCoordinateButton = new JCheckBox("Draw offset coordinates");
        JCheckBox dontDrawCoordinates = new JCheckBox("Don't draw metadata");
        JCheckBox drawTerrainType = new JCheckBox("Draw terrain type");
        dontDrawCoordinates.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(cubeCoordinateButton);
        group.add(offsetCoordinateButton);
        group.add(dontDrawCoordinates);
        group.add(drawTerrainType);

        coordinatesPanel.add(dontDrawCoordinates);
        coordinatesPanel.add(cubeCoordinateButton);
        coordinatesPanel.add(offsetCoordinateButton);
        coordinatesPanel.add(drawTerrainType);
        coordinatesPanel.setLayout(new BoxLayout(coordinatesPanel, BoxLayout.Y_AXIS));
        coordinatesPanel.setBorder(BorderFactory.createTitledBorder("Coordinates"));

        cubeCoordinateButton.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                grid.setDrawCubeCoordinates(cubeCoordinateButton.isSelected());
            }
        });

        offsetCoordinateButton.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                grid.setDrawOffsetCoordinates(offsetCoordinateButton.isSelected());
            }
        });

        drawTerrainType.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                grid.setDrawTerrainType(drawTerrainType.isSelected());
            }
        });
        return coordinatesPanel;
    }

}
