package com.sgu.tourism.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String getPwd(String pwd){
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");

            byte[] bs = instance.digest(pwd.getBytes());

            String str = "";

            for (byte b : bs) {
                int temp = b & 255;
                if (temp>=0 && temp <= 15){
                    str = str+"0"+ Integer.toHexString(temp);
                } else{
                    str = str+Integer.toHexString(temp);
                }
            }
            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

}
