/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author Nguyen
 */
public class OTPGenerator {

    private final SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

    public OTPGenerator() throws NoSuchAlgorithmException {
    }

    public String generate(int maxLength) {
        final StringBuilder otp = new StringBuilder(maxLength);
        for (int i = 0; i < maxLength; i++) {
            otp.append(secureRandom.nextInt(9));
        }
        return otp.toString();
    }
}
