package ui;

import models.Order;
import models.Product;
import models.UserType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class OrderPanel extends BasePanel implements ActionListener {
    public Order order;
    public JTable productsTable;
    public DefaultTableModel productTableModel;
    public JButton saveButton;
    public JButton cancelButton;
    public JButton deleteButton;

    public ArrayList<Product> selectedProducts;

    public OrderPanel(AdvanceFrame frame, Order order) {
        super(frame);
        this.order = order;
        selectedProducts = new ArrayList<>();

        for(Product product : this.frame.provider.products) {
            String buttonText = product.id + "." + product.brand + " - " + product.type;
            JButton button = new JButton(buttonText);
            button.addActionListener(this);
            add(button);
        }

        createTable();
        loadData();
        initialiseCommonButton();

    }

    public void loadData() {
        productTableModel.setRowCount(0);

        for(Product product : this.order.products) {
            String[] row = new String[5];
            row[0] = product.type;
            row[1] = product.brand;
            row[2] = Integer.toString(product.quantity);
            row[3] = Double.toString(product.price);
            row[4] = product.salePrice > 0 ? Double.toString(product.salePrice) : "";
            productTableModel.addRow(row);
        }

    }
    public void deleteProductAction() {
        if(productsTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "You must select product in order to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedIndex = productsTable.getSelectedRow();
        int originalSize = order.products.size() - selectedProducts.size();
        if(selectedIndex >= originalSize) {
            selectedProducts.remove(0);
        }

        order.products.remove(productsTable.getSelectedRow());
        loadData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String productText = ((JButton)e.getSource()).getText();
        String[] indexes = productText.split("\\.");
        String productID = indexes[0];

        for(Product product : this.frame.provider.products) {
            if(product.id.equals(productID)) {
                this.order.products.add(product);
                this.selectedProducts.add(product);
                break;
            }
        }

        loadData();
    }

    public void createTable() {
        String[] collumns = {"Type", "Brand", "Quantity", "Price", "Promo"};

        productTableModel = new DefaultTableModel();
        productTableModel.setColumnIdentifiers(collumns);

        productsTable = new JTable(productTableModel);
        JScrollPane pane = new JScrollPane(productsTable);
        add(pane);
    }

    public void initialiseCommonButton() {
        saveButton = new JButton("Finish");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedProducts == null || selectedProducts.size() == 0) {
                    frame.showLogin();
                    return;
                }
                int exitOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to save the order?", "Save order", JOptionPane.YES_NO_OPTION);
                if(exitOption == JOptionPane.YES_OPTION) {
                    frame.showLogin();
                }
            }
        });


        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedProducts == null || selectedProducts.size() == 0) {
                    frame.showLogin();
                    return;
                }
                int cancelOption = JOptionPane.showConfirmDialog(null, "Would you like to save the changes?","Warning", JOptionPane.YES_NO_CANCEL_OPTION);
                if(cancelOption == JOptionPane.YES_OPTION) {
                    frame.showLogin();
                } else if(cancelOption == JOptionPane.NO_OPTION) {
                    ArrayList<Product> tempArray = new ArrayList<>();
                    for (int i = 0; i < order.products.size() - selectedProducts.size(); i++) {
                        Product product = order.products.get(i);
                        tempArray.add(product);
                    }
                    order.products = new ArrayList<>(tempArray);
                    frame.showLogin();
                }
            }
        });


        deleteButton = new JButton("Delete product");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProductAction();
            }
        });

        if(this.frame.provider.currentUser.type == UserType.MANAGER) {
            add(cancelButton);
            add(deleteButton);
            saveButton.setText("Save");
        }

        add(saveButton);
    }
}
