/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controler.GiController;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author 920 369
 */
public class LOi {
    public LOi() {
        
    }
    GiController gi=new GiController();
    public double[] tiledatloi(String mssv) {
        double[] loi = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int  slgi=0;
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * from sinhvien where mssv='"+mssv+"' and totnghiep=1";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()){ 
                return loi;
            }
            //tinhs LO1
            sql = "SELECT COUNT(mamon) slgi FROM mon_li WHERE li=1";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()){ 
                slgi=rs.getInt("slgi");
            }
            sql = "SELECT  mamon,gi, li from mon_li where li=1";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){ 
                double[] gidat=gi.getTiLeDatGi(mssv, rs.getString("mamon"));
                loi[1]=loi[1]+gidat[rs.getInt("gi")-1]/slgi;
                
            }
            //tinhs LO2
            sql = "SELECT COUNT(mamon) slgi FROM mon_li WHERE li=2";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()){ 
                slgi=rs.getInt("slgi");
            }
            sql = "SELECT  mamon,gi, li from mon_li where li=2";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){ 
                double[] gidat=gi.getTiLeDatGi(mssv, rs.getString("mamon"));
                loi[2]=loi[2]+gidat[rs.getInt("gi")-1]/slgi;
                
            }
            
            //tinhs LO3
            sql = "SELECT COUNT(mamon) slgi FROM mon_li WHERE li=3";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()){ 
                slgi=rs.getInt("slgi");
            }
            sql = "SELECT  mamon,gi, li from mon_li where li=3";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){ 
                double[] gidat=gi.getTiLeDatGi(mssv, rs.getString("mamon"));
                loi[3]=loi[3]+gidat[rs.getInt("gi")-1]/slgi;
                
            }
            //tinhs LO4
            sql = "SELECT COUNT(mamon) slgi FROM mon_li WHERE li=4";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()){ 
                slgi=rs.getInt("slgi");
            }
            sql = "SELECT  mamon,gi, li from mon_li where li=4";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){ 
                double[] gidat=gi.getTiLeDatGi(mssv, rs.getString("mamon"));
                loi[4]=loi[4]+gidat[rs.getInt("gi")-1]/slgi;
                
            }
            //tinhs LO5
            sql = "SELECT COUNT(mamon) slgi FROM mon_li WHERE li=5";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()){ 
                 slgi=rs.getInt("slgi");
            }
            sql = "SELECT  mamon,gi, li from mon_li where li=5";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){ 
                double[] gidat=gi.getTiLeDatGi(mssv, rs.getString("mamon"));
                loi[5]=loi[5]+gidat[rs.getInt("gi")-1]/slgi;
                
            }
            //tinhs LO6
            sql = "SELECT COUNT(mamon) slgi FROM mon_li WHERE li=6";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()){ 
               slgi=rs.getInt("slgi");
            }
            sql = "SELECT  mamon,gi, li from mon_li where li=6";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){ 
                double[] gidat=gi.getTiLeDatGi(mssv, rs.getString("mamon"));
                loi[6]=loi[6]+gidat[rs.getInt("gi")-1]/slgi;
                
            }
            //tinhs LO7
            sql = "SELECT COUNT(mamon) slgi FROM mon_li WHERE li=7";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()){ 
                slgi=rs.getInt("slgi");
            }
            sql = "SELECT  mamon,gi, li from mon_li where li=7";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){ 
                double[] gidat=gi.getTiLeDatGi(mssv, rs.getString("mamon"));
                loi[7]=loi[7]+gidat[rs.getInt("gi")-1]/slgi;
                
            }
            //tinhs LO8
            sql = "SELECT COUNT(mamon) slgi FROM mon_li WHERE li=8";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()){ 
               slgi=rs.getInt("slgi");
            }
            sql = "SELECT  mamon,gi, li from mon_li where li=8";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){ 
                double[] gidat=gi.getTiLeDatGi(mssv, rs.getString("mamon"));
                loi[8]=loi[8]+gidat[rs.getInt("gi")-1]/slgi;
                
            }
            //tinhs LO9
            sql = "SELECT COUNT(mamon) slgi FROM mon_li WHERE li=9";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()){ 
                slgi=rs.getInt("slgi");
            }
            sql = "SELECT  mamon,gi, li from mon_li where li=9";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){ 
                double[] gidat=gi.getTiLeDatGi(mssv, rs.getString("mamon"));
                loi[9]=loi[9]+gidat[rs.getInt("gi")-1]/slgi;
                
            }
            //tinhs LO10
            sql = "SELECT COUNT(mamon) slgi FROM mon_li WHERE li=10";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            if (rs.next()){ 
                slgi=rs.getInt("slgi");
            }
            sql = "SELECT  mamon,gi, li from mon_li where li=10";
            ps = cons.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){ 
                double[] gidat=gi.getTiLeDatGi(mssv, rs.getString("mamon"));
                loi[10]=loi[10]+gidat[rs.getInt("gi")-1]/slgi;
                
            }
            ps.close();
            rs.close();
            cons.close();
            return loi;
        } catch (Exception ex) {
            printStackTrace();
        }
        return loi;
    }
    

}
