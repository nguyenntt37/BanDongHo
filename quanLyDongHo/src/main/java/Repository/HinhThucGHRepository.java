/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import org.hibernate.Session;
import util.HibernatUtil;

/**
 *
 * @author Nguyen
 */
public class HinhThucGHRepository {
    public Object[] getAll() {
        Object[] o = null;
        try ( Session session = HibernatUtil.getFACTORY().openSession()) {
            o = session.createQuery("FROM HinhThucGH htgh").getResultList().toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
