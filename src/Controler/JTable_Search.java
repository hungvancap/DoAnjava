/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
    public boolean add(User s){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/test_db", "root", "");
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
