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
public class GioHangChiTietCustom {
    
    private Integer id;
    
    private String maSP;
    
    private String tenSP;
    
    private BigDecimal giaBanSP;

    private int soLuong;
    
    private Integer idSP;

    public Object[] todataRow() {
        return new Object[]{ maSP, tenSP, giaBanSP, soLuong};
    }
}
