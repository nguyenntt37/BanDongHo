/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
import viewmodel.BanHang_SPCustom;
import viewmodel.ChiTietSPCustom;
import viewmodel.DongSPCustom;
import viewmodel.MauSacCustom;
import viewmodel.NamSXCustom;
import viewmodel.SanPhamCustom3;
import viewmodel.ThuongHieuCustomer;

/**
 *
 * @author asus_vinh
 */
public class SanPhamReponstory {

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
            session.save(ct);
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
                    + " m.sanPham.ma ,"
                    + "m.sanPham.ten ,"
                    + " m.namBH ,"
                    + "m.thuongHieu.ten ,"
                    + "m.mauSac.ten ,"
                    + "m.nSX.ten, "
                    + "m.soLuongTon, "
                    + "m.giaNhap, "
                    + "m.giaBan, "
                    + "m.moTa, "
                    + "m.ngayTao, "
                    + "m.ngaySua, "
                    + "m.trangThai, "
                    + "m.sanPham.xuatXu, "
                    + "m.sanPham.kinh, "
                    + "m.sanPham.dayDeo, "
                    + "m.sanPham.ChucNang "
                    + ") "
                    + "from model.sanpham.ChiTietSanPham m order by m.ngayTao desc");
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
            session.save(sp);
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

    public boolean updateSp(SanPham sp, String ten, int id, String ma, String ChucNang, String dayDeo, String kinh, String matSo, String may, String xuatXu, int trangthai) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update SanPham s set s.ma =:ma,s.ten=:ten,s.ChucNang=:ChucNang,s.dayDeo=:dayDeo,s.kinh=:kinh,s.matSo=:matSo,s.may=:may,s.xuatXu=:xuatXu,s.trangthai=:trangthai where s.id = :d");
            query.setParameter("ma", ma);
            query.setParameter("ten", ten);
            query.setParameter("ChucNang", ChucNang);
            query.setParameter("dayDeo", dayDeo);
            query.setParameter("kinh", kinh);
            query.setParameter("matSo", matSo);
            query.setParameter("may", may);
            query.setParameter("xuatXu", xuatXu);
            query.setParameter("trangthai", trangthai);
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
            int namBH, int soLuongTon, int trangThai) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            Query query = session.createQuery("update ChiTietSanPham c set c.sanPham.id=:idSP,c.dongsp.id=:idDongSP,c.mauSac.id =:idMau,"
                    + "                        c.nSX.id=:idNamSX,c.giaBan=:giaBan,c.giaNhap=:giaNhap,c.namBH=:namBH,c.soLuongTon=:soLuong, c.trangThai=:trangThai  "
                    + "                       where c.id =:id ");
            query.setParameter("idSP", idSP);
            query.setParameter("idDongSP", idDongSP);
            query.setParameter("idMau", idMau);
            query.setParameter("idNamSX", idNamSX);
            query.setParameter("giaBan", giaBan);
            query.setParameter("giaNhap", giaNhap);
            query.setParameter("namBH", namBH);
            query.setParameter("soLuong", soLuongTon);
            query.setParameter("trangThai", trangThai);
            query.setParameter("id", id);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (HibernateException e) {
            tran.rollback();
            return false;
        }

    }

    public boolean addChiTietSp(ChiTietSanPham sp) {
        Transaction tran = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            session.save(sp);
            sp.setMa(String.valueOf("CSTP" + sp.getId()));
            tran.commit();
            return true;
        } catch (HibernateException e) {
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

    public List<SanPhamCustom3> getAllSP() {
        List<SanPhamCustom3> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.SanPhamCustom3(s.id,s.ma,s.ten,s.ChucNang,s.dayDeo,s.kinh,s.matSo,s.may,s.xuatXu,s.ngayTao,s.trangthai)from model.sanpham.SanPham s ");
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
            sesion.save(dp);
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
            session.save(nsx);
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

    public List<BanHang_SPCustom> getBH_SPCustom() {
        List<BanHang_SPCustom> lstSPCustom = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstSPCustom = session.createQuery("SELECT ctsp.ma, CONCAT(ctsp.dongsp.ten,' ',ctsp.thuongHieu.ten,' ',ctsp.sanPham.ten), ctsp.giaBan, ctsp.mauSac.ten, ctsp.sanPham.may, ctsp.sanPham.kinh, ctsp.sanPham.xuatXu, ctsp.soLuongTon "
                    + "FROM ChiTietSanPham ctsp WHERE ctsp.soLuongTon > 0").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSPCustom;
    }

    public Object[] getBH_SPCustomByDongSP(int idDSP) {
        Object[] o = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            o = session.createQuery("SELECT ctsp.ma, CONCAT(ctsp.dongsp.ten,' ',ctsp.thuongHieu.ten,' ',ctsp.sanPham.ten), ctsp.giaBan, ctsp.mauSac.ten, ctsp.sanPham.may, ctsp.sanPham.kinh, ctsp.sanPham.xuatXu, ctsp.soLuongTon "
                    + "FROM ChiTietSanPham ctsp WHERE ctsp.dongsp.id = :idDSP").setParameter("idDSP", idDSP).getResultList().toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public void loadSLTon(int sl, int idCTSP) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            Query query = session.createNativeQuery("UPDATE Chi_TietSP SET So_Luong_Ton -= :sl WHERE Chi_TietSP.Id = :idCTSP");
            query.setParameter("sl", sl);
            query.setParameter("idCTSP", idCTSP);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object[] searchSP(String search) {
        Object[] o = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            o = session.createQuery("SELECT ctsp.ma, CONCAT(ctsp.dongsp.ten,' ',ctsp.thuongHieu.ten,' ',ctsp.sanPham.ten), ctsp.giaBan, ctsp.mauSac.ten, ctsp.sanPham.may, ctsp.sanPham.kinh, ctsp.sanPham.xuatXu, ctsp.soLuongTon "
                    + "FROM ChiTietSanPham ctsp WHERE ctsp.thuongHieu.ten LIKE :search OR ctsp.sanPham.ten LIKE :search OR ctsp.ma LIKE :search").setParameter("search", "%" + search + "%").getResultList().toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public List<ChiTietSPCustom> getAll2(int heSo) {
        List<ChiTietSPCustom> lists = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select  "
                    + " new viewmodel.ChiTietSPCustom("
                    + " m.id ,"
                    + " m.sanPham.ma ,"
                    + "m.sanPham.ten ,"
                    + " m.namBH ,"
                    + "m.thuongHieu.ten ,"
                    + "m.mauSac.ten ,"
                    + "m.nSX.ten, "
                    + "m.soLuongTon, "
                    + "m.giaNhap, "
                    + "m.giaBan, "
                    + "m.moTa, "
                    + "m.ngayTao, "
                    + "m.ngaySua, "
                    + "m.trangThai, "
                    + "m.sanPham.xuatXu, "
                    + "m.sanPham.kinh, "
                    + "m.sanPham.dayDeo, "
                    + "m.sanPham.ChucNang "
                    + ") "
                    + "from model.sanpham.ChiTietSanPham m order by m.ngayTao");
            lists = query.setFirstResult(heSo).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lists;

    }

    public List<ChiTietSPCustom> search(String ten) {
        List<ChiTietSPCustom> lists = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select  "
                    + " new viewmodel.ChiTietSPCustom("
                    + " m.id ,"
                    + " m.sanPham.ma ,"
                    + "m.sanPham.ten ,"
                    + " m.namBH ,"
                    + "m.thuongHieu.ten ,"
                    + "m.mauSac.ten ,"
                    + "m.nSX.ten, "
                    + "m.soLuongTon, "
                    + "m.giaNhap, "
                    + "m.giaBan, "
                    + "m.moTa, "
                    + "m.ngayTao, "
                    + "m.ngaySua, "
                    + "m.trangThai, "
                    + "m.sanPham.xuatXu, "
                    + "m.sanPham.kinh, "
                    + "m.sanPham.dayDeo, "
                    + "m.sanPham.ChucNang "
                    + ") "
                    + "from model.sanpham.ChiTietSanPham m where m.sanPham.ten like :ten or m.sanPham.ma like :ten");
            query.setParameter("ten", "%" + ten + "%");
            lists = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return lists;
    }

    public static void main(String[] args) {
        SanPhamReponstory sp = new SanPhamReponstory();
        List<SanPhamCustom3> list = sp.getAllSP();
        for (SanPhamCustom3 o : list) {

            System.out.println(o);
        }
    }

}
