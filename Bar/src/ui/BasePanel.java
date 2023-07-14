package ui;

import javax.swing.*;
import java.awt.*;

public class BasePanel extends JPanel {
    public AdvanceFrame frame;

    public BasePanel(AdvanceFrame frame) {
        this.frame = frame;
        setLayout(new FlowLayout());
    }
}
