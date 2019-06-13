/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DBConnection;
import Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class JTable_Search  {

    /**
     * Creates new form JTable_Search
     */
    
    private Connection conn;
//    public JTable_Search() {
//        initComponents();
//        
//        // call findUsers function
//        findUsers();
//        
//    }
//     public static Connection getMySQLConnection() throws SQLException,
//         ClassNotFoundException {
//     String hostName = "localhost";
// 
//     String dbName = "new_schema";
//     String userName = "root";
//     String password = "12345";
// 
//     return getMySQLConnection(hostName, dbName, userName, password);
// }
// 
// public static Connection getMySQLConnection(String hostName, String dbName,
//         String userName, String password) throws SQLException,
//         ClassNotFoundException {
//     // Khai báo class Driver cho DB MySQL
//     // Việc này cần thiết với Java 5
//     // Java6 tự động tìm kiếm Driver thích hợp.
//     // Nếu bạn dùng Java6, thì ko cần dòng này cũng được.
//     Class.forName("com.mysql.jdbc.Driver");
// 
//     // Cấu trúc URL Connection dành cho Oracle
//     // Ví dụ: jdbc:mysql://localhost:3306/simplehr
//     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
// 
//     Connection conn = DriverManager.getConnection(connectionURL, userName,
//             password);
//     return conn;
// }
    public boolean add(User s){
        try {
            conn = DBConnection.getConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        String sql = "INSERT INTO student(mssv, malop, diemcau1, diemcau2, diemcau3, tongdiema4) "
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(0, s.getmssv());
            ps.setString(1, s.getmalop());
            ps.setFloat(2, s.getdiemcau1());
            ps.setFloat(3, s.getdiemcau2());
            ps.setFloat(4, s.getdiemcau3());
            ps.setFloat(5, s.gettongdiema4());
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
