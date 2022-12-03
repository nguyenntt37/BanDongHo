/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.List;
import model.hoadon.HoaDon;
import viewmodel.HDCTCustom;
import viewmodel.HoaDonCustom;

/**
 *
 * @author Nguyen
 */
public interface IHoaDonService {
    List<HoaDonCustom> getAllHD();
    List<HDCTCustom> getAllHDCT(int maHD);
    Object[] getAllPTTT();
    Object[] getAllHTGH();
    Object[] getTGTaoTheoNam();
    List<HoaDonCustom> getAllHDBySearching(String search);
    List<HoaDonCustom> getHDTheoTrangThai(int trangThai);
    List<HoaDonCustom> getHDTheoPTTT(String tenPTTT);
    List<HoaDonCustom> getHDTheoHTGH(String tenHTGH);
    List<HoaDonCustom> getHDTheoTongTien(BigDecimal from, BigDecimal to);
    List<HoaDonCustom> getHDTheoThang(int thang);
    List<HoaDonCustom> getHDTheoNam(int nam);
    List<HoaDonCustom> getHDTheoThangNam(int thang, int nam);
}
