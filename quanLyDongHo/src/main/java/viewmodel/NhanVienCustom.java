/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.nhanvien.ChucVu;

/**
 *
 * @author Nguyen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NhanVienCustom {
    private String maNV;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private String diaChi;
    private String soDT;
    private String tenChucVu;
    private int trangThai;
}
