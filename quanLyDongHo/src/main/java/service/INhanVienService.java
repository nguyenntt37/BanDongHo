/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.nhanvien.NhanVien;
import viewmodel.NhanVienCustom;

/**
 *
 * @author Nguyen
 */
public interface INhanVienService {
    List<NhanVienCustom> get();
    String add(NhanVien nv);
    String update(NhanVien nv, String maNV);
    NhanVien getByMaNV(String maNV);
    String updateTrangThai(NhanVien nv, String maNV);
    List<NhanVienCustom>Search(String sdt);
    String updateMatKhau(NhanVien nv, String maNV, String matKhau);
}
