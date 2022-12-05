/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.NhanVienRepository;
import model.nhanvien.NhanVien;
import service.IForgotPasswordService;

/**
 *
 * @author TN
 */
public class ForgotPasswordServiceImpl implements IForgotPasswordService {
    NhanVienRepository nvRepo = new NhanVienRepository();

    @Override
    public NhanVien getNV(int idNV) {
        return nvRepo.getById(idNV);
    }

    @Override
    public String getEmailById(int idNV) {
        return nvRepo.getEmailById(idNV);
    }

    @Override
    public void changePassword(int idNV, String password) {
        nvRepo.changePassword(idNV, password);
    }
}
