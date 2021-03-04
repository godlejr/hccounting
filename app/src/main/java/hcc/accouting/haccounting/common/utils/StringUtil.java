package hcc.accouting.haccounting.common.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class StringUtil {

    public static String getWonMoneyFormatByPriceAndCurrCd(float price, String currCd) {

        if ("KRW".equals(currCd)) {
            int money = (int) price;

            String pattern = "###,###;-###,###";

            DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
            decimalFormat.applyPattern(pattern);

            String formattedStringPrice = decimalFormat.format(money);
            return formattedStringPrice;
        } else {
            return String.valueOf(price);
        }
    }

    public static String getWonMoneyFormatByIntegerPriceAndCurrCd(Integer price, String currCd) {

        if ("KRW".equals(currCd)) {
            int money =  price;

            String pattern = "###,###;-###,###";

            DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
            decimalFormat.applyPattern(pattern);

            String formattedStringPrice = decimalFormat.format(money);
            return formattedStringPrice;
        } else {
            return String.valueOf(price);
        }
    }

    public static String getCurrentFormatByCurrCd(String currCd) {
        String current = "KRW";
        if ("KRW".equals(currCd)) {
            current = "원";
        }
        return current;
    }

    public static String getCalculateDateFormatByDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            //
        }
        String message = null;


        simpleDateFormat = new SimpleDateFormat("yyyy년mm월dd일");
        message = simpleDateFormat.format(date);

        return message;
    }

    public static String getTodayWithHyphen() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(date);

        return today;
    }

    public static String getToday() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = simpleDateFormat.format(date);

        return today;
    }

    public static String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        String time = simpleDateFormat.format(date);

        return time;
    }


}
