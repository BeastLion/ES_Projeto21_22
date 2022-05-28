package Alerts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FailAlert extends JDialog{
    private JPanel Error;
    private JButton OKButton;
    private JLabel outputText;

    public FailAlert(JFrame parent, String error) throws IOException {
        super(parent);
        setTitle("ERROR");
        setContentPane(Error);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        outputText.setText(error);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
