package Veiculos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VeiculosConsultar extends JDialog {
    private JTable veiculosTable;
    private JPanel veiculosPanel;
    private JButton cancelarButton;
    String header[] = {"ID","Matricula","Marca","Modelo","Preco"};

    public VeiculosConsultar(JFrame parent) {
        super(parent);
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        setTitle("Consultar Veiculos");
        setContentPane(veiculosPanel);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Faz a tabela -> Vai buscar os veiculos
    private void createUIComponents() {
        // TODO: place custom component creation code here
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        DefaultTableModel model = new DefaultTableModel(0, header.length);
        model.setColumnIdentifiers(header);
        veiculosTable = new JTable(model);
        gestorVeiculos.selectVeiculos(model);
    }

    public static void main(String[] args) {
        VeiculosConsultar veiculosConsultar = new VeiculosConsultar(null);
    }
}
