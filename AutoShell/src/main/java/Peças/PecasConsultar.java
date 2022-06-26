package Peças;

import Menus.FrameMenuGeralDinamico;
import Veiculos.GestorVeiculos;
import Veiculos.Veiculos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PecasConsultar extends JDialog  {
    private JPanel pecasPanel;
    private JButton cancelarButton;
    private JTable pecasTable;
    DefaultTableModel model;
    String header[] = {"ID","Matricula","Marca","Modelo","Preco","DonosAnt","Descricao"};

    public PecasConsultar(JFrame parent) {
        super(parent);

        GestorPecas gestorPecas = new GestorPecas();
        setTitle("Consultar Peças");
        setContentPane(pecasPanel);
        setMinimumSize(new Dimension(1366,768));
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
        pecasTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Dá nos o ID que precisamos
                PecasConsultarDetalhado pecasConsultarDetalhado = new PecasConsultarDetalhado(null,(String) pecasTable.getModel().getValueAt(pecasTable.getSelectedRow(), 1));
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
        pecasTable = new JTable(model);
        gestorVeiculos.selectVeiculos(model);
    }
}
