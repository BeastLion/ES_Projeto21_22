package Veiculos;

import Login.FrameLogin;
import Menus.FrameMenuGeralDinamico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VeiculosEliminar extends JDialog {

    String header[] = {"ID","Matricula","Marca","Modelo","Preco"};
    private JTable veiculosTable;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JTextField matriculaText;
    private JPanel veiculosEliminar;
    private boolean status;

    public VeiculosEliminar(JFrame parent) {
        super(parent);
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        setTitle("Eliminar Veiculo");
        setContentPane(veiculosEliminar);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = matriculaText.getText();

                if (matricula != null){
                    status = gestorVeiculos.eliminarVeiculo(matricula);
                    if (status){
                        JOptionPane.showMessageDialog(VeiculosEliminar.this, "Veiculo apagado da Base de dados "+ matricula);
                    } else{
                        JOptionPane.showMessageDialog(VeiculosEliminar.this, "Não existe um veiculo com essa matricula","Tente novamente",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Veiculos veiculos = new Veiculos();
                dispose();
                FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, veiculos);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        VeiculosEliminar veiculosEliminar = new VeiculosEliminar(null);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        DefaultTableModel model = new DefaultTableModel(0, header.length);
        model.setColumnIdentifiers(header);
        veiculosTable = new JTable(model);
        gestorVeiculos.selectVeiculos(model);
    }

}
