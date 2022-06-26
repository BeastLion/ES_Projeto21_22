package Menus;

import Cliente.Cliente;
import Eventos.Eventos;
import Filial.Filial;
import Manutenção.Manutencao;
import Peças.Pecas;
import Peças.PecasConsultar;
import Transação.Transacao;
import Veiculos.Veiculos;
import Veiculos.VeiculosInserir;
import Veiculos.VeiculosEliminar;
import Veiculos.VeiculosEditar;
import Veiculos.VeiculosConsultar;
import Veiculos.GestorVeiculos;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.*;

public class FrameMenuGeralDinamico extends JDialog {
    private JTable tab;
    private JButton consultarButton;
    private JButton inserirButton;
    private JButton eliminarButton;
    private JButton editarButton;
    private JButton importarButton;
    private JButton exportarButton;
    private JButton voltarButton;
    private JPanel MenuDireita;
    private JPanel MenuEsquerda;
    private JPanel painelPrincipal;
    private JPanel MenuVeiculo;

    String header[] = {"ID","Matricula","Marca","Modelo","Preco"};

    public FrameMenuGeralDinamico(JFrame parent, Veiculos veiculos){
        super(parent);
        setTitle("Menu Veiculo");
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vai para a página de Consultar
                dispose();
                VeiculosConsultar veiculosConsultar = new VeiculosConsultar(null);
            }
        });
        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vai para a página de Inserir
                dispose();
                try {
                    VeiculosInserir veiculosInserir = new VeiculosInserir(null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VeiculosEditar veiculosEditar = new VeiculosEditar(null);
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vai para a página de eliminar
                dispose();
                VeiculosEliminar veiculosEliminar = new VeiculosEliminar(null);
            }
        });
        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == importarButton) {
                    JFileChooser fileChooser = new JFileChooser();

                    int response = fileChooser.showOpenDialog(parent); // select file to open

                    if (response == JFileChooser.APPROVE_OPTION) {
                        FileSystem fileSys = FileSystems.getDefault();
                        Path srcPath = fileSys.getPath(fileChooser.getSelectedFile().getAbsolutePath());
                        GestorVeiculos gestorVeiculos = new GestorVeiculos();
                        System.out.println(srcPath.toString());
                        gestorVeiculos.importCSVtoDB(srcPath.toString());
                    }
                }
            }
        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorVeiculos gestorVeiculos = new GestorVeiculos();
                try {
                    gestorVeiculos.exportVeiculosToCSV("SELECT * FROM VEICULOS","veiculosExport");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Manda de volta para trás
                dispose();
                FrameMenu menu = new FrameMenu(null);
            }
        });
        setVisible(true);
    }

    public FrameMenuGeralDinamico(JFrame parent, Pecas Pecas){
        super(parent);
        setTitle("Menu Peças");
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vai para a página de Consultar
                dispose();
                PecasConsultar pecasConsultar = new PecasConsultar(null);
            }
        });
        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public FrameMenuGeralDinamico(JFrame parent, Transacao transacao){
        super(parent);
        setTitle("Menu Transações");
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public FrameMenuGeralDinamico(JFrame parent, Manutencao manutencao){
        super(parent);
        setTitle("Menu Manutenção");
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public FrameMenuGeralDinamico(JFrame parent, Eventos eventos){
        super(parent);
        setTitle("Menu Eventos");
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public FrameMenuGeralDinamico(JFrame parent, Filial filial){
        super(parent);
        setTitle("Menu Filiais");
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public FrameMenuGeralDinamico(JFrame parent, Cliente cliente){
        super(parent);
        setTitle("Menu Clientes");
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exportarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        DefaultTableModel model = new DefaultTableModel(0, header.length);
        model.setColumnIdentifiers(header);
        tab = new JTable(model);
        gestorVeiculos.selectVeiculos(model);
    }

    public static void main(String[] args) {
        Veiculos veiculos = null;
        FrameMenuGeralDinamico frameMenuGeralDinamico = new FrameMenuGeralDinamico(null,veiculos);
    }
}
