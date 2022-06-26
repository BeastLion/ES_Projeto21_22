package Filial;

import DBCONFIG.DB;

import java.sql.*;

public class GestorFiliais {
    DB db;

    // Iniciar a ligação da BD
    public GestorFiliais() {
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
                row = new Object[]{resultSet.getInt("ID"), resultSet.getString("Nome"), resultSet.getString("Localização"), resultSet.getInt("Stock MAX"), resultSet.getInt("Viaturas MAX"), resultSet.getString("Website"), resultSet.getString("Instagram")};
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
}
