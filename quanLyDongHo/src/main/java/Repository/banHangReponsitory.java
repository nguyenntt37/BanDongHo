/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import model.giohang.GioHang;
import model.giohang.GioHangChiTiet;
import model.hoadon.HoaDon;
import model.hoadon.hoaDonChiTiet;
import model.sanpham.ChiTietSanPham;
import model.sanpham.SanPham;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernatUtil;
import viewmodel.ChiTietSPCustom;
import viewmodel.GioHangChiTietCustom;
import viewmodel.hoaDonCustom;

/**
 *
 * @author asus_vinh
 */
public class banHangReponsitory {

    public List<ChiTietSPCustom> getAsll() {
        List<ChiTietSPCustom> lsit = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.ChiTietSPCustom(c.id,"
                    + "c.sanPham.ma,"
                    + "c.sanPham.ten,"
                    + "c.giaBan,"
                    + "c.mauSac.ten,"
                    + "c.thuongHieu.ten,"
                    + "c.sanPham.dayDeo,"
                    + "c.sanPham.may,"
                    + "c.sanPham.xuatXu,"
                    + "c.soLuongTon"
                    + ")from model.sanpham.ChiTietSanPham c");
            lsit = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return lsit;
    }

    public List<ChiTietSPCustom> getSearh(String ten) {
        List<ChiTietSPCustom> lsit = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.ChiTietSPCustom(c.id,"
                    + "c.sanPham.ma,"
                    + "c.sanPham.ten,"
                    + "c.giaBan,"
                    + "c.mauSac.ten,"
                    + "c.thuongHieu.ten,"
                    + "c.sanPham.dayDeo,"
                    + "c.sanPham.may,"
                    + "c.sanPham.xuatXu,"
                    + "c.soLuongTon"
                    + ")from model.sanpham.ChiTietSanPham  c  where c.sanPham.ten like:name");
            query.setParameter("name", "%" + ten + "%");
            lsit = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }

        return lsit;
    }

    public List<GioHangChiTietCustom> getAllGH() {
        List<GioHangChiTietCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select  "
                    + " new viewmodel.GioHangChiTietCustom("
                    + "m.id,"
                    + " m.chiTietSP.sanPham.ma as maSP,"
                    + "m.chiTietSP.sanPham.ten as TenSp,"
                    + "m.chiTietSP.giaBan, "
                    + "m.soLuong, "
                    + "m.chiTietSP.id"
                    + ") "
                    + "from model.giohang.GioHangChiTiet m");
            list = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<hoaDonCustom> getAllHD() {
        List<hoaDonCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select  "
                    + " new viewmodel.hoaDonCustom("
                    + " m.id,"
                    + "m.ngayTao,"
                    + "m.nhanVien.ten, "
                    + "m.tenNguoiNhan "
                    + ") "
                    + "from model.hoadon.HoaDon m");
            list = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete(GioHangChiTiet gh, int id) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("delete from GioHangChiTiet gh where gh.id = :dc");
            query.setParameter("dc", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            // tran.rollback();
            return false;
        }
    }

    public boolean addHD(hoaDonChiTiet hd) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.save(hd);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public boolean addHoaDon(HoaDon hd) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.save(hd);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public boolean addGH(GioHangChiTiet gh) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();

            session.save(gh);

            transaction.commit();
            return true;
        } catch (HibernateException e) {
            transaction.rollback();
            return false;
        }
    }

    public boolean addGioHang(GioHang g) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.save(g);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            transaction.rollback();
            return false;
        }

    }

    public boolean updateGioHangChiTiet(GioHangChiTiet ct, int soLuong, Integer id) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update GioHangChiTiet c set c.soLuong=:s where c.chiTietSP.id =:id");
            query.setParameter("s", soLuong);
            query.setParameter("id", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public boolean updateHD(HoaDon ct, String tennguoiNhan, String sdt, String diaChi, int id) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update HoaDon c set c.diaChi=:s, c.tenNguoiNhan=:ten,c.sdt=:sdt where c.id =:id");
            query.setParameter("s", diaChi);
            query.setParameter("ten", tennguoiNhan);
            query.setParameter("sdt", sdt);
            query.setParameter("id", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        banHangReponsitory rp = new banHangReponsitory();
        List<hoaDonCustom> list = rp.getAllHD();
        for (hoaDonCustom o : list) {
            System.out.println(o.getId());
        }
    }
}
