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
                    + "m.thanhPho)"
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

    public static void main(String[] args) {
        KhachHangRespository kh = new KhachHangRespository();
        List<KhachHangCutoms> list = kh.getAllKH();
        for (KhachHangCutoms khachHangCutoms : list) {
            System.out.println(khachHangCutoms.getMa());
        }
    }
}