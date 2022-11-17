/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author asus_vinh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSPCustom {

    private Integer id;

    private String maSP;

    private String tenSP;

    private BigDecimal giaBan;

    private String tenMauSac;

    private String tenThuongHieu;

    private String dayDeo;

    private String may;
     
    private String xuatXu;
    
    private int soLuongTon;


    @Override
    public String toString() {
        return tenSP;
    }

    public int toStringID() {
        return id;
    }

   

   

    public Object[] todataRowDS() {
        return new Object[]{maSP, tenSP, giaBan, tenMauSac, tenThuongHieu,  dayDeo, may, xuatXu, soLuongTon};
    }
}
