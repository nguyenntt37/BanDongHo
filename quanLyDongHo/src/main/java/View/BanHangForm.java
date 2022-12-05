/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Util.DatetimeUtil;
import Util.MoneyUtil;
import View.login.gui.Login;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import model.HoaDon.HinhThucGH;
import model.HoaDon.PhuongThucTT;
import model.KhachHang;
import model.hoadon.HoaDon;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import service.IBanHangService;
import service.INhanVienService;
import service.impl.BanHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import viewmodel.BanHang_HDCustom;
import viewmodel.DongSPCustom;

/**
 *
 * @author Nguyen
 */
public class BanHangForm extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private JTableHeader tblHDHeader, tblHDCTHeader, tblSPHeader;
    private DefaultTableCellRenderer tblHDCTCellRenderer, tblSPCellRenderer, tblHDCellRenderer;
    private TableColumnModel tblSPColModel, tblHDCTColModel, tblHDColModel;
    private DefaultComboBoxModel cboModelDongSP, cboModelHTGH, cboModelPTTT;
    private DefaultTableModel tblModelHD, tblModelSP, tblModelHDCT;
    private INhanVienService nvService = new NhanVienServiceImpl();
    private IBanHangService bhService = new BanHangServiceImpl();
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public BanHangForm() {
        setLookAndFeel();
        initComponents();
        init();
//        initWebcam();
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HoaDonForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() {
        setTableModel();
        initTableStructure();
        loadTableHDCho();
        loadTableSP();
        loadAllCombobox();
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Nhập mã hoặc tên sản phẩm");
    }

    private void setTableModel() {
        tblModelHD = (DefaultTableModel) tblHoaDonCho.getModel();
        tblModelHDCT = (DefaultTableModel) tblHDCT.getModel();
        tblModelSP = (DefaultTableModel) tblSanPham.getModel();
    }

    private void initTableStructure() {
        tblHDHeader = tblHoaDonCho.getTableHeader();
        tblHDCTHeader = tblHDCT.getTableHeader();
        tblSPHeader = tblSanPham.getTableHeader();
        tblHDHeader.setFont(new Font("segoeui", Font.BOLD, 12));
        tblHDHeader.setBackground(Color.LIGHT_GRAY);
        tblHDCTHeader.setFont(new Font("segoeui", Font.BOLD, 12));
        tblHDCTHeader.setBackground(Color.LIGHT_GRAY);
        tblSPHeader.setFont(new Font("segoeui", Font.BOLD, 12));
        tblSPHeader.setBackground(Color.LIGHT_GRAY);

        tblHDCTCellRenderer = (DefaultTableCellRenderer) tblHDCT.getDefaultRenderer(this.getClass());
        tblHDCellRenderer = (DefaultTableCellRenderer) tblHoaDonCho.getDefaultRenderer(this.getClass());
        tblSPCellRenderer = (DefaultTableCellRenderer) tblSanPham.getDefaultRenderer(this.getClass());
        tblHDCTCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblHDCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblSPCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tblSPColModel = tblSanPham.getColumnModel();
        tblHDColModel = tblHoaDonCho.getColumnModel();
        tblHDCTColModel = tblHDCT.getColumnModel();
        tblHDCTColModel.getColumn(0).setPreferredWidth(30);
        tblHDCTColModel.getColumn(1).setPreferredWidth(200);
        tblHDColModel.getColumn(0).setPreferredWidth(30);
        tblSPColModel.getColumn(0).setPreferredWidth(50);
        tblSPColModel.getColumn(1).setPreferredWidth(200);
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 150));

        executor.execute(this);
    }

    public void xuatFileHoaDon() {
        HoaDon hd = bhService.getHDById(getIdHD());
        File file = new File("src\\main\\java\\file\\HD" + getIdHD() + ".docx");
        try {
            try ( XWPFDocument document = new XWPFDocument()) {
                FileOutputStream out = new FileOutputStream(file);

                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                paragraph.setAlignment(ParagraphAlignment.CENTER);
                run.setText("CỬA HÀNG ĐỒNG HỒ ĐỂU");
                run.setFontFamily("Bahnschrift");
                run.setFontSize(19);
                run.setBold(true);

                XWPFParagraph paragraph2 = document.createParagraph();
                XWPFRun run2 = paragraph2.createRun();
                paragraph2.setAlignment(ParagraphAlignment.CENTER);
                run2.setText("ĐC: Số 69, đường Trần Duy Hưng, quận Paper Bridge, TP.Hà Nội");
                run2.setFontFamily("Bahnschrift");

                XWPFParagraph paragraph3 = document.createParagraph();
                XWPFRun run3 = paragraph3.createRun();
                paragraph3.setAlignment(ParagraphAlignment.CENTER);
                run3.setText("ĐT: 0969.049.053");
                run3.setFontFamily("Bahnschrift");
                run3.setTextPosition(50);

                XWPFParagraph paragraph4 = document.createParagraph();
                XWPFRun run4 = paragraph4.createRun();
                paragraph4.setAlignment(ParagraphAlignment.CENTER);
                run4.setText("HÓA ĐƠN BÁN HÀNG");
                run4.setFontFamily("Bahnschrift");
                run4.setFontSize(26);
                run4.setBold(true);

                XWPFParagraph paragraph5 = document.createParagraph();
                XWPFRun run5 = paragraph5.createRun();
                paragraph5.setAlignment(ParagraphAlignment.CENTER);
                run5.setText("Hóa đơn: " + hd.getMa());
                run5.setFontFamily("Bahnschrift");
                run5.setTextPosition(50);

                XWPFParagraph paragraph6 = document.createParagraph();
                XWPFRun run6 = paragraph6.createRun();
                run6.setText("Khách hàng: " + hd.getKhachHang().getHo() + " " + hd.getKhachHang().getTenDem() + " " + hd.getKhachHang().getTen());
                run6.setFontFamily("Bahnschrift");

                XWPFParagraph paragraph7 = document.createParagraph();
                XWPFRun run7 = paragraph7.createRun();
                run7.setText("Địa chỉ: " + hd.getKhachHang().getDiaChi());
                run7.setFontFamily("Bahnschrift");

                XWPFParagraph paragraph8 = document.createParagraph();
                XWPFRun run8 = paragraph8.createRun();
                run8.setText("SĐT: " + hd.getKhachHang().getSdt());
                run8.setFontFamily("Bahnschrift");

                XWPFParagraph paragraph9 = document.createParagraph();
                XWPFRun run9 = paragraph9.createRun();
                run9.setText("Ngày lập: " + hd.getTgTao());
                run9.setFontFamily("Bahnschrift");
                run9.setTextPosition(20);

                XWPFTable table = document.createTable(tblHDCT.getRowCount() + 2, 5);
                table.setWidth(100);

                XWPFTableRow row = table.getRow(0);
                XWPFParagraph paragraph10 = row.getCell(0).addParagraph();
                paragraph10.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun run10 = paragraph10.createRun();
                run10.setText("Mã sản phẩm");
                run10.setFontFamily("Bahnschrift");
                run10.setBold(true);
                run10.setTextPosition(20);

                XWPFParagraph paragraph11 = row.getCell(1).addParagraph();
                paragraph11.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun run11 = paragraph11.createRun();
                run11.setText("Tên sản phẩm");
                run11.setFontFamily("Bahnschrift");
                run11.setBold(true);
                run11.setTextPosition(20);

                XWPFParagraph paragraph12 = row.getCell(2).addParagraph();
                paragraph12.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun run12 = paragraph12.createRun();
                run12.setText("Số lượng");
                run12.setFontFamily("Bahnschrift");
                run12.setBold(true);
                run12.setTextPosition(20);

                XWPFParagraph paragraph13 = row.getCell(3).addParagraph();
                paragraph13.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun run13 = paragraph13.createRun();
                run13.setText("Đơn giá");
                run13.setFontFamily("Bahnschrift");
                run13.setBold(true);
                run13.setTextPosition(20);

                XWPFParagraph paragraph14 = row.getCell(4).addParagraph();
                paragraph14.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun run14 = paragraph14.createRun();
                run14.setText("Thành tiền");
                run14.setFontFamily("Bahnschrift");
                run14.setBold(true);
                run14.setTextPosition(20);

                for (int i = 0; i < tblHDCT.getRowCount(); i++) {
                    table.getRow(i + 1).getCell(0).setText(tblHDCT.getValueAt(i, 0).toString()); //Ma san pham
                    table.getRow(i + 1).getCell(1).setText(tblHDCT.getValueAt(i, 1).toString()); //Ten san pham
                    table.getRow(i + 1).getCell(2).setText(tblHDCT.getValueAt(i, 3).toString()); //So luong
                    table.getRow(i + 1).getCell(3).setText(tblHDCT.getValueAt(i, 2).toString()); //Don gia
                    table.getRow(i + 1).getCell(4).setText(String.valueOf(MoneyUtil.removeDecimalPart(tblHDCT.getValueAt(i, 3).toString()) * MoneyUtil.removeDecimalPart(tblHDCT.getValueAt(i, 2).toString()))); //Thanh tien
                }

                int tongSL = 0;
                for (int i = 0; i < tblHDCT.getRowCount(); i++) {
                    tongSL += Integer.parseInt(tblHDCT.getValueAt(i, 3) + "");
                }

                table.getRow(tblHDCT.getRowCount() + 1).getCell(0).setText("Tổng");
                table.getRow(tblHDCT.getRowCount() + 1).getCell(1).setText("");
                table.getRow(tblHDCT.getRowCount() + 1).getCell(2).setText(tongSL + "");
                table.getRow(tblHDCT.getRowCount() + 1).getCell(3).setText("");
                table.getRow(tblHDCT.getRowCount() + 1).getCell(4).setText(hd.getTongTien().toString());

                XWPFParagraph paragraph22 = document.createParagraph();
                paragraph22.setAlignment(ParagraphAlignment.LEFT);

                XWPFParagraph paragraph15 = document.createParagraph();
                paragraph15.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun run20 = paragraph15.createRun();
                run20.setText("TỔNG TIỀN PHẢI THANH TOÁN: " + lblThanhToan.getText() + " VNĐ");
                run20.setFontFamily("Bahnschrift");
                run20.setBold(true);

                XWPFParagraph paragraph24 = document.createParagraph();
                paragraph24.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun run24 = paragraph24.createRun();
                run24.setText("Tiền khách trả: " + txtTienKhachDua.getText() + " VNĐ");
                run24.setFontFamily("Bahnschrift");

                XWPFParagraph paragraph26 = document.createParagraph();
                paragraph26.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun run26 = paragraph26.createRun();
                run26.setText("Phương thức thanh toán: " + cboPhuongThucTT.getSelectedItem());
                run26.setFontFamily("Bahnschrift");

                XWPFParagraph paragraph25 = document.createParagraph();
                paragraph25.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun run25 = paragraph25.createRun();
                run25.setText("Tiền trả lại: " + lblTienThuaTraKhach.getText() + " VNĐ");
                run25.setFontFamily("Bahnschrift");

                XWPFParagraph paragraph23 = document.createParagraph();
                paragraph23.setAlignment(ParagraphAlignment.RIGHT);
                XWPFRun run23 = paragraph23.createRun();
                run23.setText("-------------------------------------------------------------------------------------------------------------------------------------------");
                run23.setFontFamily("Calibri");

                XWPFParagraph paragraph16 = document.createParagraph();
                paragraph16.setAlignment(ParagraphAlignment.RIGHT);
                XWPFRun run16 = paragraph16.createRun();
                run16.setText("Người bán hàng");
                run16.setFontFamily("Bahnschrift");
                run16.setBold(true);
                run16.setFontSize(11);
                run16.setTextPosition(30);

                XWPFParagraph paragraph17 = document.createParagraph();
                paragraph17.setAlignment(ParagraphAlignment.RIGHT);
                XWPFRun run17 = paragraph17.createRun();
                run17.setText(hd.getNhanVien().getHo() + " " + hd.getNhanVien().getTenDem() + " " + hd.getNhanVien().getTen());
                run17.setFontFamily("Bahnschrift");
                run17.setTextPosition(80);

                XWPFParagraph paragraph18 = document.createParagraph();
                paragraph18.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun run18 = paragraph18.createRun();
                run18.setText("Cảm ơn quý khách đã mua hàng!");
                run18.setFontFamily("Bahnschrift");

                XWPFParagraph paragraph19 = document.createParagraph();
                paragraph19.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun run19 = paragraph19.createRun();
                run19.setText("Hẹn gặp lại!");
                run19.setFontFamily("Bahnschrift");

                document.write(out);
                out.close();
            }
            if (JOptionPane.showConfirmDialog(this, "Xuất file thành công. Mở file?", "Vui lòng chọn", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Desktop.getDesktop().open(file);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void loadAllCombobox() {
        loadCboDongSP();
        loadCboPTTT();
        loadCboHTGH();
    }

    private void loadCboDongSP() {
        cboModelDongSP = (DefaultComboBoxModel) cboDongSP.getModel();
        cboModelDongSP.addElement("Tất cả");
        for (Object o : bhService.loadCboDongSP()) {
            cboModelDongSP.addElement(o);
        }
    }

    private void loadCboPTTT() {
        cboModelPTTT = (DefaultComboBoxModel) cboPhuongThucTT.getModel();
        for (Object o : bhService.getAllPTTT()) {
            cboModelPTTT.addElement(o);
        }
    }

    private void loadCboHTGH() {
        cboModelHTGH = (DefaultComboBoxModel) cboHinhThucGH.getModel();
        for (Object o : bhService.getAllHTGH()) {
            cboModelHTGH.addElement(o);
        }
    }

    public void loadTableHDCho() {
        tblModelHD.setRowCount(0);
        List<BanHang_HDCustom> lstHD = bhService.getHDCho();
        for (BanHang_HDCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getNvTao(),
                hd.getKhachHang()
            });
        }
    }

    private void loadTableSP() {
        tblModelSP.setRowCount(0);
        for (Object o : bhService.getSPCustom()) {
            tblModelSP.addRow((Object[]) o);
        }
    }

    private void loadTableSPBySearching(String search) {
        tblModelSP.setRowCount(0);
        for (Object o : bhService.searchSP(search)) {
            tblModelSP.addRow((Object[]) o);
        }
    }

    private void loadTableSPByDongSP(int i) {
        tblModelSP.setRowCount(0);
        for (Object o : bhService.getBH_SPCustomByDongSP(i)) {
            tblModelSP.addRow((Object[]) o);
        }
    }

    private void loadTableHDCT() {
        tblModelHDCT.setRowCount(0);
        int idHD = getIdHD();
        for (Object o : bhService.getHDCT(idHD)) {
            tblModelHDCT.addRow((Object[]) o);
        }
    }

    private void insertSPToHDCT(int sl) {
        try {
            int idHD = getIdHD();
            if (tblSanPham.getSelectedRowCount() >= 1) {
                int idCTSP = getIdCTSP(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0).toString());
                bhService.insertSPToHDCT(idHD, sl, idCTSP);
                bhService.loadSLTon(sl, idCTSP);
                loadTableHDCT();
                loadTableSP();
                loadTongTien();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần thêm");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn cần thêm");
            e.printStackTrace(System.out);
        }
    }

    private void loadTongTien() {
        long tongTien = 0;
        try {
            if (tblHDCT.getRowCount() == 0) {
                lblTongTien.setText("0");
                lblThanhToan.setText("0");
                txtTienKhachDua.setText("0");
                lblTienThuaTraKhach.setText("0");
                return;
            }
            for (int i = 0; i < tblHDCT.getRowCount(); i++) {
                long donGia = MoneyUtil.removeDecimalPart(tblHDCT.getValueAt(i, 2).toString());
                int soLuong = Integer.parseInt(tblHDCT.getValueAt(i, 3).toString());
                tongTien += (soLuong * donGia);
                lblTongTien.setText(String.valueOf(tongTien));
                lblThanhToan.setText(String.valueOf(tongTien));
                long thanhToan = tongTien;
                long tienKhachDua = Long.parseLong(txtTienKhachDua.getText().trim());
                lblTienThuaTraKhach.setText(String.valueOf(tienKhachDua - thanhToan));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void loadTienThua() {
        txtTienKhachDua.putClientProperty("JComponent.outline", "");
        if (txtTienKhachDua.getText().trim().equals("")) {
            lblTienThuaTraKhach.setText("0");
            return;
        } else {
            try {
                long tienKhachDuaFormated = MoneyUtil.removeDecimalPart(txtTienKhachDua.getText().trim());
                if (tienKhachDuaFormated < 0) {
                    txtTienKhachDua.putClientProperty("JComponent.outline", "error");
                }
                txtTienKhachDua.setText(String.valueOf(tienKhachDuaFormated));
                long thanhToan = MoneyUtil.removeDecimalPart(lblThanhToan.getText());
                long tienKhachDua = MoneyUtil.removeDecimalPart(txtTienKhachDua.getText().trim());
                long tienThua = tienKhachDua - thanhToan;
                lblTienThuaTraKhach.setText(String.valueOf(tienThua));
            } catch (NumberFormatException e) {
                txtTienKhachDua.putClientProperty("JComponent.outline", "error");
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
    }

    private void thanhToan() {
        PhuongThucTT pttt;
        HinhThucGH htgh;
        try {
            int idHD = getIdHD();
            BigDecimal tongTien = new BigDecimal(lblTongTien.getText());
            BigDecimal tienTraLai = new BigDecimal(lblTienThuaTraKhach.getText());
            String ghiChu = txtghiChu.getText();
            htgh = (HinhThucGH) cboModelHTGH.getSelectedItem();
            pttt = (PhuongThucTT) cboModelPTTT.getSelectedItem();
            bhService.thanhToanHD(idHD, DatetimeUtil.getCurrentDateAndTime(), tongTien, tienTraLai, ghiChu, pttt.getId(), htgh.getId());
            JOptionPane.showMessageDialog(this, "Thanh toán hoá đơn thành công");
            if (JOptionPane.showConfirmDialog(this, "Xuất file hoá đơn?", "Vui lòng chọn", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                xuatFileHoaDon();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hoá đơn cần thanh toán");
            e.printStackTrace(System.out);
        }
    }

    private void xoaSPOnHDCT() {
        try {
            int idCTSP = getIdCTSP(tblModelHDCT.getValueAt(tblHDCT.getSelectedRow(), 0).toString());
            int sl = Integer.parseInt(tblModelHDCT.getValueAt(tblHDCT.getSelectedRow(), 3).toString());
            bhService.deleteSPOnHDCT(idCTSP, sl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xoá");
            e.printStackTrace(System.out);
        }
    }

    private void xoaAllSPOnHDCT() {
        int idCTSP, sl;
        try {
            for (int i = 0; i < tblHDCT.getRowCount(); i++) {
                idCTSP = getIdCTSP(tblModelHDCT.getValueAt(i, 0).toString());
                sl = Integer.parseInt(tblModelHDCT.getValueAt(i, 3).toString());
                bhService.deleteSPOnHDCT(idCTSP, sl);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui long chon san pham can xoa");
            e.printStackTrace(System.out);
        }
    }

    private void huyHD() {
        try {
            int idHD = getIdHD();
            bhService.huyHD(idHD);
            JOptionPane.showMessageDialog(this, "Đã huỷ hoá đơn");
            loadTableHDCho();
            tblModelHDCT.setRowCount(0);
            lblMaHoaDon.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui long chon hoa don can huy");
            e.printStackTrace(System.out);
        }
    }

    private void updateSLTon() {
        try {
            int idCTSP, sl;
            if (tblModelHDCT.getRowCount() > 0) {
                for (int i = 0; i < tblHDCT.getRowCount(); i++) {
                    idCTSP = getIdCTSP(tblModelHDCT.getValueAt(i, 0).toString());
                    sl = Integer.parseInt(tblModelHDCT.getValueAt(i, 3).toString());
                    bhService.updateSLTon(idCTSP, sl);
                }
                loadTableSP();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui long chon hoa don can huy");
            e.printStackTrace(System.out);
        }
    }

    private void insertNewHD() {
        KhachHang kh = new KhachHang();
        HoaDon hd = new HoaDon();
        kh.setId(getIdKH());
        hd.setKhachHang(kh);
        hd.setNhanVien(nvService.getByMaNV(Login.getCurrentLoginUsername()));
        hd.setTgTao(DatetimeUtil.getCurrentDateAndTime());
        hd.setTrangThaiTT(0);
        bhService.insert(hd);
    }

    public static void setInfoKhachHang(KhachHang kh) {
        lblMaKH.setText(kh.getMa());
        lblTenKH.setText(kh.getHo() + " " + kh.getTenDem() + " " + kh.getTen());
    }

    private void resetGUI() {
        tblHoaDonCho.clearSelection();
        tblHDCT.clearSelection();
        tblSanPham.clearSelection();
        lblMaHoaDon.setText(null);
        tblModelHDCT.setRowCount(0);
        lblTongTien.setText("0");
        lblThanhToan.setText("0");
        lblTienThuaTraKhach.setText("0");
        txtTienKhachDua.setText("0");
        txtghiChu.setText(null);
        cboPhuongThucTT.setSelectedIndex(0);
        cboHinhThucGH.setSelectedIndex(0);
    }

    private int getIdHD() {
        return Integer.parseInt(lblMaHoaDon.getText().substring(2));
    }

    private int getIdKH() {
        int idKH = 0;
        try {
            idKH = Integer.parseInt(lblMaKH.getText().substring(2));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng");
        }
        return idKH;
    }

    private int getIdCTSP(String idCTSP) {
        return Integer.parseInt(idCTSP.substring(4));
    }

    private boolean isTienKhachDuaValid() {
        if (Long.parseLong(lblTienThuaTraKhach.getText()) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace(System.out);
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }

            if (result != null) {
                txtTimKiem.setText(result.getText());
                txtTimKiem.setCaretPosition(txtTimKiem.getText().length() - 1);
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        btnXoaSP = new javax.swing.JButton();
        btnXoaTatCaSP = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnThemSanPham = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cboDongSP = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnTao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtghiChu = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboPhuongThucTT = new javax.swing.JComboBox<>();
        btnHuyHoaDon = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cboHinhThucGH = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lblMaKH = new javax.swing.JLabel();
        btnThayDoiKH = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        btnChonKH = new javax.swing.JButton();
        txtTienKhachDua = new javax.swing.JTextField();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblTienThuaTraKhach = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        webCamPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelBorder5 = new view.Swing.PanelBorder();
        jLabel29 = new javax.swing.JLabel();
        panelBorder6 = new view.Swing.PanelBorder();
        jLabel30 = new javax.swing.JLabel();
        panelBorder11 = new view.Swing.PanelBorder();
        jLabel45 = new javax.swing.JLabel();
        panelBorder1 = new view.Swing.PanelBorder();
        jLabel46 = new javax.swing.JLabel();
        panelBorder7 = new view.Swing.PanelBorder();
        jLabel31 = new javax.swing.JLabel();
        panelBorder12 = new view.Swing.PanelBorder();
        jLabel47 = new javax.swing.JLabel();
        panelBorder13 = new view.Swing.PanelBorder();
        jLabel48 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(1146, 768));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên sản phẩm", "Đơn giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDCT.setRowHeight(25);
        tblHDCT.setShowGrid(true);
        jScrollPane5.setViewportView(tblHDCT);

        btnXoaSP.setBackground(new java.awt.Color(51, 102, 153));
        btnXoaSP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnXoaSP.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaSP.setText("Xóa sản phẩm");
        btnXoaSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 153), 1, true));
        btnXoaSP.setPreferredSize(new java.awt.Dimension(60, 30));
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnXoaTatCaSP.setBackground(new java.awt.Color(51, 102, 153));
        btnXoaTatCaSP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnXoaTatCaSP.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTatCaSP.setText("Xóa tất cả");
        btnXoaTatCaSP.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 153), 1, true));
        btnXoaTatCaSP.setPreferredSize(new java.awt.Dimension(60, 30));
        btnXoaTatCaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTatCaSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTatCaSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnXoaTatCaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên sản phẩm", "Đơn giá", "Màu sắc", "Loại máy", "Kính", "Xuất xứ", "SL còn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(25);
        tblSanPham.setShowGrid(true);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblSanPham);

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Tìm kiếm sản phẩm:");

        btnThemSanPham.setBackground(new java.awt.Color(51, 102, 153));
        btnThemSanPham.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnThemSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSanPham.setText("Thêm sản phẩm");
        btnThemSanPham.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 153), 1, true));
        btnThemSanPham.setPreferredSize(new java.awt.Dimension(60, 30));
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Dòng sản phẩm:");

        cboDongSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDongSPItemStateChanged(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel14.setText("* Double-click vào từng dòng trên bảng nếu muốn thêm từng sản phẩm một");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(161, 161, 161))
                            .addComponent(cboDongSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        btnTao.setBackground(new java.awt.Color(51, 102, 153));
        btnTao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnTao.setForeground(new java.awt.Color(255, 255, 255));
        btnTao.setText("Tạo");
        btnTao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 153), 1, true));
        btnTao.setPreferredSize(new java.awt.Dimension(101, 25));
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Tổng tiền:");

        txtghiChu.setColumns(20);
        txtghiChu.setRows(5);
        jScrollPane7.setViewportView(txtghiChu);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Mã hóa đơn:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Phương thức thanh toán:");

        btnHuyHoaDon.setBackground(new java.awt.Color(51, 102, 153));
        btnHuyHoaDon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHuyHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyHoaDon.setText("Hủy hóa đơn");
        btnHuyHoaDon.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 153), 1, true));
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(51, 102, 153));
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 153), 1, true));
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Tiền thừa trả khách:");

        lblTongTien.setText("0");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Ghi chú:");

        btnThanhToan.setBackground(new java.awt.Color(51, 102, 153));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 153), 1, true));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tiền khách đưa:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Hình thức giao hàng:");

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblMaKH.setForeground(new java.awt.Color(255, 0, 0));
        lblMaKH.setText("Vui lòng chọn!");

        btnThayDoiKH.setBackground(new java.awt.Color(51, 102, 153));
        btnThayDoiKH.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnThayDoiKH.setForeground(new java.awt.Color(255, 255, 255));
        btnThayDoiKH.setText("Thay đổi");
        btnThayDoiKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 153), 1, true));
        btnThayDoiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiKHActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Mã khách hàng:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Tên khách hàng:");

        lblTenKH.setForeground(new java.awt.Color(255, 0, 0));
        lblTenKH.setText(" ");

        btnChonKH.setBackground(new java.awt.Color(51, 102, 153));
        btnChonKH.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btnChonKH.setForeground(new java.awt.Color(255, 255, 255));
        btnChonKH.setText("Chọn");
        btnChonKH.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 153), 1, true));
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThayDoiKH, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(btnChonKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChonKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblTenKH)
                    .addComponent(btnThayDoiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnChonKH, btnThayDoiKH});

        txtTienKhachDua.setText("0");
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        lblMaHoaDon.setText(" ");

        jLabel13.setText("VNĐ");

        jLabel15.setText("VNĐ");

        jLabel16.setText("VNĐ");

        lblTienThuaTraKhach.setText("0");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Thanh toán:");

        lblThanhToan.setText("0");

        jLabel17.setText("VNĐ");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboPhuongThucTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(btnHuyHoaDon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(37, 37, 37)
                                .addComponent(cboHinhThucGH, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel20))
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(lblThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel17))
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel13))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15))))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(lblMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(21, 21, 21)
                                .addComponent(lblTienThuaTraKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16))
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblThanhToan, lblTongTien});

        jPanel12Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuyHoaDon, btnLamMoi});

        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaHoaDon))
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTongTien)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lblThanhToan)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel16)
                    .addComponent(lblTienThuaTraKhach))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cboHinhThucGH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 46, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi)
                    .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel12Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnHuyHoaDon, btnLamMoi});

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Thời gian tạo", "Nhân viên", "Khách hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCho.setRowHeight(25);
        tblHoaDonCho.setShowGrid(true);
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblHoaDonCho);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );

        webCamPanel.setBackground(new java.awt.Color(255, 255, 255));
        webCamPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét mã sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        webCamPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        webCamPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 250, 150));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(webCamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(webCamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        panelBorder5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder5MouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Bán hàng");

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel29)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        panelBorder6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder6MouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText(" hóa đơn");

        javax.swing.GroupLayout panelBorder6Layout = new javax.swing.GroupLayout(panelBorder6);
        panelBorder6.setLayout(panelBorder6Layout);
        panelBorder6Layout.setHorizontalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder6Layout.setVerticalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel30)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        panelBorder11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder11MouseClicked(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText(" Nhân viên");

        javax.swing.GroupLayout panelBorder11Layout = new javax.swing.GroupLayout(panelBorder11);
        panelBorder11.setLayout(panelBorder11Layout);
        panelBorder11Layout.setHorizontalGroup(
            panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder11Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel45)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder11Layout.setVerticalGroup(
            panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder11Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addGap(23, 23, 23))
        );

        panelBorder1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder1MouseClicked(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Đăng Xuất");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel46)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel46)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelBorder7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder7MouseClicked(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Sản phẩm");

        javax.swing.GroupLayout panelBorder7Layout = new javax.swing.GroupLayout(panelBorder7);
        panelBorder7.setLayout(panelBorder7Layout);
        panelBorder7Layout.setHorizontalGroup(
            panelBorder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder7Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder7Layout.setVerticalGroup(
            panelBorder7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel31)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        panelBorder12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder12MouseClicked(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel47.setText("Thống kê");

        javax.swing.GroupLayout panelBorder12Layout = new javax.swing.GroupLayout(panelBorder12);
        panelBorder12.setLayout(panelBorder12Layout);
        panelBorder12Layout.setHorizontalGroup(
            panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder12Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel47)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder12Layout.setVerticalGroup(
            panelBorder12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder12Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel47)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelBorder13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder13MouseClicked(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Khách hàng");

        javax.swing.GroupLayout panelBorder13Layout = new javax.swing.GroupLayout(panelBorder13);
        panelBorder13.setLayout(panelBorder13Layout);
        panelBorder13Layout.setHorizontalGroup(
            panelBorder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder13Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel48)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder13Layout.setVerticalGroup(
            panelBorder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder13Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
        xoaSPOnHDCT();
        loadTableHDCT();
        loadTongTien();
        loadTableSP();
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        // TODO add your handling code here:
        String s = JOptionPane.showInputDialog(this, "Nhập số lượng sản phẩm cần thêm");
        if (s != null) {
            try {
                int sl = Integer.parseInt(s);
                if (sl > 0) {
                    try {
                        if (sl <= Integer.parseInt(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 7).toString())) {
                            insertSPToHDCT(sl);
                        } else {
                            JOptionPane.showMessageDialog(this, "Ồ nầu! Số lượng vừa nhập đã vượt quá số sản phẩm còn lại trong cửa hàng");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần thêm");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
            }
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        // TODO add your handling code here:
        KhachHang kh = (KhachHang) tblModelHD.getValueAt(tblHoaDonCho.getSelectedRow(), 3);
        lblMaHoaDon.setText((String) tblHoaDonCho.getValueAt(tblHoaDonCho.getSelectedRow(), 0));
        lblMaKH.setText(kh.getMa());
        lblTenKH.setText(kh.toString());
        loadTableHDCT();
        loadTongTien();
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            insertSPToHDCT(1);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnXoaTatCaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTatCaSPActionPerformed
        // TODO add your handling code here:
        xoaAllSPOnHDCT();
        loadTableHDCT();
        loadTableSP();
        loadTongTien();
    }//GEN-LAST:event_btnXoaTatCaSPActionPerformed

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        // TODO add your handling code here:
        loadTienThua();
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (isTienKhachDuaValid()) {
            thanhToan();
            resetGUI();
            loadTableHDCho();
        } else
            JOptionPane.showMessageDialog(this, "Số tiền khách đưa chưa đủ để thanh toán hoá đơn");
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        resetGUI();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        // TODO add your handling code here:
        updateSLTon();
        huyHD();
        resetGUI();
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        // TODO add your handling code here:
        resetGUI();
        insertNewHD();
        loadTableHDCho();
        tblHoaDonCho.scrollRectToVisible(tblHoaDonCho.getCellRect(tblModelHD.getRowCount() - 1, tblModelHD.getRowCount() - 1, true));
        tblHoaDonCho.setRowSelectionInterval(tblModelHD.getRowCount() - 1, tblModelHD.getRowCount() - 1);
        lblMaHoaDon.setText(tblHoaDonCho.getValueAt(tblModelHD.getRowCount() - 1, 0).toString());
    }//GEN-LAST:event_btnTaoActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        // TODO add your handling code here:
        cboDongSP.setSelectedIndex(0);
        if (txtTimKiem.getText().trim().length() > 0) {
            loadTableSPBySearching(txtTimKiem.getText().trim());
        } else
            loadTableSP();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        // TODO add your handling code here:
        new KhachHangJD(this, true).setVisible(true);
    }//GEN-LAST:event_btnChonKHActionPerformed

    private void btnThayDoiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiKHActionPerformed
        // TODO add your handling code here:
        if (!tblHoaDonCho.getSelectionModel().isSelectionEmpty()) {
            bhService.updateKhachHang(getIdKH(), getIdHD());
        }
        loadTableHDCho();
    }//GEN-LAST:event_btnThayDoiKHActionPerformed

    private void panelBorder5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder5MouseClicked
        //        card.show(pnlCand1, "pnlCard2");
    }//GEN-LAST:event_panelBorder5MouseClicked

    private void panelBorder6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder6MouseClicked
        HoaDonForm view = new HoaDonForm();
        this.dispose();
        view.setVisible(true);
        webcam.close();
    }//GEN-LAST:event_panelBorder6MouseClicked

    private void panelBorder11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder11MouseClicked
        NhanVienForm2 view = new NhanVienForm2();
        this.dispose();
        view.setVisible(true);
        webcam.close();
    }//GEN-LAST:event_panelBorder11MouseClicked

    private void panelBorder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder1MouseClicked
        LoginForm view = new LoginForm();
        this.dispose();
        view.setVisible(true);
        webcam.close();
    }//GEN-LAST:event_panelBorder1MouseClicked

    private void panelBorder7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder7MouseClicked
        ChiTietSPForm view = new ChiTietSPForm();
        this.dispose();
        view.setVisible(true);
        webcam.close();
    }//GEN-LAST:event_panelBorder7MouseClicked

    private void panelBorder12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder12MouseClicked
        ThongKeForm view = new ThongKeForm();
        this.dispose();
        view.setVisible(true);
        webcam.close();
    }//GEN-LAST:event_panelBorder12MouseClicked

    private void panelBorder13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder13MouseClicked
        KhachHangView view = new KhachHangView();
        this.dispose();
        view.setVisible(true);
        webcam.close();
    }//GEN-LAST:event_panelBorder13MouseClicked

    private void cboDongSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDongSPItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (cboDongSP.getSelectedIndex() == 0) {
                loadTableSP();
            } else {
                DongSPCustom dsp = (DongSPCustom) cboModelDongSP.getSelectedItem();
                loadTableSPByDongSP(dsp.getId());
            }
        }
    }//GEN-LAST:event_cboDongSPItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BanHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BanHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BanHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BanHangForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BanHangForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThayDoiKH;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXoaTatCaSP;
    private javax.swing.JComboBox<String> cboDongSP;
    private javax.swing.JComboBox<String> cboHinhThucGH;
    private javax.swing.JComboBox<String> cboPhuongThucTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblMaHoaDon;
    private static javax.swing.JLabel lblMaKH;
    private static javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblTienThuaTraKhach;
    private javax.swing.JLabel lblTongTien;
    private view.Swing.PanelBorder panelBorder1;
    private view.Swing.PanelBorder panelBorder11;
    private view.Swing.PanelBorder panelBorder12;
    private view.Swing.PanelBorder panelBorder13;
    private view.Swing.PanelBorder panelBorder5;
    private view.Swing.PanelBorder panelBorder6;
    private view.Swing.PanelBorder panelBorder7;
    private javax.swing.JTable tblHDCT;
    private static javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextArea txtghiChu;
    private javax.swing.JPanel webCamPanel;
    // End of variables declaration//GEN-END:variables
}
