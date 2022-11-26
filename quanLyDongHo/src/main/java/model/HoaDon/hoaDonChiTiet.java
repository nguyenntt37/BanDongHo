/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.hoadon;

import model.sanpham.ChiTietSanPham;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author asus_vinh
 */
@Data
@Entity
@Table(name = "Hoa_Don_Chi_Tiet")
public class hoaDonChiTiet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_Hoa_Don", nullable = false)
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "Id_Chi_TietSP", nullable = false)
    private ChiTietSanPham chiTietSP;

    @Column(name = "So_Luong")
    private int soLuong;

    @Column(name = "trang_thai")
    private Integer trangthai;

    @Column(name = "ngay_tao")
    private String ngayTao;

    @Column(name = "ngay_sua")
    private String ngaySua;

}
