/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Util.ExportExcelChiTietSP;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.sanpham.ChiTietSanPham;
import model.sanpham.DongSp;
import model.sanpham.MauSac;
import model.sanpham.NamSanXuat;
import model.sanpham.SanPham;
import model.sanpham.thuongHieu;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import service.impl.DongSPServicelmpl;
import service.impl.NamSanXuatServicelmpl;
import service.impl.SanPHamSeviceImpl;
import viewmodel.ChiTietSPCustom;
import viewmodel.DongSPCustom;
import viewmodel.MauSacCustom;
import viewmodel.NamSXCustom;
import viewmodel.NhanVienCustom;
import viewmodel.SanPhamCustom;
import viewmodel.SanPhamCustom3;
import viewmodel.ThuongHieuCustomer;

/**
 *
 * @author nguye
 */
public class ChiTietSPForm extends javax.swing.JFrame {

    DefaultTableModel dtmDanhSachSP = new DefaultTableModel();
    List<ChiTietSPCustom> listSPPage = new ArrayList<>();
    DefaultComboBoxModel dcbmMauSac = new DefaultComboBoxModel();
    DefaultComboBoxModel dcbmThuongHieu = new DefaultComboBoxModel();
    DefaultComboBoxModel dcbmDongSX = new DefaultComboBoxModel();
    DefaultComboBoxModel dcbmNamSX = new DefaultComboBoxModel();
    DefaultComboBoxModel dcbmChiTietSP = new DefaultComboBoxModel();
    DefaultComboBoxModel dcbmSanPham = new DefaultComboBoxModel();
    List<ChiTietSPCustom> listSP = new ArrayList<>();
    List<SanPhamCustom3> listSPc = new ArrayList<>();
    List<MauSacCustom> listMau = new ArrayList<>();
    List<NamSXCustom> listNamSanXuats = new ArrayList<>();
    List<DongSPCustom> listDongSp = new ArrayList<>();
    private SanPHamSeviceImpl spSevice = new SanPHamSeviceImpl();
    private NamSanXuatServicelmpl nsxs = new NamSanXuatServicelmpl();
    private DongSPServicelmpl dsps = new DongSPServicelmpl();
    private int soTrang = 1;
    /**
     * Creates new form ChiTietSPForm
     */
    public ChiTietSPForm() {
        initComponents();
        setLocationRelativeTo(null);
        tblCTSanPham.setModel(dtmDanhSachSP);
        String[] hedear = {"ID", "Mã SP", "Tên Sp", "Giá bán", "Màu sắc", "Thương hiệu", "Dây đeo", "Kính",
            "Xuất xứ", "Ngày tạo", "Số Lượng tồn", "trạng thái"};
        dtmDanhSachSP.setColumnIdentifiers(hedear);
//        showDataRow(listSP);
        //   loadDataToTableSP();

        cbbMauSac(spSevice.getAllMauSac());
        addCBBNamSX(nsxs.getListNamSanXuat());
        addCBBDongSp(dsps.getListDongSP());
        cbbSanPham(spSevice.getAllSanPham());
        cbbChiTietSP(spSevice.getAllSanPham());
        cbbThuongHieu(spSevice.getAllThuongHieu());

        listSP = spSevice.getAll();
        int heSo = (soTrang * 10) - 10;
        listSPPage = spSevice.getAll2(heSo);
        showDataRow(listSPPage);
        if (listSPPage.size() == 0) {
            btnFirst.setEnabled(false);
            btnNext.setEnabled(false);
            btnBack.setEnabled(false);
            btnLast.setEnabled(false);
        }
        btnFirst.setEnabled(false);
        btnBack.setEnabled(false);
        updPanel();

    }

    private void showDataRow(List<ChiTietSPCustom> listSPPage) {
        dtmDanhSachSP.setRowCount(0);
        for (ChiTietSPCustom p : listSPPage) {
            dtmDanhSachSP.addRow(p.todataRowDS());
        }
    }

    private void cbbChiTietSP(List<SanPhamCustom3> list) {
        cboSanPham.setModel(dcbmChiTietSP);
        for (SanPhamCustom3 o : list) {
            dcbmChiTietSP.addElement(o);
        }
    }

