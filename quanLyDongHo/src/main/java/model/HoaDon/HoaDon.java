/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.hoadon;

import model.KhachHang;
import model.nhanvien.NhanVien;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author asus_vinh
 */
@Data
@Entity
@Table(name = "Hoa_Don")
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

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ngay_Tao")
    private String ngayTao;

    @Column(name = "Ngay_Thanh_Toan")
    private String ngayThanhToan;

    @Column(name = "Ngay_Ship")
    private String ngayShip;

    @Column(name = "Ngay_Nhan")
    private String ngayNhan;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "Ten_Nguoi_Nhan")
    private String tenNguoiNhan;

    @Column(name = "Dia_Chi")
    private String diaChi;

    @Column(name = "Sdt")
    private String sdt;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.EAGER)
    private List<hoaDonChiTiet> list = new ArrayList<>();
    
       
    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.EAGER)
    private List<hoaDonKhuyenMai> lists = new ArrayList<>();

}
