package ui;

import database.DataProvider;
import models.Order;

import javax.swing.*;
import java.awt.*;

public class AdvanceFrame extends JFrame {
    public LoginPanel loginPanel;
    public TablesPanel tablesPanel;
    public OrderPanel orderPanel;
    public BillPanel billPanel;

    public DataProvider provider;

    public AdvanceFrame() {
        super("Advance Bar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new FlowLayout());

        provider = new DataProvider();
        provider.loadData();

        showLogin();
    }

    public void showLogin() {
        loginPanel = new LoginPanel(this);
        setContentPane(loginPanel);
        validate();
    }
    public void showTables() {
        tablesPanel = new TablesPanel(this);
        setContentPane(tablesPanel);
        validate();
    }
    public void showTableOrder(Order order) {
        orderPanel = new OrderPanel(this, order);
        setContentPane(orderPanel);
        validate();
    }
    public void showBill(Order order) {
        billPanel = new BillPanel(this, order);
        setContentPane(billPanel);
        validate();
    }
}
