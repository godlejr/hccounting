package hcc.accouting.haccounting.common.utils;


import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256Util {
    private String iv;
    private Key keySpec;


    public AES256Util() throws UnsupportedEncodingException {
        this.iv = "4gt6gb565i9568k7y46ju84wef5j7mub".substring(0, 16);

        byte[] keyBytes = new byte[16];
        byte[] b = "4gt6gb565i9568k7y46ju84wef5j7mub".getBytes("UTF-8");

        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        this.keySpec = keySpec;
    }

    /**
     * AES256 으로 암호화 한다.
     *
     * @param str 암호화할 문자열
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    public String encrypt(String str)
            throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
        String enStr = null;
        if (str != null) {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
            byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
            enStr = new String(Base64.encodeToString(encrypted, Base64.DEFAULT));
        }
        return enStr;
    }

    /**
     * AES256으로 암호화된 txt 를 복호화한다.
     *
     * @param str 복호화할 문자열
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    public String decrypt(String str)
            throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
        String deStr = null;
        if (str != null) {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
            byte[] byteStr = Base64.decode(str.getBytes(), Base64.DEFAULT);
            deStr = new String(c.doFinal(byteStr), "UTF-8");
        }
        return deStr;
    }
}