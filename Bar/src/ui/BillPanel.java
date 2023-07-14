package ui;

import models.Order;
import models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillPanel extends BasePanel implements ActionListener {
    public Order order;
    public JTable productsTable;
    public DefaultTableModel productTableModel;

    public JButton endOrderButton;
    public JButton backButton;
    public JCheckBox discountCheckbox;
    public JLabel totalOrderPriceLabel;

    public BillPanel(AdvanceFrame frame, Order order) {
        super(frame);
        this.order = order;

        createComponents();
        createTable();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == discountCheckbox) {
            totalOrderPriceLabel.setText(this.order.getTotalPriceString(discountCheckbox.isSelected()));
        } else if(e.getSource() == endOrderButton) {
            for (Order order : this.frame.provider.orders) {
                if(order.tableNumber.equals(this.order.tableNumber)) {
                    this.frame.provider.orders.remove(order);
                    break;
                }
            }
            this.frame.showLogin();
        } else if(e.getSource() == backButton) {
            this.frame.showLogin();
        }
    }


    public void createComponents() {
        backButton = new JButton("GO BACK");
        backButton.addActionListener(this);
        add(backButton);

        endOrderButton = new JButton("FINISH ORDER");
        endOrderButton.addActionListener(this);
        add(endOrderButton);

        discountCheckbox = new JCheckBox("Loyal Card");
        discountCheckbox.addActionListener(this);
        add(discountCheckbox);

        totalOrderPriceLabel = new JLabel(this.order.getTotalPriceString(false));
        add(totalOrderPriceLabel);
    }
}
