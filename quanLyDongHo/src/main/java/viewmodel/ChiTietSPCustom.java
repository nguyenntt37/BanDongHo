/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author asus_vinh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSPCustom {

    private Integer id;

    private String maSP;

    private String tenSP;

    private int NBH;

    private String ThuongHieu;

    private String mau;

    private String nSX;

    private int soLuongTon;

    private BigDecimal giaNhap;

    private BigDecimal giaBan;

    private String moTa;

    private String ngayTao;

    private String ngaySua;

    private Integer trangThai;

    private String xuatXu;

    private String kinh;
    
    private String dayDeo;

    private String chucNang;

    @Override
    public String toString() {
        return tenSP;
    }

    public int toStringID() {
        return id;
    }

    public Object[] todataRowDS() {
        return new Object[]{id,maSP, tenSP, giaBan, mau, ThuongHieu, dayDeo, kinh, xuatXu, soLuongTon,trangThai};
    }
}
