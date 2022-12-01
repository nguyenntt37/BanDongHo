/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.KhachHang;

/**
 *
 * @author Nguyen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanHang_HDCustom {
    private String maHD;
    private String tgTao;
    private String nvTao;
    private KhachHang khachHang;
}
