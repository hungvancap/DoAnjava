/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DBConnection;
import View.FrameSinhVien;
import View.LOPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;

/**
 *
 * @author LENOVO
 */
public class LOController {

    private String mssv;
    private LOPanel lopanel;
    private javax.swing.JLabel jLbNganh;
    private javax.swing.JLabel jLb1;
    private javax.swing.JLabel jLb10;
    private javax.swing.JLabel jLb2;
    private javax.swing.JLabel jLb3;
    private javax.swing.JLabel jLb4;
    private javax.swing.JLabel jLb5;
    private javax.swing.JLabel jLb6;
    private javax.swing.JLabel jLb7;
    private javax.swing.JLabel jLb8;
    private javax.swing.JLabel jLb9;

    public LOController(String mssv, LOPanel lopanel, JLabel jLbNganh, JLabel jLb1, JLabel jLb2, JLabel jLb3, JLabel jLb4, JLabel jLb5, JLabel jLb6, JLabel jLb7, JLabel jLb8, JLabel jLb9, JLabel jLb10) {
        this.lopanel = lopanel;
        this.jLbNganh = jLbNganh;
        this.jLb1 = jLb1;
        this.jLb2 = jLb2;
        this.jLb3 = jLb3;
        this.jLb4 = jLb4;
        this.jLb5 = jLb5;
        this.jLb6 = jLb6;
        this.jLb7 = jLb7;
        this.jLb8 = jLb8;
        this.jLb9 = jLb9;
        this.jLb10 = jLb10;
        this.mssv = mssv;
    }

    public void setEvent() {
        try {
            //Kết nối DB
            Connection cons = DBConnection.getConnection();
            //Kiểm tra ngành học bằng mssv để in ra Label jLbNganh trong LOPanel
            String sql = "SELECT nganh FROM sinhvien WHERE mssv = '" + mssv + "'";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nganh = rs.getString("nganh");
                if (nganh.toString().equals("CNTT")) {
                    jLbNganh.setText("Chuẩn đầu ra (Learning Outcome) ngành Công nghệ Thông tin");
                } else {
                    jLbNganh.setText("Chuẩn đầu ra (Learning Outcome) ngành Khoa học Dữ liệu");
                }
                //Lấy LO 1 từ DB
                String sql1 = "SELECT lo1 FROM nganh WHERE manganh = '" + nganh + "'";
                PreparedStatement ps1 = cons.prepareCall(sql1);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    String lo1 = rs1.getString("lo1");
                    jLb1.setText(lo1);
                }
                //Lấy LO 2 từ DB
                String sql2 = "SELECT lo2 FROM nganh WHERE manganh = '" + nganh + "'";
                PreparedStatement ps2 = cons.prepareCall(sql2);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    String lo2 = rs2.getString("lo2");
                    jLb2.setText(lo2);
                }
                //Lấy LO 3 từ DB
                String sql3 = "SELECT lo3 FROM nganh WHERE manganh = '" + nganh + "'";
                PreparedStatement ps3 = cons.prepareCall(sql3);
                ResultSet rs3 = ps3.executeQuery();
                if (rs3.next()) {
                    String lo3 = rs3.getString("lo3");
                    jLb3.setText(lo3);
                }
                //Lấy LO 4 từ DB
                String sql4 = "SELECT lo4 FROM nganh WHERE manganh = '" + nganh + "'";
                PreparedStatement ps4 = cons.prepareCall(sql4);
                ResultSet rs4 = ps4.executeQuery();
                if (rs4.next()) {
                    String lo4 = rs4.getString("lo4");
                    jLb4.setText(lo4);
                }
                //Lấy LO 5 từ DB
                String sql5 = "SELECT lo5 FROM nganh WHERE manganh = '" + nganh + "'";
                PreparedStatement ps5 = cons.prepareCall(sql5);
                ResultSet rs5 = ps5.executeQuery();
                if (rs5.next()) {
                    String lo5 = rs5.getString("lo5");
                    jLb5.setText(lo5);
                }
                //Lấy LO 6 từ DB
                String sql6 = "SELECT lo6 FROM nganh WHERE manganh = '" + nganh + "'";
                PreparedStatement ps6 = cons.prepareCall(sql6);
                ResultSet rs6 = ps6.executeQuery();
                if (rs6.next()) {
                    String lo6 = rs6.getString("lo6");
                    jLb6.setText(lo6);
                }
                //Lấy LO 7 từ DB
                String sql7 = "SELECT lo7 FROM nganh WHERE manganh = '" + nganh + "'";
                PreparedStatement ps7 = cons.prepareCall(sql7);
                ResultSet rs7 = ps7.executeQuery();
                if (rs7.next()) {
                    String lo7 = rs7.getString("lo7");
                    jLb7.setText(lo7);
                }
                //Lấy LO 8 từ DB
                String sql8 = "SELECT lo8 FROM nganh WHERE manganh = '" + nganh + "'";
                PreparedStatement ps8 = cons.prepareCall(sql8);
                ResultSet rs8 = ps8.executeQuery();
                if (rs8.next()) {
                    String lo8 = rs8.getString("lo8");
                    jLb8.setText(lo8);
                }
                //Lấy LO 9 từ DB
                String sql9 = "SELECT lo9 FROM nganh WHERE manganh = '" + nganh + "'";
                PreparedStatement ps9 = cons.prepareCall(sql9);
                ResultSet rs9 = ps9.executeQuery();
                if (rs9.next()) {
                    String lo9 = rs9.getString("lo9");
                    jLb9.setText(lo9);
                }
                //Lấy LO 10 từ DB
                String sql10 = "SELECT lo10 FROM nganh WHERE manganh = '" + nganh + "'";
                PreparedStatement ps10 = cons.prepareCall(sql10);
                ResultSet rs10 = ps10.executeQuery();
                if (rs10.next()) {
                    String lo10 = rs10.getString("lo10");
                    jLb10.setText(lo10);
                }
                ps1.close();
                rs1.close();
                ps2.close();
                rs2.close();
                ps3.close();
                rs3.close();
                ps4.close();
                rs4.close();
                ps5.close();
                rs5.close();
                ps6.close();
                rs6.close();
                ps7.close();
                rs7.close();
                ps8.close();
                rs8.close();
                ps9.close();
                rs9.close();
                ps10.close();
                rs10.close();
            }
            ps.close();
            rs.close();
            cons.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
