/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.ArrayList;
import java.util.List;
import model.hoadon.hoaDonChiTiet;
import org.hibernate.Session;
import util.HibernatUtil;

/**
 *
 * @author Nguyen
 */
public class HoaDonCTRepository {
    //Lay HDCT theo ma HD
    public List<hoaDonChiTiet> getAll(String maHD) {
        List<hoaDonChiTiet> lstHDCT = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHDCT = session.createQuery("FROM hoaDonChiTiet hdct WHERE hdct.Id_Hoa_Don = :maHD").setParameter("maHD", maHD).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHDCT;
    }
}
