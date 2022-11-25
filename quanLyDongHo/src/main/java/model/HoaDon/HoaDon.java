/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.hoadon;

import model.KhachHang;
import model.nhanvien.NhanVien;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import model.khuyenmai.hoaDonKhuyenMai;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author asus_vinh
 */
@Data
@Entity
@Table(name = "Hoa_Don")
@NamedQuery(name = "HoaDon.GET_ALL", query = "FROM HoaDon")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdKH")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "IdNV")
    private NhanVien nhanVien;

    @Column(name = "TG_Tao")
    private String tgTao;

    @Column(name = "Tong_Tien")
    private long tongTien;

    @Column(name = "Tong_KM")
    private long tongKM;

    @Column(name = "Tien_Tra_Lai")
    private long tienTraLai;

    @Column(name = "Trang_Thai_TT") //-1: Da huy, 0: Da thanh toan, 1: Cho thanh toan
    private byte trangThaiTT;

    @Column(name = "Phuong_Thuc_TT") //0: Thanh toan truc tiep, 1: Chuyen khoan
    private byte phuongThucTT;

    @Column(name = "Ghi_Chu")
    private String ghiChu;

    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.EAGER)
    private List<hoaDonChiTiet> lstHDCT = new ArrayList<>();

    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.EAGER)
    private List<hoaDonKhuyenMai> lstHDKM = new ArrayList<>();
}
