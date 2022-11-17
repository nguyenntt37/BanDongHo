/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sanpham;

import model.giohang.GioHangChiTiet;
import model.hoadon.hoaDonChiTiet;
import java.math.BigDecimal;
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
import model.khuyenmai.chiTietSPKhuyenMai;

/**
 *
 * @author asus_vinh
 */
@Data
@Entity
@Table(name = "Chi_TietSP")
public class ChiTietSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdSP")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "Id_Nsx")
    private NamSanXuat nSX;

    @ManyToOne
    @JoinColumn(name = "Id_Mau_Sac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "Id_DongSP")
    private DongSp dongsp;
    
    @ManyToOne
    @JoinColumn(name = "id_Thuong_hieu")
    private thuongHieu thuongHieu;

    @Column(name = "NamBH")
    private int namBH;

    @Column(name = "Mo_Ta")
    private String moTa;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "So_Luong_Ton")
    private int soLuongTon;

    @Column(name = "Gia_Nhap")
    private BigDecimal giaNhap;

    @Column(name = "Gia_Ban")
    private BigDecimal giaBan;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @OneToMany(mappedBy = "chiTietSP", fetch = FetchType.EAGER)
    List<hoaDonChiTiet> listt = new ArrayList<>();

    @OneToMany(mappedBy = "chiTietSP", fetch = FetchType.EAGER)
    List<GioHangChiTiet> listtt = new ArrayList<>();

    @OneToMany(mappedBy = "chiTietSP", fetch = FetchType.EAGER)
    private List<chiTietSPKhuyenMai> list = new ArrayList<>();

    public ChiTietSanPham(Integer id) {
        this.id = id;
    }

    public ChiTietSanPham() {
    }
    

    
}
