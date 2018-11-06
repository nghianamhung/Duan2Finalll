package com.example.khangit.project_group3.model;

import android.widget.TextView;

import java.io.Serializable;

public class Sanpham implements Serializable{
    public int id;
    public String Tensanpham;
    public Integer Giasanpham;
    public String Hinhanhsanpham;
    public String Motasanpham;
    public int IDSanpham;
    public int Soluongsanpham;
    public static int Soluonggioihan;






    public Sanpham(int id, String tensanpham, Integer giasanpham, String hinhanhsanpham, String motasanpham, int IDSanpham, int Soluongsanpham) {
        this.id = id;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        Hinhanhsanpham = hinhanhsanpham;
        Motasanpham = motasanpham;
        this.IDSanpham = IDSanpham;
        this.Soluongsanpham = Soluongsanpham;
        Soluonggioihan = Soluongsanpham -1;
    }




    public int getSoluongsanpham(){return Soluongsanpham;}

    public void setSoluongsanpham(int soluongsanpham) {
        Soluongsanpham = soluongsanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public Integer getGiasanpham() {
        return Giasanpham;
    }

    public void setGiasanpham(Integer giasanpham) {
        Giasanpham = giasanpham;
    }

    public String getHinhanhsanpham() {
        return Hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        Hinhanhsanpham = hinhanhsanpham;
    }

    public String getMotasanpham() {
        return Motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        Motasanpham = motasanpham;
    }

    public int getIDSanpham() {
        return IDSanpham;
    }

    public void setIDSanpham(int IDSanpham) {
        this.IDSanpham = IDSanpham;
    }
}
