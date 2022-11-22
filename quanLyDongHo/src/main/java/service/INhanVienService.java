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
    void add(NhanVien nv);
    void update(NhanVien nv, String maNV);
}
