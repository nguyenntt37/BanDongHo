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

    List<KhachHangCutoms> listAll();

    String add(KhachHang kh);

    String delete(int id);

}
