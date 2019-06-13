/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class LopsinhhoatController {

    private String selection;private JTable jtableView;
    public LopsinhhoatController(JTable jtableView, String selection) {
        this.selection=selection;this.jtableView=jtableView;
    }
    public void setEvent(){
        try {
            String []title={"Mã lớp","Tên môn","Điểm quá trình","Điểm giữa kì","Điểm thực hành","Điểm cuối kì"};
            DefaultTableModel model=new DefaultTableModel(title,0);
            Connection conn=DBConnection.getConnection();
            Statement stm=conn.createStatement();
            String sql1="select distinct * from sinhvien where malopsh='"+selection+"'";
            ArrayList arr=new ArrayList();
            ResultSet rs1=stm.executeQuery(sql1);
            while(rs1.next())
            {
                arr.add(rs1.getString("hotensv"));
            }
            rs1.close();
            Object[] ob=new Object[arr.size()];
            for(int i=0;i<arr.size();i++)
            {
                ob[i]=arr.get(i);
            }
            String name=(String) JOptionPane.showInputDialog(null,"Xin mời chọn sinh viên:","Selection!!!", JOptionPane.PLAIN_MESSAGE,null, ob,"");
            String sql2="select distinct sv_lophoc.malophoc,lophoc.tenlop,bangdiema1.tongdiema1,bangdiema2.tongdiema2,bangdiema3.tongdiema3,bangdiema4.tongdiema4\n" +
                        "from sv_lophoc,lophoc,bangdiema1,bangdiema2,bangdiema3,bangdiema4,lopsinhhoat,sinhvien\n" +
                        "where bangdiema1.malop=sv_lophoc.malophoc \n" +
                        "and bangdiema2.malop=sv_lophoc.malophoc \n" +
                        "and bangdiema3.malop=sv_lophoc.malophoc \n" +
                        "and bangdiema4.malop=sv_lophoc.malophoc\n" +
                        "and sv_lophoc.mssv=sinhvien.mssv\n" +
                        "and lophoc.malophoc=sv_lophoc.malophoc\n" +
                        "and sinhvien.malopsh=lopsinhhoat.malopsh\n" +
                        "and sinhvien.hotensv='"+name+"'" +
                        "and lopsinhhoat.malopsh='"+selection+"'";
            ResultSet rs2=stm.executeQuery(sql2);
            while (rs2.next()) {                
                Vector vt=new Vector();
                vt.add(rs2.getString("malophoc"));
                vt.add(rs2.getString("tenlop"));
                vt.add(rs2.getString("tongdiema1"));
                vt.add(rs2.getString("tongdiema2"));
                vt.add(rs2.getString("tongdiema3"));
                vt.add(rs2.getString("tongdiema4"));
                model.addRow(vt);
                jtableView.setModel(model);
            }
            rs2.close();stm.close();conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(LopsinhhoatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
