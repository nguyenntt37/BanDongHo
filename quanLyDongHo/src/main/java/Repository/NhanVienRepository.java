/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.ArrayList;
import java.util.List;
import model.nhanvien.NhanVien;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernatUtil;
import viewmodel.NhanVienCustom;

/**
 *
 * @author Nguyen
 */
public class NhanVienRepository {

    public List<NhanVienCustom> get() {
        List<NhanVienCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.NhanVienCustom("
                    + "m.ma,"
                    + "(m.ho + ' ' + m.tenDem +' '+ m.ten),"
                    + "m.gioiTinh,"
                    + "m.ngaySinh,"
                    + "m.diaChi,"
                    + "m.sdt,"
                    + "m.trangThai)"
                    + "from model.nhanvien.NhanVien m ");
            // query.setParameter("d", ten);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return list;

    }

    public boolean add(NhanVien nv) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            session.save(nv);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(NhanVien nv, String maNV) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("UPDATE NhanVien nv SET nv.ho = :ho, nv.tenDem = :tenDem, nv.ten = :ten, nv.gioiTinh = :gioiTinh, nv.ngaySinh = :ngaySinh, nv.diaChi = :diaChi, nv.sdt = :sdt, nv.trangThai = :trangThai WHERE nv.ma = :ma");
            query.setParameter("ho", nv.getHo());
            query.setParameter("tenDem", nv.getTenDem());
            query.setParameter("ten", nv.getTen());
            query.setParameter("gioiTinh", nv.getGioiTinh());
            query.setParameter("ngaySinh", nv.getNgaySinh());
            query.setParameter("diaChi", nv.getDiaChi());
            query.setParameter("sdt", nv.getSdt());
            query.setParameter("trangThai", nv.getTrangThai());
            query.setParameter("ma", maNV);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public NhanVien getByMaNV(String maNV) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            NhanVien nv;
            Query q = session.createQuery("FROM NhanVien nv WHERE nv.ma = :maNV").setParameter("maNV", maNV);
            if (q.getResultList().size() == 0) {
                return null;
            }
            nv = (NhanVien) q.getResultList().get(0);
            return nv;
        }
    }

    public boolean updateTrangThai(NhanVien nv, String maNV) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("UPDATE NhanVien nv SET nv.trangThai = :trangThai WHERE nv.ma = :ma");
            query.setParameter("trangThai", 0);
            query.setParameter("ma", maNV);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<NhanVienCustom> Search(String sdt) {
        List<NhanVienCustom> list = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("select new viewmodel.NhanVienCustom("
                    + "m.ma,"
                    + "(m.ho + ' ' + m.tenDem +' '+ m.ten),"
                    + "m.gioiTinh,"
                    + "m.ngaySinh,"
                    + "m.diaChi,"
                    + "m.sdt,"
                    + "m.trangThai)"
                    + "from model.nhanvien.NhanVien m where m.sdt like :sdt");
            query.setParameter("sdt", "%" + sdt + "%");
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();

        }
        return list;
    }

    public NhanVien getById(int idNV) {
        NhanVien nv = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            nv = session.get(NhanVien.class, idNV);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return nv;
    }

    public String getEmailById(int idNV) {
        String s = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            s = (String) session.createQuery("SELECT nv.email FROM NhanVien nv WHERE nv.id = :id").setParameter("id", idNV).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return s;
    }
    
    public void changePassword(int idNV, String password) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            session.createQuery("UPDATE NhanVien nv SET nv.matKhau = :password WHERE nv.id = :idNV")
                    .setParameter("password", password)
                    .setParameter("idNV", idNV)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        NhanVienRepository nv = new NhanVienRepository();
        List<NhanVienCustom> list = nv.Search("0123456789");
        for (NhanVienCustom nv1 : list) {
            System.out.println(nv1.toString());
        }
    }
}
