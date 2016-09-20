package com.vanphat.hokien.sqlitenangcao;

/**
 * Created by HoKien on 9/15/2016.
 */
public class SanPham {
    public Integer ID;
    public String TenSP;
    public Integer GiaSP;
    public byte[] HinhSP;

    public SanPham(Integer ID, String tenSP, Integer giaSP, byte[] hinhSP) {
        this.ID = ID;
        TenSP = tenSP;
        GiaSP = giaSP;
        HinhSP = hinhSP;
    }
}
