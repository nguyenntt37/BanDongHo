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
    public List<KhachHangCutoms> listAll() {
        return khr.getAllKH();
    }

    @Override
    public String add(KhachHang kh) {
        boolean add = khr.add(kh);
        if (add) {
            return "Thêm thành công!";
        } else {
            return "Thêm thất bại!";
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

}
