/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.TypedQuery;
import model.sanpham.MauSac;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.internal.Util;
import org.hibernate.query.Query;
import util.HibernatUtil;

/**
 *
 * @author nguye
 */
public class MauSacRepository {
    Session session = HibernatUtil.getFACTORY().openSession();

    public List<MauSac> getList() {
        List<MauSac> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            TypedQuery<MauSac> query = session.createQuery("select m  from MauSac m", MauSac.class);
            list = query.getResultList();

        }
        return list;
    }

    public boolean add(MauSac ms) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.save(ms);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public void Update(MauSac mauSac) {
        Transaction trns = null;
        Session session = HibernatUtil.getFACTORY().openSession();
        try {
            trns = session.beginTransaction();
            session.update(mauSac);
            
        } catch (RuntimeException e) {
            if(trns != null){
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    
    public String delete(String ma) {
        try (Session session = HibernatUtil.getFACTORY().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                String hql = "DELETE MauSac p WHERE p.ma = :ma";
                Query query = session.createQuery(hql);
                query.setParameter("ma", ma);
                int result = query.executeUpdate();
                if (result == 0) {
                    ma = "";
                }
                trans.commit();
            } catch (Exception e) {
                ma = "";
            }
        }
        return ma;
    }
}
