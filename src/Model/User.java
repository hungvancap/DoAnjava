/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class User {

    private String mssv;
    private String malop;
    private float diemcau1;
    private float diemcau2;
    private float diemcau3;
    private float tongdiema4;

    public User(String mssv, String malop, float diemcau1, float diemcau2, float diemcau3, float tongdiema4) {
        this.mssv = mssv;
        this.malop = malop;
        this.diemcau1 = diemcau1;
        this.diemcau2 = diemcau2;
        this.diemcau3 = diemcau3;
        this.tongdiema4 = tongdiema4;

    }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getmssv() {
        return mssv;
    }

    public String getmalop() {
        return malop;
    }

    public float getdiemcau1() {
        return diemcau1;
    }

    public float getdiemcau2() {
        return diemcau2;
    }

    public float getdiemcau3() {
        return diemcau3;
    }

    public float gettongdiema4() {
        return tongdiema4;
    }
      public void setmssv(String ID) {
        this.mssv = ID;
    }
        public void setmalop(String ID) {
        this.malop = ID;
    }
          public void setc1(float ID) {
        this.diemcau1 = ID;
    }
            public void setc2(float ID) {
        this.diemcau2 = ID;
    }
              public void setc3(float ID) {
        this.diemcau3 = ID;
    }
                public void seta(float ID) {
        this.tongdiema4 = ID;
    }


}
