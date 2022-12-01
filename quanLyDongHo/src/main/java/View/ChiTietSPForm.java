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
//    private 

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
//        cbbChiTietSP(spSevice.getAllSanPham());
        cbbThuongHieu(spSevice.getAllThuongHieu());

        listSP = spSevice.getAll();
        int heSo = (soTrang * 5) - 5;
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

//    private void cbbChiTietSP(List<SanPhamCustom3> list) {
//        cboSanPham.setModel(dcbmChiTietSP);
//        for (SanPhamCustom3 o : list) {
//            dcbmChiTietSP.addElement(o);
//        }
//    }
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

    private void setTruePhanTrang() {
        btnFirst.setEnabled(true);
        btnNext.setEnabled(true);
        btnBack.setEnabled(true);
        btnLast.setEnabled(true);
    }

    private void mose(int index) {
        ChiTietSPCustom sp = listSPPage.get(index);
//        List<ChiTietSPCustom> listload = new SanPHamSeviceImpl().getAll();
//        ChiTietSPCustom sp = listload.get(index);
//        cboSanPham.setSelectedItem(sp.getTenSP());
//        MauSacCustom ms = listMau.get(index);
//        DongSPCustom dp = listDongSp.get(index);
//        NamSXCustom ns = listNamSanXuats.get(index);
        txtID.setText(sp.getId().toString());
        txtGiaBan.setText(String.valueOf(sp.getGiaBan()));
        txtGiaNhap.setText(String.valueOf(sp.getGiaNhap()));
        txtMota.setText(sp.getMoTa());
        txtNamBH.setText(String.valueOf(sp.getNBH()));
        txtSoLuong.setText(String.valueOf(sp.getSoLuongTon()));
        cboSanPham.setSelectedIndex(index);
        cboNSX.setSelectedIndex(index);
        cboMauSac.setSelectedIndex(index);
        cboThuongHieu.setSelectedIndex(index);
        cboDongSP.setSelectedIndex(index);
//        List<MauSacCustom> listloadms = new SanPHamSeviceImpl().getAllMauSac();
//        MauSacCustom ms = listloadms.get(index);
//        cboMauSac.setSelectedItem(ms.getTen());
//        List<DongSPCustom> listloaddsp = new DongSPServicelmpl().getListDongSP();
//        DongSPCustom dsp = listloaddsp.get(index);
//        cboDongSP.setSelectedItem(dsp.getTen());
//        List<NamSXCustom> listloadnsx = new NamSanXuatServicelmpl().getListNamSanXuat();
//        NamSXCustom nsx = listloadnsx.get(index);
//        cboNSX.setSelectedItem(nsx.getTen());
//        List<ThuongHieuCustomer> listloadth = new SanPHamSeviceImpl().getAllThuongHieu();
//        ThuongHieuCustomer th = listloadth.get(index);
//        cboThuongHieu.setSelectedItem(th.getTen());
//        txtIdChiTietSP.setText(sp.getId().toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        txtIdChiTietSP = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTSanPham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        panelBorder5 = new view.Swing.PanelBorder();
        jLabel29 = new javax.swing.JLabel();
        panelBorder6 = new view.Swing.PanelBorder();
        jLabel30 = new javax.swing.JLabel();
        panelBorder11 = new view.Swing.PanelBorder();
        jLabel45 = new javax.swing.JLabel();
        panelBorder1 = new view.Swing.PanelBorder();
        jLabel46 = new javax.swing.JLabel();
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

        lbIdMauSac.setForeground(new java.awt.Color(255, 255, 255));
        lbIdMauSac.setText("jLabel2");

        lbIdDongSp.setForeground(new java.awt.Color(255, 255, 255));
        lbIdDongSp.setText("jLabel2");

        lbIdNSX.setForeground(new java.awt.Color(255, 255, 255));
        lbIdNSX.setText("jLabel3");

        lbIdSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lbIdSanPham.setText("jLabel4");

        lbIdThuongHieu.setForeground(new java.awt.Color(255, 255, 255));
        lbIdThuongHieu.setText("jLabel5");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbIdSanPham))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addComponent(lbIdNSX)
                                    .addGap(175, 175, 175))
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addComponent(jLabel43)
                                            .addGap(18, 18, 18)
                                            .addComponent(lbIdMauSac))
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addComponent(jLabel44)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lbIdThuongHieu))
                                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                                        .addGap(14, 14, 14)
                                                        .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbIdDongSp))
                                    .addComponent(jLabel41))
                                .addGap(175, 175, 175)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSanPham)
                            .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(lbIdSanPham)
                .addGap(4, 4, 4)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(cboNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNsx))
                .addGap(10, 10, 10)
                .addComponent(lbIdNSX)
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(btnDongSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbIdDongSp, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMauSac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbIdMauSac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThuongHieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbIdThuongHieu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết sản phẩm"));

        jLabel34.setText("ID:");

        txtID.setEditable(false);

        jLabel35.setText("Giá bán");

        jLabel36.setText("Giá Nhập");

        jLabel37.setText("Năm BH");

        jLabel38.setText("mô tả ");

        jLabel39.setText("Số lượng");

        txtIdChiTietSP.setBackground(new java.awt.Color(0, 255, 255));
        txtIdChiTietSP.setForeground(new java.awt.Color(255, 255, 255));
        txtIdChiTietSP.setText("jLabel2");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(10, 10, 10)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(txtIdChiTietSP)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtIdChiTietSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        panelBorder5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder5MouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Danh sách Sản Phẩm");

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addContainerGap())
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
        jLabel30.setText("Danh sách hoa don");

        javax.swing.GroupLayout panelBorder6Layout = new javax.swing.GroupLayout(panelBorder6);
        panelBorder6.setLayout(panelBorder6Layout);
        panelBorder6Layout.setHorizontalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(23, 23, 23))
        );
        panelBorder6Layout.setVerticalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder6Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(24, 24, 24))
        );

        panelBorder11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder11MouseClicked(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Danh sách Nhân viên");

        javax.swing.GroupLayout panelBorder11Layout = new javax.swing.GroupLayout(panelBorder11);
        panelBorder11.setLayout(panelBorder11Layout);
        panelBorder11Layout.setHorizontalGroup(
            panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder11Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addGap(23, 23, 23))
        );
        panelBorder11Layout.setVerticalGroup(
            panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder11Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addGap(24, 24, 24))
        );

        panelBorder1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder1MouseClicked(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Dang xuat");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel46)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel46)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panelBorder11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(panelBorder6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(panelBorder11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        txtSoTrang.setText("jLabel2");

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
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhapFile, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jLabel1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelBorder5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder5MouseClicked
//        card.show(pnlCand1, "pnlCard2");
    }//GEN-LAST:event_panelBorder5MouseClicked

    private void panelBorder6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder6MouseClicked
