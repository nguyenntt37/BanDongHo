/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.util.List;
import model.nhanvien.NhanVien;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernatUtil;

/**
 *
 * @author Nguyen
 */
public class NhanVienRepository {

    public List<NhanVien> get() {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            List<NhanVien> list;
            Query q = session.createQuery("FROM NhanVien");
            list = q.getResultList();
            return list;
        }
    }

    public void add(NhanVien nv) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            session.save(nv);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(NhanVien nv, String maNV) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
