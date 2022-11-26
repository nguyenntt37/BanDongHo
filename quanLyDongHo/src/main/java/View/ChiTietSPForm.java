/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Util.ExportExcelChiTietSP;
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
import viewmodel.SanPhamCustom;
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
    List<SanPhamCustom> listSPc = new ArrayList<>();
    List<MauSacCustom> listMau = new ArrayList<>();
    List<NamSXCustom> listNamSanXuats = new ArrayList<>();
    List<DongSPCustom> listDongSp = new ArrayList<>();
    private SanPHamSeviceImpl spSevice = new SanPHamSeviceImpl();
    private NamSanXuatServicelmpl nsxs = new NamSanXuatServicelmpl();
    private DongSPServicelmpl dsps = new DongSPServicelmpl();
    private int soTrang = 1;
//    private 

    /**
     * Creates new form ChiTietSPForm
     */
    public ChiTietSPForm() {
        initComponents();
        setLocationRelativeTo(null);
        tblCTSanPham.setModel(dtmDanhSachSP);
        String[] hedear = {"ID", "Mã SP", "Tên Sp", "Giá bán", "Màu sắc", "Thương hiệu", "Dây đeo", "Kính", "Xuất xứ", "Ngày tạo", "Số Lượng tồn", "trạng thái"};
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
        int heSo = (soTrang * 5) - 5;
        listSPPage = spSevice.getAll2(heSo);
        showDataRow(listSPPage);
        updPanel();
    }

    private void showDataRow(List<ChiTietSPCustom> listSP) {
        dtmDanhSachSP.setRowCount(0);
        for (ChiTietSPCustom p : listSP) {
            dtmDanhSachSP.addRow(p.todataRowDS());
        }
    }

    private void cbbChiTietSP(List<SanPhamCustom> list) {
        cboSanPham.setModel(dcbmChiTietSP);
        for (SanPhamCustom o : list) {
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

    private void cbbSanPham(List<SanPhamCustom> listSPc) {
        cboSanPham.setModel(dcbmSanPham);
        for (SanPhamCustom o : listSPc) {
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

    public void fillData(int index) {
        ChiTietSPCustom ctsp = listSPPage.get(index);
        cboSanPham.setSelectedIndex(index);
        cboNSX.setSelectedIndex(index);
        cboMauSac.setSelectedIndex(index);
        cboThuongHieu.setSelectedIndex(index);
        cboDongSP.setSelectedIndex(index);
        txtIdChiTietSP.setText(ctsp.getId().toString());
        txtID.setText(ctsp.getId() + "");
        txtNamBH.setText(ctsp.getNSX() + "");
        txtMota.setText(ctsp.getMoTa());
        txtSoLuong.setText(ctsp.getSoLuongTon() + "");
        txtGiaNhap.setText(ctsp.getGiaNhap() + "");
        txtGiaBan.setText(ctsp.getGiaBan() + "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
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
        lbIdMauSac = new javax.swing.JLabel();
        lbIdDongSp = new javax.swing.JLabel();
        lbIdNSX = new javax.swing.JLabel();
        lbIdSanPham = new javax.swing.JLabel();
        lbIdThuongHieu = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        txtNamBH = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtMota = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTSanPham = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        txtXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        btnNhapFile = new javax.swing.JButton();
        txtIdChiTietSP = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtSoTrang = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Quản lý sản phẩm");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel40.setText("Sản phẩm");

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

        lbIdMauSac.setText("jLabel2");

        lbIdDongSp.setText("jLabel2");

        lbIdNSX.setText("jLabel3");

        lbIdSanPham.setText("jLabel4");

        lbIdThuongHieu.setText("jLabel5");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(18, 18, 18)
                                .addComponent(cboSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(49, 49, 49)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbIdSanPham)
                                    .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(btnNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbIdDongSp)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbIdNSX)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(lbIdMauSac))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbIdThuongHieu)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(btnSanPham)
                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbIdSanPham)
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNsx))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbIdNSX)
                .addGap(4, 4, 4)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDongSP))
                .addGap(9, 9, 9)
                .addComponent(lbIdDongSp)
                .addGap(6, 6, 6)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMauSac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbIdMauSac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThuongHieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIdThuongHieu)
                .addGap(29, 29, 29))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết sản phẩm"));

        jLabel34.setText("ID:");

        txtID.setEditable(false);

        jLabel35.setText("Giá bán");

        jLabel36.setText("Giá Nhập");

        jLabel37.setText("Năm BH");

        jLabel38.setText("mô tả ");

        jLabel39.setText("Số lượng");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addGroup(jPanel3Layout.createSequentialGroup()
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        txtXoa.setText("Xóa");
        txtXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtXoaActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnXuatFile.setText("Xuất");
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        btnNhapFile.setText("Nhập");
        btnNhapFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(btnNhapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNhapFile, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtIdChiTietSP.setText("jLabel2");

        jPanel1.setBackground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnFirst.setText("|<<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnBack.setText("<<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        txtSoTrang.setText("jLabel2");

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

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
                .addGap(72, 72, 72)
                .addComponent(btnFirst)
                .addGap(88, 88, 88)
                .addComponent(btnBack)
                .addGap(71, 71, 71)
                .addComponent(txtSoTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addComponent(btnNext)
                .addGap(75, 75, 75)
                .addComponent(btnLast)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst)
                    .addComponent(btnBack)
                    .addComponent(txtSoTrang)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(txtIdChiTietSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(255, 255, 255))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtIdChiTietSP))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelBorder5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder5MouseClicked
        //card.show(pnlCand1, "pnlCard2");
    }//GEN-LAST:event_panelBorder5MouseClicked

    private void panelBorder6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder6MouseClicked
        //card.show(pnlCand1, "pnlCard3");
    }//GEN-LAST:event_panelBorder6MouseClicked

    private void panelBorder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder1MouseClicked