    private void cbbMauSac(List<MauSacCustom> listMau) {
        cboMauSac.setModel(dcbmMauSac);
        for (MauSacCustom o : listMau) {
            dcbmMauSac.addElement(o);
        }

    }

    private void cbbThuongHieu(List<ThuongHieuCustomer> listTH) {
        cboThuongHieu.setModel(dcbmThuongHieu);
        for (ThuongHieuCustomer o : listTH) {
            dcbmThuongHieu.addElement(o);
        }

    }

    private void cbbSanPham(List<SanPhamCustom3> listSPc) {
        cboSanPham.setModel(dcbmSanPham);
        for (SanPhamCustom3 o : listSPc) {
            dcbmSanPham.addElement(o);
        }
    }

    private void addCBBNamSX(List<NamSXCustom> listNamSanXuats) {
        cboNSX.setModel(dcbmNamSX);
        for (NamSXCustom o : listNamSanXuats) {
            dcbmNamSX.addElement(o);
        }

    }

    private void addCBBDongSp(List<DongSPCustom> listDongSPCustoms) {
        cboDongSP.setModel(dcbmDongSX);
        for (DongSPCustom o : listDongSPCustoms) {
            dcbmDongSX.addElement(o);
        }

    }

    private void setTruePhanTrang() {
        btnFirst.setEnabled(true);
        btnNext.setEnabled(true);
        btnBack.setEnabled(true);
        btnLast.setEnabled(true);
    }

