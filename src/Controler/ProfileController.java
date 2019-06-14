/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DBConnection;
import Model.LOi;
import View.ProfilePanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author LENOVO
 */
public class ProfileController {

    private String mssv;
    private ProfilePanel profilepanel;
    private javax.swing.JLabel jLbGioiTinh;
    private javax.swing.JLabel jLbKhoa;
    private javax.swing.JLabel jLbLop;
    private javax.swing.JLabel jLbMssv;
    private javax.swing.JLabel jLbNganh;
    private javax.swing.JLabel jLbNgaySinh;
    private javax.swing.JLabel jLbTen;
    private JLabel jlbTrangThai;
    private  JProgressBar jpg1;
    private  JProgressBar jpg2;
    private  JProgressBar jpg3;
    private  JProgressBar jpg4;
    private  JProgressBar jpg5;
    private  JProgressBar jpg6;
    private  JProgressBar jpg8;
    private  JProgressBar jpg7;
    private  JProgressBar jpg9;
    private  JProgressBar jpg10;
    private final JLabel jlbl1;
    private final JLabel jlbl2;
    private final JLabel jlbl5;
    private final JLabel jlbl3;
    private final JLabel jlbl7;
    private final JLabel jlbl4;
    private final JLabel jlbl6;
    private final JLabel jlbl8;
    private final JLabel jlbl9;
    private final JLabel jlbl10;

    public ProfileController(String mssv, ProfilePanel profilepanel, JLabel jLbTen, JLabel jLbMssv, JLabel jLbGioiTinh, JLabel jLbNgaySinh, JLabel jLbNganh, JLabel jLbLop, JLabel jlbTrangThai,JProgressBar jpg1,JProgressBar jpg2,JProgressBar jpg3,JProgressBar jpg4,JProgressBar jpg5,JProgressBar jpg6,JProgressBar jpg7,JProgressBar jpg8,JProgressBar jpg9,JProgressBar jpg10,JLabel jlbl1,JLabel jlbl2,JLabel jlbl3,JLabel jlbl4,JLabel jlbl5,JLabel jlbl6,JLabel jlbl7,JLabel jlbl8,JLabel jlbl9,JLabel jlbl10) {
        this.mssv = mssv;
        this.jLbTen = jLbTen;
        this.jLbMssv = jLbMssv;
        this.jLbGioiTinh = jLbGioiTinh;
        this.jLbNgaySinh = jLbNgaySinh;
        this.jLbNganh = jLbNganh;
        this.jLbLop = jLbLop;
        this.jlbTrangThai = jlbTrangThai;
        this.jpg1=jpg1;
        this.jpg2=jpg2;
        this.jpg3=jpg3;
        this.jpg4=jpg4;
        this.jpg5=jpg5;
        this.jpg6=jpg6;
        this.jpg7=jpg7;
        this.jpg8=jpg8;
        this.jpg9=jpg9;
        this.jpg10=jpg10;
        this.jlbl1=jlbl1;
        this.jlbl2=jlbl2;
        this.jlbl3=jlbl3;
        this.jlbl4=jlbl4;
        this.jlbl5=jlbl5;
        this.jlbl6=jlbl6;
        this.jlbl7=jlbl7;
        this.jlbl8=jlbl8;
        this.jlbl9=jlbl9;
        this.jlbl10=jlbl10;
    }

    public void setEven() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM (sinhvien join lopsinhhoat on sinhvien.malopsh = lopsinhhoat.malopsh) join nganh on sinhvien.nganh = nganh.manganh WHERE mssv = '" + mssv + "'";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ten = rs.getString("hotensv");
                jLbTen.setText(ten);
                String mssv = rs.getString("mssv");
                jLbMssv.setText(mssv);
                String gioitinh;
                if (rs.getInt("gioitinh") == 1) {
                    gioitinh = "Nam";
                } else {
                    gioitinh = "Nữ";
                }
                jLbGioiTinh.setText(gioitinh);
                String ngaysinh = rs.getString("ngaysinh");
                jLbNgaySinh.setText(ngaysinh);
                String nganh = rs.getString("tennganh");
                jLbNganh.setText(nganh);
                String lop = rs.getString("tenlop");
                jLbLop.setText(lop);
                if (rs.getInt("totnghiep") == 1) {
                    jlbTrangThai.setText("Đã tốt nghiệp");
                    
                    LOi loi=new LOi();
                    double[] value=loi.tiledatloi(mssv);
//                    for (int i=0;i<11;i++){
//                        System.out.println((int)(value[i]*100)+"  ");
//                    }
                    jpg1.setValue((int)(value[1]*100));
                    jpg2.setValue((int)(value[2]*100));
                    jpg3.setValue((int)(value[3]*100));
                    jpg4.setValue((int)(value[4]*100));
                    jpg5.setValue((int)(value[5]*100));
                    jpg6.setValue((int)(value[6]*100));
                    jpg7.setValue((int)(value[7]*100));
                    jpg8.setValue((int)(value[8]*100));
                    jpg9.setValue((int)(value[9]*100));
                    jpg10.setValue((int)(value[10]*100));
                    jpg1.setVisible(true);
                    jpg2.setVisible(true);
                    jpg3.setVisible(true);
                    jpg4.setVisible(true);
                    jpg5.setVisible(true);
                    jpg6.setVisible(true);
                    jpg7.setVisible(true);
                    jpg8.setVisible(true);
                    jpg9.setVisible(true);
                    jpg10.setVisible(true);
                    jlbl1.setVisible(true);
                    jlbl2.setVisible(true);
                    jlbl3.setVisible(true);
                    jlbl4.setVisible(true);
                    jlbl5.setVisible(true);
                    jlbl6.setVisible(true);
                    jlbl7.setVisible(true);
                    jlbl8.setVisible(true);
                    jlbl9.setVisible(true);
                    jlbl10.setVisible(true);
                } else {
                    jlbTrangThai.setText("Chưa tốt nghiệp");
                }
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
