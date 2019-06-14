/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author 920 369
 */
public class GiController {

    float[] a = new float[4];
    float g1 = 0, g2 = 0, g3 = 0, g4 = 0;

    public GiController() {

    }

    public float[] getTiLeDatGi(String mssv,String mamon) {   //hàm lấy tỉ lệ đạt các Gi trên môn mamon của sinh viên có mssv mssv
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT mamon, ai, tile FROM ai WHERE mamon='"+mamon+"'";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a[0] = rs.getFloat("tile");
            }
            if (rs.next()) {
                a[1] = rs.getFloat("tile");
            }
            if (rs.next()) {
                a[2] = rs.getFloat("tile");
            }
            if (rs.next()) {
                a[3] = rs.getFloat("tile");
            }
            //tính các Gi trong a1
            float[] gia1 = {0, 0, 0, 0};
            float[] gicta1 = {0, 0, 0, 0};//tỉ gi/môn trong a1
            float tongdiema1=0;
            sql = "select * from cautruca1";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                gicta1[0] = (rs.getFloat("g1") /10)*a[0];
                gicta1[1] = (rs.getFloat("g2") /10)*a[0];
                gicta1[2] = (rs.getFloat("g3") /10)*a[0];
                gicta1[3] = (rs.getFloat("g4") /10)*a[0];
            }
            sql = "select * from (bangdiema1 JOIN lophoc on bangdiema1.malop=lophoc.malophoc) where mssv='"+mssv+"' and lophoc.mamon='"+mamon+"'";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                tongdiema1=rs.getFloat("tongdiema1");
            }
            
             gia1[0] = tongdiema1 * gicta1[0]/10;
             gia1[1] = tongdiema1 * gicta1[1]/10;
             gia1[2] = tongdiema1 * gicta1[2]/10;
             gia1[3] = tongdiema1 * gicta1[3]/10;
//            System.out.println("Trong a1: ");
//            for (int i = 0; i < 4; i++) {
//                System.out.println("Cau truc: " + gicta1[i] + " Dat: " + gia1[i]);
//            }
            //tính các gi trong a2
            float[] gia2 = {0, 0, 0, 0};
            float[] gicta2 = {0, 0, 0, 0};

            sql = "select * from bangdiema2, lophoc where lophoc.malophoc=bangdiema2.malop and mssv='"+mssv+"' and lophoc.mamon='"+mamon+"'";// Bảng điểm đạt được a2
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            float[] diemcau = {0, 0, 0, 0};

            if (rs.next()) {
                diemcau[0] = rs.getFloat("diemcau1");
                diemcau[1] = rs.getFloat("diemcau2");
                diemcau[2] = rs.getFloat("diemcau3");
                diemcau[3] = rs.getFloat("tongdiema2");
            }
            sql = "SELECT * from cautruca2 where mamon='"+mamon+"'";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            rs = ps.executeQuery();
            float[] cta2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//4gi X 3 câu =12 giá trị (bảng cấu trúc a2);
            float[] cta2diemcuacau = {0, 0, 0};
            if (rs.next()) {
                cta2[0] = rs.getFloat("g1");
                cta2[1] = rs.getFloat("g2");
                cta2[2] = rs.getFloat("g3");
                cta2[3] = rs.getFloat("g4");
                cta2diemcuacau[0] = rs.getFloat("diemcuacau");
            }
            if (rs.next()) {
                cta2[4] = rs.getFloat("g1");
                cta2[5] = rs.getFloat("g2");
                cta2[6] = rs.getFloat("g3");
                cta2[7] = rs.getFloat("g4");
                cta2diemcuacau[1] = rs.getFloat("diemcuacau");
            }
            if (rs.next()) {
                cta2[8] = rs.getFloat("g1");
                cta2[9] = rs.getFloat("g2");
                cta2[10] = rs.getFloat("g3");
                cta2[11] = rs.getFloat("g4");
                cta2diemcuacau[2] = rs.getFloat("diemcuacau");
            }
            float[] sumgicta2 = {0, 0, 0, 0};// tổng điểm mà các Gi có trong cấu trúc điểm a2
            sumgicta2[0] = cta2[0] + cta2[4] + cta2[8];
            sumgicta2[1] = cta2[1] + cta2[5] + cta2[9];
            sumgicta2[2] = cta2[2] + cta2[6] + cta2[10];
            sumgicta2[3] = cta2[3] + cta2[7] + cta2[11];
            float[] sumgidat = {0, 0, 0, 0};// tổng điểm sv đạt đc theo các gi trong cấu trúc điểm a2
            sumgidat[0] = diemcau[0] * (cta2[0] / cta2diemcuacau[0]) + diemcau[1] * (cta2[4] / cta2diemcuacau[1]) + diemcau[2] * (cta2[8] / cta2diemcuacau[2]);
            sumgidat[1] = diemcau[0] * (cta2[1] / cta2diemcuacau[0]) + diemcau[1] * (cta2[5] / cta2diemcuacau[1]) + diemcau[2] * (cta2[9] / cta2diemcuacau[2]);
            sumgidat[2] = diemcau[0] * (cta2[2] / cta2diemcuacau[0]) + diemcau[1] * (cta2[6] / cta2diemcuacau[1]) + diemcau[2] * (cta2[10] / cta2diemcuacau[2]);
            sumgidat[3] = diemcau[0] * (cta2[3] / cta2diemcuacau[0]) + diemcau[1] * (cta2[7] / cta2diemcuacau[1]) + diemcau[2] * (cta2[11] / cta2diemcuacau[2]);

            // tính tỉ lệ của các gi trong cấu trúc a2/môn
            gicta2[0] = sumgicta2[0] / 10 * a[1];
            gicta2[1] = sumgicta2[1] / 10 * a[1];
            gicta2[2] = sumgicta2[2] / 10 * a[1];
            gicta2[3] = sumgicta2[3] / 10 * a[1];
            //tính tỉ lệ đạt gi/gi của môn nhờ a2
            gia2[0] = sumgidat[0] / 10 * a[1];
            gia2[1] = sumgidat[1] / 10 * a[1];
            gia2[2] = sumgidat[2] / 10 * a[1];
            gia2[3] = sumgidat[3] / 10 * a[1];

