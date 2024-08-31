package com.example.xuong_sqlite_su24_ph48770.Model;

public class PhongBanEntity {
    private int maPB;
    private String tenPB;

    public PhongBanEntity(int maPB, String tenPB) {
        this.maPB = maPB;
        this.tenPB = tenPB;
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
