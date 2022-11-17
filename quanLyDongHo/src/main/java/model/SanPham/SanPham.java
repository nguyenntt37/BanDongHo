/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.sanpham;

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
@Table(name = "San_Pham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "trang_thai")
    private Integer trangthai;

    @Column(name = "ngay_tao")
    private String ngayTao;

    @Column(name = "ngay_sua")
    private String ngaySua;
    
     @Column(name = "xuat_Xu")
    private String xuatXu;
     
      @Column(name = "kinh")
    private String kinh;
      
       @Column(name = "may")
    private String may;
       
        @Column(name = "mat_So")
    private String matSo;
        
         @Column(name = "day_Deo")
    private String dayDeo;
         
          @Column(name = "Chuc_nang")
    private String ChucNang;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<ChiTietSanPham> list = new ArrayList<>();

}
