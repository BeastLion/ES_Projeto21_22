package Alerts;

import Veiculos.Veiculos;
import Veiculos.GestorVeiculos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConfirmarDelete extends JDialog {

    private JPanel confirmDelete;
    private JButton OKButton;
    private JButton CANCELARButton;

    public ConfirmarDelete(JFrame parent, String matricula, Veiculos veiculos) throws IOException {
        super(parent);
        setTitle("ERROR");
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        setContentPane(confirmDelete);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        if (matricula == null) {
            FailAlert failAlert = new FailAlert(null, "ESTA MATRICULA NÃO SE ENCONTRA \n NA NOSSA BASE DE DADOS");
        }

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestorVeiculos.checkMatriculaDuplicada(matricula) == -1) {
                     gestorVeiculos.eliminarVeiculo(matricula);
                        try {
                            dispose();
                            SuccessAlert successAlert = new SuccessAlert(null, "VEICULOS ELIMINADO COM SUCESSO");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                    try {
                        FailAlert failAlert = new FailAlert(null, "NÃO FOI POSSIVEL ELIMINAR ESTE \n VEICULO COM ESTA MATRICULA");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        CANCELARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
