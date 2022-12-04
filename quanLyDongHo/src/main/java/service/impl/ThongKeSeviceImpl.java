/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.ThongKeResponsitory;
import java.math.BigDecimal;
import java.util.List;
import service.IThongKeSevice;
import viewmodel.ThongKeCustom;

/**
 *
 * @author asus_vinh
 */
public class ThongKeSeviceImpl implements IThongKeSevice {

    private ThongKeResponsitory thongKe = new ThongKeResponsitory();

    @Override
    public List<ThongKeCustom> getAll() {
        return thongKe.getAll();
    }

    @Override
    public List<ThongKeCustom> searh(String ten) {
        return thongKe.searh(ten);
    }

    @Override
    public List<ThongKeCustom> searhNam(String nam) {
        return thongKe.searhNam(nam);
    }

    @Override
    public BigDecimal doanhThu() {
        return thongKe.doanhThu();
    }

    @Override
    public Long soHoaDon() {
        return thongKe.soHoaDon();
    }

    @Override
    public Long soHoaDonHuy(int trangthai) {
        return thongKe.soHoaDonHuy(trangthai);
    }

    @Override
    public Long soSanPhamBanDuoc() {
        return thongKe.soSanPhamBanDuoc();
    }

    @Override
    public BigDecimal doanhThuTheoNam() {
        return thongKe.doanhThuTheoNam();
    }

    @Override
    public BigDecimal doanhThuTheoTungThang() {
        return thongKe.doanhThuTheoTungThang();
    }

    @Override
    public BigDecimal doanhThuTheoTungThangCBB(String ngayTao) {
        return thongKe.doanhThuTheoTungThangCBB(ngayTao);
    }

    @Override
    public BigDecimal doanhThuTheoTungNamCBB(String ngayTao) {
        return thongKe.doanhThuTheoTungNamCBB(ngayTao);
    }

    @Override
    public Long soHoaDonTheoThang(String ngayTao) {
        return thongKe.soHoaDonTheoThang(ngayTao);
    }

    @Override
    public Long soHoaDonTheoNam(String ngayTao) {
        return thongKe.soHoaDonTheoNam(ngayTao);
    }

    @Override
    public Long soHoaDonHuyTheoThang(int trangthai, String ngayTao) {
        return thongKe.soHoaDonHuyTheoThang(trangthai, ngayTao);
    }

    @Override
    public Long soHoaDonHuyTheoNam(int trangthai, String ngayTao) {
        return thongKe.soHoaDonHuyTheoNam(trangthai, ngayTao);
    }

    @Override
    public Long soSanPhamBanDuocTheoThang(String ngayTao) {
        return thongKe.soSanPhamBanDuocTheoThang(ngayTao);
    }

    @Override
    public Long soSanPhamBanDuocTheoNam(String ngayTao) {
        return thongKe.soSanPhamBanDuocTheoNam(ngayTao);
    }

    @Override
    public Long soHoaDonTheoNamN() {
        return thongKe.soHoaDonTheoNamN();
    }

    @Override
    public Long soSanPhamBanDuocTheoNamN() {
        return thongKe.soSanPhamBanDuocTheoNamN();
    }

    @Override
    public Long soSanPhamBanDuocTheoThangN() {
        return thongKe.soHoaDonTheoThangN();
    }

    @Override
    public Long soHoaDonTheoThangN() {
        return thongKe.soSanPhamBanDuocTheoThangN();
    }

    @Override
    public Long soHoaDonHuyTheoThangN() {
        return thongKe.soHoaDonTheoThangN();
     }

    @Override
    public Long soHoaDonHuyTheoNamN() {
         return thongKe.soHoaDonTheoThangN();
    }

}
