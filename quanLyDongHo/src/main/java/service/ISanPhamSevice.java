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
import model.sanpham.thuongHieu;
import viewmodel.ChiTietSPCustom;
import viewmodel.DongSPCustom;
import viewmodel.MauSacCustom;
import viewmodel.NamSXCustom;
import viewmodel.SanPhamCustom;
import viewmodel.ThuongHieuCustomer;


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
    
    List<ThuongHieuCustomer> getAllThuongHieu();
    
    String addThuongHieu(thuongHieu th);

    List<ChiTietSPCustom> getOne(String ten);

//    List<KhachHangCutoms> getAllll();

    String addSanPham(SanPham sp);

    String addDongSp(DongSp dp);

    String addNamSX(NamSanXuat nsx);

    String addMauSac(MauSac ms);

//    String addChiTietSP(ChiTietSanPham sp);

    String updateSanPham(SanPham sp, String ten, int id, String ma);
    
    String updateThuongHieu(thuongHieu th, int id, String ma, String ten);

    String updateDongSp(DongSp sp, int id, String ma, String ten);

    String updateNamSX(NamSanXuat sp, int id, String ma, String ten);

    String updateMauSac(MauSac ms, int id, String ma, String ten);

    String updateSPCT(ChiTietSanPham ct, int id, int idSP, int idDongSP, int idMau, int idNamSX, BigDecimal giaBan, BigDecimal giaNhap,
            int namBH, int soLuongTon);

    String deleteChitietSP(ChiTietSanPham sp, int id);

    String deleteSP(SanPham sp, int id);

    String deleteDongSp(DongSp sp, int id);

    String deleteNamSX(NamSanXuat sp, int id);
    
    String deleteThuongHieu(thuongHieu th, int id);

    String deleteMauSac(MauSac ms, int id);
    
    List<ChiTietSPCustom> getAll2(int heSo);
    
    List<ChiTietSPCustom> search(String ten);
}
