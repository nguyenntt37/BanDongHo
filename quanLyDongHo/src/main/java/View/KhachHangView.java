/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Repository.KhachHangRespository;
import View.login.gui.Login;
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
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import model.Validate;
import service.impl.KhachHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import viewmodel.KhachHangCutoms;
import viewmodel.NhanVienCustom;

/**
 *
 * @author admin
 */
public class KhachHangView extends javax.swing.JFrame implements Runnable, ThreadFactory {

    /**
     * Creates new form KhachHang
     */
    DefaultTableModel dtm = new DefaultTableModel();
    List<KhachHangCutoms> listKH = new ArrayList<>();
    KhachHangServiceImpl service = new KhachHangServiceImpl();
    DefaultComboBoxModel dcmb = new DefaultComboBoxModel();
    List<KhachHangCutoms> lisKHC = new ArrayList<>();
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private NhanVienServiceImpl serviceNV = new NhanVienServiceImpl();
    private List<NhanVienCustom> listNhanVien = new ArrayList<>();
    private int soTrang = 1;

    public KhachHangView() {
        initComponents();
        tableKH.setModel(dtm);
        String[] name = {"ID", "Mã", "Tên", "Tên Đệm", "Họ", "SĐT", "Quốc Gia", "Thành Phố", "Trạng thái"};
        dtm.setColumnIdentifiers(name);
        listKH = service.getAllKH();
        txtID.setEnabled(false);
        txtMa.setEnabled(false);
        loadCbb();
        initWebcam();

        lisKHC = service.getAllKH();
        int heSo = (soTrang * 5) - 5;
        listKH = service.getAll(heSo);
        showTB(listKH);
        if (listKH.size() == 0) {
            btnFirst2.setEnabled(false);
            btnNext2.setEnabled(false);
            btnBack2.setEnabled(false);
            btnLast2.setEnabled(false);
        }
        btnFirst2.setEnabled(false);
        btnBack2.setEnabled(false);
        updPanel();
        tenNV();
    }

    private void tenNV() {
        listNhanVien = serviceNV.get();
        String ma = Login.getCurrentLoginUsername();
        for (NhanVienCustom nv : listNhanVien) {
            if (nv.getMaNV().equalsIgnoreCase(ma)) {
                tenNV.setText(nv.getHoTen());
            }
        }
        jLabel16.setIcon(new ImageIcon("src/main/java/View/ttpthanh.png"));
    }

