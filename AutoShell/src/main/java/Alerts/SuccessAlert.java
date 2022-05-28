package Alerts;

import Veiculos.GestorVeiculos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SuccessAlert extends JDialog {
    private JPanel Success;
    private JLabel outputText;
    private JButton OKButton;

    public SuccessAlert(JFrame parent, String sign) throws IOException {
        super(parent);
        setTitle("SUCCESS");
        setContentPane(Success);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        outputText.setText(sign);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
