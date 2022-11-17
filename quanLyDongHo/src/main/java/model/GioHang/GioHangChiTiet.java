/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.giohang;

import model.sanpham.ChiTietSanPham;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
/**
 *
 * @author asus_vinh
 */
@Data
@Entity
@Table(name = "Gio_Hang_Chi_Tiet")
public class GioHangChiTiet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Id_Gio_Hang", nullable = false)
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "Idchi_TietSP", nullable = false)
    private ChiTietSanPham chiTietSP;

    @Column(name = "So_Luong")
    private int soLuong;

    @Column(name = "Don_Gia")
    private BigDecimal donGia;

    @Column(name = "Don_Gia_Khi_Giam")
    private BigDecimal donGiaKhiGiam;

    @Column(name = "trang_thai")
    private Integer trangthai;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    public GioHangChiTiet(GioHang gioHang, ChiTietSanPham chiTietSP, int soLuong) {
        this.gioHang = gioHang;
        this.chiTietSP = chiTietSP;
        this.soLuong = soLuong;
    }

    public GioHangChiTiet() {
    }

   

}
