/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

//import com.mycompany.mavenproject1.hibernate.BanHangHibernate;

import Repository.SanPhamReponstory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.ChiTietSanPham;
import model.sanpham.DongSp;
import model.sanpham.MauSac;
import model.sanpham.NamSanXuat;
import model.sanpham.SanPham;
import model.sanpham.thuongHieu;
import service.ISanPhamSevice;
import viewmodel.ChiTietSPCustom;
import viewmodel.DongSPCustom;
import viewmodel.MauSacCustom;
import viewmodel.NamSXCustom;
import viewmodel.SanPhamCustom;
import viewmodel.SanPhamCustom3;
import viewmodel.ThuongHieuCustomer;


/**
 *
 * @author asus_vinh
 */
public class SanPHamSeviceImpl implements ISanPhamSevice {

    private SanPhamReponstory banHang = new SanPhamReponstory();
//    private KhachHangReponstory jkh = new KhachHangReponstory();

    @Override
    public List<ChiTietSPCustom> getAll() {
        return banHang.getAll();
    }
    public List<ChiTietSPCustom> getAsll(){
        return banHang.getAsll();
    }
    public List<ChiTietSPCustom> getFind(int namBH){
        return banHang.getFind(namBH);
    }
    @Override
    public List<MauSacCustom> getAllMauSac() {
        return banHang.getAllMauSac();
    }

    @Override
    public List<NamSXCustom> getAllNamSX() {
        return banHang.getAllNamSX();
    }

    @Override
    public List<DongSPCustom> getAllDongSp() {
        return banHang.getAllDongSP();
    }

    @Override
    public List<SanPhamCustom3> getAllSanPham() {
        return banHang.getAllSP();
    }

    @Override
    public List<ThuongHieuCustomer> getAllThuongHieu() {
        return banHang.getAllThuongHieu();
    }
    
    @Override
    public String addThuongHieu(thuongHieu th) {
        if (banHang.addThuongHieu(th)) {
            return "Thêm  thành công";
        } else {
            return "thêm thất bại";
        }
    }
    
    @Override
    public List<ChiTietSPCustom> getOne(String ten) {
        if (ten.isEmpty()) {
            return banHang.getAll();
        } else {
            return banHang.getOne(ten);
        }

    }

//    @Override
//    public List<KhachHangCutoms> getAllll() {
//        return jkh.getAllKH();
//    }

    @Override
    public String addSanPham(SanPham sp) {
        if (banHang.add(sp)) {
            return "Thêm  thành công";
        } else {
            return "thêm thất bại";
        }
    }

    @Override
    public String addMauSac(MauSac ms) {
        if (banHang.addMauSac(ms)) {
            return "Thêm  thành công";
        } else {
            return "thêm thất bại";
        }
    }


    public String addChiTietSP(ChiTietSanPham sp) {

        if (banHang.addChiTietSp(sp)) {
            return "Them thanh cong";
        } else {
            return "thêm thất bại";
        }

    }
//

    @Override
    public String updateSanPham(SanPham sp, String ten, int id, String ma, String ChucNang, String dayDeo, String kinh, String matSo, String may, String xuatXu) {
        if (banHang.updateSp(sp, ten, id, ma, ChucNang, dayDeo, kinh, matSo, may, xuatXu)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String updateThuongHieu(thuongHieu th, int id, String ma, String ten) {
        if (banHang.updateThuongHieu(th, id, ma, ten)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }
    
    @Override
    public String updateMauSac(MauSac ms, int id, String ma, String ten) {
        if (banHang.updateMauSac(ms, id, ma, ten)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String updateSPCT(ChiTietSanPham ct, int id, int idSP, int idDongSP, int idMau, int idNamSX, BigDecimal giaBan, BigDecimal giaNhap,
            int namBH, int soLuongTon) {
        if (banHang.updateChitietSP(ct, id, idSP, idDongSP, idMau, idNamSX, giaBan, giaNhap, namBH, soLuongTon)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String deleteChitietSP(ChiTietSanPham sp, int id) {
        if (banHang.deleteChitietSP(sp, id)) {
            return "delete thành công";
        } else {
            return "delete thất bại";
        }
    }

    @Override
    public String deleteThuongHieu(thuongHieu th, int id) {
        if (banHang.deleteThuongHieu(th, id)) {
            return "delete thành công";
        } else {
            return "delete thất bại";
        }
    }
    
    @Override
    public String deleteSP(SanPham sp, int id) {
        if (banHang.deleteSP(sp, id)) {
            return "delete thành công";
        } else {
            return "delete thất bại";
        }

    }

    @Override
    public String deleteMauSac(MauSac ms, int id) {
        if (banHang.deleteMau(ms, id)) {
            return "delete thành công";
        } else {
            return "delete thất bại";
        }

    }

    @Override
    public String addDongSp(DongSp dp) {
        if (banHang.addDongSp(dp)) {
            return "Thêm  thành công";
        } else {
            return "thêm thất bại";
        }
    }

    @Override
    public String addNamSX(NamSanXuat nsx) {
        if (banHang.addNamSX(nsx)) {
            return "Thêm  thành công";
        } else {
            return "thêm thất bại";
        }
    }

    @Override
    public String updateDongSp(DongSp sp, int id, String ma, String ten) {
        if (banHang.updateDongSP(sp, id, ma, ten)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String updateNamSX(NamSanXuat sp, int id, String ma, String ten) {
        if (banHang.updateNamSX(sp, id, ma, ten)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String deleteDongSp(DongSp sp, int id) {
        if (banHang.deleteDongSP(sp, id)) {
            return "delete thành công";
        } else {
            return "delete thất bại";
        }
    }
    
    @Override
    public String deleteNamSX(NamSanXuat sp, int id) {
        if (banHang.deleteNamSX(sp, id)) {
            return "delete thành công";
        } else {
            return "delete thất bại";
        }
    }
    
    @Override
    public List<ChiTietSPCustom> getAll2(int heSo) {
        List<ChiTietSPCustom> list = banHang.getAll2(heSo);
        List<ChiTietSPCustom> list2 = new ArrayList<>();
        int index = 0;
        for (ChiTietSPCustom l : list) {
            list2.add(l);
            index++;
            if (index == 10) 
                break;
        }
        return list2;
    }

    @Override
    public List<ChiTietSPCustom> search(String ten) {
        return banHang.search(ten);
    }
}
