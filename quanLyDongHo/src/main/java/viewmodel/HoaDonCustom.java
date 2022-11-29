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
public class HoaDonCustom {
    private int maHD;
    private BigDecimal tongTien;
    private BigDecimal tienThua;
    private String pttt;
    private String htgh;
    private String tgTao;
    private String trangThaiTT;
    private int maNV;
    private String tenNV;
    private int maKH;
    private String tenKH;
    private String ghiChu;
}
