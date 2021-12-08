package com.example.appdt.model;

import java.util.List;

public class loaisp {
    public int ID;
    public String tenloaisanpham;
    public String hinhanhsanpham;


//    public loaisp(int ID, String tenloaisanpham, String hinhanhloaisanpham) {
//        this.ID = ID;
//        this.tenloaisanpham = tenloaisanpham;
//        this.hinhanhloaisanpham = hinhanhloaisanpham;
//    }


    public loaisp(int ID, String tenloaisanpham, String hinhanhloaisanpham ) {
        this.ID = ID;
        this.tenloaisanpham = tenloaisanpham;
        this.hinhanhsanpham = hinhanhloaisanpham;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenloaisanpham() {
        return tenloaisanpham;
    }

    public void setTenloaisanpham(String tenloaisanpham) {
        this.tenloaisanpham = tenloaisanpham;
    }

    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }
}
