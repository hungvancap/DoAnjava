package Model;

import View.ConnectForm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    public static Connection getConnection() {
        ConnectForm getdata=new ConnectForm(null, true);
        getdata.setVisible(true);
        String[] data=getdata.getData();
        try {
            Connection cons = null;
            Class.forName("com.mysql.jdbc.Driver");
            cons = DriverManager.getConnection("jdbc:mysql://"+data[0]+":3306/"+data[1], data[2], data[3]);
//            cons = DriverManager.getConnection("jdbc:mysql://localhost:3306/doanjava", "root", "thuan920");
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
