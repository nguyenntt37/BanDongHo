/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.NhanVienRepository;
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
        return repo.get();
    }

    @Override
    public String add(NhanVien nv) {
        if(String.valueOf(nv.getMa()).isEmpty()||String.valueOf(nv.getGioiTinh()).isEmpty()||nv.getHo().isEmpty()||nv.getTen().isEmpty()||nv.getTenDem().isEmpty()||nv.getDiaChi().isEmpty()||nv.getSdt().isEmpty()||String.valueOf(nv.getTrangThai()).isEmpty()){
            return "Vui lòng nhập đủ dữ liệu";
        }
        boolean check = repo.add(nv);
        if(check){
            return "Thêm thành công";
        }
        else return "Thêm thất bại";
    }

    @Override
    public String update(NhanVien nv, String maNV) {
        if(String.valueOf(nv.getMa()).isEmpty()||String.valueOf(nv.getGioiTinh()).isEmpty()||nv.getHo().isEmpty()||nv.getTen().isEmpty()||nv.getTenDem().isEmpty()||nv.getDiaChi().isEmpty()||nv.getSdt().isEmpty()||String.valueOf(nv.getTrangThai()).isEmpty()){
            return "Vui lòng nhập đủ dữ liệu";
        }
        boolean check = repo.update(nv, maNV);
        if(check){
            return "Sua thanh cong";
        }
        else return "Sua that bai";
    }

    @Override
    public NhanVien getByMaNV(String maNV) {
        NhanVien nv = repo.getByMaNV(maNV);
        return nv;
    }

    @Override
    public String updateTrangThai(NhanVien nv, String maNV) {
        boolean check = repo.updateTrangThai(nv, maNV);
        if(check){
            return "Update thanh cong";
        }
        else return "Update that bai";

    }

    @Override
    public List<NhanVienCustom> Search(String sdt) {
        return repo.Search(sdt);
    }

    @Override
    public String updateMatKhau(NhanVien nv, String maNV, String matKhau) {
        boolean check = repo.updateMatKhau(nv, maNV, matKhau);
        if(check){
            return "Đổi mật khẩu thành công";
        }
        else return "Đổi mật khẩu thất bại";
    }
    

}
