package Menus;

import Cliente.Cliente;
import Estatistica.Estatisticas;
import Eventos.Eventos;
import Filial.Filial;
import Login.FrameLogin;
import Manutenção.Manutencao;
import Peças.Pecas;
import Transação.Transacao;
import Veiculos.Veiculos;
import Veiculos.VeiculosInserir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameMenu extends JDialog {
    private JButton veiculosButton;
    private JPanel panel1;
    private JButton manutencaoButton;
    private JButton peçasButton;
    private JButton filiaisButton;
    private JButton eventosButton;
    private JButton transaçãoButton;
    private JButton clientesButton;
    private JButton estatisticasButton;

    public FrameMenu(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(panel1);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        veiculosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Veiculos veiculos = new Veiculos();
                dispose();
                FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, veiculos);
            }
        });
        manutencaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manutencao manutencao = new Manutencao();
                dispose();
                FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, manutencao);
            }
        });
        peçasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pecas pecas = new Pecas();
                dispose();
                FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, pecas);
            }
        });
        filiaisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filial filial = new Filial();
                dispose();
                FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, filial);

            }
        });
        eventosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eventos eventos = new Eventos();
                dispose();
                FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, eventos);
            }
        });
        transaçãoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transacao transacao = new Transacao();
                dispose();
                FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, transacao);
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente();
                dispose();
                FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, cliente);
            }
        });
        estatisticasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Estatisticas estatisticas = new Estatisticas();
                dispose();
                //FrameMenuGeralDinamico menu = new FrameMenuGeralDinamico(null, estatisticas);
            }
        });
        setVisible(true);
    }
}


