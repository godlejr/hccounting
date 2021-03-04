package hcc.accouting.haccounting.common.broadcast.presenter;

import android.content.Context;
import android.telephony.SmsMessage;
import android.util.Log;

import hcc.accouting.haccounting.common.broadcast.interactor.CustomBroadcastReceiverInteractor;
import hcc.accouting.haccounting.common.broadcast.interactor.CustomBroadcastReceiverInteractorImpl;
import hcc.accouting.haccounting.common.broadcast.view.CustomBroadcastReceiverView;
import hcc.accouting.haccounting.common.dto.HttpErrorDto;
import hcc.accouting.haccounting.common.dto.SmsTransferDto;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.utils.SMSMessageUtil;

public class CustomBroadcastReceiverPresenterImpl implements CustomBroadcastReceiverPresenter {

    private CustomBroadcastReceiverView mCustomBroadcastReceiverView;
    private CustomBroadcastReceiverInteractor mCustomBroadcastReceiverInteractor;
    private SMSMessageUtil mSmsMessageUtil;


    public CustomBroadcastReceiverPresenterImpl(CustomBroadcastReceiverView mCustomBroadcastReceiverView) {
        this.mCustomBroadcastReceiverView = mCustomBroadcastReceiverView;
        this.mCustomBroadcastReceiverInteractor = new CustomBroadcastReceiverInteractorImpl(this);
        this.mSmsMessageUtil = new SMSMessageUtil();

    }


    @Override
    public void onSMSMessageProcess(Object[] objects, User user, Context mContext) {
        this.mCustomBroadcastReceiverInteractor.setSmsRepository(mContext);
        if (user != null) {

            SmsMessage[] smsMessages = new SmsMessage[objects.length];
            for (int i = 0; i < objects.length; i++) {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);


                String message = smsMessages[i].getMessageBody();
                String compPhone = smsMessages[i].getOriginatingAddress();

                SmsTransferDto smsTransferDto = new SmsTransferDto();
                smsTransferDto.setCompCd(user.getCompCd());
                smsTransferDto.setEmpNo(user.getEmpNo()); //Encrypted
                smsTransferDto.setCompPhone(compPhone);
                smsTransferDto.setMessage(message);

                //DB 저장

                this.mCustomBroadcastReceiverInteractor.getCardHistoryByProcessingSMSWithML(smsTransferDto);

            }
        }
    }

    @Override
    public void onHttpError(HttpErrorDto httpErrorDto) {
        if (httpErrorDto == null) {
            mCustomBroadcastReceiverView.showMessage("네트워크 불안정합니다. 다시 시도하세요.");
        } else {
            mCustomBroadcastReceiverView.showMessage(httpErrorDto.message());
        }
    }

    @Override
    public void onSuccessGetCardHistoryByProcessingSMSWithML(CardHistory cardHistory) {
        if (cardHistory != null) {
            //notification 날리기
            String vendorNm = cardHistory.getVendorNm();

          //  Log.e("")
            this.mCustomBroadcastReceiverView.showNotificationMessage(vendorNm + "에서 법인카드 사용이 진행되었습니다.", cardHistory);
        } else {

        }
    }

    @Override
    public Context getContext() {
        return this.mCustomBroadcastReceiverView.getContext();
    }


}
