package com.vanphat.hokien.sqlitenangcao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by HoKien on 9/15/2016.
 */
public class SanPhamAdapter extends BaseAdapter {
    Context mycontext;
    int myLayout;
    List<SanPham> arraySanPham;

    public SanPhamAdapter(Context mycontext, int myLayout, List<SanPham> arraySanPham) {
        this.mycontext = mycontext;
        this.myLayout = myLayout;
        this.arraySanPham = arraySanPham;
    }

    @Override
    public int getCount() {
        return arraySanPham.size();
    }

    @Override
    public Object getItem(int i) {
        return arraySanPham.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(myLayout, null);

        TextView txtTen = (TextView) view.findViewById(R.id.txtTenSP);
        TextView txtGia = (TextView) view.findViewById(R.id.txtGiaSP);
        ImageView imgHinhSP = (ImageView) view.findViewById(R.id.imgHinhSP);

        DecimalFormat decimalFormat = new DecimalFormat("###, ###, ###");
        txtTen.setText(arraySanPham.get(i).TenSP);
        txtGia.setText("Giá: " + decimalFormat.format(arraySanPham.get(i).GiaSP) + "đ");

        //chuyen
        byte[] byteHinh = arraySanPham.get(i).HinhSP;
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteHinh, 0, byteHinh.length);
        imgHinhSP.setImageBitmap(bitmap);
        return view;
    }
}
