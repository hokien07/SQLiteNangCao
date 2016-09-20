package com.vanphat.hokien.sqlitenangcao;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by HoKien on 9/13/2016.
 */
public class SQLite extends SQLiteOpenHelper {

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    //bắt buộc sử dụng với những File kiểu Blob
    public void INSERT_SANPHAM(String tenSP, int giaSP, byte[] hinhSP ){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO SanPham VALUES(null, ?, ?, ?)";
        //lấy giá trị của dấu ?
        SQLiteStatement statement = database.compileStatement(sql);
        //xóa bộ nhớ đệm
        statement.clearBindings();

        //gán giá trị cho ?
        statement.bindString(1, tenSP);
        statement.bindLong(2, giaSP);
        statement.bindBlob(3, hinhSP);
        statement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
