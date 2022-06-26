package Estatistica;

import DBCONFIG.DB;

import java.sql.*;

public class GestorEstatistica {
    DB db;

    // Iniciar a ligação da BD
    public GestorEstatistica() {
        this.db = new DB();
    }

    // Check nif
    public int checkMatriculaDuplicada(int nif) {
        try {

            Connection conn = DriverManager.getConnection(db.getDB_URL(), db.getUSERNAME(), db.getPASSWORD());
            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM veiculos WHERE nif=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, nif);
            // Guardamos os nossos resultados na variavel
            ResultSet resultSet = preparedStatement.executeQuery();
            Object[] row = {null};
            // Vai para o 1º resultado guardado no query
            if (resultSet.next()) {
                // Guardamos numa linha
                row = new Object[]{resultSet.getInt("Numero Cliente"), resultSet.getString("Nome"), resultSet.getString("Email"), resultSet.getString("Sede/Filial"), resultSet.getInt("NIF"), resultSet.getInt("Telemovel")};
            }
            if (row == null) {
                return 0;
            }
            if (row.length > 1) {
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

    Object[] topMarcaVendidas(){
        try{

            Connection conn = DriverManager.getConnection(db.getDB_URL(),db.getUSERNAME(),db.getPASSWORD());

            Statement stat = conn.createStatement();

            String sql = "SELECT COUNT(marca) FROM veiculos WHERE id IN (SELECT veiculoId FROM transacao where )";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            // Guardamos os nossos resultados na variavel
            ResultSet resultSet = preparedStatement.executeQuery();

            // Vai para o 1º resultado guardado no query
            if (resultSet.next()){
                // Guardamos numa linha
                Object[] row = {resultSet.getInt("id"),resultSet.getString("Matricula"),resultSet.getString("Marca"),resultSet.getString("Modelo"),resultSet.getString("Preco"),resultSet.getString("DonosAnt"),resultSet.getString("Descricao"),resultSet.getString("Imagem")};
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
}
