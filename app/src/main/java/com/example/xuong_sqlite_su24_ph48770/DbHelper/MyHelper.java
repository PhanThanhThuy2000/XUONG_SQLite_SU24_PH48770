package com.example.xuong_sqlite_su24_ph48770.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context, "congty7 ", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users ( id INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT NOT NULL UNIQUE, pass TEXT NOT NULL,confirmpass NOT NULL);");
        db.execSQL("CREATE TABLE PhongBan ( maPB INTEGER PRIMARY KEY AUTOINCREMENT,tenPB NOT NULL UNIQUE);");
        db.execSQL("CREATE TABLE NhanVien (ma_nv INTEGER PRIMARY KEY AUTOINCREMENT,ho_dem TEXT NOT NULL,ten TEXT NOT NULL,dia_chi TEXT,maPB INTEGER REFERENCES PhongBan (maPB));");

        db.execSQL("INSERT INTO PhongBan VALUES (1, 'Hanh chinh')");
        db.execSQL("INSERT INTO NhanVien VALUES (1, 'Phan Thanh','Thuy','Ninh Binh', 1),(2, 'Phan Thanh','Thuy','Ninh Binh', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS users;");
            db.execSQL("DROP TABLE IF EXISTS PhongBan;");
            db.execSQL("DROP TABLE IF EXISTS NhanVien;");
            onCreate(db);
        }
    }
}
