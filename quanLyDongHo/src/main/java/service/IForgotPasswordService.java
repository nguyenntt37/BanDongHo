/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import model.nhanvien.NhanVien;

/**
 *
 * @author TN
 */
public interface IForgotPasswordService {

    NhanVien getNV(int idNV);
    String getEmailById(int idNV);
    void changePassword(int idNV, String password);
}
