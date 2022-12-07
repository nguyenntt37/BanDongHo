/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import service.LoginService;
import Repository.LoginRepository;
import Repository.NhanVienRepository;
import model.nhanvien.NhanVien;

/**
 *
 * @author FPT
 */
public class LoginSeviceImpl implements LoginService {

    private NhanVienRepository nvRepo = new NhanVienRepository();
    private LoginRepository login = new LoginRepository();

    @Override
    public boolean getLogin(String useName, String pass) {
        boolean get = login.getLogin(useName, pass);
        if (get) {
            return true;

        } else {
            return false;
        }
    }

    @Override
    public boolean checkQuyen(String maNV) {
        NhanVien nv = nvRepo.getByMaNV(maNV);
        if (nv.getChucVu().getId() == 1) {
            return true;
        } else {
            return false;
        }
    }
}
