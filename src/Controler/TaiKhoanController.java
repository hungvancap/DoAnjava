package Controler;

import Model.DBConnection;
import View.FrameGiangVien;
import View.LoginForm;
import View.FrameSinhVien;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TaiKhoanController {

    private LoginForm dialog;
    private JButton btnSubmit;
    private JTextField jtfTenDangNhap;
    private JPasswordField jtfMatKhau;
    private JLabel jlbMsg;

    public TaiKhoanController(LoginForm dialog, JButton btnSubmit, JTextField jtfTenDangNhap,
            JPasswordField jtfMatKhau, JLabel jlbMsg) {
        this.dialog = dialog;
        this.btnSubmit = btnSubmit;
        this.jtfTenDangNhap = jtfTenDangNhap;
        this.jtfMatKhau = jtfMatKhau;
        this.jlbMsg = jlbMsg;

    }

    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jtfTenDangNhap.getText().length() == 0 || jtfMatKhau.getText().length() == 0) {
                    jlbMsg.setText("Chưa điền đủ thông tin!");
                } else {
                    try {
                        Connection cons = DBConnection.getConnection();
                        String sql1 = "SELECT magv,pass,hotengv FROM giangvien WHERE magv='" + jtfTenDangNhap.getText().toString() + "' and pass='" + jtfMatKhau.getText().toString() + "'";
                        String sql2 = "SELECT mssv,pass,hotensv FROM sinhvien WHERE mssv='" + jtfTenDangNhap.getText().toString() + "' and pass='" + jtfMatKhau.getText().toString() + "'";
                        PreparedStatement ps1 = cons.prepareCall(sql1);
                        PreparedStatement ps2 = cons.prepareCall(sql2);
                        ResultSet rs1 = ps1.executeQuery();
                        ResultSet rs2 = ps2.executeQuery();
                        if (rs1.next()) {
                            String name=rs1.getString("hotengv");
                            FrameGiangVien jfGiangVien = new FrameGiangVien();
                            jfGiangVien.setLocationRelativeTo(null);
                            jfGiangVien.setVisible(true);
                            dialog.dispose();

                        } else if (rs2.next()) {
                            String name=rs2.getString("hotensv");
                            String mssv = rs2.getString("mssv");
                            FrameSinhVien jfSinhVien = new FrameSinhVien(mssv);
                            jfSinhVien.setLocationRelativeTo(null);
                            jfSinhVien.setVisible(true);
                            dialog.dispose();
                        } else {
                            jlbMsg.setText("Sai thông tin đăng nhập!");
                        }
                        ps1.close();
                        rs2.close();
                        ps1.close();
                        rs2.close();
                        cons.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    
                }
            }
        });

    }

}
