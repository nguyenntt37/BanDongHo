/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.MauSacRepository;
import java.util.ArrayList;
import java.util.List;
import model.sanpham.MauSac;
import service.MauSacService;

/**
 *
 * @author nguye
 */
public class MauSacServiceImpl implements MauSacService{
    private MauSacRepository mauSacRepository = new MauSacRepository();
    
    @Override
    public List<MauSac> getListSinhVien() {
        return mauSacRepository.getList();
    }
    
    @Override
    public String addSinhVien(MauSac ms) {
        if (mauSacRepository.add(ms)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }
    
    @Override
    public String delete(String ma) {
         return mauSacRepository.delete(ma);
    }
    
    @Override
    public void Update(MauSac mauSac) {
        mauSacRepository.Update(mauSac);
    }
}
