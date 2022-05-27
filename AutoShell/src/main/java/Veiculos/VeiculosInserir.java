package Veiculos;

import DBCONFIG.DB;
import Menus.FrameMenuGeralDinamico;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.*;
import java.util.Objects;

public class VeiculosInserir extends  JDialog{
    private JTable veiculosTable;
    private JPanel veiculosPanel;
    private JTextField precoText;
    private JTextField modeloText;
    private JTextField marcaText;
    private JTextField matriculaText;
    private JTextField donosAntText;
    private JTextField descricaoText;
    private JLabel matriculaLabel;
    private JLabel marcaLabel;
    private JLabel modeloLabel;
    private JLabel precoLabel;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JButton carregarFotoButton;
    private JTextField linkText;
    private JPanel teste;
    private JTextField textPathHidden;
    private JLabel picLabel;
    private String destRelPath;
    String header[] = {"ID","Matricula","Marca","Modelo","Preco","DonosAnt","Descricao"};


    public VeiculosInserir(JFrame parent) throws IOException {
        super(parent);
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        setTitle("Inserir Veiculo");
        setContentPane(veiculosPanel);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        linkText.setEnabled(false);
        textPathHidden.setVisible(false);
        confirmarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Buscar a informação dos campos
            String matricula = matriculaText.getText();
            String marca = marcaText.getText();
            String modelo = modeloText.getText();
            String preco = precoText.getText();
            String donosAnt = donosAntText.getText();
            String descricao = descricaoText.getText();
            String path = textPathHidden.getText();

            if (Objects.equals(matricula, "") || Objects.equals(marca, "") || Objects.equals(modelo, "") || Objects.equals(preco, "")){
                JOptionPane.showMessageDialog(VeiculosInserir.this, "Não pode inserir sem preencher os campos","Preencha todos os campos",JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (donosAnt == null){
                donosAnt = "0";
            }
            if (path == null){
                destRelPath = "/src/main/resources/default.jpg";
            }

            // Chamar a função de inserção
            gestorVeiculos.insertVeiculos(matricula,marca,modelo,preco, donosAnt, descricao, path);

            // Resetar os campos após inserção
            matriculaText.setText("");
            marcaText.setText("");
            modeloText.setText("");
            precoText.setText("");
            donosAntText.setText("");
            descricaoText.setText("");

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

    public static void main(String[] args) throws IOException {
        VeiculosInserir veiculosInserir = new VeiculosInserir(null);
    }
}
