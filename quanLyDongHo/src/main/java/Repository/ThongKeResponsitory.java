/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.hoadon.hoaDonChiTiet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernatUtil;
import viewmodel.ThongKeCustom;

/**
 *
 * @author asus_vinh
 */
public class ThongKeResponsitory {

    public List<ThongKeCustom> getAll() {
        List<ThongKeCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.ThongKeCustom("
                    + "p.chiTietSP.sanPham.ten,"
                    + "p.soLuong,"
                    + "p.dongia,"
                    + "p.ngayTao"
                    + ")from model.hoadon.hoaDonChiTiet p ");
            list = query.getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ThongKeCustom> searh(String ten) {
        List<ThongKeCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.ThongKeCustom("
                    + "p.chiTietSP.sanPham.ten,"
                    + "p.soLuong,"
                    + "p.dongia,"
                    + "p.ngayTao"
                    + ")from model.hoadon.hoaDonChiTiet p where p.chiTietSP.sanPham.ten like :ten");
            query.setParameter("ten", "%" + ten + "%");
            list = query.getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ThongKeCustom> searhNam(String nam) {
        List<ThongKeCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.ThongKeCustom("
                    + "p.chiTietSP.sanPham.ten,"
                    + "p.soLuong,"
                    + "p.dongia,"
                    + "p.ngayTao"
                    + ")from model.hoadon.hoaDonChiTiet p where datepart(yyyy, p.ngayTao) like :nam");
            query.setParameter("nam", "%" + nam + "%");
            list = query.getResultList();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public BigDecimal doanhThu() {
        BigDecimal result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select sum(p.dongia * p.soLuong) from hoaDonChiTiet  p");

            result = (BigDecimal) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public Long soHoaDon() {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT(p.id) from hoaDonChiTiet  p");

            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public Long soHoaDonHuy(int trangthai) {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT(p.id) from hoaDonChiTiet  p WHERE p.trangthai =:trangthai");
            query.setParameter("trangthai", trangthai);
            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public Long soSanPhamBanDuoc() {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT( p.chiTietSP.id) from hoaDonChiTiet  p  ");
            //   query.setParameter("trangthai", trangthai);
            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public BigDecimal doanhThuTheoNam() {
        BigDecimal result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select   sum(p.dongia * p.soLuong) from hoaDonChiTiet p  GROUP BY datepart(yyyy, p.ngayTao)");

            result = (BigDecimal) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public BigDecimal doanhThuTheoTungThang() {
        BigDecimal result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select   sum(p.dongia * p.soLuong) from hoaDonChiTiet p  GROUP BY datepart(mm, p.ngayTao)");

            result = (BigDecimal) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public BigDecimal doanhThuTheoTungThangCBB(String ngayTao) {
        BigDecimal result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select   sum(p.dongia * p.soLuong) from hoaDonChiTiet p where datepart(mm, p.ngayTao) =:ngayTao  ");
            query.setParameter("ngayTao", ngayTao);
            result = (BigDecimal) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public BigDecimal doanhThuTheoTungNamCBB(String ngayTao) {
        BigDecimal result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select   sum(p.dongia * p.soLuong) from hoaDonChiTiet p where datepart(yyyy, p.ngayTao) =:ngayTao  ");
            query.setParameter("ngayTao", ngayTao);
            result = (BigDecimal) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public Long soHoaDonTheoThang(String ngayTao) {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT(p.id) from hoaDonChiTiet  p where datepart(mm, p.ngayTao) =:ngayTao");
            query.setParameter("ngayTao", ngayTao);
            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public Long soHoaDonTheoNam(String ngayTao) {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT(p.id) from hoaDonChiTiet  p where datepart(yyyy, p.ngayTao) =:ngayTao");
            query.setParameter("ngayTao", ngayTao);
            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public Long soHoaDonHuyTheoThang(int trangthai, String ngayTao) {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT(p.id) from hoaDonChiTiet  p WHERE p.trangthai =:trangthai and datepart(mm, p.ngayTao) =:ngayTao ");
            query.setParameter("trangthai", trangthai);
            query.setParameter("ngayTao", ngayTao);
            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public Long soHoaDonHuyTheoNam(int trangthai, String ngayTao) {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT(p.id) from hoaDonChiTiet  p WHERE p.trangthai =:trangthai and datepart(yyyy, p.ngayTao) =:ngayTao ");
            query.setParameter("trangthai", trangthai);
            query.setParameter("ngayTao", ngayTao);
            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public Long soSanPhamBanDuocTheoThang(String ngayTao) {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT( p.chiTietSP.id) from hoaDonChiTiet  p where datepart(mm, p.ngayTao) =:ngayTao ");
            query.setParameter("ngayTao", ngayTao);
            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public Long soSanPhamBanDuocTheoNam(String ngayTao) {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT( p.chiTietSP.id) from hoaDonChiTiet  p where datepart(yyyy, p.ngayTao) =:ngayTao ");
            query.setParameter("ngayTao", ngayTao);
            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }
    
    public Long soHoaDonTheoNamN() {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT(p.id) from hoaDonChiTiet  p GROUP BY  datepart(yyyy, p.ngayTao)");
        //    query.setParameter("ngayTao", ngayTao);
            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }
    
    public Long soSanPhamBanDuocTheoNamN() {
        Long result = null;
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select COUNT( p.chiTietSP.id) from hoaDonChiTiet  p GROUP BY  datepart(yyyy, p.ngayTao) ");
    //        query.setParameter("ngayTao", ngayTao);
            result = (Long) query.getResultList().get(0);
            transaction.commit();
        }
        return result;
    }

    public static void main(String[] args) {
        ThongKeResponsitory th = new ThongKeResponsitory();
         System.out.println("ok  " + th.doanhThuTheoTungThangCBB("06"));
//        List<ThongKeCustom> list = th.searhNam("2021");
//        for (ThongKeCustom o : list) {
//            System.out.println(o.doanhThu());
//        }
    }
}
