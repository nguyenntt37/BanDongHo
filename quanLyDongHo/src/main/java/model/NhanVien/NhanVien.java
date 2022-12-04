/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.nhanvien;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.hoadon.HoaDon;
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
import model.giohang.GioHang;
import org.hibernate.annotations.Nationalized;

/**
 * @author asus_vinh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Nhan_Vien")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Ma")
    private String ma;

    @Nationalized
    @Column(name = "Ten")
    private String ten;

    @Nationalized
    @Column(name = "Ten_Dem")
    private String tenDem;

    @Nationalized
    @Column(name = "Ho")
    private String ho;

    @Nationalized
    @Column(name = "Gioi_Tinh")
    private String gioiTinh;

    @Column(name = "NgaySinh")
    private String ngaySinh;

    @Nationalized
    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Sdt")
    private String sdt;

    @Column(name = "MatKhau")
    private String matKhau;
    
    @Column(name = "Email", unique = true)
    private String email;

    @Column(name = "IdGuiBC")
    private String idGuiBC;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "IdCH")
    private CuaHang cuaHang;

    @ManyToOne
    @JoinColumn(name = "IdCV")
    private ChucVu chucVu;

    @Column(name = "ngay_tao")
    private String ngayTao;

    @Column(name = "ngay_sua")
    private String ngaySua;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.EAGER)
    private List<HoaDon> listt = new ArrayList<>();

    @OneToMany(mappedBy = "idNV", fetch = FetchType.EAGER)
    private List<GioHang> lists = new ArrayList<>();
}
