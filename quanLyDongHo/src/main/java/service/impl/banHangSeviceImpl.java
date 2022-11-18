/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.banHangReponsitory;
import java.util.List;
import model.giohang.GioHang;
import model.giohang.GioHangChiTiet;
import model.hoadon.HoaDon;
import model.hoadon.hoaDonChiTiet;
import service.banHangSevice;
import viewmodel.ChiTietSPCustom;
import viewmodel.GioHangChiTietCustom;
import viewmodel.hoaDonCustom;

/**
 *
 * @author asus_vinh
 */
public class banHangSeviceImpl implements banHangSevice {
    
    private banHangReponsitory banHangRepo = new banHangReponsitory();
    
    @Override
    public List<hoaDonCustom> getAllHD() {
        return banHangRepo.getAllHD();
    }
    
    @Override
    public List<GioHangChiTietCustom> getAllGH() {
        return banHangRepo.getAllGH();
    }
    
    @Override
    public List<ChiTietSPCustom> getAsll() {
        return banHangRepo.getAsll();
    }
    
    @Override
    public String addHD(hoaDonChiTiet hd) {
        if (banHangRepo.addHD(hd)) {
            return "";
        } else {
            return "";
        }
        
    }
    
    @Override
    public String addHoaDon(HoaDon hd) {
        if (banHangRepo.addHoaDon(hd)) {
            return "";
        } else {
            return "";
        }
        
    }
    
    @Override
    public String delete(GioHangChiTiet gh, int id) {
        if (banHangRepo.delete(gh, id)) {
            return "";
        } else {
            return "";
        }
    }
    
    @Override
    public String addGH(GioHangChiTiet gh) {
        if (banHangRepo.addGH(gh)) {
            return "";
        } else {
            return "";
        }
    }
    
    @Override
    public String addGioHang(GioHang g) {
        if (banHangRepo.addGioHang(g)) {
            return "";
        } else {
            return "";
        }
    }
    
    @Override
    public String updateGioHangChiTiet(GioHangChiTiet ct, int soLuong, int id) {
        if (banHangRepo.updateGioHangChiTiet(ct, soLuong, id)) {
            return "";
        } else {
            return "";
        }
    }
    
    @Override
    public List<ChiTietSPCustom> getSearh(String ten) {
        return banHangRepo.getSearh(ten);
    }
    
    @Override
    public String updateHD(HoaDon ct, String tennguoiNhan, String sdt, String diaChi, int id) {
        if (banHangRepo.updateHD(ct, tennguoiNhan, sdt, diaChi, id)) {
            return "";
        } else {
            return "";
        }
        
    }
    
}
