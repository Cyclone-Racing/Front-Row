package org.main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Formatter;
import java.util.Locale;

public class DatasetRowPanel extends JPanel {
    private int index;
    private Dataset dataset;

    private JLabel indexLabel;
    private JColorChooser colorChooser;
    private JButton colorButton;
    private JTextField nameField;
    private JLabel nameFieldLabel;
    private JTextField labelField;
    private JLabel labelFieldLabel;
    private JCheckBox autoScale;
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

        colorButton = new JButton(" ■ ");
        colorButton.setFont(Theme.mediumFont);
        colorButton.setForeground(dataset.getColor());
        colorButton.setBorder(new LineBorder(Color.BLACK));
        colorButton.setFocusPainted(false);
        colorButton.addActionListener(event -> {
            Color color = JColorChooser.showDialog(this, "Pick a Color for " + dataset.getName(), dataset.getColor());
            if(color != null) {
                colorButton.setForeground(color);
                System.out.println("Dialog: " + color.getRGB());
                dataset.setColor(new Color(color.getRGB()));
            }
        });

        nameField = new JTextField(dataset.getName());
        nameField.setMaximumSize(new Dimension(100, (int) nameField.getPreferredSize().getHeight()));

        labelField = new JTextField(dataset.getLabel().equals("") ? "" : dataset.getLabel());
        labelField.setMaximumSize(new Dimension(100, (int) labelField.getPreferredSize().getHeight()));

        autoScale = new JCheckBox("Autoscale");
        autoScale.setMaximumSize(autoScale.getPreferredSize());
        autoScale.setBackground(Color.white);
        autoScale.addActionListener(event -> {
            dataset.autoDetectMaxMin = autoScale.isSelected();
            maxField.setEnabled(!autoScale.isSelected());
            minField.setEnabled(!autoScale.isSelected());
        });

        SpinnerNumberModel maxNumberModel = new SpinnerNumberModel(dataset.getMax(), Integer.MIN_VALUE / 100, Integer.MAX_VALUE / 100, 1);
        maxField = new JSpinner(maxNumberModel);
        maxField.setMaximumSize(maxField.getPreferredSize());
        maxField.addChangeListener(e -> dataset.setMax((int) maxField.getValue()));

        SpinnerNumberModel minNumberModel = new SpinnerNumberModel(dataset.getMin(), Integer.MIN_VALUE / 100, Integer.MAX_VALUE / 100, 1);
        minField = new JSpinner(minNumberModel);
        minField.setMaximumSize(minField.getPreferredSize());
        minField.addChangeListener(e -> dataset.setMin((int) minField.getValue()));


        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        int indexStringLength = String.valueOf(DatasetController.getDatasets().size()).length();
        indexLabel = new JLabel(formatter.format("%0" + indexStringLength + "d", index) + ":");
        nameFieldLabel = new JLabel("Name: ");
        labelFieldLabel = new JLabel("Label: ");
        maxFieldLabel = new JLabel("Max: ");
        minFieldLabel = new JLabel("Min: ");

        add(indexLabel);
        add(colorButton);
        add(nameFieldLabel);
        add(labelFieldLabel);
        add(maxFieldLabel);
        add(minFieldLabel);
        add(nameField);
        add(labelField);
        add(autoScale);
        add(maxField);
        add(minField);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(Theme.datasetRowHorizontalPadding)
                                .addComponent(indexLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                        GroupLayout.DEFAULT_SIZE, Theme.datasetRowPadding)
                                .addComponent(colorButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                        GroupLayout.DEFAULT_SIZE, Theme.datasetRowPadding)
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
                                        GroupLayout.DEFAULT_SIZE, Theme.maxDatasetRowHorizontalPadding / 2)
                                .addComponent(autoScale)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED,
                                        GroupLayout.DEFAULT_SIZE, Theme.maxDatasetRowHorizontalPadding / 2)
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
                                .addComponent(colorButton)
                                .addComponent(nameFieldLabel)
                                .addComponent(nameField)
                                .addComponent(labelFieldLabel)
                                .addComponent(labelField)
                                .addComponent(autoScale)
                                .addComponent(maxFieldLabel)
                                .addComponent(maxField)
                                .addComponent(minFieldLabel)
                                .addComponent(minField)
                        )
                        .addGap(Theme.datasetRowVerticalPadding)
        );
    }

    public void confirmDatasetLabels(){
        updateDatasetName(nameField.getText());
        updateDatasetLabel(labelField.getText());
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
