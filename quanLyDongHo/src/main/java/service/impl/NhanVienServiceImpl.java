/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import Repository.NhanVienRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.nhanvien.NhanVien;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;
import static org.hibernate.type.descriptor.java.JdbcDateTypeDescriptor.DATE_FORMAT;
import service.INhanVienService;
import viewmodel.NhanVienCustom;

/**
 *
 * @author Nguyen
 */
public class NhanVienServiceImpl implements INhanVienService {

    NhanVienRepository repo = new NhanVienRepository();
    private List<String>listEmail = new ArrayList();

    @Override
    public List<NhanVienCustom> get() {
        return repo.get();
    }

    @Override
    public String add(NhanVien nv) {
        try{
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if(String.valueOf(nv.getMa()).isEmpty()||String.valueOf(nv.getGioiTinh()).isEmpty()||nv.getHo().isEmpty()||nv.getTen().isEmpty()||nv.getTenDem().isEmpty()||nv.getDiaChi().isEmpty()||nv.getSdt().isEmpty()||String.valueOf(nv.getTrangThai()).isEmpty()){
            return "Vui lòng nhập đủ dữ liệu";
        }
        List<NhanVienCustom>list = repo.get();
        for(NhanVienCustom nv4 : list){
            NhanVien nv5 = repo.getByMaNV(nv4.getMaNV());
            listEmail.add(nv5.getEmail());
        }
        for(int i =0; i<listEmail.size(); i++){
            if(nv.getEmail().equals(listEmail.get(i))){
                return "Không thể thêm nhân viên trùng email";
            }
        }
        if(nv.getTrangThai()!=1){
            return "Không thể thêm nhân viên đã nghỉ việc";
        }
        if(!nv.getEmail().matches(regex)){
            return "Email bạn nhập vào không đúng định dạng";
        }
        for(NhanVienCustom nv3 : list){
            if(nv3.getMaNV().equals(nv.getMa())){
                return "Không thể thêm nhân viên trùng mã";
            }
        }
        if(!nv.getSdt().matches("\\d+")){
            return "SDT phải toàn là số";
        }
        String ngayThang = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(ngayThang);
        df.setLenient(false);
        df.parse(nv.getNgaySinh());
        boolean check = repo.add(nv);
        if(check){
            return "Thêm thành công";
            
        }
        else return "Thêm thất bại";
        }
        catch (ParseException e) {
            return "Thêm thất bại, ngày sinh không hợp lệ \n"
                    + "Ngày sinh hợp lệ có định dạng yyyy-MM-dd";
        }
    }

    @Override
    public String update(NhanVien nv, String maNV) {
        try{
        if(String.valueOf(nv.getMa()).isEmpty()||String.valueOf(nv.getGioiTinh()).isEmpty()||nv.getHo().isEmpty()||nv.getTen().isEmpty()||nv.getTenDem().isEmpty()||nv.getDiaChi().isEmpty()||nv.getSdt().isEmpty()||String.valueOf(nv.getTrangThai()).isEmpty()){
            return "Vui lòng nhập đủ dữ liệu";
        }
        String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if(!nv.getEmail().matches(regex)){
            return "Email bạn nhập vào không đúng định dạng";
        }
        List<NhanVienCustom>list = repo.get();
        NhanVien nv6 = repo.getByMaNV(maNV);
        for(NhanVienCustom nv3 : list){
            if(nv3.getMaNV().equals(nv.getMa())&&!nv.getMa().equals(nv6.getMa())){
                return "Không thể thêm nhân viên trùng mã";
            }
        }
        for(NhanVienCustom nv4 : list){
            NhanVien nv5 = repo.getByMaNV(nv4.getMaNV());
            listEmail.add(nv5.getEmail());
        }
        NhanVien nv3 =repo.getByMaNV(maNV);
        for(int i =0; i<listEmail.size(); i++){
            if(nv.getEmail().equals(listEmail.get(i))&&!nv3.getEmail().equals(nv.getEmail())){
                if(nv3.getEmail().equals(listEmail.get(i)))
                return "Không thể sửa nhân viên trùng email";
            }
        }
        if(!nv.getSdt().matches("\\d+")){
            return "SDT phải toàn là số";
        }
        String ngayThang = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(ngayThang);
        df.setLenient(false);
        df.parse(nv.getNgaySinh());
        boolean check = repo.update(nv, maNV);
        if(check){
            return "Sua thanh cong";
        }
        else return "Sua that bai";
        }
        catch (ParseException e) {
            return "Sửa thất bại, ngày sinh không hợp lệ \n"
                    + "Ngày sinh hợp lệ có định dạng yyyy-MM-dd";
        }
    }

    @Override
    public NhanVien getByMaNV(String maNV) {
        NhanVien nv = repo.getByMaNV(maNV);
        return nv;
    }

    @Override
    public String updateTrangThai(NhanVien nv, String maNV) {
        boolean check = repo.updateTrangThai(nv, maNV);
        if(check){
            return "Update thanh cong";
        }
        else return "Update that bai";

    }

    @Override
    public List<NhanVienCustom> Search(String sdt) {
        return repo.Search(sdt);
    }

    @Override
    public String updateMatKhau(NhanVien nv, String maNV, String matKhau) {
        boolean check = repo.updateMatKhau(nv, maNV, matKhau);
        if(check){
            return "Đổi mật khẩu thành công";
        }
        else return "Đổi mật khẩu thất bại";
    }
    

}
