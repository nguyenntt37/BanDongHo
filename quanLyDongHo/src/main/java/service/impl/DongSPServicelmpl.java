/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.DongSPRepository;
import java.util.List;
import model.sanpham.DongSp;
import viewmodel.DongSPCustom;

/**
 *
 * @author Dell 5580
 */
public class DongSPServicelmpl {
    private DongSPRepository dongSPRepository = new DongSPRepository();
    
   public List<DongSPCustom> getListDongSP() {
        return dongSPRepository.getAllDongSP();
    }
    
   
    public String add(DongSp dp) {
        if (dongSPRepository.addDongSp(dp)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    
    public String delete(DongSp dp, int id) {
          
           if (dongSPRepository.deleteDongSP(dp, id)) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại";
        }
    }
    
    
    public String update(DongSp dp, int id, String ma, String ten) {
        
        if (dongSPRepository.updateDongSP(dp, id, ma, ten)) {
            return "Update thành công";
        } else {
            return "Update thất bại";
        }
    }
}
