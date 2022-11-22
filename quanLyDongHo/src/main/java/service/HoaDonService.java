/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.List;
import model.hoadon.HoaDon;
import model.hoadon.hoaDonChiTiet;
import viewmodel.HoaDonCustom2;
import viewmodel.hoaDonCustom;

/**
 *
 * @author asus_vinh
 */
public interface HoaDonService {


    List<HoaDonCustom2> getAllHoaDon();

    String add(HoaDon hd);

    String addHoaDonChiTiet(hoaDonChiTiet hd);

    String addHoaDonChiTietNhanVien(hoaDonChiTiet hd);

    String delete(hoaDonChiTiet hd, int id);

    String deleteHD(HoaDon hd, int id);

    String updateHoaDonCT(hoaDonChiTiet hdct, HoaDon hd, int id,
            int idCtSP, String ngayTao, String ngayNhan, String ngayShip, String ngayThanhToan,
            String tenNguoiNhan, int soLuong, int tinhTrang, BigDecimal donGia, String sdt, String diaChi);
    String updateTinhTrang(HoaDon hd, int tinhTrang, String idHD); 
    
}
