package io.github.pubudug.hexgrid.demo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
