package com.vanphat.hokien.sqlitenangcao;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ManHinhSanPham extends AppCompatActivity {

    ImageButton imgbtnCamera, imgbtnopenfolder;
    ImageView imgHinh;
    EditText edtTenSanPham, edtGiaSanPham;
    Button btnThemSP;
    int REQUEST_CDE_CAMERA = 123;
    int RESULT_LOAD_IMAGE = 789;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_san_pham);
        AnhXa();
        imgbtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CDE_CAMERA);
            }
        });

        imgbtnopenfolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.db.INSERT_SANPHAM(edtTenSanPham.getText().toString(),
                        Integer.parseInt(edtGiaSanPham.getText().toString()),
                               ImageView_To_Byte(imgHinh) );
            }
        });

    }

    public byte[] ImageView_To_Byte(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    //lấy kết quả gửi về ImgView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CDE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }

        //xin quyen READ_EXTERNAL_STRAGE
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imgHinh.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa() {
        imgbtnCamera = (ImageButton) findViewById(R.id.imageButtonTakePicture);
        imgbtnopenfolder = (ImageButton) findViewById(R.id.imageButtonopenfolder);
        imgHinh = (ImageView) findViewById(R.id.imageView);
        btnThemSP = (Button)findViewById(R.id.btnThemSanPham);
        edtGiaSanPham = (EditText) findViewById(R.id.edtGiaSanPham);
        edtTenSanPham = (EditText) findViewById(R.id.edtTenSanPham);
    }
}
