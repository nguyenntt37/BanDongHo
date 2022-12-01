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
 * @author nguye
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamCustom3 {
    private Integer id;
    private String maSP;
    private String tenSP;
    private String ChucNang;
    private String dayDeo;
    private String kinh;
    private String matSo;
    private String may;
    private String xuatXu;
    private String ngayTao;
    private Integer trangthai;
    
    @Override
    public String toString() {
        return tenSP;
    }
    
    public Integer toStringId(){ 
       return id;
   }
    
    public Object[] toDataRow(){
       return new Object[]{id,maSP,tenSP,ChucNang,dayDeo,kinh,matSo,may,xuatXu,ngayTao,trangthai};
   }
}
