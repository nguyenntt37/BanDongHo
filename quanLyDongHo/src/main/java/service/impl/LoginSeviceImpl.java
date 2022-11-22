/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import service.LoginService;
import Repository.LoginRepository;

/**
 *
 * @author FPT
 */
public class LoginSeviceImpl implements LoginService {

    private LoginRepository login = new LoginRepository();

    @Override
    public boolean getLogin(String useName, String pass) {
        boolean get = login.getLogin(useName, pass);
        if (get) {
            return true;

        } else {
            return false;
        }
    }

}
