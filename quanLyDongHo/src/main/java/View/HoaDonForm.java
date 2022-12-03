/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Util.DatetimeUtil;
import Util.MoneyUtil;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import model.HoaDon.HinhThucGH;
import model.HoaDon.PhuongThucTT;
import service.IHoaDonService;
import service.impl.HoaDonServiceImpl;
import viewmodel.HDCTCustom;
import viewmodel.HoaDonCustom;

/**
 *
 * @author Nguyen
 */
public class HoaDonForm extends javax.swing.JFrame {

    private DefaultTableCellRenderer tblHDCellRenderer, tblHDCTCellRenderer;
    private DefaultTableModel tblModelHD, tblModelHDCT;
    private TableColumnModel tblHDColModel, tblHDCTColModel;
    private JTableHeader tblHDHeader, tblHDCTHeader;
    private IHoaDonService hdService = new HoaDonServiceImpl();
    private DefaultComboBoxModel cboModelPTTT, cboModelHTGH, cboModelNam;

    /**
     * Creates new form HoaDonForm
     */
    public HoaDonForm() {
        setLookAndFeel();
        initComponents();
        init();
    }

    private void init() {
        initTableStructure();
        loadCboPTTT();
        loadCboHTGH();
        loadTableHD();
        loadCboNam();
        txtTimKiemHD.putClientProperty("JTextField.placeholderText", "Nhập mã hoá đơn hoặc mã/tên nhân viên hoặc mã/tên khách hàng");
    }

