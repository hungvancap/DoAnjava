/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DBConnection;
import View.Bangdiemlophoc;
import View.Bangdiemlopsinhhoat;
import View.FrameGiangVien;
import View.bieudo;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class GiangVienController {
    private FrameGiangVien gv_form;
    private JButton jbtxem;
    private JButton jbtnhap;
    private JButton jbtThongke;
    private JButton jbtout;
    private JPanel jpnView;

    public GiangVienController(FrameGiangVien form, JButton jbtxem, JButton jbtnhap, JButton jbtThongke, JButton jbtout, JPanel jpnView) {
        this.gv_form=form;
        this.jbtxem=jbtxem;
        this.jbtThongke=jbtThongke;
        this.jbtout=jbtout;
        this.jpnView=jpnView;
    }
    

    public void setEvent() {
        jbtxem.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                try {
                    //Hien thi ma lop de giang vien chon:
                    Connection conn=DBConnection.getConnection();
                    Statement stm=conn.createStatement();
                    String sql1="select * from lophoc where magv='"+gv_form.magv+"'";
                    String sql2="Select * from lopsinhhoat where covan='"+gv_form.magv+"'";
                    ArrayList arr;
                    try (ResultSet rs1 = stm.executeQuery(sql1)) {
                        arr = new ArrayList();
                        while(rs1.next())
                        {
                            arr.add(rs1.getString("malophoc"));
                        }
                        rs1.close();
                    }
                    ResultSet rs2=stm.executeQuery(sql2);
                    while(rs2.next())
                    {
                        arr.add(rs2.getString("malopsh"));
                    }
                    rs2.close();
                    Object [] op=new Object[arr.size()];
                    for(int i=0;i<arr.size();i++)
                    {
                     op[i]=arr.get(i);
                    }
                    String selection=(String) JOptionPane.showInputDialog(null,"Xin mời chọn mã lớp:","Selection!!!", JOptionPane.PLAIN_MESSAGE,null, op,"");
                    //kiem tra ma lop da chon co phai la ma lop sinh hoat khong:
                    String sql3="Select * from lopsinhhoat";
                    boolean check=false;
                    ResultSet rs3=stm.executeQuery(sql3);
                    while(rs3.next()){
                        if(rs3.getString("malopsh").equals(selection)) check=true;
                    }
                    rs3.close();
                    jpnView.removeAll();
                    jpnView.setLayout(new BorderLayout());
                    if(check==false)
                    {
                     
                    jpnView.add(new Bangdiemlophoc(selection));
                    
                    }
                    else{
                    jpnView.add(new Bangdiemlopsinhhoat(selection));
                        
                    }
                    jpnView.validate();
                    jpnView.repaint();
                    stm.close();conn.close();
                            } catch (SQLException ex) {
                    Logger.getLogger(GiangVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jbtThongke.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                try {
                    Connection conn=DBConnection.getConnection();
                    Statement stm=conn.createStatement();
                    String sql1="select * from lophoc where magv='"+gv_form.magv+"'";
                    ArrayList arr1,arr2;
                    try (ResultSet rs1 = stm.executeQuery(sql1)) {
                        arr1 = new ArrayList();
                        while(rs1.next())
                        {
                            arr1.add(rs1.getString("malophoc"));
                        }
                        rs1.close();
                    }
                    Object [] op1=new Object[arr1.size()];
                    for(int i=0;i<arr1.size();i++)
                    {
                     op1[i]=arr1.get(i);
                    }
                    String classname=(String) JOptionPane.showInputDialog(null,"Xin mời chọn mã lớp:","Selection!!!", JOptionPane.PLAIN_MESSAGE,null, op1,"");
                    String sql2="select sinhvien.hotensv,sv_lophoc.malophoc from sv_lophoc,sinhvien,lophoc\n" +
                                "where sinhvien.mssv=sv_lophoc.mssv\n" +
                                "and sv_lophoc.malophoc=lophoc.malophoc\n" +
                                "and sv_lophoc.malophoc='"+classname+"'";
                    try (ResultSet rs2 = stm.executeQuery(sql2)) {
                        arr2=new ArrayList();
                        while (rs2.next()) {
//                            
                            arr2.add(rs2.getString("hotensv"));
                        }
                        rs2.close();
                    }
                    Object []op2=new Object[arr2.size()];
                    for(int i=0;i<arr2.size();i++)
                    {
                     op2[i]=arr2.get(i);
                    }
                    String studentname=(String) JOptionPane.showInputDialog(null, "Xin mời chọn sinh viên:","Selection!!!", JOptionPane.PLAIN_MESSAGE,null, op2, "");
                    GiController controller=new GiController();
                    String sql3="select sv_lophoc.mssv,monhoc.mamon\n" +
                                "from sv_lophoc,sinhvien,lophoc,monhoc\n" +
                                "where sv_lophoc.malophoc=lophoc.malophoc\n" +
                                "and lophoc.mamon=monhoc.mamon\n" +
                                "and sv_lophoc.mssv=sinhvien.mssv\n" +
                                "and sinhvien.hotensv='"+studentname+"'";
                    ResultSet rs3=stm.executeQuery(sql3);
                    while (rs3.next()) {                        
                        GiController controll=new GiController();
                        controll.getTiLeDatGi(rs3.getString("mssv"),rs3.getString("mamon"));
                    }
                    rs3.close();
                    jpnView.removeAll();
                    jpnView.setLayout(new BorderLayout());
                    jpnView.add(new bieudo());
                } catch (SQLException ex) {
                    Logger.getLogger(GiangVienController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
