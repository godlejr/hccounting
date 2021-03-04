package hcc.accouting.haccounting.common.broadcast.interactor;

import android.content.Context;

import hcc.accouting.haccounting.common.broadcast.presenter.CustomBroadcastReceiverPresenter;
import hcc.accouting.haccounting.common.dto.SmsTransferDto;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.utils.HttpErrorUtil;
import hcc.accouting.haccounting.repository.remote.async.SmsRepository;
import hcc.accouting.haccounting.repository.remote.interceptor.HttpInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomBroadcastReceiverInteractorImpl implements CustomBroadcastReceiverInteractor {

    private CustomBroadcastReceiverPresenter mCustomBroadcastReceiverPresenter;

    private SmsRepository mSmsRepository;


    public CustomBroadcastReceiverInteractorImpl(CustomBroadcastReceiverPresenter customBroadcastReceiverPresenter) {
        this.mCustomBroadcastReceiverPresenter = customBroadcastReceiverPresenter;
    }


    @Override
    public void setSmsRepository(Context mContext) {
        this.mSmsRepository = new HttpInterceptor(mContext).getRetrofitForSmsRepository().create(SmsRepository.class);
    }

    @Override
    public SmsRepository getSmsRepository() {
        return this.mSmsRepository;
    }

    @Override
    public void clearSmsRepository() {
        if (this.mSmsRepository != null) {
            this.mSmsRepository = null;
        }
    }

    @Override
    public void getCardHistoryByProcessingSMSWithML(SmsTransferDto smsTransferDto) {
        Call<CardHistory> call = getSmsRepository().getCardHistoryByProcessingSMSWithML(smsTransferDto);

        call.enqueue(new Callback<CardHistory>() {
            @Override
            public void onResponse(Call<CardHistory> call, Response<CardHistory> response) {
                if (response.isSuccessful()) {
                    CardHistory cardHistory = response.body();
                    mCustomBroadcastReceiverPresenter.onSuccessGetCardHistoryByProcessingSMSWithML(cardHistory);
                } else {
                    mCustomBroadcastReceiverPresenter.onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<CardHistory> call, Throwable t) {
                t.printStackTrace();
                mCustomBroadcastReceiverPresenter.onHttpError(null);
            }
        });
    }


}
