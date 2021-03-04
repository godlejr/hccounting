package hcc.accouting.haccounting.ui.transfer.interactor;

import java.util.List;

import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.BudgetCheck;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.common.utils.HttpErrorUtil;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractorImpl;
import hcc.accouting.haccounting.ui.transfer.presenter.TransferPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferInteractorImpl<V extends TransferPresenter> extends BaseInteractorImpl<V> implements TransferInteractor<V> {

    private List<CardHistory> selectedCardHistories;
    //전표 결제용
    private  List<SlipHeader> slipHeaders;
    private int cardHistoryId;

    @Override
    public List<CardHistory> getSelectedCardHistories() {
        return selectedCardHistories;
    }

    @Override
    public void setSelectedCardHistories(List<CardHistory> selectedCardHistories) {
        this.selectedCardHistories = selectedCardHistories;
    }

    @Override
    public List<SlipHeader> getSlipHeaders() {
        return slipHeaders;
    }

    @Override
    public void setSlipHeaders(List<SlipHeader> slipHeaders) {
        this.slipHeaders = slipHeaders;
    }

    @Override
    public int getCardHistoryId() {
        return cardHistoryId;
    }

    @Override
    public void setCardHistoryId(int cardHistoryId) {
        this.cardHistoryId = cardHistoryId;
    }

    @Override
    public void getBudgetCheckByTransferringSlipHeaders(List<SlipHeader> slipHeaders) {
        Call<BudgetCheck> call = getSlipHeaderRepository().getBudgetCheckByTransferringSlipHeaders(slipHeaders);
        call.enqueue(new Callback<BudgetCheck>() {
            @Override
            public void onResponse(Call<BudgetCheck> call, Response<BudgetCheck> response) {
                if (response.isSuccessful()) {
                    BudgetCheck budgetCheck = response.body();
                    getBasePresenter().onSuccessGetBudgetCheckByTransferringSlipHeaders(budgetCheck);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<BudgetCheck> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    public TransferInteractorImpl() {
    }
}
