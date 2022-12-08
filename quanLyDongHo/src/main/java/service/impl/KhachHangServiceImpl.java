/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.KhachHangRespository;
import java.util.ArrayList;
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
    public String update(KhachHang khachHang, String ma, int trangthai, String ho, String quocGia, String sdt, String ten, String tenDem, String thanhPho, int id) {

        return khr.update(khachHang, ma, trangthai, ho, quocGia, sdt, ten, tenDem, thanhPho, id) ? "cập nhập thành công" : "Cập nhật thất bại";
    }

    @Override
    public List<KhachHangCutoms> search(String ten) {
        return khr.search(ten);
    }

    @Override
    public List<KhachHangCutoms> locTrangThai(int trangThai) {
        return khr.locTrangThai(trangThai);
    }

    @Override
    public List<KhachHangCutoms> getAll(int heSo) {
    List<KhachHangCutoms>likhc = new ArrayList<>();
     int i = 0;
        for (KhachHangCutoms khachHangCutoms : khr.getAll(heSo)) {
            likhc.add(khachHangCutoms);
            i++;
            if (i==5) {
               break;
            }
        }
         return likhc;
        
    } }


