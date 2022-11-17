/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.sanpham.NamSanXuat;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernatUtil;
import viewmodel.NamSXCustom;

/**
 *
 * @author Dell 5580
 */
public class NamSanXuatRepository {
    public List<NamSXCustom> getAllNamSX() {
        List<NamSXCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.NamSXCustom("
                    + "p.id,"
                    + "p.ten"
                    + ")from  model.sanpham.NamSanXuat p ");
            list = query.getResultList();
          
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean deleteNamSX(NamSanXuat nsx, int id) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("delete from NamSanXuat p where p.id=:id ");
            query.setParameter("id", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
    public boolean updateNamSX(NamSanXuat nsx, int id, String ma, String ten) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update NamSanXuat p set p.ma=:ma,p.ten=:ten where p.id=:id ");
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
    public boolean addNamSX(NamSanXuat nsx) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.saveOrUpdate(nsx);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        NamSanXuatRepository v=new NamSanXuatRepository();
        NamSanXuat nsx = new NamSanXuat();
        nsx.setTen("Tu");
        boolean b = v.deleteNamSX(nsx, 1);
        if(b){
            System.out.println("ok");
        }else{
            System.out.println("no");
        }
    }
}