    private void initTableStructure() {
        tblHDHeader = tblHoaDon.getTableHeader();
        tblHDCTHeader = tblHDCT.getTableHeader();
        tblHDHeader.setFont(new Font("segoeui", Font.BOLD, 12));
        tblHDHeader.setBackground(Color.LIGHT_GRAY);
        tblHDCTHeader.setFont(new Font("segoeui", Font.BOLD, 12));
        tblHDCTHeader.setBackground(Color.LIGHT_GRAY);

        tblHDCellRenderer = (DefaultTableCellRenderer) tblHoaDon.getDefaultRenderer(this.getClass());
        tblHDCTCellRenderer = (DefaultTableCellRenderer) tblHDCT.getDefaultRenderer(this.getClass());
        tblHDCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblHDCTCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tblHDColModel = tblHoaDon.getColumnModel();
        tblHDCTColModel = tblHDCT.getColumnModel();
        tblHDColModel.getColumn(0).setPreferredWidth(30);
        tblHDColModel.getColumn(5).setPreferredWidth(90);
        tblHDColModel.getColumn(7).setPreferredWidth(30);
        tblHDColModel.getColumn(9).setPreferredWidth(30);
        tblHDCTColModel.getColumn(0).setPreferredWidth(30);
        tblHDCTColModel.getColumn(1).setPreferredWidth(30);
        tblHDCTColModel.getColumn(2).setPreferredWidth(200);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HoaDonForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCboNam() {
        cboModelNam = (DefaultComboBoxModel) cboNam.getModel();
        for (Object o : hdService.getTGTaoTheoNam()) {
            cboModelNam.addElement(o);
        }
    }

    private void loadCboPTTT() {
        cboModelPTTT = (DefaultComboBoxModel) cboPhuongThucTT.getModel();
        for (Object o : hdService.getAllPTTT()) {
            cboModelPTTT.addElement(o);
        }
    }

    private void loadCboHTGH() {
        cboModelHTGH = (DefaultComboBoxModel) cboHinhThucGH.getModel();
        for (Object o : hdService.getAllHTGH()) {
            cboModelHTGH.addElement(o);
        }
    }

    private void loadTableHD() {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        List<HoaDonCustom> lstHD = hdService.getAllHD();
        for (HoaDonCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTongTien())),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTienThua())),
                hd.getPttt() == null ? "" : hd.getPttt(),
                hd.getHtgh(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    private void loadTableHDCT(int maHD) {
        tblModelHDCT = (DefaultTableModel) tblHDCT.getModel();
        tblModelHDCT.setRowCount(0);
        List<HDCTCustom> lstHDCT = hdService.getAllHDCT(maHD);
        for (HDCTCustom hdct : lstHDCT) {
            tblModelHDCT.addRow(new Object[]{
                hdct.getMaHDCT(),
                hdct.getMaCTSP(),
                hdct.getTenCTSP(),
                hdct.getSoLuong(),
                MoneyUtil.removeDecimalPart(String.valueOf(hdct.getDonGia())),
                MoneyUtil.removeDecimalPart(String.valueOf(hdct.getThanhTien()))
            });
        }
    }

    private void loadTableHDBySearching(String search) {
        tblModelHD.setRowCount(0);
        for (HoaDonCustom hd : hdService.getAllHDBySearching(search)) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                hd.getTongTien(),
                hd.getTienThua(),
                hd.getPttt(),
                hd.getHtgh(),
                hd.getTgTao(),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    private void loadHDChoTT() {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        List<HoaDonCustom> lstHD = hdService.getHDTheoTrangThai(0);
        for (HoaDonCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTongTien())),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTienThua())),
                hd.getPttt() == null ? "" : hd.getPttt(),
                hd.getHtgh(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    private void loadHDDaTT() {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        List<HoaDonCustom> lstHD = hdService.getHDTheoTrangThai(1);
        for (HoaDonCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTongTien())),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTienThua())),
                hd.getPttt() == null ? "" : hd.getPttt(),
                hd.getHtgh(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    private void loadHDDaHuy() {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        List<HoaDonCustom> lstHD = hdService.getHDTheoTrangThai(-1);
        for (HoaDonCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTongTien())),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTienThua())),
                hd.getPttt() == null ? "" : hd.getPttt(),
                hd.getHtgh(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    private void loadHDTheoPTTT(int idPTTT) {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        List<HoaDonCustom> lstHD = hdService.getHDTheoPTTT(idPTTT);
        for (HoaDonCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTongTien())),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTienThua())),
                hd.getPttt() == null ? "" : hd.getPttt(),
                hd.getHtgh(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    private void loadHDTheoHTGH(int idHTGH) {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        List<HoaDonCustom> lstHD = hdService.getHDTheoHTGH(idHTGH);
        for (HoaDonCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTongTien())),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTienThua())),
                hd.getPttt() == null ? "" : hd.getPttt(),
                hd.getHtgh(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    private void loadHDTheoTongTien(BigDecimal from, BigDecimal to) {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        List<HoaDonCustom> lstHD = hdService.getHDTheoTongTien(from, to);
        for (HoaDonCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTongTien())),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTienThua())),
                hd.getPttt() == null ? "" : hd.getPttt(),
                hd.getHtgh(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    private void loadHDTheoThang(int thang) {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        List<HoaDonCustom> lstHD = hdService.getHDTheoThang(thang);
        for (HoaDonCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTongTien())),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTienThua())),
                hd.getPttt() == null ? "" : hd.getPttt(),
                hd.getHtgh(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    private void loadHDTheoNam(int nam) {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        List<HoaDonCustom> lstHD = hdService.getHDTheoNam(nam);
        for (HoaDonCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTongTien())),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTienThua())),
                hd.getPttt() == null ? "" : hd.getPttt(),
                hd.getHtgh(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    private void loadHDTheoThangNam(int thang, int nam) {
        tblModelHD = (DefaultTableModel) tblHoaDon.getModel();
        tblModelHD.setRowCount(0);
        List<HoaDonCustom> lstHD = hdService.getHDTheoThangNam(thang, nam);
        for (HoaDonCustom hd : lstHD) {
            tblModelHD.addRow(new Object[]{
                hd.getMaHD(),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTongTien())),
                MoneyUtil.removeDecimalPart(String.valueOf(hd.getTienThua())),
                hd.getPttt() == null ? "" : hd.getPttt(),
                hd.getHtgh(),
                DatetimeUtil.convertDatetimeFormat(hd.getTgTao()),
                hd.getTrangThaiTT(),
                hd.getMaNV(),
                hd.getTenNV(),
                hd.getMaKH(),
                hd.getTenKH(),
                hd.getGhiChu()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        txtTimKiemHD = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cboThang = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cboTongTien = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboHinhThucGH = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboPhuongThucTT = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboTrangThaiTT = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐCT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDCT.setShowGrid(true);
        jScrollPane5.setViewportView(tblHDCT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Tổng tiền", "Tiền thừa trả khách", "Phương thức thanh toán", "Hình thức giao hàng", "TG lập hoá đơn", "Trạng thái thanh toán", "Mã NV", "Tên NV", "Mã KH", "Tên KH", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setShowGrid(true);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHoaDon);

        txtTimKiemHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemHDKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Tìm kiếm hóa đơn:");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Tháng:");

        cboThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cboThang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboThangItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Năm:");

        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Tổng tiền:");

        cboTongTien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "< 3 triệu đồng", "3 - 8 triệu đồng", "8 - 15 triệu đồng", "15 - 30 triệu đồng", "30 - 60 triệu đồng", "> 60 triệu đồng" }));
        cboTongTien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTongTienItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cboTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Hình thức giao hàng");

        cboHinhThucGH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboHinhThucGH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboHinhThucGHItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cboHinhThucGH, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboHinhThucGH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Phương thức thanh toán");

        cboPhuongThucTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboPhuongThucTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPhuongThucTTItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cboPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Trạng thái thanh toán:");

        cboTrangThaiTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chờ thanh toán", "Đã thanh toán", "Đã huỷ" }));
        cboTrangThaiTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTrangThaiTTItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cboTrangThaiTT, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTrangThaiTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(0, 102, 204));

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
                .addContainerGap(60, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
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
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int maHD = Integer.parseInt(tblModelHD.getValueAt(tblHoaDon.getSelectedRow(), 0).toString().substring(2));
        loadTableHDCT(maHD);
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimKiemHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHDKeyReleased
        // TODO add your handling code here:
        cboTrangThaiTT.setSelectedIndex(0);
        cboPhuongThucTT.setSelectedIndex(0);
        cboHinhThucGH.setSelectedIndex(0);
        cboTongTien.setSelectedIndex(0);
        cboThang.setSelectedIndex(0);
        cboNam.setSelectedIndex(0);
        if (txtTimKiemHD.getText().trim().length() > 0) {
            loadTableHDBySearching(txtTimKiemHD.getText().trim());
        } else
            loadTableHD();
    }//GEN-LAST:event_txtTimKiemHDKeyReleased

    private void panelBorder5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder5MouseClicked
        BanHangForm view = new BanHangForm();
        this.dispose();
        view.setVisible(true);
    }//GEN-LAST:event_panelBorder5MouseClicked

    private void panelBorder6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder6MouseClicked
        //        card.show(pnlCand1, "pnlCard3");
    }//GEN-LAST:event_panelBorder6MouseClicked

    private void panelBorder11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder11MouseClicked
        NhanVienForm2 view = new NhanVienForm2();
        this.dispose();
        view.setVisible(true);
    }//GEN-LAST:event_panelBorder11MouseClicked

    private void panelBorder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder1MouseClicked
        LoginForm view = new LoginForm();
        this.dispose();
        view.setVisible(true);
    }//GEN-LAST:event_panelBorder1MouseClicked

    private void panelBorder7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder7MouseClicked
        ChiTietSPForm view = new ChiTietSPForm();
        this.dispose();
        view.setVisible(true);
    }//GEN-LAST:event_panelBorder7MouseClicked

    private void panelBorder12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder12MouseClicked
        ThongKeForm view = new ThongKeForm();
        this.dispose();
        view.setVisible(true);
    }//GEN-LAST:event_panelBorder12MouseClicked

    private void panelBorder13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder13MouseClicked
        KhachHangView view = new KhachHangView();
        this.dispose();
        view.setVisible(true);
    }//GEN-LAST:event_panelBorder13MouseClicked

    private void cboTrangThaiTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTrangThaiTTItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cboPhuongThucTT.setSelectedIndex(0);
            cboHinhThucGH.setSelectedIndex(0);
            cboTongTien.setSelectedIndex(0);
            cboThang.setSelectedIndex(0);
            cboNam.setSelectedIndex(0);
            txtTimKiemHD.setText("");
            switch (cboTrangThaiTT.getSelectedIndex()) {
                case 1:
                    loadHDChoTT();
                    break;
                case 2:
                    loadHDDaTT();
                    break;
                case 3:
                    loadHDDaHuy();
                    break;
                default:
                    loadTableHD();
                    break;
            }
        }
    }//GEN-LAST:event_cboTrangThaiTTItemStateChanged

    private void cboPhuongThucTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPhuongThucTTItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cboTrangThaiTT.setSelectedIndex(0);
            cboHinhThucGH.setSelectedIndex(0);
            cboTongTien.setSelectedIndex(0);
            cboThang.setSelectedIndex(0);
            cboNam.setSelectedIndex(0);
            txtTimKiemHD.setText("");
            if (cboPhuongThucTT.getSelectedIndex() == 0) {
                loadTableHD();
            } else {
                PhuongThucTT pttt = (PhuongThucTT) cboPhuongThucTT.getSelectedItem();
                loadHDTheoPTTT(pttt.getId());
            }
        }
    }//GEN-LAST:event_cboPhuongThucTTItemStateChanged

    private void cboHinhThucGHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHinhThucGHItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cboTrangThaiTT.setSelectedIndex(0);
            cboPhuongThucTT.setSelectedIndex(0);
            cboTongTien.setSelectedIndex(0);
            cboThang.setSelectedIndex(0);
            cboNam.setSelectedIndex(0);
            txtTimKiemHD.setText("");
            if (cboHinhThucGH.getSelectedIndex() == 0) {
                loadTableHD();
            } else {
                HinhThucGH htgh = (HinhThucGH) cboHinhThucGH.getSelectedItem();
                loadHDTheoPTTT(htgh.getId());
            }
        }
    }//GEN-LAST:event_cboHinhThucGHItemStateChanged

    private void cboTongTienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTongTienItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cboTrangThaiTT.setSelectedIndex(0);
            cboPhuongThucTT.setSelectedIndex(0);
            cboHinhThucGH.setSelectedIndex(0);
            cboThang.setSelectedIndex(0);
            cboNam.setSelectedIndex(0);
            txtTimKiemHD.setText("");
            switch (cboTongTien.getSelectedIndex()) {
                case 1:
                    loadHDTheoTongTien(BigDecimal.valueOf(1), BigDecimal.valueOf(2999999));
                    break;
                case 2:
                    loadHDTheoTongTien(BigDecimal.valueOf(3000000), BigDecimal.valueOf(7999999));
                    break;
                case 3:
                    loadHDTheoTongTien(BigDecimal.valueOf(8000000), BigDecimal.valueOf(14999999));
                    break;
                case 4:
                    loadHDTheoTongTien(BigDecimal.valueOf(15000000), BigDecimal.valueOf(29999999));
                    break;
                case 5:
                    loadHDTheoTongTien(BigDecimal.valueOf(30000000), BigDecimal.valueOf(59999999));
                    break;
                case 6:
                    loadHDTheoTongTien(BigDecimal.valueOf(60000000), BigDecimal.valueOf(999999999).multiply(BigDecimal.valueOf(1000)));
                    break;
                default:
                    loadTableHD();
                    break;
            }
        }
    }//GEN-LAST:event_cboTongTienItemStateChanged

    private void cboThangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThangItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cboTrangThaiTT.setSelectedIndex(0);
            cboPhuongThucTT.setSelectedIndex(0);
            cboHinhThucGH.setSelectedIndex(0);
            cboTongTien.setSelectedIndex(0);
            txtTimKiemHD.setText("");
            if (cboThang.getSelectedIndex() > 0) {
                if (cboNam.getSelectedIndex() == 0) {
                    loadHDTheoThang(Integer.parseInt(cboThang.getSelectedItem().toString()));
                } else {
                    loadHDTheoThangNam(Integer.parseInt(cboThang.getSelectedItem().toString()), Integer.parseInt(cboNam.getSelectedItem().toString()));
                }
            } else {
                loadTableHD();
            }
        }
    }//GEN-LAST:event_cboThangItemStateChanged

    private void cboNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cboTrangThaiTT.setSelectedIndex(0);
            cboPhuongThucTT.setSelectedIndex(0);
            cboHinhThucGH.setSelectedIndex(0);
            cboTongTien.setSelectedIndex(0);
            txtTimKiemHD.setText("");
            if (cboNam.getSelectedIndex() > 0) {
                if (cboThang.getSelectedIndex() == 0) {
                    loadHDTheoNam(Integer.parseInt(cboNam.getSelectedItem().toString()));
                } else {
                    loadHDTheoThangNam(Integer.parseInt(cboThang.getSelectedItem().toString()), Integer.parseInt(cboNam.getSelectedItem().toString()));
                }
            } else {
                loadTableHD();
            }
        }
    }//GEN-LAST:event_cboNamItemStateChanged

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
            java.util.logging.Logger.getLogger(HoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboHinhThucGH;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboPhuongThucTT;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JComboBox<String> cboTongTien;
    private javax.swing.JComboBox<String> cboTrangThaiTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private view.Swing.PanelBorder panelBorder1;
    private view.Swing.PanelBorder panelBorder11;
    private view.Swing.PanelBorder panelBorder12;
    private view.Swing.PanelBorder panelBorder13;
    private view.Swing.PanelBorder panelBorder5;
    private view.Swing.PanelBorder panelBorder6;
    private view.Swing.PanelBorder panelBorder7;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtTimKiemHD;
    // End of variables declaration//GEN-END:variables
}
