package com.example.xuong_sqlite_su24_ph48770.Model;

public class NhanVienEntity {
    private int ma_nv;
    private String ho_dem;
    private String ten;
    private String dia_chi;
    private int maPB;
    private String tenPB;

    public NhanVienEntity(int ma_nv, String ho_dem, String ten, String dia_chi, int maPB, String tenPB) {
        this.ma_nv = ma_nv;
        this.ho_dem = ho_dem;
        this.ten = ten;
        this.dia_chi = dia_chi;
        this.maPB = maPB;
        this.tenPB = tenPB;
    }
    public int getMa_nv() {
        return ma_nv;
    }

    public void setMa_nv(int ma_nv) {
        this.ma_nv = ma_nv;
    }

    public String getHo_dem() {
        return ho_dem;
    }

    public void setHo_dem(String ho_dem) {
        this.ho_dem = ho_dem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public int getMaPB() {
        return maPB;
    }

    public void setMaPB(int maPB) {
        this.maPB = maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }
}

