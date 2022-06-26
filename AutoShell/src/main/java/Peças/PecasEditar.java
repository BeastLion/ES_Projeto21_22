package Peças;

import Alerts.FailAlert;
import Alerts.SuccessAlert;
import Menus.FrameMenuGeralDinamico;
import Veiculos.GestorVeiculos;
import Veiculos.Veiculos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

public class PecasEditar extends JDialog {
    private JPanel VeiculoEditar;
    private JTable veiculosTable;
    private JTextField matriculaText;
    private JTextField marcaText;
    private JTextField modeloText;
    private JTextField precoText;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JTextField searchMatricula;
    private JTextField hidden;
    private JTextField donosText;
    private JTextField descricaoText;
    private JTextField linkText;
    private JTextField textPathHidden;
    private JButton carregarFotoButton;
    String header[] = {"ID","Matricula","Marca","Modelo","Preco","DonosAnt","Descricao"};

    public PecasEditar(JFrame parent) {
        super(parent);
        setTitle("Editar Veiculo");
        GestorPecas gestorPecas = new GestorPecas();
        setContentPane(VeiculoEditar);
        setMinimumSize(new Dimension(1366,768));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Hidden id field
        hidden.setVisible(false);
        textPathHidden.setVisible(false);

        // Desligamos a escrita
        matriculaText.setEnabled(false);
        marcaText.setEnabled(false);
        modeloText.setEnabled(false);
        precoText.setEnabled(false);
        donosText.setEnabled(false);
        descricaoText.setEnabled(false);
        confirmarButton.setEnabled(false);
        linkText.setEnabled(false);

        // Key Listener
        searchMatricula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                // Buscar os nossos dados baseados na nossa matricula
                String matricula = searchMatricula.getText();

                // Vamos buscar os nossos dados para por nos campos
                Object[] row = gestorPecas.selectPecasSKU(matricula);

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
                String path = textPathHidden.getText();

                if (Objects.equals(matriculaEdit, "") || Objects.equals(marcaEdit, "") || Objects.equals(modeloEdit, "") || Objects.equals(precoEdit, "")){
                    try {
                        FailAlert failAlert = new FailAlert(null,"TEM DE PREENCHER OS CAMPOS");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    return;
                }

                if (donosEdit == null){
                    donosEdit = "0";
                }
                if (path == null){
                    path = "/src/main/resources/default.jpg";
                }

                if (gestorPecas.checkSKUDuplicada(matriculaEdit) == 0) {

                    // Fazemos a sua edição
                    //gestorPecas.editarPecas(matriculaEdit, marcaEdit, modeloEdit, precoEdit, donosEdit, descricao, path, id);

                    // Procurar
                    searchMatricula.setText(matriculaEdit);

                    try {
                        SuccessAlert successAlert = new SuccessAlert(null, "VEICULOS EDITADO COM SUCESSO");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                } else{
                    try {
                        FailAlert failAlert = new FailAlert(null, "ESTA MATRICULA JÁ ESTÁ A SER USADA");
                    } catch (IOException ex) {
                        ex.printStackTrace();
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
        carregarFotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == carregarFotoButton) {
                    JFileChooser fileChooser = new JFileChooser();

                    int response = fileChooser.showOpenDialog(parent); // select file to open

                    if (response == JFileChooser.APPROVE_OPTION) {
                        FileSystem fileSys = FileSystems.getDefault();
                        Path srcPath = fileSys.getPath(fileChooser.getSelectedFile().getAbsolutePath());
                        Path destPath = fileSys.getPath(System.getProperty("user.dir") + "/src/main/resources/" + fileChooser.getSelectedFile().getName());
                        try {
                            Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
                            textPathHidden.setText("/src/main/resources/" + fileChooser.getSelectedFile().getName());
                            linkText.setText(System.getProperty("user.dir") + "/src/main/resources/" + fileChooser.getSelectedFile().getName());

                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                }

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
}
