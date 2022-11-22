/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import model.nhanvien.NhanVien;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernatUtil;

/**
 *
 * @author FPT
 */
public class LoginRepository {
     public boolean getLogin(String useName, String pass) {
        Transaction tran = null;
        NhanVien user = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            tran = session.beginTransaction();
            user = (NhanVien) session.createQuery("from NhanVien where ma =:fn").setParameter("fn", useName).uniqueResult();
            if (user != null && user.getMatKhau().equals(pass)) {
                return true;
            }
            tran.commit();
        } catch (HibernateException e) {
            if (tran != null) {
                tran.rollback();
            }

            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        LoginRepository login = new LoginRepository();
        boolean b = login.getLogin("12A","1234");
        if(b){
            System.out.println("ok");
        }else {
            System.out.println("thoat");
        }
        
    }
}