//            System.out.println("Trong a2: ");
//            for (int i = 0; i < 4; i++) {
//                System.out.println("Cau truc: " + gicta2[i] + " Dat: " + gia2[i]);
//            }
            //tính các Gi trong a3
            float[] gia3 = {0, 0, 0, 0};
            float[] gicta3 = {0, 0, 0, 0};//tỉ gi/môn trong a3
            float tongdiema3=0;
            sql = "select * from cautruca3";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                gicta3[0] = (rs.getFloat("g1") /10)*a[2];
                gicta3[1] = (rs.getFloat("g2") /10)*a[2];
                gicta3[2] = (rs.getFloat("g3") /10)*a[2];
                gicta3[3] = (rs.getFloat("g4") /10)*a[2];
            }
            sql = "select * from (bangdiema3 JOIN lophoc on bangdiema3.malop=lophoc.malophoc) where mssv='"+mssv+"' and lophoc.mamon='"+mamon+"'";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                tongdiema3=rs.getFloat("tongdiema3");
            }
            
             gia3[0] = tongdiema3 * gicta3[0]/10;
             gia3[1] = tongdiema3 * gicta3[1]/10;
             gia3[2] = tongdiema3 * gicta3[2]/10;
             gia3[3] = tongdiema3 * gicta3[3]/10;

