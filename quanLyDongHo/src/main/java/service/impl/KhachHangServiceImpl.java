    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.KhachHangRespository;
import java.util.List;
import model.KhachHang;
import service.KhachHangService;
import viewmodel.KhachHangCutoms;

/**
 *
 * @author admin
 */
public class KhachHangServiceImpl implements KhachHangService {

    KhachHangRespository khr = new KhachHangRespository();

    @Override
    public List<KhachHangCutoms> getAllKH() {
        return khr.getAllKH();
    }

    @Override
    public String add(KhachHang kh) {
        boolean add = khr.add(kh);
        if (add) {
            return "Add thành công!";
        } else {
            return "Add thất bại!";
        }
    }

    @Override
    public String delete(int id) {
        boolean delete = khr.delete(id);
        if (delete) {
            return "delete thành công!";
        } else {
            return "delete thất bại!";
        }
    }

    @Override
    public String update(KhachHangCutoms kh) {
        KhachHang kh1 = new  KhachHang();
        kh.setHo(kh1.getHo());
        kh.setMa(kh1.getMa());
        kh.setten(kh1.getTen());
        kh.setQuocGia(kh1.getQuocGia());
        kh.setSdt(kh1.getSdt());
        kh.setTenDem(kh1.getTenDem());
        kh.setThanhPho(kh1.getThanhPho());
        kh.setId(kh1.getId());
        if (!khr.checkUpdate(kh1.getMa(), String.valueOf(kh.getId()))) {
            return "Mã không được trùng";
        }
        return khr.update(kh1) ? "" : "Cập nhật thất bại";
    }

    @Override
    public List<KhachHangCutoms> search(String ten) {
        return khr.search(ten);
    }

    @Override
    public List<KhachHangCutoms> locTrangThai(int trangThai) {
        return khr.locTrangThai(trangThai);
        }


}
