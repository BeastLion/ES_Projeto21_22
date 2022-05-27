package Veiculos;

import Menus.FrameMenuGeralDinamico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VeiculosEditar extends JDialog {
    private JTable veiculosTable;
    private JPanel VeiculoEditar;
    private JTextField matriculaText;
    private JTextField marcaText;
    private JTextField modeloText;
    private JTextField precoText;
    private JTextField donosText;
    private JTextField descricaoText;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JTextField searchMatricula;
    private JTextField hidden;
    String header[] = {"ID","Matricula","Marca","Modelo","Preco","DonosAnt","Descricao"};

    public VeiculosEditar(JFrame parent) {
        super(parent);
        setTitle("Editar Veiculo");
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        setContentPane(VeiculoEditar);
        setMinimumSize(new Dimension(450, 475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Hidden id field
        hidden.setVisible(false);

        // Desligamos a escrita
        matriculaText.setEnabled(false);
        marcaText.setEnabled(false);
        modeloText.setEnabled(false);
        precoText.setEnabled(false);
        donosText.setEnabled(false);
        descricaoText.setEnabled(false);
        confirmarButton.setEnabled(false);

        // Key Listener
        searchMatricula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                // Buscar os nossos dados baseados na nossa matricula
                String matricula = searchMatricula.getText();

                // Vamos buscar os nossos dados para por nos campos
                Object[] row = gestorVeiculos.selectVeiculosMatricula(matricula);

                if (row[0] != null){

                    // Permitimos escrita nos botões
                    confirmarButton.setEnabled(true);
                    matriculaText.setEnabled(true);
                    marcaText.setEnabled(true);
                    modeloText.setEnabled(true);
                    precoText.setEnabled(true);
                    donosText.setEnabled(true);
                    descricaoText.setEnabled(true);

                    // Os valores dos nossos campos
                    hidden.setText(row[0].toString());
                    matriculaText.setText(row[1].toString());
                    marcaText.setText(row[2].toString());
                    modeloText.setText(row[3].toString());
                    precoText.setText(row[4].toString());
                    donosText.setText(row[5].toString());
                    descricaoText.setText(row[6].toString());

                } else {

                    confirmarButton.setEnabled(false);
                    matriculaText.setEnabled(false);
                    marcaText.setEnabled(false);
                    modeloText.setEnabled(false);
                    precoText.setEnabled(false);
                    donosText.setEnabled(false);
                    descricaoText.setEnabled(false);

                    matriculaText.setText("--- Not Found");
                    marcaText.setText("--- Not Found");
                    modeloText.setText("--- Not Found");
                    precoText.setText("--- Not Found");
                    donosText.setText("--- Not Found");
                    descricaoText.setText("--- Not Found");
                }
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Vamos buscar os dados finais
                int id = Integer.parseInt(hidden.getText());
                String matriculaEdit = matriculaText.getText();
                String marcaEdit = marcaText.getText();
                String modeloEdit = modeloText.getText();
                String precoEdit = precoText.getText();
                String donosEdit = donosText.getText();
                String descricao = descricaoText.getText();

                // Fazemos a sua edição
                gestorVeiculos.editarVeiculos(matriculaEdit,marcaEdit,modeloEdit,precoEdit,donosEdit,descricao,id);

                // Procurar
                searchMatricula.setText(matriculaEdit);

            }
        });
        setVisible(true);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Veiculos veiculos = new Veiculos();
                dispose();
                FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, veiculos);
            }
        });
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
        VeiculosEditar veiculosEditar = new VeiculosEditar(null);
    }
}
