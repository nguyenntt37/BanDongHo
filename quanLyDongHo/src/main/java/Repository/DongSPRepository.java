/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.sanpham.DongSp;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernatUtil;
import viewmodel.DongSPCustom;

/**
 *
 * @author Dell 5580
 */
public class DongSPRepository {

    public List<DongSPCustom> getAllDongSP() {
        List<DongSPCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.DongSPCustom("
                    + "p.id,"
                    + "p.ma,"
                    + "p.ten"
                    + ")from model.sanpham.DongSp p ");
            list = query.getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Object[] getAllDongSPham() {
        return getAllDongSP().toArray();
    }

    public boolean addDongSp(DongSp dp) {
        Transaction tran = null;
        try ( Session sesion = HibernatUtil.getFACTORY().openSession()) {
            tran = sesion.beginTransaction();
            sesion.save(dp);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDongSP(DongSp dp, int id, String ma, String ten) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update DongSp p set p.ma=:ma,p.ten=:ten where p.id=:id ");
            query.setParameter("ma", ma);
            query.setParameter("ten", ten);
            query.setParameter("id", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (Exception e) {
            tran.rollback();
            return false;
        }
    }

    public boolean deleteDongSP(DongSp dp, int id) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("delete from DongSp p where p.id=:id ");
            query.setParameter("id", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static void main(String[] args) {
       
    DongSPRepository v=new DongSPRepository();
    List<DongSPCustom> b = v.getAllDongSP();
        for (DongSPCustom dongSPCustom : b) {
            System.out.println(dongSPCustom.getId());
        }
//        DongSp nsx = new DongSp();
//        nsx.setTen("Tu");
//        boolean b = v.updateDongSP(nsx, 1, "uu", "yy");
//        if(b){
//            System.out.println("ok");
//        }else{
//            System.out.println("no");
//        }
    }
}
