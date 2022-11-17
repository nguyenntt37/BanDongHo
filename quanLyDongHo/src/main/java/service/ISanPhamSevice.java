/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.List;
import model.sanpham.ChiTietSanPham;
import model.sanpham.DongSp;
import model.sanpham.MauSac;
import model.sanpham.NamSanXuat;
import model.sanpham.SanPham;
import viewmodel.ChiTietSPCustom;
import viewmodel.DongSPCustom;
import viewmodel.MauSacCustom;
import viewmodel.NamSXCustom;
import viewmodel.SanPhamCustom;
/**
 *
 * @author asus_vinh
 */
public interface ISanPhamSevice {

    List<ChiTietSPCustom> getAll();

    List<MauSacCustom> getAllMauSac();

    List<NamSXCustom> getAllNamSX();

    List<DongSPCustom> getAllDongSp();

    List<SanPhamCustom> getAllSanPham();

    List<ChiTietSPCustom> getOne(String ten);

    // List<KhachHangCutoms> getAllll();

    String addSanPham(SanPham sp);

    String addDongSp(DongSp dp);

    String addNamSX(NamSanXuat nsx);

    String addMauSac(MauSac ms);

    String addChiTietSP(ChiTietSanPham sp);

    String updateSanPham(SanPham sp, String ten, String id, String ma);

    String updateDongSp(DongSp sp, String id, String ma, String ten);

    String updateNamSX(NamSanXuat sp, String id, String ma, String ten);

    String updateMauSac(MauSac ms, String id, String ma, String ten);

    String updateSPCT(ChiTietSanPham ct, String id, String idSP, String idDongSP, String idMau, String idNamSX, BigDecimal giaBan, BigDecimal giaNhap,
            int namBH, int soLuongTon);

    String deleteChitietSP(ChiTietSanPham sp, String id);

    String deleteSP(SanPham sp, String id);

    String deleteDongSp(DongSp sp, String id);

    String deleteNamSX(NamSanXuat sp, String id);

    String deleteMauSac(MauSac ms, String id);
}
