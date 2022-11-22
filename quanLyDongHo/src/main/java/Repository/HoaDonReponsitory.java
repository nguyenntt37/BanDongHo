/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import model.hoadon.HoaDon;
import model.hoadon.hoaDonChiTiet;
import model.sanpham.ChiTietSanPham;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernatUtil;
import viewmodel.HoaDonCustom2;
import viewmodel.hoaDonCustom;

/**
 *
 * @author asus_vinh
 */
public class HoaDonReponsitory {

    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> list = new ArrayList();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            String hql = "select E.id,E.ma from model.hoadon.hoaDon E";
            Query query = session.createQuery(hql);
            list = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateTinhTrang(HoaDon hd, int tinhTrang, String idHD) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update HoaDon c set c.trangThai =:s where c.id =:id");
            query.setParameter("s", tinhTrang);
            query.setParameter("id", idHD);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
    
    public List<HoaDonCustom2> getAllHD() {
        List<HoaDonCustom2> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select  "
                    + " new viewmodel.HoaDonCustom2("
                    + "m.hoaDon.id,"
                    + "m.hoaDon.ma, "
                    + "m.chiTietSP.sanPham.ten, "
                    + "m.hoaDon.ngayTao, "
                    + "m.hoaDon.ngayThanhToan,"
                    + "m.hoaDon.ngayShip, "
                    + "m.hoaDon.ngayNhan, "
                    + "m.hoaDon.tenNguoiNhan, "
                    + "m.hoaDon.diaChi, "
                    + "m.hoaDon.sdt, "
                    + "m.soLuong, "
                    + "m.dongia, "
                    + "m.hoaDon.trangThai "
                    + ") "
                    + "from model.hoadon.hoaDonChiTiet m");
            list = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean add(HoaDon hd) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.saveOrUpdate(hd);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public boolean addHoaDonChiTiet(hoaDonChiTiet hd) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.saveOrUpdate(hd);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public boolean delete(hoaDonChiTiet hd, Integer idHD) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("delete from hoaDonChiTiet h where h.hoaDon.id =:d");
            query.setParameter("d", idHD);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteHD(HoaDon hd, Integer idHD) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("delete from HoaDon  where id =:d");
            query.setParameter("d", idHD);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateHoaDonChiTiet(hoaDonChiTiet hdct, int id, int idCtSP, int soLuong, BigDecimal donGia) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update hoaDonChiTiet c set c.chiTietSP.id=:idSp, " + " c.soLuong=:sl,"
                    + " c.dongia=:donGia where c.hoaDon.id =:id");
            query.setParameter("idSp", idCtSP);
            query.setParameter("sl", soLuong);
            query.setParameter("donGia", donGia);
            query.setParameter("id", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }
//    hoaDonChiTiet hdct, String id,
//            String idCtSP, String ngayTao, String ngayNhan, String ngayShip, String ngayThanhToan,
//            String tenNguoiNhan, int soLuong, int tinhTrang, BigDecimal donGia, String sdt, String diaChi

    public boolean updateHoaDon(HoaDon hd, int id, String ngayTao, String ngayThanhToan, String ngayShip, String ngayNhan,
            String tenNguoiNhan, int tinhTrang, String sdt, String diaChi) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update HoaDon c set c.ngayTao=:ngayTao,c.ngayNhan=:ngayNhan,c.ngayShip=:ngayShip,c.ngayThanhToan=:ngayThanhToan,"
                    + "c.tenNguoiNhan=:tenNguoiNhan,c.trangThai=:trangThai,c.sdt=:sdt,c.diaChi=:diaChi    "
                    + " where c.id =:id");
            query.setParameter("ngayNhan", ngayNhan);
            query.setParameter("ngayShip", ngayShip);
            query.setParameter("ngayThanhToan", ngayThanhToan);
            query.setParameter("ngayTao", ngayTao);
            query.setParameter("tenNguoiNhan", tenNguoiNhan);
            query.setParameter("trangThai", tinhTrang);
            query.setParameter("sdt", sdt);
            query.setParameter("diaChi", diaChi);
            query.setParameter("id", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public static void main(String[] args) {
        HoaDonReponsitory hd = new HoaDonReponsitory();
        List<HoaDonCustom2> list = hd.getAllHD();
        for (HoaDonCustom2 hd2 : list) {
            System.out.println(hd2.toString());
        }

        HoaDon r = new HoaDon();

    }

}
