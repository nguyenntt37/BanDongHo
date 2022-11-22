/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.HoaDonReponsitory;
import java.math.BigDecimal;
import java.util.List;
import model.hoadon.HoaDon;
import model.hoadon.hoaDonChiTiet;
import service.HoaDonService;
import viewmodel.HoaDonCustom2;

/**
 *
 * @author asus_vinh
 */
public class HoaDonServiceIpml implements HoaDonService {

    HoaDonReponsitory hoaDonHi = new HoaDonReponsitory();


    @Override
    public String add(HoaDon hd) {
        if (hoaDonHi.add(hd)) {
            return "";
        } else {
            return "";
        }
    }

    @Override
    public String addHoaDonChiTiet(hoaDonChiTiet hd) {
        if (hoaDonHi.addHoaDonChiTiet(hd)) {
            return "Thanh toán thành công";
        } else {
            return "Thanh toán thất bại";
        }
    }

    @Override
    public String addHoaDonChiTietNhanVien(hoaDonChiTiet hd) {
        if (hoaDonHi.addHoaDonChiTiet(hd)) {
            return "add thành công";
        } else {
            return "add thất bại";
        }
    }


    @Override
    public String delete(hoaDonChiTiet hd, int id) {
        if (hoaDonHi.delete(hd, id)) {
            return "xóa thành công";
        } else {
            return "xóa thất bại";
        }
    }

    @Override
    public String deleteHD(HoaDon hd, int id) {
        if (hoaDonHi.deleteHD(hd, id)) {
            return "";
        } else {
            return "";
        }
    }

    @Override
    public String updateHoaDonCT(hoaDonChiTiet hdct,HoaDon hd, int id,
            int idCtSP, String ngayTao, String ngayNhan, String ngayShip, String ngayThanhToan,
            String tenNguoiNhan, int soLuong, int tinhTrang, BigDecimal donGia, String sdt, String diaChi) {
        
      if(hoaDonHi.updateHoaDonChiTiet(hdct, id, idCtSP, soLuong, donGia)&&hoaDonHi.updateHoaDon(hd, id, ngayTao, ngayThanhToan, ngayShip, ngayNhan, tenNguoiNhan, tinhTrang, sdt, diaChi)){
          return "update thanh công";
      }else{
          return "update thất bại";
      }
    }

    @Override
    public List<HoaDonCustom2> getAllHoaDon() {
        return hoaDonHi.getAllHD();
    }

    @Override
    public String updateTinhTrang(HoaDon hd, int tinhTrang, String idHD) {
        if(hoaDonHi.updateTinhTrang(hd, tinhTrang, idHD)){
            return "Update thanh cong";
        }
        else return "Update that bai";
    }


}
