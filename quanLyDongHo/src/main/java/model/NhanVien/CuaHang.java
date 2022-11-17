/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.nhanvien;

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
import lombok.Data;

/**
 *
 * @author asus_vinh
 */
@Data
@Entity
@Table(name = "Cua_Hang")
public class CuaHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "Dia_Chi")
    private String diaChi;

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

    @OneToMany(mappedBy = "cuaHang", fetch = FetchType.EAGER)
    List<NhanVien> list = new ArrayList<>();

}
