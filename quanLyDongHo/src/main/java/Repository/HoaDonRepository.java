/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.hoadon.HoaDon;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernatUtil;

/**
 *
 * @author Nguyen
 */
public class HoaDonRepository {

    //Tao hoa don moi
    public void insert(HoaDon hd) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            session.save(hd);
            hd.setMa(String.valueOf("HD" + hd.getId()));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Lay danh sach hoa don
    public List<HoaDon> getAll() {
        List<HoaDon> lstHoaDon = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHoaDon = session.createNamedQuery("HoaDon.GET_ALL").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHoaDon;
    }

    //Lay hoa don theo ID
    public HoaDon getById(String id) {
        HoaDon hd = new HoaDon();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            hd = session.get(HoaDon.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    //Lay danh sach hoa don theo trang thai
    public List<HoaDon> getHDTheoTrangThai(int trangThai) {
        List<HoaDon> lstHD = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHD = session.createQuery("FROM HoaDon hd WHERE hd.trangThaiTT = :trangThai").setParameter("trangThai", trangThai).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }

    public List<HoaDon> getHDTheoPTTT(String tenPTTT) {
        List<HoaDon> lstHD = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHD = session.createQuery("FROM HoaDon hd WHERE hd.phuongThucTT.ten = :tenPTTT").setParameter("tenPTTT", tenPTTT).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }

    public List<HoaDon> getHDTheoHTGH(String tenHTGH) {
        List<HoaDon> lstHD = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHD = session.createQuery("FROM HoaDon hd WHERE hd.HinhThucGH.ten = :tenHTGH").setParameter("tenHTGH", tenHTGH).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }

    public List<HoaDon> getHDTheoTongTien(BigDecimal from, BigDecimal to) {
        List<HoaDon> lstHD = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHD = session.createQuery("FROM HoaDon hd WHERE hd.tongTien IS NOT NULL AND hd.tongTien BETWEEN :from AND :to")
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }

    public List<HoaDon> getHDTheoThang(int thang) {
        List<HoaDon> lstHD = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHD = session.createQuery("FROM HoaDon hd WHERE MONTH(hd.tgTao) = :thang")
                    .setParameter("thang", thang)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }

    public List<HoaDon> getHDTheoNam(int nam) {
        List<HoaDon> lstHD = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHD = session.createQuery("FROM HoaDon hd WHERE YEAR(hd.tgTao) = :nam")
                    .setParameter("nam", nam)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }

        public List<HoaDon> getHDTheoThangNam(int thang, int nam) {
        List<HoaDon> lstHD = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHD = session.createQuery("FROM HoaDon hd WHERE YEAR(hd.tgTao) = :nam AND MONTH(hd.tgTao) = :thang")
                    .setParameter("nam", nam)
                    .setParameter("thang", thang)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }
        
    public void huyHD(int idHD) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            Query q = session.createQuery("UPDATE HoaDon hd SET hd.trangThaiTT = -1 WHERE hd.id = :idHD");
            q.setParameter("idHD", idHD);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getTGTaoTheoNam() {
        List lstNam = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstNam = session.createQuery("SELECT DISTINCT YEAR(hd.tgTao) AS nam FROM HoaDon hd ORDER BY nam ASC").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNam;
    }

    public List<HoaDon> searchHD(String search) {
        List<HoaDon> lstHD = new ArrayList<>();
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            lstHD = session.createQuery("FROM HoaDon hd WHERE hd.ma LIKE :search "
                    + "OR hd.nhanVien.ma LIKE :search "
                    + "OR CONCAT(hd.nhanVien.ho,' ',hd.nhanVien.tenDem,' ',hd.nhanVien.ten) LIKE :search "
                    + "OR hd.khachHang.ma LIKE :search "
                    + "OR CONCAT(hd.khachHang.ho,' ',hd.khachHang.tenDem,' ',hd.khachHang.ten) LIKE :search").setParameter("search", "%" + search + "%").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }

    public void thanhToanHD(int idHD, String ngayTT, BigDecimal tongTien, BigDecimal tienTraLai, String ghiChu, int pttt, int htgh) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            Query q = session.createQuery("UPDATE HoaDon hd SET "
                    + "hd.trangThaiTT = 1, "
                    + "hd.ngayThanhToan = :ngayTT, "
                    + "hd.tongTien = :tongTien, "
                    + "hd.tienTraLai = :tienTraLai, "
                    + "hd.ghiChu = :ghiChu, "
                    + "hd.phuongThucTT.id = :pttt, "
                    + "hd.HinhThucGH.id = :htgh "
                    + "WHERE hd.id = :idHD");
            q.setParameter("ngayTT", ngayTT);
            q.setParameter("tongTien", tongTien);
            q.setParameter("tienTraLai", tienTraLai);
            q.setParameter("ghiChu", ghiChu);
            q.setParameter("htgh", htgh);
            q.setParameter("pttt", pttt);
            q.setParameter("idHD", idHD);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateKhachHang(int idKH, int idHD) {
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            session.getTransaction().begin();
            Query q = session.createQuery("UPDATE HoaDon hd SET hd.khachHang.id = :idKH WHERE hd.id = :idHD");
            q.setParameter("idKH", idKH);
            q.setParameter("idHD", idHD);
            q.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
