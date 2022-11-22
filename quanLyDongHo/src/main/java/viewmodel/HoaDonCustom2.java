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
 * @author Acer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonCustom2 {
//    hd.getMa(),hd.getTenSP(),hd.getNgayTao(),hd.getNgayThanhToan(),hd.getNgayShip(),hd.getNgayNhan(),hd.getTenNguoiNhan(),hd.getDiaChi(),hd.getSdt(),hd.getSoLuong(),hd.getDongia(),setTinhTrang(list)
    private int id;
    private String ma;
    private String tenSP;
    private String ngayTao;
    private String ngayThanhToan;
    private String ngayShip;
    private String ngayNhan;
    private String tenNguoiNhan;
    private String diaChi;
    private String sdt;
    private int soLuong;
    private BigDecimal dongia;
    private Integer trangThai;
}
