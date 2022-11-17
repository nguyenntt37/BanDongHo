/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

/**
 *
 * @author asus_vinh
 */
public class SanPhamCustom {
     private Integer id;
    private String ma;
    private String ten;

    public SanPhamCustom() {
    }

    public SanPhamCustom(Integer id,String ma, String ten) {
        this.id = id;
         this.ma = ma;
        this.ten = ten;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return ten;
    }
//    
   public Integer toStringId(){ 
       return id;
   }
    public String toStringMa(){ 
       return ma;
   }
   public Object[] toDataRow(){
       return new Object[]{id,ma,ten};
   }
}
