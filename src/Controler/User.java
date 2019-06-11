/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

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
    private float diemcau4;
    private float diemcau5;

    public User(String mssv, String malop, float diemcau1, float diemcau2, float diemcau3, float diemcau4, float diemcau5) {
        this.mssv = mssv;
        this.malop = malop;
        this.diemcau1 = diemcau1;
        this.diemcau2 = diemcau2;
        this.diemcau3 = diemcau3;
        this.diemcau4 = diemcau4;
        this.diemcau5 = diemcau5;
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

    public float getdiemcau4() {
        return diemcau4;
    }

    public float getdiemcau5() {
        return diemcau5;
    }
}
