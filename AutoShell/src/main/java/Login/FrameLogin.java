package Login;

import Menus.FrameMenu;
import Veiculos.VeiculosInserir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FrameLogin extends JDialog{
    private JTextField userText;
    private JPanel loginPanel;
    private JPasswordField passwordText;
    private JButton login;
    public User user;

    public FrameLogin(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450,475));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = String.valueOf(passwordText.getPassword());

                user = getAuthenticateUser(username,password);

                if (user != null){
                    dispose();
                    JOptionPane.showMessageDialog(FrameLogin.this, "Bem vindo "+username);
                    FrameMenu menu = new FrameMenu(null);;
                } else{
                    JOptionPane.showMessageDialog(FrameLogin.this, "Credenciais erradas","Tente novamente",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setVisible(true);
    }

    private User getAuthenticateUser(String username, String password) {
    User user = null;

    final String DB_URL="jdbc:mysql://localhost/Projeto_ES";
    final String USERNAME="root";
    final String PASSWORD ="";

    try{
        // Testa a connecção
        Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

        Statement stat = conn.createStatement();
        System.out.println(conn);
        String sql = "SELECT * FROM users WHERE name=? AND password=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            user = new User();
            user.username = resultSet.getString("name");
            user.password = resultSet.getString("password");
        }

        stat.close();
        conn.close();

    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
        return user;
    }
}
