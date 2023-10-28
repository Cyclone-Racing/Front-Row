package org.main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ToolbarPanel extends JPanel {

    public static ToolbarPanel instance = new ToolbarPanel();
    private GroupLayout layout;
    private JButton buttonA;
    private JButton buttonB;
    private JComboBox<String> comboBoxA;

    private ToolbarPanel() {
        super();

        layout = new GroupLayout(this);

        setLayout(layout);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(Theme.pageBackground);

        layout.setAutoCreateGaps(false);
        layout.setAutoCreateContainerGaps(false);

        buttonA = new JButton("Button A");
        buttonA.addActionListener(event -> {
            System.out.println(buttonA.getText());
        });

        buttonB = new JButton("Button B");
        buttonB.addActionListener(event -> {
            System.out.println(buttonB.getText());
        });

        comboBoxA = new JComboBox<>(new String[] {"String A", "String B", "String C"});
        Dimension comboBoxAMaxSize = new Dimension((int) (comboBoxA.getPreferredSize().getWidth()  * 1.1), (int) comboBoxA.getPreferredSize().getHeight());
        comboBoxA.setMaximumSize(comboBoxAMaxSize);
        comboBoxA.addActionListener(event -> {
            System.out.println(comboBoxA.getSelectedItem());
        });

        add(buttonA);
        add(buttonB);
        add(comboBoxA);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(buttonA)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                GroupLayout.DEFAULT_SIZE, Integer.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonB)
                                .addGap(Theme.toolbarPadding)
                                .addComponent(comboBoxA)
                        )
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonA)
                        .addComponent(buttonB)
                        .addComponent(comboBoxA)
        );
    }
}