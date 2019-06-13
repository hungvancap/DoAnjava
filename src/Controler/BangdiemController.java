/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DBConnection;
import View.Bangdiemlophoc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class BangdiemController {
    private Bangdiemlophoc bangdiem;private String s;private JTable jTableView;
    public BangdiemController(Bangdiemlophoc bangdiem,JTable jTableView, String selection) {
        this.bangdiem=bangdiem;this.s=selection;
        this.jTableView=jTableView;
        
    }
    public void setEvent() throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement stm=conn.createStatement();
        // Khoi tao bang:
        String []title={"Mã số sinh viên","Họ tên","Điểm quá trình","Điểm giữa kì","Điểm thực hành","Điểm cuối kì"};
        DefaultTableModel model=new DefaultTableModel(title,0);
        
            String sql1="select distinct sinhvien.mssv,sinhvien.hotensv, bangdiema1.tongdiema1,bangdiema2.tongdiema2"
                            + ",bangdiema3.tongdiema3,bangdiema4.tongdiema4,bangdiema1.malop\n" +
                            "from bangdiema1 ,bangdiema2, bangdiema3, bangdiema4 , sinhvien  , sv_lophoc\n" +
                            "where bangdiema1.malop=sv_lophoc.malophoc \n" +
                            "and bangdiema2.malop=sv_lophoc.malophoc \n" +
                            "and bangdiema3.malop=sv_lophoc.malophoc \n" +
                            "and bangdiema4.malop=sv_lophoc.malophoc\n" +
                            "and sv_lophoc.mssv=sinhvien.mssv \n" +
                            "and sv_lophoc.malophoc='"+s+"'";                          
            ResultSet rs1=stm.executeQuery(sql1);
            while(rs1.next())
                          {
                              Vector vt=new Vector();
                              vt.add(rs1.getString("mssv"));
                              vt.add(rs1.getString("hotensv"));
                              vt.add(rs1.getString("tongdiema1"));
                              vt.add(rs1.getString("tongdiema2"));
                              vt.add(rs1.getString("tongdiema3"));
                              vt.add(rs1.getString("tongdiema4"));
                              model.addRow(vt);
                              jTableView.setModel(model);
                                      
                          }
            rs1.close();
                    
        
    }
}
