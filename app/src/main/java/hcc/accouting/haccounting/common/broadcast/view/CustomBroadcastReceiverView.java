package hcc.accouting.haccounting.common.broadcast.view;

import android.content.Context;

import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.User;

public interface CustomBroadcastReceiverView {

    public void showNotificationMessage(String message, CardHistory cardHistory);

    public Context getContext();

    void showMessage(String message);
}
