package com.example.xuong_sqlite_su24_ph48770.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.xuong_sqlite_su24_ph48770.DbHelper.MyHelper;

public class UserDAO {
    String TAG = "adapter";
    private MyHelper myHelper;
    public UserDAO(Context context){
        myHelper = new MyHelper(context);
    }

    // login
    public boolean CheckLogin(String email,String pass) {
        SQLiteDatabase sqLiteDatabase = myHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE email = ? AND pass = ?", new String[]{email, pass});
        return cursor.getCount() > 0;
    }

    // register
    public boolean Register(String email,String pass,String confirmpass) {

        SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("pass",pass);
        contentValues.put("confirmpass",confirmpass);

        long check = sqLiteDatabase.insert("users",null,contentValues);
        return check != -1;
    }

    // forgot pass work
    public String ForgotPassword(String mail){
        Log.d(TAG, "ForgotPassword:1 ");

        SQLiteDatabase sqLiteDatabase = myHelper.getReadableDatabase(); // chế độ xem
        // tìm xem có trong db k
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pass FROM users WHERE email = ?", new String[]{mail});
        Log.d(TAG, "user: "+cursor.getExtras().size());
        if(cursor.getCount() > 0) {
            cursor.moveToFirst(); // chuyển con trỏ lên đầu dãy list để lấy từng thằng
            return cursor.getString(0); // mk ơ vị trí số 0
        } else {
            Log.d(TAG, "ForgotPassword:null ");

            return "";
        }

    }

}
