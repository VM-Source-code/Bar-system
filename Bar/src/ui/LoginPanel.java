package ui;

import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends BasePanel implements ActionListener {
    public JTextField loginField;
    public JLabel loginLabel;
    public JButton loginButton;

    public LoginPanel(AdvanceFrame frame) {
        super(frame);

        loginLabel = new JLabel("Please enter your code");
        add(loginLabel);

        loginField = new JTextField("Your code here...");
        add(loginField);

        loginButton = new JButton("ENTER");
        loginButton.addActionListener(this);
        add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isFound = false;

        for(User waitress : this.frame.provider.waitresses) {
            if(waitress.code.equals(loginField.getText())) {
                this.frame.provider.currentUser = waitress;
                this.frame.showTables();
                isFound = true;
                break;
            }
        }

        if(!isFound) {
            JOptionPane.showMessageDialog(null, "Wrong code!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