    public void showTB(List<KhachHangCutoms> kh) {
        dtm.setRowCount(0);
        for (KhachHangCutoms khr : kh) {
            dtm.addRow(khr.toDataRow());
        }
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel5.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 255, 250));

        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
                txtSearch.setText(result.getText().substring(0, 12));
                txtSearch.setCaretPosition(txtSearch.getText().length() - 1);
                //thay bằng txtSearch
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    public void loadCbb() {
        cbbTrangThai.setModel(dcmb);
        List<String> loadCbb = new ArrayList<>();
        loadCbb.add("All");
        loadCbb.add("Hoạt động");
        loadCbb.add("Không Hoạt động");
        dcmb.addAll(loadCbb);
        dcmb.setSelectedItem("All");

    }

    public void reset() {
        txtHo.setText("");
        txtID.setText("");
        txtMa.setText("");
        txtQuocGia.setText("");
        txtSDT.setText("");
        txtTen.setText("");
        txtTenDem.setText("");
        txtThanhPho.setText("");
    }

    public KhachHangCutoms getData() {
        String ma = txtMa.getText();
        String ten = txtTen.getText();
        String tenDem = txtTenDem.getText();
        String ho = txtHo.getText();
        String thanhPho = txtThanhPho.getText();
        String sdt = txtSDT.getText();
        String quocGia = txtQuocGia.getText();
        int id = Integer.parseInt(txtID.getText());
        if (!Validate.checkEmtry(ma, ho, ten, tenDem, sdt, thanhPho, quocGia)) {
            JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống");
            return null;
        }
        if (!Validate.checkName(ho, ten, tenDem, thanhPho, quocGia)) {
            JOptionPane.showMessageDialog(rootPane, "Tên hoặc tên đệm hoặc thành phố hoặc quốc gia không hợp lệ!");
            return null;
        }
        if (!sdt.matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$")) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không hợp lệ");
            return null;
        }
        KhachHangCutoms kh = new KhachHangCutoms(id, ma, ten, sdt, quocGia, tenDem, ho, thanhPho, WIDTH);
        return kh;
    }

    public void fillData(int index) {
        KhachHangCutoms kh = listKH.get(index);
        txtID.setText(String.valueOf(kh.getId()));
        txtMa.setText(kh.getMa());
        txtHo.setText(kh.getHo());
        txtQuocGia.setText(kh.getQuocGia());
        txtTen.setText(kh.getPass());
        txtSDT.setText(kh.getSdt());
        txtTenDem.setText(kh.getTenDem());
        txtThanhPho.setText(kh.getThanhPho());
        int trangThai = kh.getTrangthai();
        if (trangThai == 1) {
            rdoHoatdong.setSelected(true);
        } else if (trangThai == 2) {
            rdoKhongHD.setSelected(true);
        }
    }

    private void setTruePhanTrang() {
        btnFirst2.setEnabled(true);
        btnNext2.setEnabled(true);
        btnBack2.setEnabled(true);
        btnLast2.setEnabled(true);
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
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKH = new javax.swing.JTable();
        btnFirst2 = new javax.swing.JButton();
        btnBack2 = new javax.swing.JButton();
        btnNext2 = new javax.swing.JButton();
        btnLast2 = new javax.swing.JButton();
        txtSoTrang5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtHo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtThanhPho = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtQuocGia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTenDem = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rdoHoatdong = new javax.swing.JRadioButton();
        rdoKhongHD = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
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
        thongTinNhanVien = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        tenNV = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel7.setText("Nhập tên khách hàng cần tìm:");

        jLabel14.setText("Lọc trạng thái: ");

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel14)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        tableKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKH);

        btnFirst2.setBackground(new java.awt.Color(0, 102, 204));
        btnFirst2.setForeground(new java.awt.Color(255, 255, 255));
        btnFirst2.setText("|<<");
        btnFirst2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirst2ActionPerformed(evt);
            }
        });

        btnBack2.setBackground(new java.awt.Color(0, 102, 204));
        btnBack2.setForeground(new java.awt.Color(255, 255, 255));
        btnBack2.setText("<<");
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });

        btnNext2.setBackground(new java.awt.Color(0, 102, 204));
        btnNext2.setForeground(new java.awt.Color(255, 255, 255));
        btnNext2.setText(">>");
        btnNext2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext2ActionPerformed(evt);
            }
        });

        btnLast2.setBackground(new java.awt.Color(0, 102, 204));
        btnLast2.setForeground(new java.awt.Color(255, 255, 255));
        btnLast2.setText(">>|");
        btnLast2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLast2ActionPerformed(evt);
            }
        });

        txtSoTrang5.setText("SoTrang");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(btnFirst2)
                .addGap(53, 53, 53)
                .addComponent(btnBack2)
                .addGap(93, 93, 93)
                .addComponent(txtSoTrang5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(btnNext2)
                .addGap(59, 59, 59)
                .addComponent(btnLast2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFirst2)
                    .addComponent(btnBack2)
                    .addComponent(txtSoTrang5)
                    .addComponent(btnNext2)
                    .addComponent(btnLast2))
                .addGap(31, 31, 31))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("ID");

        jLabel2.setText("MÃ");

        jLabel9.setText("Họ");

        jLabel4.setText("Tên");

        jLabel10.setText("Thành Phố");

        jLabel5.setText("SĐT");

        jLabel6.setText("Quốc Gia");

        jLabel8.setText("Trạng thái");

        jLabel15.setText("Tên Đệm");

        buttonGroup1.add(rdoHoatdong);
        rdoHoatdong.setText("Hoạt động");

        buttonGroup1.add(rdoKhongHD);
        rdoKhongHD.setText("Không hoạt động");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnDelete.setBackground(new java.awt.Color(0, 102, 204));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("RESET");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 102, 204));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(0, 102, 204));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnExit.setBackground(new java.awt.Color(0, 102, 204));
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("CLOSE");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnUpdate)
                .addGap(18, 18, 18)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(250, 250, 250));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtID)
                                .addComponent(txtMa)
                                .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(rdoHoatdong, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoKhongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel5)
                                        .addGap(14, 14, 14))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtQuocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenDem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rdoHoatdong)
                            .addComponent(rdoKhongHD)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("QUẢN LÍ KHÁCH HÀNG");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Thiết lập thông tin Khách Hàng");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Tìm kiếm khách hàng");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Danh sách khách hàng");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel14.setBackground(new java.awt.Color(0, 102, 204));

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
                .addGap(69, 69, 69)
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel29)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelBorder6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder6MouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Hóa đơn");

        javax.swing.GroupLayout panelBorder6Layout = new javax.swing.GroupLayout(panelBorder6);
        panelBorder6.setLayout(panelBorder6Layout);
        panelBorder6Layout.setHorizontalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder6Layout.setVerticalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel30)
                .addContainerGap(25, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addGap(64, 64, 64))
        );
        panelBorder11Layout.setVerticalGroup(
            panelBorder11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel45)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panelBorder1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorder1MouseClicked(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Đăng xuất");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addGap(65, 65, 65))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel46)
                .addContainerGap(26, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addGap(69, 69, 69))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addGap(58, 58, 58))
        );
        panelBorder13Layout.setVerticalGroup(
            panelBorder13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder13Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addGap(24, 24, 24))
        );

        thongTinNhanVien.setBackground(new java.awt.Color(0, 102, 204));
        thongTinNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                thongTinNhanVienMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout thongTinNhanVienLayout = new javax.swing.GroupLayout(thongTinNhanVien);
        thongTinNhanVien.setLayout(thongTinNhanVienLayout);
        thongTinNhanVienLayout.setHorizontalGroup(
            thongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongTinNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );
        thongTinNhanVienLayout.setVerticalGroup(
            thongTinNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thongTinNhanVienLayout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tenNV.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tenNV.setForeground(new java.awt.Color(255, 255, 255));
        tenNV.setText("jLabel12");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBorder13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(thongTinNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(thongTinNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
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
                .addGap(0, 212, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 965, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        KhachHang kh = new KhachHang();
        boolean max = txtTen.getText().length() >= 51;
        if (max) {
            JOptionPane.showMessageDialog(rootPane, "Tên khách hàng tối đa 50 kí tự!");
            return;
        }
        if (!Validate.checkEmtry(txtHo.getText(), txtTen.getText(), txtTenDem.getText(), txtSDT.getText(), txtThanhPho.getText(), txtQuocGia.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống");
            return;
        } //        else if (!txtSDT.matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$")) {
        //            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không hợp lệ");
        //            return;
        //        }
        else if (!Validate.checkName(txtHo.getText(), txtTen.getText(), txtTenDem.getText(), txtThanhPho.getText(), txtQuocGia.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Tên hoặc tên đệm hoặc thành phố hoặc quốc gia không hợp lệ!");
            return;
        } else if (txtSDT.getText().matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$") == false) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số hoặc không hợp lệ!");
            return;
        }
        kh.setMa(txtMa.getText());
        kh.setHo(txtHo.getText());
        kh.setQuocGia(txtQuocGia.getText());
        kh.setSdt(txtSDT.getText());
        kh.setTen(txtTen.getText());
        kh.setTenDem(txtTenDem.getText());
        kh.setThanhPho(txtThanhPho.getText());
        boolean trangThai = rdoHoatdong.isSelected();
        int tt = 0;
        if (trangThai == true) {
            tt = 1;
        } else {
            tt = 2;
        }
        kh.setTrangthai(tt);
        JOptionPane.showMessageDialog(this, service.add(kh));
//        listKH = service.getAllKH();
//        showTB(listKH);
        listKH = service.getAll((soTrang * 5) - 5);
        showTB(listKH);
        updPanel();
        reset();

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void tableKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKHMouseClicked
        // TODO add your handling code here:
        int row = tableKH.getSelectedRow();
        listKH = service.getAll((soTrang * 5) - 5);
        showTB(listKH);

        fillData(row);
    }//GEN-LAST:event_tableKHMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        //      KhachHangCutoms khc = getData();
        boolean max = txtTen.getText().length() >= 51;
        if (max) {
            JOptionPane.showMessageDialog(rootPane, "Tên khách hàng tối đa 50 kí tự!");
            return;
        }
        if (!Validate.checkEmtry(txtHo.getText(), txtTen.getText(), txtTenDem.getText(), txtSDT.getText(), txtThanhPho.getText(), txtQuocGia.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống");
            return;
        } //        else if (!txtSDT.matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$")) {
        //            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không hợp lệ");
        //            return;
        //        }
        else if (!Validate.checkName(txtHo.getText(), txtTen.getText(), txtTenDem.getText(), txtThanhPho.getText(), txtQuocGia.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Tên hoặc tên đệm hoặc thành phố hoặc quốc gia không hợp lệ!");
            return;
        } else if (txtSDT.getText().matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$") == false) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số hoặc không hợp lệ!");
            return;
        }
        KhachHang kh = new KhachHang();
//        if (khc == null) {
//            return;
//        }
        //   String check = service.update(kh);
//        if (!check.equals("")) {
//            JOptionPane.showMessageDialog(rootPane, check);
//            return;
//        }
        kh.setMa(txtMa.getText());
        kh.setHo(txtHo.getText());
        kh.setQuocGia(txtQuocGia.getText());
        kh.setSdt(txtSDT.getText());
        kh.setTen(txtTen.getText());
        kh.setTenDem(txtTenDem.getText());
        kh.setThanhPho(txtThanhPho.getText());
        boolean trangThai = rdoHoatdong.isSelected();
        int tt = 0;
        if (trangThai == true) {
            tt = 1;
        } else {
            tt = 2;
        }
        kh.setTrangthai(tt);
        JOptionPane.showMessageDialog(this, service.update(kh, txtMa.getText(), tt, txtHo.getText(), txtQuocGia.getText(), txtSDT.getText(), txtTen.getText(), txtTenDem.getText(), txtThanhPho.getText(), Integer.valueOf(txtID.getText())));
//        JOptionPane.showMessageDialog(this, service.update(kh2, ma, ho, quocGia, sdt, ten, tenDem, thanhPho, id));
//        listKH = service.getAllKH();
//        showTB(listKH);
        listKH = service.getAll((soTrang * 5) - 5);
        showTB(listKH);
        updPanel();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
//        int index = tableKH.getSelectedRow();
//        KhachHangCutoms kh = listKH.get(index);
//        JOptionPane.showMessageDialog(rootPane, service.delete(kh.getId()));
//        listKH = service.getAllKH();
//        showTB(listKH);
        reset();

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        // TODO add your handling code here:
        if (cbbTrangThai.getSelectedIndex() == 0) {
            listKH = service.getAllKH();
            showTB(listKH);
        } else {
            List<KhachHangCutoms> listLoc = service.locTrangThai(cbbTrangThai.getSelectedIndex());
            showTB(listLoc);
        }


    }//GEN-LAST:event_cbbTrangThaiActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        listKH = service.search(txtSearch.getText());
        showTB(listKH);
        updPanel();

    }//GEN-LAST:event_txtSearchActionPerformed
    private void searhChing(String ten) {
        dtm.setRowCount(0);
        //  List<KhachHangCutoms> list = service.search(ten);
        for (KhachHangCutoms o : service.search(ten)) {
            dtm.addRow(o.toDataRow());
            if (ten.matches("\\d+")) {
                txtTen.setText(o.getPass());
                txtID.setText(o.getId() + "");
                txtHo.setText(o.getHo());
                txtSDT.setText(o.getSdt());
                txtTenDem.setText(o.getTenDem());
                txtThanhPho.setText(o.getThanhPho());
                txtMa.setText(o.getMa());
                txtQuocGia.setText(o.getQuocGia());
                int trangThai = o.getTrangthai();
                if (trangThai == 1) {
                    rdoHoatdong.setSelected(true);
                } else {
                    rdoKhongHD.setSelected(true);
                }

            } else {
                txtTen.setText(" ");
            }
            //  txtTen.setText(kh.getPass());
        }
    }
    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        // TODO add your handling code here:
        if (txtSearch.getText().trim().length() > 0) {
            searhChing(txtSearch.getText().trim());

        } else {
            txtTen.setText("");
            txtID.setText("");
            txtHo.setText("");
            txtSDT.setText("");
            txtTenDem.setText("");
            txtThanhPho.setText("");
            txtMa.setText("");
            txtQuocGia.setText("");
            showTB(listKH);
        }
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void panelBorder5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder5MouseClicked
        BanHangForm view = new BanHangForm();
        this.dispose();
        view.setVisible(true);
        webcam.close();
    }//GEN-LAST:event_panelBorder5MouseClicked

    private void panelBorder6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder6MouseClicked
        HoaDonForm view = new HoaDonForm();
        this.dispose();
        view.setVisible(true);
        webcam.close();
    }//GEN-LAST:event_panelBorder6MouseClicked

    private void panelBorder11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder11MouseClicked
        if (Login.getPermission()) {
            NhanVienForm2 view = new NhanVienForm2();
            this.dispose();
            view.setVisible(true);
            webcam.close();
        } else {
            JOptionPane.showMessageDialog(this, "Không có quyền truy cập!");
        }
    }//GEN-LAST:event_panelBorder11MouseClicked

    private void panelBorder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder1MouseClicked
        LoginForm view = new LoginForm();
        this.dispose();
        view.setVisible(true);
        webcam.close();
    }//GEN-LAST:event_panelBorder1MouseClicked

    private void panelBorder7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder7MouseClicked
        if (Login.getPermission()) {
            ChiTietSPForm view = new ChiTietSPForm();
            this.dispose();
            view.setVisible(true);
            webcam.close();
        } else {
            JOptionPane.showMessageDialog(this, "Không có quyền truy cập!");
        }
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

    private void thongTinNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_thongTinNhanVienMouseClicked
        ThongTinNhanVien2 view = new ThongTinNhanVien2(this, true);
        view.setVisible(true);
    }//GEN-LAST:event_thongTinNhanVienMouseClicked

    private void btnFirst2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirst2ActionPerformed

        setTruePhanTrang();
        int tongSoTrang = lisKHC.size() / 5;
        if (lisKHC.size() % 5 != 0) {
            tongSoTrang += 1;
        }
        listKH.removeAll(listKH);
        soTrang = 1;
        btnBack2.setEnabled(false);
        btnFirst2.setEnabled(false);
        int heSo = (soTrang * 10) - 10;
        listKH = service.getAll(heSo);
        showTB(listKH);
        updPanel();
        txtSoTrang5.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
    }//GEN-LAST:event_btnFirst2ActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        setTruePhanTrang();
        int tongSoTrang = lisKHC.size() / 5;
        if (lisKHC.size() % 5 != 0) {
            tongSoTrang += 1;
        }
        if (soTrang == 1) {
            showTB(listKH);
        } else {
            listKH.removeAll(listKH);
            soTrang -= 1;
            if (soTrang == 1) {
                btnFirst2.setEnabled(false);
                btnBack2.setEnabled(false);
            }
            int heSo = (soTrang * 5) - 5;
            listKH = service.getAll(heSo);
            showTB(listKH);
            txtSoTrang5.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
        }
    }//GEN-LAST:event_btnBack2ActionPerformed

    private void btnNext2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext2ActionPerformed
        setTruePhanTrang();
        int tongSoTrang = lisKHC.size() / 5;
        if (lisKHC.size() % 5 != 0) {
            tongSoTrang += 1;
        }
        if (soTrang == tongSoTrang) {
            showTB(listKH);

        } else {
            listKH.removeAll(listKH);
            soTrang += 1;
            if (soTrang == tongSoTrang) {
                btnNext2.setEnabled(false);
                btnLast2.setEnabled(false);
            }
            int heSo = (soTrang * 5) - 5;
            listKH = service.getAll(heSo);
            showTB(listKH);
            txtSoTrang5.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
        }
    }//GEN-LAST:event_btnNext2ActionPerformed

    private void btnLast2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLast2ActionPerformed
        setTruePhanTrang();
        int tongSoTrang = lisKHC.size() / 5;
        if (lisKHC.size() % 5 != 0) {
            tongSoTrang += 1;
        }
        btnNext2.setEnabled(false);
        btnLast2.setEnabled(false);
        listKH.removeAll(listKH);
        soTrang = tongSoTrang;
        int heSo = (soTrang * 5) - 5;
        listKH = service.getAll(heSo);
        showTB(listKH);
        txtSoTrang5.setText(String.valueOf(soTrang) + "/" + tongSoTrang);

    }//GEN-LAST:event_btnLast2ActionPerformed
    private void updPanel() {
        int tongSoTrang = lisKHC.size() / 5;
        if (lisKHC.size() % 5 != 0) {
            tongSoTrang += 1;
        }
        txtSoTrang5.setText(String.valueOf(soTrang) + "/" + tongSoTrang);
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
            java.util.logging.Logger.getLogger(KhachHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhachHangView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFirst2;
    private javax.swing.JButton btnLast2;
    private javax.swing.JButton btnNext2;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private view.Swing.PanelBorder panelBorder1;
    private view.Swing.PanelBorder panelBorder11;
    private view.Swing.PanelBorder panelBorder12;
    private view.Swing.PanelBorder panelBorder13;
    private view.Swing.PanelBorder panelBorder5;
    private view.Swing.PanelBorder panelBorder6;
    private view.Swing.PanelBorder panelBorder7;
    private javax.swing.JRadioButton rdoHoatdong;
    private javax.swing.JRadioButton rdoKhongHD;
    private javax.swing.JTable tableKH;
    private javax.swing.JLabel tenNV;
    private javax.swing.JPanel thongTinNhanVien;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtQuocGia;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JLabel txtSoTrang5;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDem;
    private javax.swing.JTextField txtThanhPho;
    // End of variables declaration//GEN-END:variables
}
