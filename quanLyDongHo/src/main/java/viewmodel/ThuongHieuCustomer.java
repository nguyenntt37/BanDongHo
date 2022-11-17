/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.util.Vector;

/**
 *
 * @author nguye
 */
public class ThuongHieuCustomer {

    private Integer id;
    private String ten;
    private String ma;

    public ThuongHieuCustomer() {
    }

    public ThuongHieuCustomer(Integer id, String ma,String ten) {
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

    public Integer toStringId() {
        return id;
    }
    public Object[] toDataRow(){
        return new Object[]{id,ma,ten};
    }

}
