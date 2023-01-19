package db;

import Animals.AnimalDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static db.Conn.connect;

public class DBService {
    private Connection connect;
    public static String CREATE_TABLE =
            "CREATE TABLE animals "
                    + "(animal_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    + "animal_name TEXT NOT NULL,"
                    + "animal_type TEXT NOT NULL,"
                    + "commands TEXT,"
                    + "date_of_birth DATE);";

    public void creatTable() throws SQLException, ClassNotFoundException {
        this.connect = connect();
        try (Connection connection = this.connect) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_TABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertAnimal(AnimalDto animalDto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO animals (animal_name, animal_type, commands, date_of_birth) " +
                "VALUES (?,?,?,?);";
        this.connect = connect();
        try (Connection connection = this.connect;
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, animalDto.getName());
            pstmt.setString(2, String.valueOf(animalDto.getType()));
            pstmt.setString(3, animalDto.getCommands());
            pstmt.setDate(4, animalDto.getDate_of_birth());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
