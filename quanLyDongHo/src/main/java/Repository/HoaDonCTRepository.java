/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.ArrayList;
import java.util.List;
import model.hoadon.hoaDonChiTiet;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernatUtil;
import viewmodel.BanHang_HDCTCustom;

/**
 *
 * @author Nguyen
 */
public class HoaDonCTRepository {

    public List<hoaDonChiTiet> getAll(int id) {
        List<hoaDonChiTiet> lstHDCT = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHDCT = session.createQuery("FROM hoaDonChiTiet hdct WHERE hdct.id = :id").setParameter("id", id).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHDCT;
    }

    public Object[] getBH_HDCTCustom(int maHD) {
        Object[] o = null;
        List<BanHang_HDCTCustom> lstHDCT = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHDCT = session.createQuery("SELECT ctsp.id, concat(dsp.ten,' ', sp.ten), ctsp.giaBan, hdct.soLuong "
                    + "FROM SanPham sp JOIN ChiTietSanPham ctsp ON sp.id = ctsp.sanPham.id "
                    + "JOIN hoaDonChiTiet hdct ON ctsp.id = hdct.chiTietSP.id "
                    + "JOIN HoaDon hd ON hdct.hoaDon.id = hd.id "
                    + "JOIN DongSp dsp ON dsp.id = ctsp.dongsp.id "
                    + "WHERE hd.id = :maHD").setParameter("maHD", maHD).getResultList();
            o = lstHDCT.toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public void insertSPToHDCT(int idHD, int sl, int idCTSP) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            Query q = session.createNativeQuery("IF EXISTS (SELECT Chi_TietSP.Id FROM Hoa_Don_Chi_Tiet\n"
                    + "JOIN Chi_TietSP ON Hoa_Don_Chi_Tiet.Id_Chi_TietSP = Chi_TietSP.Id\n"
                    + "JOIN Hoa_Don ON Hoa_Don_Chi_Tiet.Id_Hoa_Don = Hoa_Don.Id\n"
                    + "WHERE Hoa_Don.Id = :idHD AND Hoa_Don_Chi_Tiet.Id_Chi_TietSP = :idCTSP)\n"
                    + "BEGIN\n"
                    + "	UPDATE Hoa_Don_Chi_Tiet\n"
                    + "	SET So_Luong += 1\n"
                    + "	WHERE Hoa_Don_Chi_Tiet.Id_Hoa_Don = :idHD\n"
                    + "AND Hoa_Don_Chi_Tiet.Id_Chi_TietSP = :idCTSP\n"
                    + "END\n"
                    + "ELSE\n"
                    + "BEGIN\n"
                    + "	INSERT INTO Hoa_Don_Chi_Tiet VALUES\n"
                    + "	(null,null,null,:sl,null,:idCTSP,:idHD)\n"
                    + "END");
            q.setParameter("idHD", idHD);
            q.setParameter("sl", sl);
            q.setParameter("idCTSP", idCTSP);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteSPOnHDCT(int idCTSP, int sl) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            Query q = session.createQuery("DELETE FROM hoaDonChiTiet hdct WHERE hdct.chiTietSP.id = :id");
            q.setParameter("id", idCTSP);
            q.executeUpdate();
            
            q = session.createQuery("UPDATE ChiTietSanPham ctsp SET ctsp.soLuongTon = ctsp.soLuongTon + :sl WHERE ctsp.id = :id");
            q.setParameter("id", idCTSP);
            q.setParameter("sl", sl);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
