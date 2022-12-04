/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.List;
import viewmodel.ThongKeCustom;

/**
 *
 * @author asus_vinh
 */
public interface IThongKeSevice {

    List<ThongKeCustom> getAll();

    List<ThongKeCustom> searh(String ten);

    List<ThongKeCustom> searhNam(String nam);

    BigDecimal doanhThu();

    Long soHoaDon();

    Long soHoaDonHuy(int trangthai);

    Long soSanPhamBanDuoc();

    BigDecimal doanhThuTheoNam();

    BigDecimal doanhThuTheoTungThang();

    BigDecimal doanhThuTheoTungThangCBB(String ngayTao);

    BigDecimal doanhThuTheoTungNamCBB(String ngayTao);

    Long soHoaDonTheoThang(String ngayTao);

    Long soHoaDonTheoNam(String ngayTao);

    Long soHoaDonHuyTheoThang(int trangthai, String ngayTao);

    Long soHoaDonHuyTheoNam(int trangthai, String ngayTao);

    Long soSanPhamBanDuocTheoThang(String ngayTao);

    Long soSanPhamBanDuocTheoNam(String ngayTao);

    Long soHoaDonTheoNamN();

    Long soSanPhamBanDuocTheoNamN();

    Long soSanPhamBanDuocTheoThangN();

    Long soHoaDonTheoThangN();

    Long soHoaDonHuyTheoThangN();

    Long soHoaDonHuyTheoNamN();
}
