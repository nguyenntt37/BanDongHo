/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.hoadon.HoaDon;

/**
 *
 * @author Nguyen
 */
public interface IHoaDonService {
    void insert(HoaDon hd);
    List<HoaDon> getAll();
    HoaDon getById(String id);
    List<HoaDon> getHDTheoTrangThai(byte trangThai);
}
