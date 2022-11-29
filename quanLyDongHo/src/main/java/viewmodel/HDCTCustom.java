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
 * @author Nguyen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HDCTCustom {
    private int maHDCT;
    private int maCTSP;
    private String tenCTSP;
    private int soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;
}
