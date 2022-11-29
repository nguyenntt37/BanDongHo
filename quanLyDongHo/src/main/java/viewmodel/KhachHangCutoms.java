/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

/**
 *
 * @author asus_vinh
 */
public class KhachHangCutoms {
    private int id;
    private String ma;
    private String ten;
    private String sdt;
    private String quocGia;
    private String tenDem;
    private String ho;
    private String thanhPho;
    private Integer trangthai;
    
    public KhachHangCutoms() {
    }

    public KhachHangCutoms(int id, String ma, String ten, String sdt, String quocGia, String tenDem, String ho, String thanhPho, int trangthai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.quocGia = quocGia;
        this.tenDem = tenDem;
        this.ho = ho;
        this.thanhPho = thanhPho;
        this.trangthai = trangthai;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getten() {
        return ten;
    }

    public void setten(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    @Override
    public String toString() {
        return "KhachHangCutoms{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", sdt=" + sdt + ", quocGia=" + quocGia + ", tenDem=" + tenDem + ", ho=" + ho + ", thanhPho=" + thanhPho + ", trangthai=" + trangthai + '}';
    }


    public Object[] toDataRow(){
        return  new Object[]{id, ma, ten, tenDem, ho, sdt, quocGia,thanhPho,trangthai};
    }
    

   

}
