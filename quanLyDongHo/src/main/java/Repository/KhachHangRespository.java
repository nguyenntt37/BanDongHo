/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernatUtil;
import viewmodel.KhachHangCutoms;
import model.KhachHang;
import org.hibernate.Transaction;

/**
 *
 * @author admin
 */
public class KhachHangRespository {

    public List<KhachHangCutoms> getAllKH() {
        List<KhachHangCutoms> list = new ArrayList<>();
        //   KhachHang kh = new KhachHang();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.KhachHangCutoms("
                    + "m.id,"
                    + "m.ma,"
                    + "m.ten,"
                    + "m.sdt,"
                    + "m.quocGia,"
                    + "m.tenDem,"
                    + "m.ho,"
                    + "m.thanhPho,"
                    + "m.trangthai)"
                    + "from model.KhachHang m ");
            // query.setParameter("d", ten);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return list;
    }

    public boolean add(KhachHang kh) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {

            tran = session.beginTransaction();
            session.save(kh);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public boolean delete(int id) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
            transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean update(KhachHang kh) {
        Transaction transaction = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
            transaction = session.beginTransaction();
            session.update(kh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    public boolean checkUpdate(String ma, String id) {
        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
            String hql = "from KhachHang kh where kh.ma = :ma and kh.id = :id";
            javax.persistence.Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("ma", ma);
            return query.getResultList().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<KhachHangCutoms> search(String ten) {
        List<KhachHangCutoms> list = new ArrayList<>();
        //   KhachHang kh = new KhachHang();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.KhachHangCutoms("
                    + "m.id,"
                    + "m.ma,"
                    + "m.ten,"
                    + "m.sdt,"
                    + "m.quocGia,"
                    + "m.tenDem,"
                    + "m.ho,"
                    + "m.thanhPho,"
                    + "m.trangthai)"
                    + "from model.KhachHang m WHERE m.ten LIKE :ten");
            query.setParameter("ten", "%" + ten + "%");
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return list;
    }

    public List<KhachHangCutoms> locTrangThai(int trangThai) {
        List<KhachHangCutoms> list = new ArrayList<>();
        //   KhachHang kh = new KhachHang();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.KhachHangCutoms("
                    + "m.id,"
                    + "m.ma,"
                    + "m.ten,"
                    + "m.sdt,"
                    + "m.quocGia,"
                    + "m.tenDem,"
                    + "m.ho,"
                    + "m.thanhPho,"
                    + "m.trangthai)"
                    + "from model.KhachHang m WHERE m.trangthai = :trangThai");
            query.setParameter("trangThai", trangThai );
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return list;
    }
//    public boolean update(KhachHang khachHang, String ma, String ho, String quocGia, String sdt, String ten, String tenDem, String thanhPho, int id) {
//        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
//            session.getTransaction().begin();
//            Query query = session.createQuery("UPDATE KhachHang kh SET kh.ma =:ma, kh.ho =:ho, kh.ten =:ten,kh.sdt =:sdt, kh.tenDem =:tenDem, kh.thanhPho =:thanhPho, kh.quocGia =:quocGia WHERE kh.id = :id");
//            query.setParameter("ma", ma);
//            query.setParameter("tenDem", tenDem);
//            query.setParameter("ten", ten);
//            query.setParameter("ho", ho);
//            query.setParameter("sdt", sdt);
//            query.setParameter("thanhPho", thanhPho);
//            query.setParameter("quocGia", quocGia);
//            query.executeUpdate();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        KhachHangRespository kh = new KhachHangRespository();
        List<KhachHangCutoms> list = kh.locTrangThai(0);
        for (KhachHangCutoms khachHangCutoms : list) {
            System.out.println(khachHangCutoms.getMa());
        }
    }
}
