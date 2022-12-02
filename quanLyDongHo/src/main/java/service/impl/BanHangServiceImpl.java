/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.DongSPRepository;
import Repository.HinhThucGHRepository;
import Repository.HoaDonCTRepository;
import Repository.HoaDonRepository;
import Repository.PhuongThucTTRepository;
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
    DongSPRepository dspRepo = new DongSPRepository();
    HinhThucGHRepository htghRepo = new HinhThucGHRepository();
    PhuongThucTTRepository ptttRepo = new PhuongThucTTRepository();

    @Override
    public void insert(HoaDon hd) {
        hdRepo.insert(hd);
    }

    @Override
    public List<BanHang_HDCustom> getHDCho() {
        List<HoaDon> lstHD = hdRepo.getHDTheoTrangThai(0);
        List<BanHang_HDCustom> lstHDCustom = new ArrayList<>();
        for (HoaDon hd : lstHD) {
            lstHDCustom.add(new BanHang_HDCustom(String.valueOf(hd.getMa()), hd.getTgTao(), hd.getNhanVien().getMa(), hd.getKhachHang()));
        }
        return lstHDCustom;
    }

    @Override
    public Object[] getSPCustom() {
        return spRepo.getBH_SPCustom().toArray();
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
    public void huyHD(int idHD) {
        hdRepo.huyHD(idHD);
    }
    
    @Override
    public void updateSLTon(int idCTSP, int sl) {
        hdctRepo.updateSLTon(idCTSP, sl);
    }

    @Override
    public void thanhToanHD(int idHD, String ngayTT, BigDecimal tongTien, BigDecimal tienTraLai, String ghiChu, int pttt, int htgh) {
        hdRepo.thanhToanHD(idHD, ngayTT, tongTien, tienTraLai, ghiChu, pttt, htgh);
    }

    @Override
    public void deleteSPOnHDCT(int idCTSP, int sl) {
        hdctRepo.deleteSPOnHDCT(idCTSP, sl);
    }

    @Override
    public Object[] loadCboDongSP() {
        return dspRepo.getAllDongSP().toArray();
    }

    @Override
    public Object[] getBH_SPCustomByDongSP(int idDSP) {
        return spRepo.getBH_SPCustomByDongSP(idDSP);
    }

    @Override
    public Object[] getAllHTGH() {
        return htghRepo.getAll();
    }

    @Override
    public Object[] getAllPTTT() {
        return ptttRepo.getAll();
    }

    @Override
    public Object[] searchSP(String search) {
        return spRepo.searchSP(search);
    }

    @Override
    public void updateKhachHang(int idKH, int idHD) {
        hdRepo.updateKhachHang(idKH, idHD);
    }
}
