/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.sanpham.MauSac;

/**
 *
 * @author nguye
 */
public interface MauSacService {
    public List<MauSac> getListSinhVien();
    
    public String addSinhVien(MauSac ms);
    
    public String delete(String ma);
    
    public void Update(MauSac mauSac);
}
