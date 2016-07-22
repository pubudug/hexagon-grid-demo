package io.github.pubudug.hexgrid.demo;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
        JCheckBox cubeCoordinateButton = new JCheckBox("Draw cube coordinates");
        JCheckBox offsetCoordinateButton = new JCheckBox("Draw offset coordinates");
        JCheckBox dontDrawCoordinates = new JCheckBox("Don't draw coordinates");
        ButtonGroup group = new ButtonGroup();
        group.add(cubeCoordinateButton);
        group.add(offsetCoordinateButton);
        group.add(dontDrawCoordinates);

        add(dontDrawCoordinates);
        add(cubeCoordinateButton);
        add(offsetCoordinateButton);
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
    }

}
