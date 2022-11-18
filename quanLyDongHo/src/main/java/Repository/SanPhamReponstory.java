/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.TypedQuery;
import model.sanpham.ChiTietSanPham;
import model.sanpham.DongSp;
import model.sanpham.MauSac;
import model.sanpham.NamSanXuat;
import model.sanpham.SanPham;
import model.sanpham.thuongHieu;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernatUtil;
import viewmodel.ChiTietSPCustom;
import viewmodel.DongSPCustom;
import viewmodel.MauSacCustom;
import viewmodel.NamSXCustom;
import viewmodel.SanPhamCustom;
import viewmodel.ThuongHieuCustomer;

/**
 *
 * @author asus_vinh
 */
public class SanPhamReponstory{

    public List<ChiTietSPCustom> getAsll() {
        List<ChiTietSPCustom> lsit = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.ChiTietSPCustom(c.id,"
                    + "c.maSP,"
                    + "c.tenSP,"
                    + "c.giaBan,"
                    + "c.tenMauSac"
                    + "c.tenThuongHieu"
                    + "c.dayDeo"
                    + "c.may"
                    + "c.xuatXu"
                    + "c.soLuongTon"
                    + ")from model.sanpham.ChiTietSanPham c");
            lsit = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lsit;
    }

    public List<ChiTietSPCustom> getFind(int namBh) {
        List<ChiTietSPCustom> lsit = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.ChiTietSPCustom(c.id,"
                    + "c.namBH,"
                    + "c.moTa,"
                    + "c.soLuongTon,"
                    + "c.giaNhap"
                    + ")from model.SanPham.ChiTietSanPham c where c.namBH =: namBH");
            query.setParameter("namBH", namBh);
            lsit = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lsit;
    }

