/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.HoaDonCTRepository;
import Repository.HoaDonRepository;
import Repository.SanPhamReponstory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.hoadon.HoaDon;
import service.IBanHangService;
import viewmodel.BanHang_HDCustom;

/**
 *
 * @author Nguyen
 */
public class BanHangServiceImpl implements IBanHangService {
    HoaDonRepository hdRepo = new HoaDonRepository();
    HoaDonCTRepository hdctRepo = new HoaDonCTRepository();
    SanPhamReponstory spRepo = new SanPhamReponstory();

    @Override
    public List<BanHang_HDCustom> getHDCho(int trangThai) {
        List<HoaDon> lstHD = hdRepo.getHDTheoTrangThai(trangThai);
        List<BanHang_HDCustom> lstHDCustom = new ArrayList<>();
        for (HoaDon hd : lstHD) {
            lstHDCustom.add(new BanHang_HDCustom(String.valueOf(hd.getId()), hd.getTgTao(), hd.getNhanVien().getMa(), hd.getKhachHang().getHo() + " " + hd.getKhachHang().getTenDem() + " " + hd.getKhachHang().getTen()));
        }
        return lstHDCustom;
    }

    @Override
    public Object[] getSPCustom() {
        return spRepo.getBH_SPCustom();
    }

    @Override
    public Object[] getHDCT(int idHD) {
        return hdctRepo.getBH_HDCTCustom(idHD);
    }

    @Override
    public void insertSPToHDCT(int idHD, int sl, int idCTSP) {
        hdctRepo.insertSPToHDCT(idHD, sl, idCTSP);
    }
    
    @Override
    public void loadSLTon(int sl, int idCTSP) {
        spRepo.loadSLTon(sl, idCTSP);
    }

    @Override
    public void setTTDaHuy(int idHD) {
        hdRepo.setTTDaHuy(idHD);
    }

    @Override
    public void setTTDaThanhToan(int idHD, String ngayTT, BigDecimal tongTien, BigDecimal tienTraLai, int htgh, int pttt) {
        hdRepo.setTTDaThanhToan(idHD, ngayTT, tongTien, tienTraLai, htgh, pttt);
    }
}
