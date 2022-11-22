/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.NhanVienRepository;
import java.util.ArrayList;
import java.util.List;
import model.nhanvien.NhanVien;
import service.INhanVienService;
import viewmodel.NhanVienCustom;

/**
 *
 * @author Nguyen
 */
public class NhanVienServiceImpl implements INhanVienService {

    NhanVienRepository repo = new NhanVienRepository();

    @Override
    public List<NhanVienCustom> get() {
        List<NhanVien> nvList = repo.get();
        List<NhanVienCustom> nvCustomList = new ArrayList<>();
        for (NhanVien nv : nvList) {
            nvCustomList.add(new NhanVienCustom(nv.getMa(), nv.getHo() + " " + nv.getTenDem() + " " + nv.getTen(), nv.getGioiTinh(), nv.getNgaySinh(), nv.getDiaChi(), nv.getSdt(), nv.getTrangThai()));
        }
        return nvCustomList;
    }

    @Override
    public void add(NhanVien nv) {
        repo.add(nv);
    }

    @Override
    public void update(NhanVien nv, String maNV) {
        repo.update(nv, maNV);
    }
}
