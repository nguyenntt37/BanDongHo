/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.HoaDonRepository;
import java.util.List;
import model.hoadon.HoaDon;
import service.IHoaDonService;

/**
 *
 * @author Nguyen
 */
public class HoaDonServiceImpl implements IHoaDonService {
    HoaDonRepository repo = new HoaDonRepository();
    
    @Override
    public void insert(HoaDon hd) {
        repo.insert(hd);
    }

    @Override
    public List<HoaDon> getAll() {
        List<HoaDon> lstHoaDon;
        lstHoaDon = repo.getAll();
        return lstHoaDon;
    }

    @Override
    public HoaDon getById(String id) {
        HoaDon hd;
        hd = repo.getById(id);
        return hd;
    }

    @Override
    public List<HoaDon> getHDTheoTrangThai(byte trangThai) {
        List<HoaDon> lstHDTheoTT;
        lstHDTheoTT = repo.getHDTheoTrangThai(trangThai);
        return lstHDTheoTT;
    }
}