//        card.show(pnlCand1, "pnlCard3");
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
        showDataRow(listSPPage);
        updPanel();
    }//GEN-LAST:event_btnThemActionPerformed

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        SanPhamCustom3 d = (SanPhamCustom3) cboSanPham.getSelectedItem();
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

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        ChiTietSanPham ct = new ChiTietSanPham();
        JOptionPane.showMessageDialog(this, spSevice.updateSPCT(ct, Integer.parseInt(txtIdChiTietSP.getText()), Integer.parseInt(lbIdSanPham.getText()),
                Integer.parseInt(lbIdDongSp.getText()), Integer.parseInt(lbIdMauSac.getText()), Integer.parseInt(lbIdNSX.getText()), BigDecimal.valueOf(Double.valueOf(txtGiaBan.getText())),
                BigDecimal.valueOf(Double.valueOf(txtGiaNhap.getText())), Integer.valueOf(txtNamBH.getText()), Integer.valueOf(txtSoLuong.getText())));
        showDataRow(listSPPage);
        updPanel();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblCTSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSanPhamMouseClicked
        int row = tblCTSanPham.getSelectedRow();
//        fillData(index);
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
    private javax.swing.JComboBox<String> cboDongSP;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboNSX;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbIdDongSp;
    private javax.swing.JLabel lbIdMauSac;
    private javax.swing.JLabel lbIdNSX;
    private javax.swing.JLabel lbIdSanPham;
    private javax.swing.JLabel lbIdThuongHieu;
    private view.Swing.PanelBorder panelBorder1;
    private view.Swing.PanelBorder panelBorder11;
    private view.Swing.PanelBorder panelBorder5;
    private view.Swing.PanelBorder panelBorder6;
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
