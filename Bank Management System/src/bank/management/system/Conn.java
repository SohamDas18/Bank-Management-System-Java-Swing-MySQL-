package bank.management.system;

import java.sql.*;

public class Conn {
    public Connection connection;
    public Statement statement;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/banksystem", // DB name
                    "root", // username
                    "SohamDas12345@" // password
            );
            statement = connection.createStatement(); // so you can still use executeUpdate
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


