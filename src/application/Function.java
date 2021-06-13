/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zouheir
 */
public class Function {
    
        
        public static String CredAndPass(String original) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        // Note here that I use a StringBuilder instead of a StringBuffer
        // as it is not meant to be shared so no need to use a thread safe
        // builder of String
        StringBuilder sb = new StringBuilder(32);
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        // Returns the result
        return sb.toString();
    }
    
    
}
