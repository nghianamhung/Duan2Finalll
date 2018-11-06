package com.example.khangit.project_group3.model;

import java.io.Serializable;

public class Danhgia implements Serializable {
    public String iddanhgia;
    public String username;
    public String danhgiasanpham;
    public String masanpham;

    public Danhgia(String Iddanhgia, String Username, String Danhgiasanpham, String Masanpham)  {
        iddanhgia = Iddanhgia;
        username = Username;
        danhgiasanpham = Danhgiasanpham;
        masanpham = Masanpham;
    }


    public String getIddanhgia() {
        return iddanhgia;
    }

    public void setIddanhgia(String iddanhgia) {
        this.iddanhgia = iddanhgia;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDanhgiasanpham() {
        return danhgiasanpham;
    }

    public void setDanhgiasanpham(String danhgiasanpham) {
        this.danhgiasanpham = danhgiasanpham;
    }

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }



}