package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static db.ConnectData.*;

public class Conn {
    public static final String CREATE_TABLE =
            "CREATE TABLE animals "
                    + "(animal_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "animal_name TEXT NOT NULL,"
                    + "animal_type TEXT NOT NULL,"
                    + "commands TEXT,"
                    + "date_of_birth DATE);";

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertAnimal(){

    }
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(DRIVER);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_TABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