    public boolean save(ChiTietSanPham ct) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.saveOrUpdate(ct);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public boolean up(ChiTietSanPham ct, int id, int soluong) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update ChiTietSanPham c set c.soLuongTon=:soLuongTon  where c.id=: id ");
            query.setParameter("soLuongTon", soluong);
            query.setParameter("id", id);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public List<ChiTietSPCustom> getAll() {
        List<ChiTietSPCustom> lists = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select  "
                    + " new viewmodel.ChiTietSPCustom("
                    + " m.id ,"
                    + " m.sanPham.ma as maSP,"
                    + "m.sanPham.ten as TenSp,"
                    + " m.giaBan ,"
                    + "m.mauSac.ten as mausac,"
                    + "m.thuongHieu.ten as thuongHieu,"
                    + "m.sanPham.dayDeo, "
                    + "m.sanPham.may, "
                    + "m.sanPham.xuatXu, "
                    + "m.soLuongTon "
                    + ") "
                    + "from model.sanpham.ChiTietSanPham m ");
            lists = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lists;
    }

    public List<ChiTietSPCustom> getOne(String ten) {
        List<ChiTietSPCustom> lists = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select  "
                    + " new viewmodel.ChiTietSPCustom("
                    + " m.id ,"
                    + " m.sanPham.ma as maSP,"
                    + "m.sanPham.ten as TenSp,"
                    + "m.nSX.ten as NSX,"
                    + "m.mauSac.ten as mausac,"
                    + "m.dongsp.ten ,"
                    + "m.namBH as namBH,"
                    + "m.moTa, "
                    + "m.soLuongTon, "
                    + "m.giaNhap, "
                    + "m.giaBan "
                    + ") "
                    + "from model.sanpham.ChiTietSanPham m where m.sanPham.ten = :d");
            query.setParameter("d", ten);
            lists = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lists;
    }

    public ChiTietSanPham findById(int id) {
        ChiTietSanPham ct;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            TypedQuery<ChiTietSanPham> query = session.createQuery("select c from ChiTietSanPham c where c.sanPham.id=:id", ChiTietSanPham.class);
            query.setParameter("id", id);
            ct = query.getSingleResult();
        }
        return ct;
    }

    public boolean add(SanPham sp) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {

            tran = session.beginTransaction();
            session.saveOrUpdate(sp);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public boolean addMauSac(MauSac ms) {
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
    
    public boolean addThuongHieu(thuongHieu th) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.save(th);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public boolean updateSp(SanPham sp, String ten, int id, String ma) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update SanPham s set s.ma =:ma,s.ten=:ten where s.id = :d");
            query.setParameter("ma", ma);
            query.setParameter("ten", ten);
            query.setParameter("d", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public boolean updateMauSac(MauSac ms, int id, String ma, String ten) {
        Transaction tran = null;
        try ( Session sesion = HibernatUtil.getFACTORY().openSession()) {
            tran = sesion.beginTransaction();
            Query query = sesion.createQuery("update  MauSac s set s.ma =:ma,s.ten=:ten where s.id =  :d ");
            query.setParameter("ma", ma);
            query.setParameter("ten", ten);
            query.setParameter("d", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }

    public boolean updateThuongHieu(thuongHieu th, int id, String ma, String ten) {
        Transaction tran = null;
        try ( Session sesion = HibernatUtil.getFACTORY().openSession()) {
            tran = sesion.beginTransaction();
            Query query = sesion.createQuery("update  thuongHieu s set s.ma =:ma,s.ten=:ten where s.id =  :d ");
            query.setParameter("ma", ma);
            query.setParameter("ten", ten);
            query.setParameter("d", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }
    }
    
    public boolean updateChitietSP(ChiTietSanPham ct, int id, int idSP, int idDongSP, int idMau, int idNamSX,
            BigDecimal giaBan, BigDecimal giaNhap,
            int namBH, int soLuongTon) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update ChiTietSanPham c set c.sanPham.id=:idSP,c.dongsp.id=:idDongSP,c.mauSac.id =:idMau,"
                    + "                        c.nSX.id=:idNamSX,c.giaBan=:giaBan,c.giaNhap=:giaNhap,c.namBH=:namBH,c.soLuongTon=:soLuong  "
                    + "                       where c.id =:id ");
            query.setParameter("idSP", idSP);
            query.setParameter("idDongSP", idDongSP);
            query.setParameter("idMau", idMau);
            query.setParameter("idNamSX", idNamSX);
            query.setParameter("giaBan", giaBan);
            query.setParameter("giaNhap", giaNhap);
            query.setParameter("namBH", namBH);
            query.setParameter("soLuong", soLuongTon);
            query.setParameter("id", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }

    }

    public boolean deleteSP(SanPham sp, int id) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("delete from SanPham s where s.id =:d ");
            query.setParameter("d", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public boolean deleteChitietSP(ChiTietSanPham ct, int id) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("delete from ChiTietSanPham p where p.id = :d");
            query.setParameter("d", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public boolean deleteMau(MauSac ms, int id) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("delete from MauSac s where s.id=:d ");
            query.setParameter("d", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public boolean deleteThuongHieu(thuongHieu th, int id) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("delete from thuongHieu s where s.id=:d ");
            query.setParameter("d", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
    
    public List<SanPhamCustom> getAllSP() {
        List<SanPhamCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.SanPhamCustom(s.id,s.ma,s.ten)from model.sanpham.SanPham s ");
            list = query.list();

        } catch (HibernateException e) {
        }
        return list;
    }

    public List<MauSacCustom> getAllMauSac() {
        List<MauSacCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.MauSacCustom (s.id,s.ma,s.ten) from model.sanpham.MauSac s");
            list = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NamSXCustom> getAllNamSX() {
        List<NamSXCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.NamSXCustom("
                    + "p.id,"
                    + "p.ten"
                    + ")from model.sanpham.NamSanXuat p ");
            list = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DongSPCustom> getAllDongSP() {
        List<DongSPCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.DongSPCustom("
                    + "p.id,"
                    + "p.ten"
                    + ")from model.sanpham.DongSp p ");
            list = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<ThuongHieuCustomer> getAllThuongHieu() {
        List<ThuongHieuCustomer> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.ThuongHieuCustomer("
                    + "p.id,"
                    + "p.ma,"
                    + "p.ten"
                    + ")from model.sanpham.thuongHieu p ");
            list = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addDongSp(DongSp dp) {
        Transaction tran = null;
        try ( Session sesion = HibernatUtil.getFACTORY().openSession()) {
            tran = sesion.beginTransaction();
            sesion.saveOrUpdate(dp);
            tran.commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        SanPhamReponstory sp = new SanPhamReponstory();
        List<SanPhamCustom> list = sp.getAllSP();
        for (SanPhamCustom o : list) {
            System.out.println(o.toString());
        }
    }

}
