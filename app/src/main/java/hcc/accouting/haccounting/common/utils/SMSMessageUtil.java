package hcc.accouting.haccounting.common.utils;

import android.util.Log;

import hcc.accouting.haccounting.common.dto.SmsMessageDto;

public class SMSMessageUtil {

    public SMSMessageUtil() {

    }

    public SmsMessageDto samsungCardMessageParser(String Message){
        String logTag = "SmsReceiver";
        Log.e(logTag,"Start Parser");
        String[] mMsgPart = new String[4];
        String[] mMsgCardPart = new String[2]; // 두번째 줄  / 카드사 / 카드번호 / 승인여부 / 카드소유자
        String[] mMsgCardAccesccPart = new String[3]; // 세번째 줄  / 승인금액 / 할부여부
        String[] mMsgCardUsage = new String[4]; // 승인날짜 / 승인시간 / 사용처


        mMsgPart = Message.split("\\n");
        mMsgCardPart = mMsgPart[1].split(" ");
        mMsgCardAccesccPart = mMsgPart[2].split(" ");
        mMsgCardUsage =  mMsgPart[3].split(" ");

        Log.e(logTag,"0 : " + "삼성");
        Log.e(logTag,"1 : " + mMsgCardPart[0].substring(2,6));
        Log.e(logTag,"2 : " + mMsgCardPart[0].substring(6));
        Log.e(logTag,"3 : " + mMsgCardPart[1].toString());
        Log.e(logTag,"0 : " + mMsgCardAccesccPart[0]);
        Log.e(logTag,"1 : " + mMsgCardAccesccPart[1]);
        Log.e(logTag,"0 : " + mMsgCardUsage[0]);
        Log.e(logTag,"1 : " + mMsgCardUsage[1]);
        Log.e(logTag,"2 : " + mMsgCardUsage[2]);


        SmsMessageDto mSmsMessageDto = new SmsMessageDto();
        mSmsMessageDto.setCardCompany("삼성");
        mSmsMessageDto.setCardNumber(mMsgCardPart[0].substring(2,5));
        mSmsMessageDto.setCardAccess(mMsgCardPart[0].substring(5));
        mSmsMessageDto.setCardOwner(mMsgCardPart[1].toString());
        mSmsMessageDto.setPrice(mMsgCardAccesccPart[0].toString());
        mSmsMessageDto.setDate(mMsgCardUsage[0]);
        mSmsMessageDto.setTime(mMsgCardUsage[1]);
        mSmsMessageDto.setUsage(mMsgCardUsage[2]);

        return  mSmsMessageDto;
    }

}
