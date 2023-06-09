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
                    + "m.trangthai,"
                    + "m.ngaySua)"
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
            kh.setMa(String.valueOf("KH" + kh.getId()));
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

//    public boolean update(KhachHang kh) {
//        Transaction transaction = null;
//        try ( Session session = HibernatUtil.getFACTORY().openSession();) {
//            transaction = session.beginTransaction();
//            session.update(kh);
//            transaction.commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            transaction.rollback();
//            return false;
//        }
//    }
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
                    + "m.trangthai,"
                    + "m.ngaySua)"
                    + "from model.KhachHang m WHERE m.ten LIKE :ten or m.ngaySua like :ten");
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
                    + "m.trangthai,"
                    + "m.ngaySua)"
                    + "from model.KhachHang m WHERE m.trangthai = :trangThai");
            query.setParameter("trangThai", trangThai);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return list;
    }

    public boolean update(KhachHang khachHang, String ma, int trangthai, String ho, String quocGia, String sdt, String ten, String tenDem, String thanhPho, int id) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("UPDATE KhachHang kh SET kh.ma =:ma, kh.ho =:ho, kh.ten =:ten,kh.sdt =:sdt, kh.tenDem =:tenDem, kh.thanhPho =:thanhPho, kh.quocGia =:quocGia,kh.trangthai =:trangthai WHERE kh.id = :id");
            query.setParameter("ma", ma);
            query.setParameter("tenDem", tenDem);
            query.setParameter("ten", ten);
            query.setParameter("ho", ho);
            query.setParameter("sdt", sdt);
            query.setParameter("thanhPho", thanhPho);
            query.setParameter("quocGia", quocGia);
            query.setParameter("trangthai", trangthai);
            query.setParameter("id", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<KhachHangCutoms> getAll(int heSo) {
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
                    + "m.trangthai,"
                    + "m.ngaySua)"
                    + "from model.KhachHang m ");
            // query.setParameter("d", ten);
            list = query.setFirstResult(heSo).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return list;
    }

    public static void main(String[] args) {
        KhachHangRespository kh = new KhachHangRespository();
        KhachHang khachHang = new KhachHang();
//        boolean up = kh.update(khachHang, "ph1", 1, "lên", "vn", "1111111", "vinhdz", "nguyễn", "hn", 2);
////        for (KhachHangCutoms khachHangCutoms : list) {
////            System.out.println(khachHangCutoms.getMa());
////        }
//        if (up) {
//            System.out.println("ok");
//        } else {
//            System.out.println("no");
//        }
        List<KhachHangCutoms> likh = new ArrayList<>();
        likh = kh.search("0012");
        for (KhachHangCutoms khachHangCutoms : likh) {
            System.out.println(khachHangCutoms.getMa());
        }
    }
}
