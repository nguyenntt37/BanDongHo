/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.NamSanXuatRepository;
import java.util.List;
import model.sanpham.NamSanXuat;
import viewmodel.NamSXCustom;

/**
 *
 * @author Dell 5580
 */
public class NamSanXuatServicelmpl {
    private NamSanXuatRepository namSanXuatRepository = new NamSanXuatRepository();
    
    public List<NamSXCustom> getListNamSanXuat() {
        return namSanXuatRepository.getAllNamSX();
    }
    
   
    public String addNamSanXuat(NamSanXuat ms) {
        if (namSanXuatRepository.addNamSX(ms)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    
    public String delete(NamSanXuat nsx, int id) {
          
           if (namSanXuatRepository.deleteNamSX(nsx, id)) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }
    
    
    public String Update(NamSanXuat nsx, int id, String ma, String ten) {
        
        if (namSanXuatRepository.updateNamSX(nsx, id, ma, ten)) {
            return "Update thành công";
        } else {
            return "Update thất bại";
        }
    }

}
