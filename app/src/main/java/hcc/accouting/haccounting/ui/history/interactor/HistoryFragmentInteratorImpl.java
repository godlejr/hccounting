package hcc.accouting.haccounting.ui.history.interactor;

import java.util.ArrayList;
import java.util.List;

import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.utils.HttpErrorUtil;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractorImpl;
import hcc.accouting.haccounting.ui.history.presenter.HistoryFragmentPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragmentInteratorImpl<V extends HistoryFragmentPresenter> extends BaseInteractorImpl<V> implements HistoryFragmentInterator<V> {

    private List<CardHistory> mCardHistories;
    private String mCardHistoryStatus;
    private boolean isCardHistoriesChecked;
    private boolean isCardHistoryFragmentPause = false;
    private boolean isInit = false;


    public HistoryFragmentInteratorImpl() {
    }

    @Override
    public boolean isCardHistoryFragmentPause() {
        return isCardHistoryFragmentPause;
    }

    @Override
    public void setCardHistoryFragmentPause(boolean cardHistoryFragmentPause) {
        isCardHistoryFragmentPause = cardHistoryFragmentPause;
    }

    @Override
    public void initCardHistories(){
        this.mCardHistories = new ArrayList<>();
        this.isCardHistoriesChecked = false;
    }

    @Override
    public void setCardHistoriesChecked(boolean cardHistoriesChecked) {
        isCardHistoriesChecked = cardHistoriesChecked;
    }

    @Override
    public boolean isCardHistoriesChecked() {
        return isCardHistoriesChecked;
    }

    @Override
    public String getCardHistoryStatus() {
        return mCardHistoryStatus;
    }

    @Override
    public void setCardHistoryStatus(String mCardHistoryStatus) {
        this.mCardHistoryStatus = mCardHistoryStatus;
    }

    @Override
    public List<CardHistory> getCardHistories() {
        return mCardHistories;
    }

    @Override
    public void setCardHistoriesAddAll(List<CardHistory> cardHistories) {
        this.mCardHistories.addAll(cardHistories);
    }

    @Override
    public void setCardHistoriesRemoveAll(List<CardHistory> cardHistories) {
        this.mCardHistories.removeAll(cardHistories);
    }

    @Override
    public void setCardHistories(List<CardHistory> cardHistories) {
        this.mCardHistories = cardHistories;
    }

    @Override
    public void getCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(final String status, String compCd, String empNo, long offset, long limit) {
        Call<List<CardHistory>> call = getCardHistoryRepository().getCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(status, compCd, empNo, offset, limit);
        call.enqueue(new Callback<List<CardHistory>>() {
            @Override
            public void onResponse(Call<List<CardHistory>> call, Response<List<CardHistory>> response) {
                if (response.isSuccessful()) {
                    List<CardHistory> cardHistories = response.body();
                    getBasePresenter().onSuccessGetCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(cardHistories, status);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<List<CardHistory>> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });

    }

    @Override
    public boolean isInit() {
        return isInit;
    }

    @Override
    public void setInit(boolean init) {
        isInit = init;
    }
}
