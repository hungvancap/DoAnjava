/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DBConnection;
import View.CoursePanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class CourseController {

    private String mssv;
    private CoursePanel coursepanel;
    private JTable jTTile;
    private GiController gicontroller;

    public CourseController(String mssv, CoursePanel coursepanel, JTable jTTile) {
        this.mssv = mssv;
        this.coursepanel = coursepanel;
        this.jTTile = jTTile;
    }

    public void setEvent() {
        try {
            String[] arr = {"Mã môn học", "Tên môn học", "Tỉ lệ đạt G1", "Tỉ lệ đạt G2", "Tỉ lệ đạt G3", "Tỉ lệ đạt G4"};
            DefaultTableModel model = new DefaultTableModel(arr, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            Connection cons = DBConnection.getConnection();
            System.out.println();
            String sql = "SELECT lophoc.mamon, tenmon FROM (((sinhvien join sv_lophoc on sinhvien.mssv = sv_lophoc.mssv) join lophoc on lophoc.malophoc = sv_lophoc.malophoc) join monhoc on lophoc.mamon = monhoc.mamon) WHERE sinhvien.mssv = '" + mssv + "'";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getString("mamon"));
                vt.add(rs.getString("tenmon"));
                vt.add("");
                vt.add("");
                vt.add("");
                vt.add("");
                model.addRow(vt);
                jTTile.setModel(model);
            }
            ps.close();
            rs.close();
            GiController ctrl = new GiController();
            for (int i = 0; i < jTTile.getRowCount(); i++) {
                sql = "SELECT diemcau1 FROM (sinhvien join bangdiema4 on sinhvien.mssv = bangdiema4.mssv) join lophoc on bangdiema4.malop = lophoc.malophoc WHERE mamon = '" + jTTile.getValueAt(i, 0) + "'";
                ps = cons.prepareCall(sql);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    jTTile.setValueAt("Chưa cập nhật", i, 2);
                    jTTile.setValueAt("Chưa cập nhật", i, 3);
                    jTTile.setValueAt("Chưa cập nhật", i, 4);
                    jTTile.setValueAt("Chưa cập nhật", i, 5);
                } else {
                    double[] tile = new double[4];
                    tile = ctrl.getTiLeDatGi(mssv, jTTile.getValueAt(i, 0).toString());
                    jTTile.setValueAt((double) Math.round(tile[0] * 100) / 100, i, 2);
                    jTTile.setValueAt((double) Math.round(tile[1] * 100) / 100, i, 3);
                    jTTile.setValueAt((double) Math.round(tile[2] * 100) / 100, i, 4);
                    jTTile.setValueAt((double) Math.round(tile[3] * 100) / 100, i, 5);
                }
                ps.close();
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
