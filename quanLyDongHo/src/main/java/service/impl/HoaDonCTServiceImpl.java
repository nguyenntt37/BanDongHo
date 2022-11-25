/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.HoaDonCTRepository;
import java.util.List;
import model.hoadon.hoaDonChiTiet;
import service.IHoaDonCTService;

/**
 *
 * @author Nguyen
 */
public class HoaDonCTServiceImpl implements IHoaDonCTService {
    HoaDonCTRepository repo = new HoaDonCTRepository();

    @Override
    public List<hoaDonChiTiet> getAll(String maHD) {
        List<hoaDonChiTiet> lstHDCT;
        lstHDCT = repo.getAll(maHD);
        return lstHDCT;
    }
}
