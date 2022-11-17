/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import ViewModels.ChiTietSPCustom;
import ViewModels.DongSPCustom;
import ViewModels.KhachHangCutoms;
import ViewModels.MauSacCustom;
import ViewModels.NamSXCustom;
import ViewModels.SanPhamCustom;
import java.math.BigDecimal;
import java.util.List;
import model.SanPham.ChiTietSanPham;
import model.SanPham.DongSp;
import model.SanPham.MauSac;
import model.SanPham.NamSanXuat;
import model.SanPham.SanPham;

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

    List<KhachHangCutoms> getAllll();

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
