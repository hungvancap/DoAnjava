/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DBConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Admin
 */
public class LuuFIleController {
    private String mssv;private String malop;String filename;
    private float cau1;private float cau2;private float cau3;
    private float tongdiem;private String maKT;

    

    public LuuFIleController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LuuFIleController(String malop,String maKT) {
       this.malop=malop;
       this.maKT=maKT;
        if (Integer.parseInt(maKT)==1) {
            filename="Bảng điểm quá trình lớp "+malop;
        }
        else if(Integer.parseInt(maKT)==2) filename="Bảng điểm kì thi giữa kì "+malop;
        else if (Integer.parseInt(maKT)==3) {
            filename="Bảng điểm thực hành "+malop;
        }
        else if(Integer.parseInt(maKT)==4) filename="Bảng điểm kì thi cuối kì "+malop;
    }
    public void WriteExcel() throws FileNotFoundException, IOException{
        try {
            Connection conn=DBConnection.getConnection();
            Statement stm=conn.createStatement();
            FileOutputStream file=new FileOutputStream(filename+".xlsx");
            XSSFWorkbook workbook=new XSSFWorkbook();
            XSSFSheet woFSheet_ma=workbook.createSheet("MSSV");
            XSSFSheet woFSheet_lop=workbook.createSheet("Lớp");
            XSSFSheet woFSheet_c1=workbook.createSheet("Câu 1");
            XSSFSheet woFSheet_c2=workbook.createSheet("Câu 2");
            XSSFSheet woFSheet_c3=workbook.createSheet("Câu 3");
            XSSFSheet woFSheet_tong=workbook.createSheet("Tổng điểm");
            XSSFRow row1,row2,row3,row4,row5,row6;
            XSSFCell cellA,cellB,cellC,cellD,cellE,cellF;
            if (Integer.parseInt(maKT)==1) {
                String sql1="select sv_lophoc.mssv,sv_lophoc.malophoc, bangdiema1.tongdiema1\n" +
                            "from sv_lophoc,bangdiema1\n" +
                            "where sv_lophoc.malophoc=bangdiema1.malop\n" +
                            "and sv_lophoc.mssv=bangdiema1.mssv\n" +
                            "and sv_lophoc.malophoc='"+malop+"'";
                ResultSet rs1=stm.executeQuery(sql1);
                int i=0;
                row1= woFSheet_ma.createRow((short)i);
                row2=woFSheet_lop.createRow((short)i);
                row3=woFSheet_tong.createRow((short)i);
                while (rs1.next()) {                    
                 i++;
                 cellA=row1.createCell(i);
                 cellA.setCellValue(rs1.getString("mssv"));
                 cellB=row2.createCell(i);
                 cellB.setCellValue(rs1.getString("malophoc"));
                 cellC=row3.createCell(i);
                 cellC.setCellValue(rs1.getString("tongdiema1"));
               }
                workbook.write(file);
                workbook.close();
                file.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LuuFIleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
