package Veiculos;

import Menus.FrameMenuGeralDinamico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VeiculosConsultar extends JDialog {
    private JTable veiculosTable;
    private JPanel veiculosPanel;
    private JButton cancelarButton;
    DefaultTableModel model;
    String header[] = {"ID","Matricula","Marca","Modelo","Preco","DonosAnt","Descricao"};
    public VeiculosConsultar(JFrame parent) {
        super(parent);

        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        setTitle("Consultar Veiculos");
        setContentPane(veiculosPanel);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Veiculos veiculos = new Veiculos();
                dispose();
                FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, veiculos);
            }
        });
        veiculosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Dá nos o ID que precisamos
                VeiculosConsultarDetalhado veiculosConsultarDetalhado = new VeiculosConsultarDetalhado(null,(String) veiculosTable.getModel().getValueAt(veiculosTable.getSelectedRow(), 1));
            }
        });
        setVisible(true);
    }

    // Faz a tabela -> Vai buscar os veiculos
    private void createUIComponents() {
        // TODO: place custom component creation code here
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        model = new DefaultTableModel(0, header.length);
        model.setColumnIdentifiers(header);
        veiculosTable = new JTable(model);
        gestorVeiculos.selectVeiculos(model);
    }

    public static void main(String[] args) {
        VeiculosConsultar veiculosConsultar = new VeiculosConsultar(null);
    }
}
