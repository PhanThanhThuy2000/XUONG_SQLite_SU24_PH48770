package com.example.xuong_sqlite_su24_ph48770.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.xuong_sqlite_su24_ph48770.DbHelper.MyHelper;
import com.example.xuong_sqlite_su24_ph48770.Model.NhanVienEntity;
import com.example.xuong_sqlite_su24_ph48770.Model.PhongBanEntity;

import java.util.ArrayList;

public class NhanVienDAO {
    MyHelper myHelper;
    SQLiteDatabase db;

    public NhanVienDAO(Context context) {
        myHelper = new MyHelper(context);
        db = myHelper.getWritableDatabase(); //Khởi tạo đối tượng db
    }

    public ArrayList<NhanVienEntity> getList() {
        ArrayList<NhanVienEntity> listNV = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT nv.ma_nv, nv.ho_dem, nv.ten, nv.dia_chi, nv.maPB,pb.tenPB FROM NhanVien nv," +
                " PhongBan pb WHERE nv.maPB = pb.maPB", null);

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int ma_nv = c.getInt(0);
                String ho_dem = c.getString(1);
                String ten = c.getString(2);
                String dia_chi = c.getString(3);
                int maPB = c.getInt(4);
                String tenPB = c.getString(5);
                NhanVienEntity nhanVienEntity = new NhanVienEntity(ma_nv, ho_dem,ten,dia_chi,maPB,tenPB);
                listNV.add(nhanVienEntity);
            } while (c.moveToNext());
        }
        return listNV;
    }

    // Các hàm tương tác
    public int addRow(String ho_dem, String ten, String dia_chi, int maPB) {
        ContentValues v = new ContentValues();
        v.put("ho_dem",ho_dem);
        v.put("ten", ten);
        v.put("dia_chi", dia_chi);
        v.put("maPB", maPB);
        return (int) db.insert("NhanVien", null, v);
    }
    public boolean updateRow(int ma_nv, String ho_dem, String ten, String dia_chi, int maPB) {
        ContentValues v = new ContentValues();
        v.put("ho_dem",ho_dem);
        v.put("ten", ten);
        v.put("dia_chi", dia_chi);
        v.put("maPB", maPB);

        String[] params = {String.valueOf(ma_nv)};
        long kq = db.update("NhanVien", v, "ma_nv = ? ", params);

        return kq > 0; // Thành công khi lớn hơn 0
    }

    public boolean deleteRow(int id) {
        String[] params = {String.valueOf(id)};
        long kq = db.delete("NhanVien", "ma_nv = ? ", params);
        return kq > 0; // Thành công khi lớn hơn 0
    }
}
