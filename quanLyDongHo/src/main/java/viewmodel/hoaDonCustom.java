/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

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
public class hoaDonCustom {

    private Integer id;

    private String ngayTao;

    private String tenNV;

    private String tenKH;
    
    
    
    public Object[] todataRowDS() {
        return new Object[]{id, ngayTao, tenNV, tenKH};
    }
}
