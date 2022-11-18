/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.giohang.GioHang;
import model.giohang.GioHangChiTiet;
import model.hoadon.HoaDon;
import model.hoadon.hoaDonChiTiet;
import viewmodel.ChiTietSPCustom;
import viewmodel.GioHangChiTietCustom;
import viewmodel.hoaDonCustom;

/**
 *
 * @author asus_vinh
 */
public interface banHangSevice {

    public List<hoaDonCustom> getAllHD();

    public List<GioHangChiTietCustom> getAllGH();

    public List<ChiTietSPCustom> getSearh(String ten);

    public List<ChiTietSPCustom> getAsll();

    public String addHD(hoaDonChiTiet hd);

    public String addHoaDon(HoaDon hd);

    public String delete(GioHangChiTiet gh, int id);

    public String addGH(GioHangChiTiet gh);

    public String addGioHang(GioHang g);

    public String updateGioHangChiTiet(GioHangChiTiet ct, int soLuong, int id);

    public String updateHD(HoaDon ct, String tennguoiNhan, String sdt, String diaChi, int id);

}
