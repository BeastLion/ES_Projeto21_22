package Veiculos;

import DBCONFIG.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class GestorVeiculos {
    DB db;

    // Iniciar a ligação da BD
    public GestorVeiculos() {
        this.db = new DB();
    }

    // Insere um veiculo
    void insertVeiculos(String matricula, String marca, String modelo, String preco) {
        try{
            // Começa a conecção
            Connection conn = DriverManager.getConnection(db.getDB_URL(),db.getUSERNAME(),db.getPASSWORD());

            // O nosso comando SQL
            String sql = "INSERT INTO veiculos(Matricula,Marca,Modelo,Preco) VALUES (?,?,?,?)";

            // Preparamos o nosso comando SQL
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            // Linkamos as nossas variaveis ao SQL (?) proteção de Injections
            preparedStatement.setString(1,matricula);
            preparedStatement.setString(2,marca);
            preparedStatement.setString(3,modelo);
            preparedStatement.setString(4,preco);

            // Executa o comando (update para DML)
            preparedStatement.executeUpdate(); // Para DML

            // Fechamos a conecção
            conn.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Seleciona todos os veiculos
    public void selectVeiculos(DefaultTableModel model){
        try{

            Connection conn = DriverManager.getConnection(db.getDB_URL(),db.getUSERNAME(),db.getPASSWORD());

            Statement stat = conn.createStatement();

            String sql = "SELECT * FROM veiculos";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            // Guardamos os nossos resultados na variavel
            ResultSet resultSet = preparedStatement.executeQuery();

            // Vai para o 1º resultado guardado no query
            while (resultSet.next()){
                // Guardamos numa linha
                Object[] row = {resultSet.getInt("id"),resultSet.getString("Matricula"),resultSet.getString("Marca"),resultSet.getString("Modelo"),resultSet.getString("Preco")};
                // Implementamos essa linha no nosso modelo (tabela)
                model.addRow(row);
            }
            // Fecha o statement
            stat.close();
            // Fecha a conecção
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // Seleciona o veiculo especifico e retorna os seus valores num OBJECT ARRAY
    Object[] selectVeiculosMatricula(String matricula){
        try{

            Connection conn = DriverManager.getConnection(db.getDB_URL(),db.getUSERNAME(),db.getPASSWORD());

            Statement stat = conn.createStatement();

            String sql = "SELECT * FROM veiculos WHERE matricula=?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,matricula);

            // Guardamos os nossos resultados na variavel
            ResultSet resultSet = preparedStatement.executeQuery();

            // Vai para o 1º resultado guardado no query
            if (resultSet.next()){
                // Guardamos numa linha
                 Object[] row = {resultSet.getInt("id"),resultSet.getString("Matricula"),resultSet.getString("Marca"),resultSet.getString("Modelo"),resultSet.getString("Preco")};
                return row;
            }
            // Fecha o statement
            stat.close();
            // Fecha a conecção
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Object[] row = {null};
        return row;
    }

    // Elimina um veiculo por o id
    public boolean eliminarVeiculo(String matricula) {
        try{

            Connection conn = DriverManager.getConnection(db.getDB_URL(),db.getUSERNAME(),db.getPASSWORD());

            Statement stat = conn.createStatement();

            String sql = "SELECT ID FROM veiculos WHERE matricula=?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1,matricula);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");

                sql = "DELETE FROM veiculos WHERE ID=?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate(); // Para DML

                // Fecha o statement
                stat.close();
                // Fecha a conecção
                conn.close();

                return true;
            }

            // Fecha o statement
            stat.close();
            // Fecha a conecção
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // Edita um veiculo baseado no seu id
    public void editarVeiculos(String matricula, String marca, String modelo, String preco, int id) {
        try{
            // Começa a conecção
            Connection conn = DriverManager.getConnection(db.getDB_URL(),db.getUSERNAME(),db.getPASSWORD());

            // O nosso comando SQL
            String sql = "UPDATE veiculos SET matricula=?, marca=?, modelo=?, preco=? WHERE id=?";

            // Preparamos o nosso comando SQL
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            // Linkamos as nossas variaveis ao SQL (?) proteção de Injections
            preparedStatement.setString(1,matricula);
            preparedStatement.setString(2,marca);
            preparedStatement.setString(3,modelo);
            preparedStatement.setString(4,preco);
            preparedStatement.setInt(5,id);

            // Executa o comando (update para DML)
            int resultSet = preparedStatement.executeUpdate(); // Para DML
            System.out.println(resultSet);

            // Fechamos a conecção
            conn.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
