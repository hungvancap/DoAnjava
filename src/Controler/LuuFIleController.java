/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DBConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Admin
 */
public class LuuFIleController {
    private String malop;String filename;
    private String maKT;

    

    public LuuFIleController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LuuFIleController(String malop,String maKT) {
       this.malop=malop;
       this.maKT=maKT;
        System.out.println(malop);
       filename=malop;} 
    public void WriteExcel() throws FileNotFoundException, IOException{
        try {
            Connection conn=DBConnection.getConnection();
            Statement stm=conn.createStatement();
            JFileChooser fc=new JFileChooser();
            fc.showSaveDialog(null);
            File f=fc.getSelectedFile();
//            FileWriter fw=new FileWriter(f, true);
//            FileOutputStream file=new FileOutputStream((File)fw);
            FileOutputStream file=new FileOutputStream(f,true);
            XSSFWorkbook workbook=new XSSFWorkbook();
            XSSFSheet woFSheet_1=workbook.createSheet("Quá trình");
            XSSFSheet woFSheet_2=workbook.createSheet("Giữa kì");
            XSSFSheet woFSheet_3=workbook.createSheet("Thực hành");
            XSSFSheet woFSheet_4=workbook.createSheet("Cuối kì");
            XSSFRow row;
            XSSFCell cellA,cellB,cellC,cellD,cellE,cellF;
            if (Integer.parseInt(maKT)==1) {
                String sql1="select sv_lophoc.mssv,sv_lophoc.malophoc, bangdiema1.tongdiema1\n" +
                            "from sv_lophoc,bangdiema1\n" +
                            "where sv_lophoc.malophoc=bangdiema1.malop\n" +
                            "and sv_lophoc.mssv=bangdiema1.mssv\n" +
                            "and sv_lophoc.malophoc='"+malop+"'";
                ResultSet rs1=stm.executeQuery(sql1);
                int i=1;
                row= woFSheet_1.createRow((short)0);
                    cellA=row.createCell(0);
                    cellA.setCellValue("MSSV");
                    cellB=row.createCell(1);
                    cellB.setCellValue("Điểm");
                while (rs1.next()) {   
                    System.out.println(rs1.getString("mssv"));
                 i++;
                 row= woFSheet_1.createRow((short)i);
                 cellA=row.createCell(0);
                 cellA.setCellValue(rs1.getString("mssv"));
                 cellB=row.createCell(1);
                 cellB.setCellValue(rs1.getString("tongdiema1"));
                 
               }
                rs1.close();
            }
            else if (Integer.parseInt(maKT)==2) {
                String sql2="select sv_lophoc.mssv,sv_lophoc.malophoc, bangdiema2.diemcau1,bangdiema2.diemcau2,bangdiema2.diemcau3,bangdiema2.tongdiema2\n" +
                            "from sv_lophoc,bangdiema2\n" +
                            "where sv_lophoc.malophoc=bangdiema2.malop\n" +
                            "and sv_lophoc.mssv=bangdiema2.mssv\n" +
                            "and sv_lophoc.malophoc='"+malop+"'";
                ResultSet rs2=stm.executeQuery(sql2);
                int i=1;
                row= woFSheet_2.createRow((short)0);
                cellA=row.createCell(0);
                cellA.setCellValue("MSSV");
                cellB=row.createCell(1);
                cellB.setCellValue("Điểm câu 1");
                cellC=row.createCell(2);
                cellC.setCellValue("Điểm câu 2");
                cellD=row.createCell(3);
                cellD.setCellValue("Điểm câu 3");
                cellE=row.createCell(4);
                cellE.setCellValue("Tổng điểm");
                while (rs2.next()) {                    
                    i++;
                     row= woFSheet_2.createRow((short)i);
                    cellA=row.createCell(0);
                cellA.setCellValue(rs2.getString("mssv"));
                cellB=row.createCell(1);
                cellB.setCellValue(rs2.getString("diemcau1"));
                cellC=row.createCell(2);
                cellC.setCellValue(rs2.getString("diemcau2"));
                cellD=row.createCell(3);
                cellD.setCellValue(rs2.getString("diemcau3"));
                cellE=row.createCell(4);
                cellE.setCellValue(rs2.getString("tongdiema2"));
                }
                rs2.close();
            }
            else if(Integer.parseInt(maKT)==3){
                String sql3="select sv_lophoc.mssv,sv_lophoc.malophoc, bangdiema3.tongdiema3\n" +
                            "from sv_lophoc,bangdiema3\n" +
                            "where sv_lophoc.malophoc=bangdiema3.malop\n" +
                            "and sv_lophoc.mssv=bangdiema3.mssv\n" +
                            "and sv_lophoc.malophoc='"+malop+"'";
                ResultSet rs3=stm.executeQuery(sql3);
                int i=1;
                    row= woFSheet_3.createRow((short)0);
                    cellA=row.createCell(0);
                    cellA.setCellValue("MSSV");
                    cellB=row.createCell(1);
                    cellB.setCellValue("Điểm");
                while(rs3.next()){
                    
                    i++;
                    row= woFSheet_3.createRow((short)i);
                    cellA=row.createCell(0);
                    cellA.setCellValue(rs3.getString("mssv"));
                    cellB=row.createCell(1);
                    cellB.setCellValue(rs3.getString("tongdiema3"));
                }
                rs3.close();
            }
            else if (Integer.parseInt(maKT)==4) {
                String sql4="select sv_lophoc.mssv,sv_lophoc.malophoc, bangdiema2.diemcau1,bangdiema2.diemcau2,bangdiema2.diemcau3,bangdiema2.tongdiema2\n" +
                            "from sv_lophoc,bangdiema2\n" +
                            "where sv_lophoc.malophoc=bangdiema2.malop\n" +
                            "and sv_lophoc.mssv=bangdiema2.mssv\n" +
                            "and sv_lophoc.malophoc='"+malop+"'";
                ResultSet rs4=stm.executeQuery(sql4);
                int i=1;
                row= woFSheet_4.createRow((short)0);
                cellA=row.createCell(0);
                cellA.setCellValue("MSSV");
                cellB=row.createCell(1);
                cellB.setCellValue("Điểm câu 1");
                cellC=row.createCell(2);
                cellC.setCellValue("Điểm câu 2");
                cellD=row.createCell(3);
                cellD.setCellValue("Điểm câu 3");
                cellE=row.createCell(4);
                cellE.setCellValue("Tổng điểm");
                while (rs4.next()) {                    
                    i++;
                     row= woFSheet_4.createRow((short)i);
                    cellA=row.createCell(0);
                cellA.setCellValue(rs4.getString("mssv"));
                cellB=row.createCell(1);
                cellB.setCellValue(rs4.getString("diemcau1"));
                cellC=row.createCell(2);
                cellC.setCellValue(rs4.getString("diemcau2"));
                cellD=row.createCell(3);
                cellD.setCellValue(rs4.getString("diemcau3"));
                cellE=row.createCell(4);
                cellE.setCellValue(rs4.getString("tongdiema4"));
                }
                rs4.close();
            }
    
                workbook.write(file);
                workbook.close();
                file.close();
        } catch (SQLException ex) {
            Logger.getLogger(LuuFIleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
