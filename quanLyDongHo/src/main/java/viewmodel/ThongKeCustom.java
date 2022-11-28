/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import Util.MoneyUtil;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author asus_vinh
 */
public class ThongKeCustom {

    private String tenSP;

    private int soLuong;

    private BigDecimal DonGia;

    private String ngayTao;

    public ThongKeCustom() {
    }

    public ThongKeCustom(String tenSP, int soLuong, BigDecimal DonGia, String ngayTao) {
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.DonGia = DonGia;
        this.ngayTao = ngayTao;
    }

    public BigDecimal doanhThu() {
        double v;
        double g = Double.valueOf(DonGia + "");
        v = soLuong * g;
        return BigDecimal.valueOf(v);
    }

    public Object[] toDataRow() {
        return new Object[]{tenSP, soLuong, MoneyUtil.formatMoney1(DonGia), MoneyUtil.formatMoney1(DonGia), MoneyUtil.formatMoney1(doanhThu())};
    }

}
