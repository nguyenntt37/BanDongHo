/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.HoaDon;

import java.util.ArrayList;
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
import model.hoadon.HoaDon;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author asus_vinh
 */
@Data
@Entity
@Table(name = "Hinh_Thuc_GH")
public class HinhThucGH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Ma")
    private String ma;

    @Nationalized
    @Column(name = "Ten")
    private String ten;

    @OneToMany(mappedBy = "HinhThucGH", fetch = FetchType.EAGER)
    private List<HoaDon> list = new ArrayList<>();

    @Override
    public String toString() {
        return ten;
    }
}
