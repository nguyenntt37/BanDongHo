/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.ArrayList;
import java.util.List;
import model.hoadon.HoaDon;
import org.hibernate.Session;
import util.HibernatUtil;

/**
 *
 * @author Nguyen
 */
public class HoaDonRepository {

    //Tao hoa don moi
    public void insert(HoaDon hd) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            session.save(hd);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Lay danh sach hoa don
    public List<HoaDon> getAll() {
        List<HoaDon> lstHoaDon = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHoaDon = session.createNamedQuery("HoaDon.GET_ALL").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    //Lay hoa don theo ID
    public HoaDon getById(String id) {
        HoaDon hd = new HoaDon();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            hd = session.get(HoaDon.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    //Lay danh sach hoa don theo trang thai
    public List<HoaDon> getHDTheoTrangThai(byte trangThai) {
        List<HoaDon> lstHDTheoTT = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHDTheoTT = session.createQuery("FROM HoaDon hd WHERE hd.trangThai = :trangThai").setParameter("trangThai", trangThai).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHDTheoTT;
    }
}
