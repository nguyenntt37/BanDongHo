/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.KhachHang;
import viewmodel.KhachHangCutoms;

/**
 *
 * @author admin
 */
public interface KhachHangService {

    List<KhachHangCutoms> getAllKH();

    String add(KhachHang kh);

    String delete(int id);

    String update(KhachHang khachHang, String ma, int trangthai, String ho, String quocGia, String sdt, String ten, String tenDem, String thanhPho, int id);

    List<KhachHangCutoms> search(String ten);

    List<KhachHangCutoms> locTrangThai(int trangThai);

}
