/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.giohang.GioHang;
import model.hoadon.HoaDon;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author asus_vinh
 */
@Entity
@Table(name = "Khach_Hang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "email")
    private String email;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "Ten_Dem")
    private String tenDem;

    @Column(name = "Ho")
    private String ho;

    @Column(name = "Ngay_Sinh")
    private String ngaySinh;

    @Column(name = "Dia_Chi")
    private String diaChi;

    @Column(name = "Sdt")
    private String sdt;

    @Column(name = "Mat_Khau")
    private String matKhau;

    @Column(name = "Thanh_Pho")
    private String thanhPho;

    @Column(name = "Quoc_Gia")
    private String quocGia;

    @Column(name = "trang_thai")
    private Integer trangthai;

    @Column(name = "ngay_tao")
    private String ngayTao;

    @Column(name = "ngay_sua")
    private String ngaySua;

    @OneToMany(mappedBy = "khachHang", fetch = FetchType.EAGER)
    private List<HoaDon> list = new ArrayList<>();

    @OneToMany(mappedBy = "khachHang", fetch = FetchType.EAGER)
    private List<GioHang> listt = new ArrayList<>();

}
