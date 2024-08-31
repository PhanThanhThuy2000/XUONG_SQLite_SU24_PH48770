package com.example.xuong_sqlite_su24_ph48770.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.xuong_sqlite_su24_ph48770.Adapter.PhongBanAdapter;
import com.example.xuong_sqlite_su24_ph48770.DbHelper.MyHelper;
import com.example.xuong_sqlite_su24_ph48770.Model.PhongBanEntity;

import java.util.ArrayList;

public class PhongBanDAO {
    MyHelper myHelper;
    SQLiteDatabase db;
    String TAG = "DAO";

    public PhongBanDAO(Context context) {
        myHelper = new MyHelper(context);
        db = myHelper.getWritableDatabase(); //Khởi tạo đối tượng db
    }

    public ArrayList<PhongBanEntity> getList() {
        ArrayList<PhongBanEntity> listPB = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM PhongBan", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int maPB = c.getInt(0);
                String name = c.getString(1);
                PhongBanEntity ojbPhongBan = new PhongBanEntity(maPB, name);
                listPB.add(ojbPhongBan);
            } while (c.moveToNext());
        }
        return listPB;
    }

    // Các hàm tương tác
    public int addRow(PhongBanEntity phongBanEntity) {
        ContentValues v = new ContentValues();
        v.put("tenPB", phongBanEntity.getTenPB());
        Log.d(TAG, "addRow: "+v);
        return (int) db.insert("PhongBan", null, v);
    }

    public boolean updateRow(PhongBanEntity phongBanEntity) {
        ContentValues v = new ContentValues();
        v.put("tenPB", phongBanEntity.getTenPB());
        String[] params = {String.valueOf(phongBanEntity.getMaPB())};
        long kq = db.update("PhongBan", v, "maPB = ? ", params);
        return kq > 0; // Thành công khi lớn hơn 0
    }

    public boolean deleteRow(int id) {
        String[] params = {String.valueOf(id)};
        long kq = db.delete("PhongBan", "maPB = ? ", params);
        return kq > 0; // Thành công khi lớn hơn 0
    }
}
