package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {

    public static Connection getConnection() {
        String connString = DBPropertyUtil.getConnString();
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connString);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver is not loaded..");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Connection could not be established..");
            e.printStackTrace();
        }
        return con;
    }
}
