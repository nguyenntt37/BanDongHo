/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.HoaDonRepository;
import java.util.ArrayList;
import java.util.List;
import model.hoadon.HoaDon;
import service.IBanHangService;
import viewmodel.HoaDonCustom2;

/**
 *
 * @author Nguyen
 */
public class BanHangServiceImpl implements IBanHangService {
    HoaDonRepository hdRepo = new HoaDonRepository();

    @Override
    public List<HoaDonCustom2> get() {
        List<HoaDon> lstHD = hdRepo.getAll();
        List<HoaDonCustom2> lstHDCustom = new ArrayList<>();
        for (HoaDon hd : lstHD) {
            lstHDCustom.add(new HoaDonCustom2(String.valueOf(hd.getId()), hd.getTgTao(), hd.getNhanVien().getMa(), hd.getKhachHang().getHo() + " " + hd.getKhachHang().getTenDem() + " " + hd.getKhachHang().getTen()));
        }
        return lstHDCustom;
    }
}
