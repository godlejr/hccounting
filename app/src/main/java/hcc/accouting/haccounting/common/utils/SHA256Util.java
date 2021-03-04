package hcc.accouting.haccounting.common.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class SHA256Util {

    public static String encryptWithSHA256(String message) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(message.getBytes(), 0, message.length());

        } catch (NoSuchAlgorithmException e) {
            log(e);
        }

        return String.format("%064x", new java.math.BigInteger(1, messageDigest.digest()));
    }

    private static void log(Throwable throwable) {
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        String className = stackTraceElements[0].getClassName();
        String methodName = stackTraceElements[0].getMethodName();
        int lineNumber = stackTraceElements[0].getLineNumber();
        String fileName = stackTraceElements[0].getFileName();

        Log.e("Exception 발생 :: ", throwable.getMessage());
        Log.e("Exception 정보 :: ", className + "." + methodName + " " + fileName + " " + lineNumber + " " + "번째 line");

    }
}
