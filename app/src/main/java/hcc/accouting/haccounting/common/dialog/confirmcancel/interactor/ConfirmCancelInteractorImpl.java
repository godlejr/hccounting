package hcc.accouting.haccounting.common.dialog.confirmcancel.interactor;

import java.util.List;

import hcc.accouting.haccounting.common.dialog.confirmcancel.presenter.ConfirmCancelPresenter;
import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.utils.HttpErrorUtil;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractorImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmCancelInteractorImpl<V extends ConfirmCancelPresenter> extends BaseInteractorImpl<V> implements ConfirmCancelInteractor<V> {

    private ConfirmCancelDialogDto confirmCancelDialogDto;
    private int flag;

    @Override
    public ConfirmCancelDialogDto getConfirmCancelDialogDto() {
        return confirmCancelDialogDto;
    }

    @Override
    public void setConfirmCancelDialogDto(ConfirmCancelDialogDto confirmCancelDialogDto) {
        this.confirmCancelDialogDto = confirmCancelDialogDto;
    }

    @Override
    public int getFlag() {
        return flag;
    }

    @Override
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public void getIsSuccessByDeletingCardHistoriesAndSlipHeaders(List<CardHistory> cardHistories) {
        Call<Boolean> call = getCardHistoryRepository().getIsSuccessByDeletingCardHistoriesAndSlipHeaders(cardHistories);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean isSuccess = response.body();
                    getBasePresenter().onSuccessGetIsSuccessByDeletingCardHistoriesAndSlipHeaders(isSuccess);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getIsSuccessByDeletingSlipHeadersAndModifyingCardHistories(List<CardHistory> cardHistories) {
        Call<Boolean> call = getCardHistoryRepository().getIsSuccessByDeletingSlipHeadersAndModifyingCardHistories(cardHistories);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Boolean isSuccess = response.body();
                    getBasePresenter().onSuccessGetIsSuccessByDeletingSlipHeadersAndModifyingCardHistories(isSuccess);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }
}
