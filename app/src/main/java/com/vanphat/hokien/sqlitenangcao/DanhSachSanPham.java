package com.vanphat.hokien.sqlitenangcao;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DanhSachSanPham extends AppCompatActivity {

    ListView lvSanPham;
    ArrayList<SanPham> mangSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham);

        lvSanPham = (ListView) findViewById(R.id.listView);
        mangSanPham = new ArrayList<SanPham>();

        Cursor cursorSP = MainActivity.db.GetData("SELECT * FROM SanPham");
        while (cursorSP.moveToNext()) {
            mangSanPham.add(new SanPham(
                    cursorSP.getInt(0),
                    cursorSP.getString(1),
                    cursorSP.getInt(2),
                    cursorSP.getBlob(3))
            );
        }

        SanPhamAdapter adapter = new SanPhamAdapter(this, R.layout.dong_san_pham, mangSanPham);
        lvSanPham.setAdapter(adapter);
    }
}
