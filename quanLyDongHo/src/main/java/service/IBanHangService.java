/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.List;
import viewmodel.BanHang_HDCustom;

/**
 *
 * @author Nguyen
 */
public interface IBanHangService {
    List<BanHang_HDCustom> getHDCho(int trangThai);
    Object[] getSPCustom();
    Object[] getHDCT(int idHD);
    void insertSPToHDCT(int idHD, int sl, int idCTSP);
    void loadSLTon(int sl, int idCTSP);
    void setTTDaHuy(int idHD);
    void setTTDaThanhToan(int idHD, String ngayTT, BigDecimal tongTien, BigDecimal tienTraLai, String ghiChu, int htgh, int pttt);
    void deleteSPOnHDCT(int idCTSP, int sl);
    Object[] loadCboDongSP();
    Object[] getBH_SPCustomByDongSP(int idDSP);
}
