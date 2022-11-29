/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import viewmodel.HDCTCustom;
import viewmodel.HoaDonCustom;

/**
 *
 * @author Nguyen
 */
public interface IHoaDonService {
    List<HoaDonCustom> getAllHD();
    List<HDCTCustom> getAllHDCT(int maHD);
//    HoaDon getById(String id);
//    List<HoaDon> getHDTheoTrangThai(byte trangThai);
}
