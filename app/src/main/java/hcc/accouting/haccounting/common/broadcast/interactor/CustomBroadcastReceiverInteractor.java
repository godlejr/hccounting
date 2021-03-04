package hcc.accouting.haccounting.common.broadcast.interactor;

import android.content.Context;

import hcc.accouting.haccounting.common.dto.SmsTransferDto;
import hcc.accouting.haccounting.repository.remote.async.SmsRepository;

public interface CustomBroadcastReceiverInteractor {
    void setSmsRepository(Context mContext);

    SmsRepository getSmsRepository();

    void clearSmsRepository();

    void getCardHistoryByProcessingSMSWithML(SmsTransferDto smsTransferDto);

}
