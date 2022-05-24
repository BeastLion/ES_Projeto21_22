package DBCONFIG;

public class DB {
    final String DB_URL="jdbc:mysql://localhost/Projeto_ES";
    final String USERNAME="root";
    final String PASSWORD ="";

    public DB() {
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
