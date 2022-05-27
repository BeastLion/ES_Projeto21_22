package Veiculos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VeiculosConsultarDetalhado extends JDialog {

    private JPanel veiculosPanel;
    private JTextField MarcaText;
    private JTextField ModeloText;
    private JTextField MatriculaText;
    private JTextField DonosText;
    private JTextField DescricaoText;
    private JButton cancelarButton;
    private JPanel imageText;
    private JLabel precoLabel;

    public VeiculosConsultarDetalhado(JFrame parent, String matricula) {
        super(parent);
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        setTitle("Consultar Veiculo Especifico");
        setContentPane(veiculosPanel);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Object[] row = gestorVeiculos.selectVeiculosMatricula(matricula);
        // Set off
        MatriculaText.setEnabled(false);
        MarcaText.setEnabled(false);
        ModeloText.setEnabled(false);
        precoLabel.setEnabled(false);
        DonosText.setEnabled(false);
        DescricaoText.setEnabled(false);
        // Set Values
        MatriculaText.setText(row[4].toString());
        MarcaText.setText(row[2].toString());
        ModeloText.setText(row[3].toString());
        precoLabel.setText("Preço: "+ row[4].toString());
        DonosText.setText(row[5].toString());
        DescricaoText.setText(row[6].toString());

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Voltar para o consultar
                dispose();
            }
        });
        setVisible(true);
    }
}