//            System.out.println("Trong a3: ");
//            for (int i = 0; i < 4; i++) {
//                System.out.println("Cau truc: " + gicta3[i] + " Dat: " + gia3[i]);
//            }

            //tính gi trong a4
            float[] gia4 = {0, 0, 0, 0};
            float[] gicta4 = {0, 0, 0, 0};

            sql = "select * from bangdiema4, lophoc where lophoc.malophoc=bangdiema4.malop and mssv='"+mssv+"' and lophoc.mamon='"+mamon+"'";// Bảng điểm đạt được a4
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            diemcau[0] = 0;
            diemcau[1] = 0;
            diemcau[2] = 0;
            diemcau[3] = 0;
            if (rs.next()) {
                diemcau[0] = rs.getFloat("diemcau1");
                diemcau[1] = rs.getFloat("diemcau2");
                diemcau[2] = rs.getFloat("diemcau3");
                diemcau[3] = rs.getFloat("tongdiema4");
            }
            sql = "SELECT * from cautruca4 where mamon='"+mamon+"'";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            rs = ps.executeQuery();
            float[] cta4 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//4gi X 3 câu =12 giá trị (bảng cấu trúc a4);
            float[] cta4diemcuacau = {0, 0, 0};
            if (rs.next()) {
                cta4[0] = rs.getFloat("g1");
                cta4[1] = rs.getFloat("g2");
                cta4[2] = rs.getFloat("g3");
                cta4[3] = rs.getFloat("g4");
                cta4diemcuacau[0] = rs.getFloat("diemcuacau");
            }
            if (rs.next()) {
                cta4[4] = rs.getFloat("g1");
                cta4[5] = rs.getFloat("g2");
                cta4[6] = rs.getFloat("g3");
                cta4[7] = rs.getFloat("g4");
                cta4diemcuacau[1] = rs.getFloat("diemcuacau");
            }
            if (rs.next()) {
                cta4[8] = rs.getFloat("g1");
                cta4[9] = rs.getFloat("g2");
                cta4[10] = rs.getFloat("g3");
                cta4[11] = rs.getFloat("g4");
                cta4diemcuacau[2] = rs.getFloat("diemcuacau");
            }
            float[] sumgicta4 = {0, 0, 0, 0};// tổng điểm mà các Gi có trong cấu trúc điểm a4
            sumgicta4[0] = cta4[0] + cta4[4] + cta4[8];
            sumgicta4[1] = cta4[1] + cta4[5] + cta4[9];
            sumgicta4[2] = cta4[2] + cta4[6] + cta4[10];
            sumgicta4[3] = cta4[3] + cta4[7] + cta4[11];

            // tổng điểm sv đạt đc theo các gi trong cấu trúc điểm a4
            sumgidat[0] = diemcau[0] * (cta4[0] / cta4diemcuacau[0]) + diemcau[1] * (cta4[4] / cta4diemcuacau[1]) + diemcau[2] * (cta4[8] / cta4diemcuacau[2]);
            sumgidat[1] = diemcau[0] * (cta4[1] / cta4diemcuacau[0]) + diemcau[1] * (cta4[5] / cta4diemcuacau[1]) + diemcau[2] * (cta4[9] / cta4diemcuacau[2]);
            sumgidat[2] = diemcau[0] * (cta4[2] / cta4diemcuacau[0]) + diemcau[1] * (cta4[6] / cta4diemcuacau[1]) + diemcau[2] * (cta4[10] / cta4diemcuacau[2]);
            sumgidat[3] = diemcau[0] * (cta4[3] / cta4diemcuacau[0]) + diemcau[1] * (cta4[7] / cta4diemcuacau[1]) + diemcau[2] * (cta4[11] / cta4diemcuacau[2]);

            // tính tỉ lệ của các gi trong cấu trúc a4/môn
            gicta4[0] = sumgicta4[0] / 10 * a[3];
            gicta4[1] = sumgicta4[1] / 10 * a[3];
            gicta4[2] = sumgicta4[2] / 10 * a[3];
            gicta4[3] = sumgicta4[3] / 10 * a[3];
            //tính tỉ lệ đạt gi/gi của môn nhờ a4
            gia4[0] = sumgidat[0] / 10 * a[3];
            gia4[1] = sumgidat[1] / 10 * a[3];
            gia4[2] = sumgidat[2] / 10 * a[3];
            gia4[3] = sumgidat[3] / 10 * a[3];

//            System.out.println("Trong a4: ");
//            for (int i = 0; i < 4; i++) {
//                System.out.println("Cau truc: " + gicta4[i] + " Dat: " + gia4[i]);
//            }

            //Kết quả
            float[] sumgict = {gicta1[0] + gicta2[0] + gicta3[0] + gicta4[0], gicta1[1] + gicta2[1] + gicta3[1] + gicta4[1], gicta1[2] + gicta2[2] + gicta3[2] + gicta4[2], gicta1[3] + gicta2[3] + gicta3[3] + gicta4[3]};
            float[] sumgi = {gia1[0] + gia2[0] + gia3[0] + gia4[0], gia1[1] + gia2[1] + gia3[1] + gia4[1], gia1[2] + gia2[2] + gia3[2] + gia4[2], gia1[3] + gia2[3] + gia3[3] + gia4[3]};
            float[] tiledatgi = {sumgi[0] / sumgict[0], sumgi[1] / sumgict[1], sumgi[2] / sumgict[2], sumgi[3] / sumgict[3]};
//            System.out.println("Kết quả cuối: ");
//            for (int i = 0; i < 4; i++) {
//                System.out.println(tiledatgi[i]);
//            }
            ps.close();
            rs.close();
            return tiledatgi;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