    private void mose(int index) {
        ChiTietSPCustom sp = listSPPage.get(index);
        txtIdChiTietSP.setText(sp.getId().toString());
        txtID.setText(sp.getId().toString());
        txtGiaBan.setText(String.valueOf(sp.getGiaBan()));
        txtGiaNhap.setText(String.valueOf(sp.getGiaNhap()));
        txtMota.setText(sp.getMoTa());
        txtNamBH.setText(String.valueOf(sp.getNBH()));
        txtSoLuong.setText(String.valueOf(sp.getSoLuongTon()));
        cboSanPham.setSelectedItem(sp.getTenSP());
        cboNSX.setSelectedItem(sp.getNSX());
        cboMauSac.setSelectedItem(sp.getMau());
        cboThuongHieu.setSelectedItem(sp.getThuongHieu());
        int trangThai = sp.getTrangThai();
        if (trangThai == 1) {
            rdoCon.setSelected(true);
        } else {
            rdoKhongCon.setSelected(true);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        btnSanPham = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        cboNSX = new javax.swing.JComboBox<>();
        btnNsx = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        cboDongSP = new javax.swing.JComboBox<>();
        btnDongSP = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        btnMauSac = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        cboThuongHieu = new javax.swing.JComboBox<>();
        btnThuongHieu = new javax.swing.JButton();
        cboSanPham = new javax.swing.JComboBox<>();
        lblIdSanPham = new javax.swing.JLabel();
        lblIdNamSX = new javax.swing.JLabel();
        lblIdDongSp = new javax.swing.JLabel();
        lblIdMauSac = new javax.swing.JLabel();
        lblIdThuongHieu = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtIdChiTietSP = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtMota = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        txtNamBH = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rdoCon = new javax.swing.JRadioButton();
        rdoKhongCon = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTSanPham = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtSoTrang = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        btnNhapFile = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
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
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel40.setText("Sản phẩm");

        btnSanPham.setBackground(new java.awt.Color(0, 102, 204));
        btnSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSanPham.setText("+");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        jLabel41.setText("NSX");

        cboNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNSXActionPerformed(evt);
            }
        });

        btnNsx.setBackground(new java.awt.Color(0, 102, 204));
        btnNsx.setForeground(new java.awt.Color(255, 255, 255));
        btnNsx.setText("+");
        btnNsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNsxActionPerformed(evt);
            }
        });

        jLabel42.setText("Dòng SP");

        cboDongSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDongSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDongSPActionPerformed(evt);
            }
        });

        btnDongSP.setBackground(new java.awt.Color(0, 102, 204));
        btnDongSP.setForeground(new java.awt.Color(255, 255, 255));
        btnDongSP.setText("+");
        btnDongSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongSPActionPerformed(evt);
            }
        });

        jLabel43.setText("Màu sắc");

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMauSacActionPerformed(evt);
            }
        });

        btnMauSac.setBackground(new java.awt.Color(0, 102, 204));
        btnMauSac.setForeground(new java.awt.Color(255, 255, 255));
        btnMauSac.setText("+");
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        jLabel44.setText("Thương hiệu");

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThuongHieuActionPerformed(evt);
            }
        });

        btnThuongHieu.setBackground(new java.awt.Color(0, 102, 204));
        btnThuongHieu.setForeground(new java.awt.Color(255, 255, 255));
        btnThuongHieu.setText("+");
        btnThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuongHieuActionPerformed(evt);
            }
        });

        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });

        lblIdSanPham.setForeground(new java.awt.Color(255, 255, 255));

        lblIdNamSX.setForeground(new java.awt.Color(255, 255, 255));

        lblIdDongSp.setForeground(new java.awt.Color(255, 255, 255));

        lblIdMauSac.setForeground(new java.awt.Color(255, 255, 255));

        lblIdThuongHieu.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblIdSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(157, 157, 157))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cboDongSP, 0, 181, Short.MAX_VALUE)
                                        .addComponent(cboNSX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel44)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addGap(14, 14, 14)
                                            .addComponent(lblIdThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(161, 161, 161))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))))
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblIdNamSX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblIdDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblIdMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(163, 163, 163)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel41))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSanPham)
                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIdSanPham)
                .addGap(12, 12, 12)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNsx))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIdNamSX)
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(btnDongSP))
                .addGap(13, 13, 13)
                .addComponent(lblIdDongSp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMauSac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdMauSac)
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThuongHieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lblIdThuongHieu)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết sản phẩm"));

        jLabel34.setText("ID:");

        jLabel35.setText("Giá bán");

        jLabel36.setText("Giá Nhập");

        jLabel37.setText("Năm BH");

        jLabel38.setText("mô tả ");

        jLabel39.setText("Số lượng");

        txtIdChiTietSP.setBackground(new java.awt.Color(0, 255, 255));
        txtIdChiTietSP.setForeground(new java.awt.Color(255, 255, 255));

        txtID.setEditable(false);

        jLabel2.setText("Trạng thái");

        rdoCon.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoCon);
        rdoCon.setText("Còn");

        rdoKhongCon.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoKhongCon);
        rdoKhongCon.setText("Không còn");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel38))
                .addGap(6, 6, 6)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtGiaNhap)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel35))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamBH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(txtIdChiTietSP))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel2)
                        .addGap(28, 28, 28)
                        .addComponent(rdoCon)
                        .addGap(57, 57, 57)
                        .addComponent(rdoKhongCon)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(rdoCon)
                    .addComponent(rdoKhongCon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(txtIdChiTietSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37)))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách"));

        tblCTSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Tên Dòng SP", "Tên NSX", "Tên màu sắc", "Tên thương hiệu", "Dây đeo", "Xuất xứ", "May", "Giá bán", "Số lượng"
            }
        ));
        tblCTSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTSanPham);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnFirst.setBackground(new java.awt.Color(0, 102, 204));
        btnFirst.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst.setText("|<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(0, 102, 204));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("<<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        txtSoTrang.setText("SoTrang");

        btnNext.setBackground(new java.awt.Color(0, 102, 204));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(0, 102, 204));
        btnLast.setForeground(new java.awt.Color(255, 255, 255));
        btnLast.setText(">>|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btnFirst)
                .addGap(81, 81, 81)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSoTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(btnNext)
                .addGap(98, 98, 98)
                .addComponent(btnLast)
                .addGap(127, 127, 127))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoTrang)
                    .addComponent(btnBack)
                    .addComponent(btnFirst)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Quản lý sản phẩm");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThem.setBackground(new java.awt.Color(0, 102, 204));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 102, 204));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 102, 204));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnXuatFile.setBackground(new java.awt.Color(0, 102, 204));
        btnXuatFile.setForeground(new java.awt.Color(255, 255, 255));
        btnXuatFile.setText("Xuất");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        btnNhapFile.setBackground(new java.awt.Color(0, 102, 204));
        btnNhapFile.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapFile.setText("Nhập");
        btnNhapFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapFileActionPerformed(evt);
            }
        });

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnNhapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(327, 327, 327)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        new SanPhamForm().setVisible(true);
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        new MauSacForm().setVisible(true);
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuongHieuActionPerformed
        new ThuongHieuForm().setVisible(true);
    }//GEN-LAST:event_btnThuongHieuActionPerformed

    private void btnNsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNsxActionPerformed
        DetailNamSX detail = new DetailNamSX(this, true);
        detail.setVisible(true);
    }//GEN-LAST:event_btnNsxActionPerformed

    private void btnDongSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongSPActionPerformed
        DetailDongSP detail = new DetailDongSP(this, true);
        detail.setVisible(true);
    }//GEN-LAST:event_btnDongSPActionPerformed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".xlsx", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Export Excel");
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                int xn = JOptionPane.showConfirmDialog(this, "Xác nhận xuất file ?");
                if (xn == JOptionPane.YES_OPTION) {
                    ExportExcelChiTietSP.writeExcel(spSevice.getAll(), fileToSave.getAbsolutePath() + filter.getDescription());
                    JOptionPane.showMessageDialog(this, "Export thành công");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Export thất bại");
            }
            System.out.println("Save as file: " + fileToSave.getAbsolutePath() + filter.getDescription());
        }

    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (txtNamBH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Năm BH không được để trống");
            return;
        } else if (txtNamBH.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Năm BH phải là số");
            return;
        } else if (txtMota.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mô tả không được để trống");
            return;
        } else if (txtMota.getText().matches("[a-z A-Z]+") == false) {
            JOptionPane.showMessageDialog(this, "Mô tả phải là chữ");
            return;
        } else if (txtSoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn không được để trống");
            return;
        } else if (txtSoLuong.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn phải là số");
            return;
        } else if (txtGiaNhap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Gía nhập không được để trống");
            return;
        } else if (txtGiaNhap.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Gía nhập phải là số");
            return;
        } else if (txtGiaBan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Gía bán không được để trống");
            return;
        } else if (txtGiaBan.getText().matches("[0-9]+") == false) {
            JOptionPane.showMessageDialog(this, "Gía bán phải là số");
            return;
        } else {
            SanPham sp = new SanPham();
            sp.setId(Integer.valueOf(lblIdSanPham.getText()));
            MauSac ms = new MauSac();
            ms.setId(Integer.valueOf(lblIdMauSac.getText()));
            DongSp dSp = new DongSp();
            dSp.setId(Integer.valueOf(lblIdDongSp.getText()));
            NamSanXuat nam = new NamSanXuat();
            nam.setId(Integer.valueOf(lblIdNamSX.getText()));
            thuongHieu th = new thuongHieu();
            th.setId(Integer.valueOf(lblIdThuongHieu.getText()));
            ChiTietSanPham ctSp = new ChiTietSanPham();
            ctSp.setSanPham(sp);
            ctSp.setDongsp(dSp);
            ctSp.setMauSac(ms);
            ctSp.setNSX(nam);
            ctSp.setThuongHieu(th);
            ctSp.setGiaBan(BigDecimal.valueOf(Double.valueOf(txtGiaBan.getText())));
            ctSp.setGiaNhap(BigDecimal.valueOf(Double.valueOf(txtGiaNhap.getText())));
            ctSp.setMoTa(txtMota.getText());
            ctSp.setNamBH(Integer.valueOf(txtNamBH.getText()));
            ctSp.setSoLuongTon(Integer.valueOf(txtSoLuong.getText()));
            boolean trangThai = rdoCon.isSelected();
            int r = 0;
            if (trangThai == true) {
                r = 1;
            } else {
                r = 0;
            }
            sp.setTrangthai(r);
            JOptionPane.showMessageDialog(this, new SanPHamSeviceImpl().addChiTietSP(ctSp));
            listSPPage = spSevice.getAll2((soTrang * 5) - 5);
            showDataRow(listSPPage);
            updPanel();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        SanPhamCustom3 d = (SanPhamCustom3) cboSanPham.getSelectedItem();
        lblIdSanPham.setText(String.valueOf(d.toStringId()));
    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void cboNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNSXActionPerformed
        NamSXCustom d = (NamSXCustom) cboNSX.getSelectedItem();
        lblIdNamSX.setText(String.valueOf(d.toStringId()));
    }//GEN-LAST:event_cboNSXActionPerformed

    private void cboMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMauSacActionPerformed
        MauSacCustom d = (MauSacCustom) cboMauSac.getSelectedItem();
        lblIdMauSac.setText(String.valueOf(d.toStringId()));
    }//GEN-LAST:event_cboMauSacActionPerformed

    private void cboDongSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDongSPActionPerformed
        DongSPCustom d = (DongSPCustom) cboDongSP.getSelectedItem();
        lblIdDongSp.setText(String.valueOf(d.toStringId()));
    }//GEN-LAST:event_cboDongSPActionPerformed

    private void cboThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThuongHieuActionPerformed
        ThuongHieuCustomer d = (ThuongHieuCustomer) cboThuongHieu.getSelectedItem();
        lblIdThuongHieu.setText(String.valueOf(d.toStringId()));
    }//GEN-LAST:event_cboThuongHieuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        ChiTietSanPham ct = new ChiTietSanPham();
        boolean trangThai = rdoCon.isSelected();
        int r = 0;
        if (trangThai == true) {
            r = 1;
        } else {
            r = 0;
        }
        ct.setTrangThai(r);
        JOptionPane.showMessageDialog(this, spSevice.updateSPCT(ct, Integer.parseInt(txtIdChiTietSP.getText()), Integer.parseInt(lblIdSanPham.getText()),
                Integer.parseInt(lblIdDongSp.getText()), Integer.parseInt(lblIdMauSac.getText()), Integer.parseInt(lblIdNamSX.getText()), BigDecimal.valueOf(Double.valueOf(txtGiaBan.getText())),
                BigDecimal.valueOf(Double.valueOf(txtGiaNhap.getText())), Integer.valueOf(txtNamBH.getText()), Integer.valueOf(txtSoLuong.getText()), r));
        listSPPage = spSevice.getAll2((soTrang * 10) - 10);
        showDataRow(listSPPage);
        updPanel();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblCTSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSanPhamMouseClicked
        int row = tblCTSanPham.getSelectedRow();
        listSPPage = spSevice.getAll2((soTrang*10)-10);
        showDataRow(listSPPage);
        mose(row);
    }//GEN-LAST:event_tblCTSanPhamMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtID.setText(null);
        txtGiaBan.setText(null);
        txtGiaNhap.setText(null);
        txtNamBH.setText(null);
        txtMota.setText(null);
        txtSoLuong.setText(null);
        cboSanPham.setSelectedIndex(0);
        cboDongSP.setSelectedIndex(0);
        cboMauSac.setSelectedIndex(0);
        cboNSX.setSelectedIndex(0);
        cboThuongHieu.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnNhapFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapFileActionPerformed
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJtableImport = null;
        String defaultCurrentDirectoryPath = "D:\\test";
        JFileChooser exFileChooser = new JFileChooser(defaultCurrentDirectoryPath);

        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlss", "xlsm");
        exFileChooser.setFileFilter(fnef);
        exFileChooser.setDialogTitle("Select Excel File");
        int exChooser = exFileChooser.showOpenDialog(null);

        if (exChooser == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = exFileChooser.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);

                excelJtableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJtableImport.getSheetAt(0);

                for (int row = 0; row < excelSheet.getLastRowNum(); row++) {
                    XSSFRow excelRow = excelSheet.getRow(row);

                    XSSFCell excelId = excelRow.getCell(0);
                    XSSFCell excelMaSp = excelRow.getCell(1);
                    XSSFCell excelTenSP = excelRow.getCell(2);
                    XSSFCell excelGiaBan = excelRow.getCell(3);
                    XSSFCell excelMauSac = excelRow.getCell(4);
                    XSSFCell excelThuongHieu = excelRow.getCell(5);
                    XSSFCell excelDayDeo = excelRow.getCell(6);
                    XSSFCell excelKinh = excelRow.getCell(7);
                    XSSFCell excelXuatXu = excelRow.getCell(8);
                    XSSFCell excelSoLuong = excelRow.getCell(9);
                    XSSFCell excelTrangThai = excelRow.getCell(10);

                    dtmDanhSachSP.addRow(new Object[]{excelId, excelMaSp, excelTenSP, excelGiaBan, excelMauSac,
                        excelThuongHieu, excelDayDeo, excelKinh, excelXuatXu, excelSoLuong, excelTrangThai});
                }

                JOptionPane.showMessageDialog(this, "Nhập thành công!");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } finally {
                try {
                    if (excelFIS != null) {
                        excelFIS.close();
                    }
                    if (excelBIS != null) {
                        excelBIS.close();
                    }
                    if (excelJtableImport != null) {
                        excelJtableImport.close();
                    }
                } catch (IOException iOException) {
                    JOptionPane.showMessageDialog(null, iOException.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnNhapFileActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        setTruePhanTrang();
        int tongSoTrang = listSP.size() / 10;
        if (listSP.size() % 10 != 0) {
            tongSoTrang += 1;
        }
        if (soTrang == tongSoTrang) {
            showDataRow(listSPPage);

        } else {
            listSPPage.removeAll(listSPPage);
            soTrang += 1;
            if (soTrang == tongSoTrang) {
                btnNext.setEnabled(false);
                btnLast.setEnabled(false);
            }
            int heSo = (soTrang * 10) - 10;
            listSPPage = spSevice.getAll2(heSo);
            showDataRow(listSPPage);
            txtSoTrang.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        setTruePhanTrang();
        listSPPage.removeAll(listSPPage);
        soTrang = 1;
        btnBack.setEnabled(false);
        btnFirst.setEnabled(false);
        int heSo = (soTrang * 10) - 10;
        listSPPage = spSevice.getAll2(heSo);
        showDataRow(listSPPage);
        updPanel();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        setTruePhanTrang();
        int tongSoTrang = listSP.size() / 10;
        if (listSP.size() % 10 != 0) {
            tongSoTrang += 1;
        }
        if (soTrang == 1) {
            showDataRow(listSPPage);
        } else {
            listSPPage.removeAll(listSPPage);
            soTrang -= 1;
            if (soTrang == 1) {
                btnFirst.setEnabled(false);
                btnBack.setEnabled(false);
            }
            int heSo = (soTrang * 10) - 10;
            listSPPage = spSevice.getAll2(heSo);
            showDataRow(listSPPage);
            txtSoTrang.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        setTruePhanTrang();
        int tongSoTrang = listSP.size() / 10;
        if (listSP.size() % 10 != 0) {
            tongSoTrang += 1;
        }
        btnNext.setEnabled(false);
        btnLast.setEnabled(false);
        listSPPage.removeAll(listSPPage);
        soTrang = tongSoTrang;
        int heSo = (soTrang * 10) - 10;
        listSPPage = spSevice.getAll2(heSo);
        showDataRow(listSPPage);
        txtSoTrang.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        listSPPage = spSevice.search(txtTimKiem.getText());
        showDataRow(listSPPage);
        updPanel();
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void panelBorder5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder5MouseClicked
        //        card.show(pnlCand1, "pnlCard2");
    }//GEN-LAST:event_panelBorder5MouseClicked

    private void panelBorder6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder6MouseClicked
        HoaDonForm view = new HoaDonForm();
        this.dispose();
        view.setVisible(true);
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

    private void updPanel() {
        int tongSoTrang = listSP.size() / 10;
        if (listSP.size() % 10 != 0) {
            tongSoTrang += 1;
        }
        txtSoTrang.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
    }

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
            java.util.logging.Logger.getLogger(ChiTietSPForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietSPForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietSPForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietSPForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChiTietSPForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDongSP;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNhapFile;
    private javax.swing.JButton btnNsx;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThuongHieu;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboDongSP;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboNSX;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIdDongSp;
    private javax.swing.JLabel lblIdMauSac;
    private javax.swing.JLabel lblIdNamSX;
    private javax.swing.JLabel lblIdSanPham;
    private javax.swing.JLabel lblIdThuongHieu;
    private view.Swing.PanelBorder panelBorder1;
    private view.Swing.PanelBorder panelBorder11;
    private view.Swing.PanelBorder panelBorder12;
    private view.Swing.PanelBorder panelBorder13;
    private view.Swing.PanelBorder panelBorder5;
    private view.Swing.PanelBorder panelBorder6;
    private view.Swing.PanelBorder panelBorder7;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoKhongCon;
    private javax.swing.JTable tblCTSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtID;
    private javax.swing.JLabel txtIdChiTietSP;
    private javax.swing.JTextField txtMota;
    private javax.swing.JTextField txtNamBH;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JLabel txtSoTrang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
