package util;

//import com.mycompany.assignment.model.Grade;
//import com.mycompany.assignment.model.Students;
//import com.mycompany.assignment.model.Users;
import model.giohang.GioHang;
import model.giohang.GioHangChiTiet;
import model.KhachHang;
import model.hoadon.HoaDon;
import model.hoadon.hoaDonChiTiet;
import model.nhanvien.ChucVu;
import model.nhanvien.CuaHang;
import model.nhanvien.NhanVien;
import model.sanpham.ChiTietSanPham;
import model.sanpham.DongSp;
import model.sanpham.MauSac;
import model.sanpham.NamSanXuat;
import model.sanpham.SanPham;

import java.util.Properties;
import model.sanpham.thuongHieu;
import model.khuyenmai.chiTietSPKhuyenMai;
import model.khuyenmai.hoaDonKhuyenMai;
import model.khuyenmai.khuyenMai;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernatUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=demoDuAn2;encrypt=true;trustServerCertificate=true;");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "111203");
        properties.put(Environment.SHOW_SQL, "true");
//        gen DB tự động
//        properties.put(Environment.HBM2DDL_AUTO, "create");
        //gen String id tự động
//        @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
//        @GeneratedValue(generator = "generator")
        conf.setProperties(properties);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(CuaHang.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(GioHang.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(DongSp.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(thuongHieu.class);
        conf.addAnnotatedClass(NamSanXuat.class);
        conf.addAnnotatedClass(khuyenMai.class);

        conf.addAnnotatedClass(ChiTietSanPham.class);
        conf.addAnnotatedClass(hoaDonChiTiet.class);
        conf.addAnnotatedClass(GioHangChiTiet.class);

        conf.addAnnotatedClass(hoaDonKhuyenMai.class);
        conf.addAnnotatedClass(chiTietSPKhuyenMai.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }

}
