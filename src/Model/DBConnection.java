package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    public static Connection getConnection() {
        
        
        try {
            Connection cons = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            cons = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_schema","root","12345");
            
//            cons = DriverManager.getConnection("jdbc:mysql://localhost:3307/doanjava", "root", "11111999");
            return cons;
        } catch (Exception ex) {
            ex.printStackTrace();
       
        }
        return null;
    }
    
    public static void main(String[] args) throws SQLException {
        Connection c = getConnection();
        c.close();
    }

}
