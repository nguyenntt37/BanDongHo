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
    private List<String>listEmail = new ArrayList();

    @Override
    public List<NhanVienCustom> get() {
        return repo.get();
    }

    @Override
    public String add(NhanVien nv) {
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\\\.[a-zA-Z0-9-]+)*$";
        if(String.valueOf(nv.getMa()).isEmpty()||String.valueOf(nv.getGioiTinh()).isEmpty()||nv.getHo().isEmpty()||nv.getTen().isEmpty()||nv.getTenDem().isEmpty()||nv.getDiaChi().isEmpty()||nv.getSdt().isEmpty()||String.valueOf(nv.getTrangThai()).isEmpty()){
            return "Vui lòng nhập đủ dữ liệu";
        }
        List<NhanVienCustom>list = repo.get();
        for(NhanVienCustom nv4 : list){
            NhanVien nv5 = repo.getByMaNV(nv4.getMaNV());
            listEmail.add(nv5.getEmail());
        }
        for(int i =0; i<listEmail.size(); i++){
            if(nv.getEmail().equals(listEmail.get(i))){
                return "Không thể thêm nhân viên trùng email";
            }
        }
        if(nv.getTrangThai()!=1){
            return "Không thể thêm nhân viên đã nghỉ việc";
        }
        if(!nv.getEmail().matches(regex)){
            return "Email bạn nhập vào không đúng định dạng";
        }
        for(NhanVienCustom nv3 : list){
            if(nv3.getMaNV().equals(nv.getMa())){
                return "Không thể thêm nhân viên trùng mã";
            }
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
        List<NhanVienCustom>list = repo.get();
        for(NhanVienCustom nv4 : list){
            NhanVien nv5 = repo.getByMaNV(nv4.getMaNV());
            listEmail.add(nv5.getEmail());
        }
        for(int i =0; i<listEmail.size(); i++){
            if(nv.getEmail().equals(listEmail.get(i))){
                return "Không thể sửa nhân viên trùng email";
            }
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
