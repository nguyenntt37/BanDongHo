/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.HinhThucGHRepository;
import Repository.HoaDonCTRepository;
import Repository.HoaDonRepository;
import Repository.PhuongThucTTRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.hoadon.HoaDon;
import model.hoadon.hoaDonChiTiet;
import service.IHoaDonService;
import viewmodel.HDCTCustom;
import viewmodel.HoaDonCustom;

/**
 *
 * @author Nguyen
 */
public class HoaDonServiceImpl implements IHoaDonService {

    HoaDonRepository hdRepo = new HoaDonRepository();
    HoaDonCTRepository hdctRepo = new HoaDonCTRepository();
    PhuongThucTTRepository ptttRepo = new PhuongThucTTRepository();
    HinhThucGHRepository htghRepo = new HinhThucGHRepository();

    @Override
    public List<HoaDonCustom> getAllHD() {
        String tttt;
        List<HoaDonCustom> lstHDCustom = new ArrayList<>();
        List<HoaDon> lstHoaDon = hdRepo.getAll();
        for (HoaDon hd : lstHoaDon) {
            tttt = String.valueOf(hd.getTrangThaiTT());
            lstHDCustom.add(new HoaDonCustom(
                    hd.getMa(),
                    hd.getTongTien() == null ? new BigDecimal(0) : hd.getTongTien(),
                    hd.getTienTraLai() == null ? new BigDecimal(0) : hd.getTienTraLai(),
                    hd.getPhuongThucTT() == null ? "Chưa có" : hd.getPhuongThucTT().getTen(),
                    hd.getHinhThucGH() == null ? "Chưa có" : hd.getHinhThucGH().getTen(),
                    hd.getTgTao(),
                    tttt.equals("-1") ? "Đã huỷ" : tttt.equals("0") ? "Chờ thanh toán" : "Đã thanh toán",
                    hd.getNhanVien().getId(),
                    hd.getNhanVien().getHo() + " " + hd.getNhanVien().getTenDem() + " " + hd.getNhanVien().getTen(),
                    hd.getKhachHang().getId(),
                    hd.getKhachHang().getHo() + " " + hd.getKhachHang().getTenDem() + " " + hd.getKhachHang().getTen(),
                    hd.getGhiChu()
            ));
        }
        return lstHDCustom;
    }

    @Override
    public List<HDCTCustom> getAllHDCT(int maHD) {
        BigDecimal donGia, thanhTien;
        List<HDCTCustom> lstHDCTCustom = new ArrayList<>();
        List<hoaDonChiTiet> lstHoaDon = hdctRepo.getAll(maHD);
        for (hoaDonChiTiet hdct : lstHoaDon) {
            donGia = hdct.getDongia() == null ? BigDecimal.valueOf(0) : hdct.getDongia();
            thanhTien = donGia.multiply(BigDecimal.valueOf(hdct.getSoLuong()));
            lstHDCTCustom.add(new HDCTCustom(hdct.getId(),
                    hdct.getChiTietSP().getId(),
                    hdct.getChiTietSP().getDongsp().getTen() + " " + hdct.getChiTietSP().getThuongHieu().getTen() + " " + hdct.getChiTietSP().getSanPham().getTen(),
                    hdct.getSoLuong(),
                    hdct.getDongia(),
                    thanhTien
            ));
        }
        return lstHDCTCustom;
    }

    //
//    @Override
//    public HoaDon getById(String id) {
//        HoaDon hd;
//        hd = repo.getById(id);
//        return hd;
//    }
//
//    @Override
//    public List<HoaDon> getHDTheoTrangThai(byte trangThai) {
//        List<HoaDon> lstHDTheoTT;
//        lstHDTheoTT = repo.getHDTheoTrangThai(trangThai);
//        return lstHDTheoTT;
//    }
    @Override
    public Object[] getAllPTTT() {
        return ptttRepo.getAll();
    }

    @Override
    public Object[] getAllHTGH() {
        return htghRepo.getAll();
    }

    @Override
    public Object[] getTGTaoTheoNam() {
        return hdRepo.getTGTaoTheoNam().toArray();
    }

    @Override
    public List<HoaDonCustom> getAllHDBySearching(String search) {
        String tttt;
        List<HoaDonCustom> lstHDCustom = new ArrayList<>();
        List<HoaDon> lstHoaDon = hdRepo.searchHD(search);
        for (HoaDon hd : lstHoaDon) {
            tttt = String.valueOf(hd.getTrangThaiTT());
            lstHDCustom.add(new HoaDonCustom(
                    hd.getMa(),
                    hd.getTongTien() == null ? new BigDecimal(0) : hd.getTongTien(),
                    hd.getTienTraLai() == null ? new BigDecimal(0) : hd.getTienTraLai(),
                    hd.getPhuongThucTT() == null ? "Chưa có" : hd.getPhuongThucTT().getTen(),
                    hd.getHinhThucGH() == null ? "Chưa có" : hd.getHinhThucGH().getTen(),
                    hd.getTgTao(),
                    tttt.equals("-1") ? "Đã huỷ" : tttt.equals("0") ? "Chờ thanh toán" : "Đã thanh toán",
                    hd.getNhanVien().getId(),
                    hd.getNhanVien().getHo() + " " + hd.getNhanVien().getTenDem() + " " + hd.getNhanVien().getTen(),
                    hd.getKhachHang().getId(),
                    hd.getKhachHang().getHo() + " " + hd.getKhachHang().getTenDem() + " " + hd.getKhachHang().getTen(),
                    hd.getGhiChu()
            ));
        }
        return lstHDCustom;
    }
}
