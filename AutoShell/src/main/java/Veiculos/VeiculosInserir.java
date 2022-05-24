package Veiculos;

import DBCONFIG.DB;
import Menus.FrameMenuGeralDinamico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VeiculosInserir extends  JDialog{
    private JTable veiculosTable;
    private JPanel veiculosPanel;
    private JTextField precoText;
    private JTextField modeloText;
    private JTextField marcaText;
    private JTextField matriculaText;
    private JLabel matriculaLabel;
    private JLabel marcaLabel;
    private JLabel modeloLabel;
    private JLabel precoLabel;
    private JButton cancelarButton;
    private JButton confirmarButton;
    String header[] = {"ID","Matricula","Marca","Modelo","Preco"};


    public VeiculosInserir(JFrame parent){
        super(parent);
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        setTitle("Inserir Veiculo");
        setContentPane(veiculosPanel);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        confirmarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String matricula = matriculaText.getText();
            String marca = marcaText.getText();
            String modelo = modeloText.getText();
            String preco = precoText.getText();

            gestorVeiculos.insertVeiculos(matricula,marca,modelo,preco);
            matriculaText.setText(" ");
            marcaText.setText(" ");
            modeloText.setText(" ");
            precoText.setText(" ");
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
        VeiculosInserir veiculosInserir = new VeiculosInserir(null);
    }
}
