package org.main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class DatasetRowPanel extends JPanel {
    private int index;
    private Dataset dataset;

    private JLabel indexLabel;
    private JColorChooser colorChooser;
    private JTextField nameField;
    private JLabel nameFieldLabel;
    private JTextField labelField;
    private JLabel labelFieldLabel;
    private JSpinner maxField;
    private JLabel maxFieldLabel;
    private JSpinner minField;
    private JLabel minFieldLabel;

    private GroupLayout layout;

    public DatasetRowPanel(int index, Dataset dataset) {
        this.index = index;
        this.dataset = dataset;

        layout = new GroupLayout(this);

        layout.setAutoCreateGaps(false);
        layout.setAutoCreateContainerGaps(false);

        setLayout(layout);
        setBackground(new Color(0xFFFFFF));
        setBorder(BorderFactory.createLineBorder(new Color(0x000000)));

        nameField = new JTextField(dataset.getName());
        nameField.setMaximumSize(new Dimension(100, (int) nameField.getPreferredSize().getHeight()));
        nameField.addActionListener(event -> {
            System.out.println(nameField.getText());
        });

        labelField = new JTextField(dataset.getLabel().equals("") ? "<Label>" : dataset.getLabel());
        labelField.setMaximumSize(new Dimension(100, (int) labelField.getPreferredSize().getHeight()));
        nameField.addActionListener(event -> {
            System.out.println(labelField.getText());
        });


        SpinnerNumberModel maxNumberModel = new SpinnerNumberModel(dataset.getMax(), Integer.MIN_VALUE / 100, Integer.MAX_VALUE / 100, 1);
        maxField = new JSpinner(maxNumberModel);
        maxField.setMaximumSize(maxField.getPreferredSize());
        maxField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println(maxField.getValue());
            }
        });

        SpinnerNumberModel minNumberModel = new SpinnerNumberModel(dataset.getMin(), Integer.MIN_VALUE / 100, Integer.MAX_VALUE / 100, 1);
        minField = new JSpinner(minNumberModel);
        minField.setMaximumSize(minField.getPreferredSize());
        minField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println(minField.getValue());
            }
        });

        indexLabel = new JLabel(index + ":");
        nameFieldLabel = new JLabel("Name: ");
        labelFieldLabel = new JLabel("Label: ");
        maxFieldLabel = new JLabel("Max: ");
        minFieldLabel = new JLabel("Min: ");

        add(indexLabel);
        add(nameFieldLabel);
        add(labelFieldLabel);
        add(maxFieldLabel);
        add(minFieldLabel);
        add(nameField);
        add(labelField);
        add(maxField);
        add(minField);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(Theme.datasetRowHorizontalPadding)
                                .addComponent(indexLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                        GroupLayout.DEFAULT_SIZE, Theme.maxDatasetRowHorizontalPadding)
                                .addComponent(nameFieldLabel)
                                .addComponent(nameField)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                        GroupLayout.DEFAULT_SIZE, Theme.maxDatasetRowHorizontalPadding)
                                .addComponent(labelFieldLabel)
                                .addComponent(labelField)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                        GroupLayout.DEFAULT_SIZE, Theme.maxDatasetRowHorizontalPadding)
                                .addComponent(maxFieldLabel)
                                .addComponent(maxField)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                        GroupLayout.DEFAULT_SIZE, Theme.maxDatasetRowHorizontalPadding)
                                .addComponent(minFieldLabel)
                                .addComponent(minField)
                                .addGap(Theme.datasetRowHorizontalPadding)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(Theme.datasetRowVerticalPadding)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(indexLabel)
                                .addComponent(nameFieldLabel)
                                .addComponent(nameField)
                                .addComponent(labelFieldLabel)
                                .addComponent(labelField)
                                .addComponent(maxFieldLabel)
                                .addComponent(maxField)
                                .addComponent(minFieldLabel)
                                .addComponent(minField)
                        )
                        .addGap(Theme.datasetRowVerticalPadding)
        );
    }

    public void updateDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public void updateDatasetName(String name) {
        this.dataset.setName(name);
    }

    public void updateDatasetLabel(String label) {
        this.dataset.setLabel(label);
    }

    public void updateDatasetMax(int max) {
        this.dataset.setMax(max);
    }

    public void updateDatasetMin(int min) {
        this.dataset.setMin(min);
    }
}