//        ViewLoginn view = new ViewLoginn();
//        this.dispose();
//        view.setVisible(true);
    }//GEN-LAST:event_panelBorder1MouseClicked

    private void panelBorder11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder11MouseClicked
//        card.show(pnlCand1, "pnlCard7");
    }//GEN-LAST:event_panelBorder11MouseClicked

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
        SanPham sp = new SanPham();
        sp.setId(Integer.valueOf(lbIdSanPham.getText()));
        MauSac ms = new MauSac();
        ms.setId(Integer.valueOf(lbIdMauSac.getText()));
        DongSp dSp = new DongSp();
        dSp.setId(Integer.valueOf(lbIdDongSp.getText()));
        NamSanXuat nam = new NamSanXuat();
        nam.setId(Integer.valueOf(lbIdNSX.getText()));
        thuongHieu th = new thuongHieu();
        th.setId(Integer.valueOf(lbIdThuongHieu.getText()));
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
        JOptionPane.showMessageDialog(this, new SanPHamSeviceImpl().addChiTietSP(ctSp));
        updPanel();
        showDataRow(listSPPage);
    }//GEN-LAST:event_btnThemActionPerformed

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        SanPhamCustom d = (SanPhamCustom) cboSanPham.getSelectedItem();
        lbIdSanPham.setText(String.valueOf(d.toStringId()));
    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void cboNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNSXActionPerformed
        NamSXCustom d = (NamSXCustom) cboNSX.getSelectedItem();
        lbIdNSX.setText(String.valueOf(d.toStringId()));
    }//GEN-LAST:event_cboNSXActionPerformed

    private void cboMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMauSacActionPerformed
        MauSacCustom d = (MauSacCustom) cboMauSac.getSelectedItem();
        lbIdMauSac.setText(String.valueOf(d.toStringId()));
    }//GEN-LAST:event_cboMauSacActionPerformed

    private void cboDongSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDongSPActionPerformed
        DongSPCustom d = (DongSPCustom) cboDongSP.getSelectedItem();
        lbIdDongSp.setText(String.valueOf(d.toStringId()));
    }//GEN-LAST:event_cboDongSPActionPerformed

    private void cboThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThuongHieuActionPerformed
        ThuongHieuCustomer d = (ThuongHieuCustomer) cboThuongHieu.getSelectedItem();
        lbIdThuongHieu.setText(String.valueOf(d.toStringId()));
    }//GEN-LAST:event_cboThuongHieuActionPerformed

    private void txtXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtXoaActionPerformed
        int index = tblCTSanPham.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Yêu cầu chọn thông tin trong bảng");
            return;
        } else {
            int choose = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa hay không?", "Thoát", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choose == JOptionPane.YES_OPTION) {
                ChiTietSanPham sp = new ChiTietSanPham();
                JOptionPane.showMessageDialog(this, new SanPHamSeviceImpl().deleteChitietSP(sp, Integer.parseInt(txtID.getText())));
                showDataRow(listSP);
            }
        }
    }//GEN-LAST:event_txtXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        ChiTietSanPham ct = new ChiTietSanPham();
        JOptionPane.showMessageDialog(this, spSevice.updateSPCT(ct, Integer.parseInt(txtIdChiTietSP.getText()), Integer.parseInt(lbIdSanPham.getText()),
                Integer.parseInt(lbIdDongSp.getText()), Integer.parseInt(lbIdMauSac.getText()), Integer.parseInt(lbIdNSX.getText()), BigDecimal.valueOf(Double.valueOf(txtGiaBan.getText())),
                BigDecimal.valueOf(Double.valueOf(txtGiaNhap.getText())), Integer.valueOf(txtNamBH.getText()), Integer.valueOf(txtSoLuong.getText())));
        updPanel();
        showDataRow(listSPPage);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblCTSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSanPhamMouseClicked
        int index = tblCTSanPham.getSelectedRow();
        fillData(index);
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
        int tongSoTrang = listSP.size() / 10;
        if (listSP.size() % 10 != 0) {
            tongSoTrang += 1;
        }
        if (soTrang == tongSoTrang) {
            showDataRow(listSPPage);

        } else {
            listSPPage.removeAll(listSPPage);
            soTrang += 1;
            int heSo = (soTrang * 10) - 10;
            listSPPage = spSevice.getAll2(heSo);
            showDataRow(listSPPage);
            txtSoTrang.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
        }

    }//GEN-LAST:event_btnNextActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        listSPPage.removeAll(listSPPage);
        soTrang = 1;
        int heSo = (soTrang * 10) - 10;
        listSPPage = spSevice.getAll2(heSo);
        showDataRow(listSPPage);
        updPanel();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        int tongSoTrang = listSP.size() / 10;
        if (listSP.size() % 10 != 0) {
            tongSoTrang += 1;
        }
        if (soTrang == 1) {
            showDataRow(listSPPage);

        } else {
            listSPPage.removeAll(listSPPage);
            soTrang -= 1;
            int heSo = (soTrang * 10) - 10;
            listSPPage = spSevice.getAll2(heSo);
            showDataRow(listSPPage);
            txtSoTrang.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        int tongSoTrang = listSP.size() / 10;
        if (listSP.size() % 10 != 0) {
            tongSoTrang += 1;
        }
        listSPPage.removeAll(listSPPage);
        soTrang = tongSoTrang;
        int heSo = (soTrang * 10) - 10;
        listSPPage = spSevice.getAll2(heSo);
        showDataRow(listSPPage);
        txtSoTrang.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
    }//GEN-LAST:event_btnLastActionPerformed

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
    private javax.swing.JComboBox<String> cboDongSP;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboNSX;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbIdDongSp;
    private javax.swing.JLabel lbIdMauSac;
    private javax.swing.JLabel lbIdNSX;
    private javax.swing.JLabel lbIdSanPham;
    private javax.swing.JLabel lbIdThuongHieu;
    private javax.swing.JTable tblCTSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtID;
    private javax.swing.JLabel txtIdChiTietSP;
    private javax.swing.JTextField txtMota;
    private javax.swing.JTextField txtNamBH;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JLabel txtSoTrang;
    private javax.swing.JButton txtXoa;
    // End of variables declaration//GEN-END:variables
}
