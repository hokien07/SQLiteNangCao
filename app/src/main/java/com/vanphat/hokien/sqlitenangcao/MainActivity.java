package com.vanphat.hokien.sqlitenangcao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnThem, btnXem;
    public static SQLite db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();

        db = new SQLite(this, "BanHang.sqlite", null, 1);
        db.QueryData("CREATE TABLE IF NOT EXISTS SanPham(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenSP VACHAR, GiaSP INTEGER, HinhSp BLOB)");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(MainActivity.this, ManHinhSanPham.class);
                startActivity(intentAdd);
            }
        });

        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DanhSachSanPham.class);
                startActivity(intent);
            }
        });
    }

    private void Anhxa() {
        btnThem = (Button) findViewById(R.id.btnSanPham);
        btnXem = (Button) findViewById(R.id.btnDanhSach);
    }
}
