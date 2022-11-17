/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

//import com.mycompany.mavenproject1.hibernate.BanHangHibernate;
import reponsitory.impl.SanPhamReponstory;
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
import reponsitory.impl.KhachHangReponstory;
import seivce.ISanPhamSevice;

/**
 *
 * @author asus_vinh
 */
public class SanPHamSeviceImpl implements ISanPhamSevice {

    private SanPhamReponstory banHang = new SanPhamReponstory();
    private KhachHangReponstory jkh = new KhachHangReponstory();

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
    public List<SanPhamCustom> getAllSanPham() {
        return banHang.getAllSP();
    }

    @Override
    public List<ChiTietSPCustom> getOne(String ten) {
        if (ten.isEmpty()) {
            return banHang.getAll();
        } else {
            return banHang.getOne(ten);
        }

    }

    @Override
    public List<KhachHangCutoms> getAllll() {
        return jkh.getAllKH();
    }

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

    @Override
    public String addChiTietSP(ChiTietSanPham sp) {

        if (banHang.addChiTietSp(sp)) {
            return "Them thanh cong";
        } else {
            return "thêm thất bại";
        }

    }
//

    @Override
    public String updateSanPham(SanPham sp, String ten, String id, String ma) {
        if (banHang.updateSp(sp, ten, id, ma)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String updateMauSac(MauSac ms, String id, String ma, String ten) {
        if (banHang.updateMauSac(ms, id, ma, ten)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String updateSPCT(ChiTietSanPham ct, String id, String idSP, String idDongSP, String idMau, String idNamSX, BigDecimal giaBan, BigDecimal giaNhap,
            int namBH, int soLuongTon) {
        if (banHang.updateChitietSP(ct, id, idSP, idDongSP, idMau, idNamSX, giaBan, giaNhap, namBH, soLuongTon)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String deleteChitietSP(ChiTietSanPham sp, String id) {
        if (banHang.deleteChitietSP(sp, id)) {
            return "delete thành công";
        } else {
            return "delete thất bại";
        }
    }

    @Override
    public String deleteSP(SanPham sp, String id) {
        if (banHang.deleteSP(sp, id)) {
            return "delete thành công";
        } else {
            return "delete thất bại";
        }

    }

    @Override
    public String deleteMauSac(MauSac ms, String id) {
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
    public String updateDongSp(DongSp sp, String id, String ma, String ten) {
        if (banHang.updateDongSP(sp, id, ma, ten)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String updateNamSX(NamSanXuat sp, String id, String ma, String ten) {
        if (banHang.updateNamSX(sp, id, ma, ten)) {
            return "Update  thành công";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public String deleteDongSp(DongSp sp, String id) {
        if (banHang.deleteDongSP(sp, id)) {
            return "delete thành công";
        } else {
            return "delete thất bại";
        }
    }

    @Override
    public String deleteNamSX(NamSanXuat sp, String id) {
        if (banHang.deleteNamSX(sp, id)) {
            return "delete thành công";
        } else {
            return "delete thất bại";
        }
    }
}
