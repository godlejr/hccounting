package hcc.accouting.haccounting.common.broadcast.presenter;

import android.content.Context;

import hcc.accouting.haccounting.common.dto.HttpErrorDto;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.User;

public interface CustomBroadcastReceiverPresenter {

    void onSMSMessageProcess(Object[] objects, User user, Context mContext);


    void onHttpError(HttpErrorDto responseHandler);

    void onSuccessGetCardHistoryByProcessingSMSWithML(CardHistory cardHistory);

    public Context getContext();

}
