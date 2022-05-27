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
    private JLabel precoLabel;
    private JLabel imageLabel;

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
        imageLabel.setSize(10,10);
        ImageIcon  img = new ImageIcon(System.getProperty("user.dir") +row[7].toString());
        Image imgg = img.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        Icon icon = new ImageIcon(imgg);
        imageLabel.setIcon(icon);

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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
