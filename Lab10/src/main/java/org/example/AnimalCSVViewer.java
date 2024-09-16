package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

public class AnimalCSVViewer extends JPanel {
    private JTable table;

    private JButton clearButton;

    private JTextField filterField;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    public AnimalCSVViewer() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Animal", "ID", "Name", "Age", "Weight", "Breed", "Color", "Edit", "Delete"});
        table = new JTable(tableModel);

        // Render buttons in "Edit" and "Delete" columns
        table.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        table.getColumn("Delete").setCellRenderer(new ButtonRenderer());

        // Add action listeners to buttons
        table.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), tableModel));
        table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), tableModel));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddDialog();
            }
        });

        JButton loadButton = new JButton("Load from CSV");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadFromCSV();
            }
        });

        filterField = new JTextField(20);
        filterField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterField.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    } catch (PatternSyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        filterField = new JTextField(20);
        filterField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applyFilter();
            }
        });

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterField.setText("");
                applyFilter();
            }
        });

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filterPanel.add(new JLabel("Filter:"));
        filterPanel.add(filterField);
        filterPanel.add(clearButton);
        add(filterPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(loadButton);
        add(buttonPanel, BorderLayout.SOUTH);


        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
    }

    private void addRow() {
        Vector<Object> emptyRow = new Vector<>();
        for (int i = 0; i < tableModel.getColumnCount() - 2; i++) {
            emptyRow.add("");
        }
        emptyRow.add("Edit");
        emptyRow.add("Delete");
        tableModel.addRow(emptyRow);
    }

    private void applyFilter() {
        String text = filterField.getText();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            } catch (PatternSyntaxException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void openAddDialog() {
        JPanel panel = new JPanel(new GridLayout(7, 2));
        JTextField animalField = new JTextField();
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField breedField = new JTextField();
        JTextField colorField = new JTextField();

        panel.add(new JLabel("Animal:"));
        panel.add(animalField);
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Weight:"));
        panel.add(weightField);
        panel.add(new JLabel("Breed:"));
        panel.add(breedField);
        panel.add(new JLabel("Color:"));
        panel.add(colorField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Row", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Add the new row to the table
            Vector<Object> newRow = new Vector<>();
            newRow.add(animalField.getText());
            newRow.add(idField.getText());
            newRow.add(nameField.getText());
            newRow.add(ageField.getText());
            newRow.add(weightField.getText());
            newRow.add(breedField.getText());
            newRow.add(colorField.getText());
            tableModel.addRow(newRow);
        }
    }

    private void loadFromCSV() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();
            try {
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");

                    if (data.length < 4) {
                        continue;
                    }

                    String animal = data[0].replaceAll("^\"|\"$", "").trim();

                    String[] newData = new String[]{"", "", "", "", "", "", ""};

                    for (int i = 0; i < Math.min(data.length, 4); i++) {
                        newData[i] = data[i].replaceAll("^\"|\"$", "").trim();
                    }

                    if (animal.equalsIgnoreCase("Pig")) {

                        if (data.length >= 5) {
                            newData[4] = data[4].trim();
                        }
                    } else if (animal.equalsIgnoreCase("Dog")) {

                        if (data.length >= 5) {
                            newData[5] = data[4].trim(); // Set the breed
                        }
                    } else if (animal.equalsIgnoreCase("Cat")) {

                        if (data.length >= 5) {
                            newData[6] = data[4].trim();
                        }
                    }

                    tableModel.addRow(newData);
                }
                br.close();
                addButtonsToTable();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void addButtonsToTable() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tableModel.setValueAt("Edit", i, tableModel.getColumnCount() - 2);
            tableModel.setValueAt("Delete", i, tableModel.getColumnCount() - 1);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Animal CSV Viewer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new AnimalCSVViewer());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            if (column == 7 || column == 8) {
                setText((value == null || value.toString().isEmpty()) ? "" : value.toString());

                setText(column == 7 ? "Edit" : "Delete");
            }
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, DefaultTableModel tableModel) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {

                if (label.equals("Edit")) {
                    editRow();
                } else if (label.equals("Delete")) {
                    deleteRow();
                }
            }
            isPushed = false;
            return label;
        }

        private void editRow() {

            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {

                String animal = (String) tableModel.getValueAt(selectedRow, 0);
                String id = (String) tableModel.getValueAt(selectedRow, 1);
                String name = (String) tableModel.getValueAt(selectedRow, 2);
                String age = (String) tableModel.getValueAt(selectedRow, 3);
                String weight = (String) tableModel.getValueAt(selectedRow, 4);
                String breed = (String) tableModel.getValueAt(selectedRow, 5);
                String color = (String) tableModel.getValueAt(selectedRow, 6);


                openEditDialog(animal, id, name, age, weight, breed, color, selectedRow);
            } else {
                JOptionPane.showMessageDialog(AnimalCSVViewer.this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void deleteRow() {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int option = JOptionPane.showConfirmDialog(AnimalCSVViewer.this, "Are you sure you want to delete this row?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    tableModel.removeRow(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(AnimalCSVViewer.this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void openEditDialog(String animal, String id, String name, String age, String weight, String breed, String color, int rowIndex) {
            JPanel panel = new JPanel(new GridLayout(7, 2));
            JTextField animalField = new JTextField(animal);
            JTextField idField = new JTextField(id);
            JTextField nameField = new JTextField(name);
            JTextField ageField = new JTextField(age);
            JTextField weightField = new JTextField(weight);
            JTextField breedField = new JTextField(breed);
            JTextField colorField = new JTextField(color);

            panel.add(new JLabel("Animal:"));
            panel.add(animalField);
            panel.add(new JLabel("ID:"));
            panel.add(idField);
            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Age:"));
            panel.add(ageField);
            panel.add(new JLabel("Weight:"));
            panel.add(weightField);
            panel.add(new JLabel("Breed:"));
            panel.add(breedField);
            panel.add(new JLabel("Color:"));
            panel.add(colorField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Row", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                tableModel.setValueAt(animalField.getText(), rowIndex, 0);
                tableModel.setValueAt(idField.getText(), rowIndex, 1);
                tableModel.setValueAt(nameField.getText(), rowIndex, 2);
                tableModel.setValueAt(ageField.getText(), rowIndex, 3);
                tableModel.setValueAt(weightField.getText(), rowIndex, 4);
                tableModel.setValueAt(breedField.getText(), rowIndex, 5);
                tableModel.setValueAt(colorField.getText(), rowIndex, 6);
            }
        }

    }
}