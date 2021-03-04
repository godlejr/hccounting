package hcc.accouting.haccounting.common.broadcast.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.ArrayList;
import java.util.List;

import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.broadcast.presenter.CustomBroadcastReceiverPresenter;
import hcc.accouting.haccounting.common.broadcast.presenter.CustomBroadcastReceiverPresenterImpl;
import hcc.accouting.haccounting.common.broadcast.view.CustomBroadcastReceiverView;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.flag.SMSflag;
import hcc.accouting.haccounting.common.utils.SMSMessageUtil;
import hcc.accouting.haccounting.common.utils.ToastUtil;
import hcc.accouting.haccounting.repository.local.SharedPreferenceManager;
import hcc.accouting.haccounting.ui.main.activity.MainActivity;
import hcc.accouting.haccounting.ui.write.activity.WriteActivity;

public class CustomBroadcastReceiver extends BroadcastReceiver implements CustomBroadcastReceiverView {

    private CustomBroadcastReceiverPresenter mCustomBroadcastReceiverPresenter;

    private SharedPreferenceManager mSharedPreferenceManager;
    private SMSMessageUtil mSMSMessageUtil;
    private Context mContext;
    private ToastUtil toastUtil;


    public CustomBroadcastReceiver() {
        this.mCustomBroadcastReceiverPresenter = new CustomBroadcastReceiverPresenterImpl(this);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        this.mSharedPreferenceManager = new SharedPreferenceManager(mContext);
        this.toastUtil = new ToastUtil(mContext);
        String intentAction = intent.getAction();

        //SMS recevier processing
        if (SMSflag.SMS_INTENT_ACTION.equals(intentAction)) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Object[] objects = (Object[]) bundle.get("pdus");
                if (objects != null) {
                    User user = this.mSharedPreferenceManager.getUser();
                    if (user != null) {
                        this.mCustomBroadcastReceiverPresenter.onSMSMessageProcess(objects, user , this.mContext);
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }

    }

    @Override
    public void showNotificationMessage(String message, CardHistory cardHistory) {

        List<CardHistory> cardHistories = new ArrayList<>();
        cardHistories.add(cardHistory);

        Intent intent = new Intent(this.mContext, WriteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        //intent
        intent.putParcelableArrayListExtra("cardHistories", (ArrayList) cardHistories);
        intent.putExtra("cardHistoryId",0);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this.mContext);

        taskStackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this.mContext)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("씀")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this.mContext);
        notificationManager.notify(0, notificationBuilder.build());

        //일단 이동은 보류
    }

    @Override
    public Context getContext() {
        return this.mContext;
    }


    @Override
    public void showMessage(String message) {
        toastUtil.showMessage(message);
    }

}
