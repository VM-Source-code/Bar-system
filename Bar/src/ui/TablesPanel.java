package ui;

import models.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TablesPanel extends BasePanel implements ActionListener {

    public JButton getBillButton;
    public JButton addProductsToTableButton;
    public JButton backButton;

    public JLabel selectedTableLabel;

    public String selectedTableNumber;

    public Order selectedOrder;

    public TablesPanel(AdvanceFrame frame) {
        super(frame);

        for(int i = 10; i <= 15; i++) {
            JButton tableButton = new JButton(Integer.toString(i));
            tableButton.addActionListener(this);
            add(tableButton);
        }

        getBillButton = new JButton("GET BILL");
        getBillButton.addActionListener(this);
        add(getBillButton);
        getBillButton.setVisible(false);

        addProductsToTableButton = new JButton("Add to Table");
        addProductsToTableButton.addActionListener(this);
        add(addProductsToTableButton);
        addProductsToTableButton.setVisible(false);

        backButton = new JButton("Go Back");
        backButton.addActionListener(this);
        add(backButton);

        selectedTableLabel = new JLabel("");
        add(selectedTableLabel);
    }

    public void goBackAction() {
        this.frame.showLogin();
    }
    public void getBillAction() {
        if(isOrderExist()) {
            this.frame.showBill(selectedOrder);
        } else {
            JOptionPane.showMessageDialog(null,"You don't have order for this table!", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void addToTableAction() {
        if(isOrderExist()) {
            this.frame.showTableOrder(selectedOrder);
        } else {
            Order newOrder = new Order(selectedTableNumber);
            this.frame.provider.orders.add(newOrder);
            this.frame.showTableOrder(newOrder);
        }

    }
    public boolean isOrderExist() {
        boolean isOrderFound = false;
        for(Order order : this.frame.provider.orders) {
            if(order.tableNumber.equals(selectedTableNumber)) {
                selectedOrder = order;
                return true;
            }
        }

        return isOrderFound;
    }

    public void selectTableAction(JButton tableButton) {
        String tableNumber = tableButton.getText();
        System.out.println(tableNumber);
        selectedTableLabel.setText("Selected table: " + tableNumber);
        selectedTableNumber = tableNumber;

        getBillButton.setVisible(true);
        addProductsToTableButton.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == getBillButton) {
            getBillAction();
        } else if(e.getSource() == backButton) {
            goBackAction();
        } else if(e.getSource() == addProductsToTableButton) {
            addToTableAction();
        } else {
            selectTableAction((JButton)e.getSource());
        }
    }
}
