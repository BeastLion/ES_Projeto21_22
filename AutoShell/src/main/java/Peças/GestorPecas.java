package Peças;

import Alerts.FailAlert;
import Alerts.SuccessAlert;
import DBCONFIG.DB;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.sql.*;
import DBCONFIG.DB;

public class GestorPecas {
    DB db;

    // Iniciar a ligação da BD
    public GestorPecas() {
        this.db = new DB();
    }

    // Check Matricula
    public int checkSKUDuplicada(String matricula){
        try{

            Connection conn = DriverManager.getConnection(db.getDB_URL(),db.getUSERNAME(),db.getPASSWORD());

            Statement stat = conn.createStatement();

            String sql ="SELECT * FROM veiculos WHERE Matricula=?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,matricula);

            // Guardamos os nossos resultados na variavel
            ResultSet resultSet = preparedStatement.executeQuery();
            Object[] row = {null};

            // Vai para o 1º resultado guardado no query
            if (resultSet.next()) {
                // Guardamos numa linha
                row = new Object[]{resultSet.getInt("id"), resultSet.getString("Matricula"), resultSet.getString("Marca"), resultSet.getString("Modelo"), resultSet.getString("Preco"), resultSet.getString("DonosAnt"), resultSet.getString("Descricao"), resultSet.getString("Imagem")};
            }
            if (row == null){
                return 0;
            }
            System.out.println("Matricula no GESTOR VEICULOS " + matricula);
            System.out.println(row.length);
            if (row.length > 1){
                return -1;
            }
            // Fecha o statement
            stat.close();
            // Fecha a conecção
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    // Insere uma peca
    void insertPecas(String matricula, String marca, String modelo, String preco) {
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

    // Seleciona todos as pecas
    public void selectPecas(DefaultTableModel model){
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

    // Seleciona a peca especifica e retorna os seus valores num OBJECT ARRAY
    Object[] selectPecasSKU(String matricula){
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

    // Elimina uma peca por o id/sku
    public boolean eliminarPeca(String matricula) {
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

    // Edita uma peca baseado no seu sku
    public void editarPecas(String matricula, String marca, String modelo, String preco, int id) {
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

    // Exporta Peças para um CSV
    public void exportVeiculosToCSV(String query, String filename) throws IOException {
        try {
            Connection conn = DriverManager.getConnection(db.getDB_URL(),db.getUSERNAME(),db.getPASSWORD());

            Statement stat = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/src/main/resources/csv/" +filename + ".csv");

            ResultSet rs = preparedStatement.executeQuery(query);

            int cols = rs.getMetaData().getColumnCount();

            for(int i = 1; i <= cols; i ++){
                fw.append(rs.getMetaData().getColumnLabel(i));
                if(i < cols) fw.append(',');
                else fw.append('\n');
            }

            while (rs.next()) {

                for(int i = 1; i <= cols; i ++){
                    fw.append(rs.getString(i));
                    if(i < cols) fw.append(',');
                }
                fw.append('\n');
            }
            fw.flush();
            fw.close();
            conn.close();
            SuccessAlert successAlert = new SuccessAlert(null, "Peças EXPORTADAS COM SUCESSO");
        } catch (Exception e) {
            FailAlert failAlert = new FailAlert(null, "Não foi possivel EXPORTAR os dados");
        }
    }

    // Importa Peças para a nossa BD
    public void importCSVtoDB(String path){

        int batchSize = 20;

        try {

            Connection connection = DriverManager.getConnection(db.getDB_URL(),db.getUSERNAME(),db.getPASSWORD());

            BufferedReader lineReader = new BufferedReader(new FileReader(path));
            String lineText = null;

            int count = 0;

            lineReader.readLine(); // skip header line

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String matricula = data[0];
                String marca = data[1];
                String modelo = data[2];
                String preco = data[3];
                String descricao = data[4];

                if (checkSKUDuplicada(matricula) == 0) {
                    String sql = "INSERT INTO veiculos(Matricula,Marca,Modelo,Preco,Descricao) VALUES (?,?,?,?,?)";
                    PreparedStatement statement = connection.prepareStatement(sql);

                    statement.setString(1, matricula);
                    statement.setString(2, marca);
                    statement.setString(3, modelo);
                    statement.setString(4, preco);
                    statement.setString(5, descricao);
                    statement.executeUpdate();

                    SuccessAlert successAlert = new SuccessAlert(null, "Veiculo com a Matricula " + matricula + "Inserido com sucesso");
                } else {
                    FailAlert failAlert = new FailAlert(null, "O veiculo com a matricula: " + matricula + " Já se encontra na nossa base de dados");
                }
            }

            lineReader.close();
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
