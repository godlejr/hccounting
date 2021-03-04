package hcc.accouting.haccounting.ui.write.interactor;

import java.util.ArrayList;
import java.util.List;

import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Budget;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.common.utils.HttpErrorUtil;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractorImpl;
import hcc.accouting.haccounting.ui.write.presenter.WritePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteInteractorImpl<V extends WritePresenter> extends BaseInteractorImpl<V> implements WriteInteractor<V> {

    private List<CardHistory> selectedCardHistories;
    private Acnt anct;
    private Budget budget;
    private Trip trip;

    private SlipHeader slipHeader;
    private int cardHistoryId;

    private boolean isFirstTransfer;

    //전표 결제용
    private  List<SlipHeader> slipHeaders;


    public WriteInteractorImpl() {
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
    public void initSlipHeaders(){
        this.slipHeaders = new ArrayList<>();
    }

    @Override
    public void setSlipHeadersAdd(SlipHeader slipHeader){
        this.slipHeaders.add(slipHeader);
    }

    @Override
    public Trip getTrip() {
        return trip;
    }

    @Override
    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public Budget getBudget() {
        return budget;
    }

    @Override
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    @Override
    public Acnt getAnct() {
        return anct;
    }

    @Override
    public void setAnct(Acnt anct) {
        this.anct = anct;
    }

    @Override
    public void initSlipHeader() {
        this.slipHeader = new SlipHeader();
    }

    @Override
    public SlipHeader getSlipHeader() {
        return slipHeader;
    }

    @Override
    public void setSlipHeader(SlipHeader slipHeader) {
        this.slipHeader = slipHeader;
    }

    @Override
    public boolean isFirstTransfer() {
        return isFirstTransfer;
    }

    @Override
    public void setFirstTransfer(boolean firstTransfer) {
        isFirstTransfer = firstTransfer;
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
    public List<CardHistory> getSelectedCardHistories() {
        return selectedCardHistories;
    }

    @Override
    public void setSelectedCardHistories(List<CardHistory> selectedCardHistories) {
        this.selectedCardHistories = selectedCardHistories;
    }

    @Override
    public void getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm(String compCd, String empNo, String vendorNm) {
        Call<List<Acnt>> call = getAcntRepository().getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm(compCd, empNo, vendorNm);
        call.enqueue(new Callback<List<Acnt>>() {
            @Override
            public void onResponse(Call<List<Acnt>> call, Response<List<Acnt>> response) {
                if (response.isSuccessful()) {
                    List<Acnt> acnts = response.body();
                    getBasePresenter().onSuccessGetAcntsWithSuggestionsByCompNoAndEmpNoAndVendorNm(acnts);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<List<Acnt>> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(String compCd, String deptCd, String acntCd, String posDate) {
        Call<Budget> call = getBudgetRepository().getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(compCd, deptCd, acntCd, posDate);
        call.enqueue(new Callback<Budget>() {
            @Override
            public void onResponse(Call<Budget> call, Response<Budget> response) {
                if (response.isSuccessful()) {
                    Budget budget = response.body();
                    getBasePresenter().onSuccessGetBudgetByCompCdAndDeptCdAndAcntCd(budget);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<Budget> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getTripsByCompCdAndEmpNoAndDate(String compCd, String empNo, String date) {
        Call<List<Trip>> call = getTripRepository().getTripsByCompCdAndEmpNoAndDate(compCd, empNo, date);
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (response.isSuccessful()) {
                    List<Trip> trips = response.body();
                    getBasePresenter().onSuccessGetTripsByCompCdAndEmpNoAndDate(trips);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getSlipHeaderBySavingSlipHeaderAndSlipDetail(SlipHeader slipHeader) {
        Call<SlipHeader> call = getSlipHeaderRepository().getSlipHeaderBySavingSlipHeaderAndSlipDetail(slipHeader);
        call.enqueue(new Callback<SlipHeader>() {
            @Override
            public void onResponse(Call<SlipHeader> call, Response<SlipHeader> response) {
                if (response.isSuccessful()) {
                    SlipHeader newSlipHeader = response.body();
                    getBasePresenter().onSuccessGetSlipHeaderBySavingSlipHeaderAndSlipDetail(newSlipHeader);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<SlipHeader> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getCardHistoryByModifyingCardHistory(CardHistory modifiedCardHistory) {
        Call<CardHistory> call = getCardHistoryRepository().getCardHistoryByModifyingCardHistory(modifiedCardHistory);
        call.enqueue(new Callback<CardHistory>() {
            @Override
            public void onResponse(Call<CardHistory> call, Response<CardHistory> response) {
                if (response.isSuccessful()) {
                    CardHistory newCardHistory = response.body();
                    getBasePresenter().onSuccessGetCardHistoryByModifyingCardHistory(newCardHistory);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<CardHistory> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getSlipHeaderByMngNoMobile(String mngNoMobile) {
        Call<SlipHeader> call = getSlipHeaderRepository().getSlipHeaderByMngNoMobile(mngNoMobile);
        call.enqueue(new Callback<SlipHeader>() {
            @Override
            public void onResponse(Call<SlipHeader> call, Response<SlipHeader> response) {
                if (response.isSuccessful()) {
                    SlipHeader slipHeader = response.body();
                    getBasePresenter().onSuccessGetSlipHeaderByMngNoMobile(slipHeader);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<SlipHeader> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getTripByCompCdAndTripCdAndDeptCd(String compCd, String tripCd, String deptCd) {
        Call<Trip> call = getTripRepository().getTripByCompCdAndTripCdAndDeptCd(compCd, tripCd, deptCd);
        call.enqueue(new Callback<Trip>() {
            @Override
            public void onResponse(Call<Trip> call, Response<Trip> response) {
                if (response.isSuccessful()) {
                    Trip trip = response.body();
                    getBasePresenter().onSuccessGetTripByCompCdAndTripCdAndDeptCd(trip);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<Trip> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getSlipHeaderByModifyingSlipHeaderAndSlipDetail(SlipHeader slipHeader) {
        Call<SlipHeader> call = getSlipHeaderRepository().getSlipHeaderByModifyingSlipHeaderAndSlipDetail(slipHeader);
        call.enqueue(new Callback<SlipHeader>() {
            @Override
            public void onResponse(Call<SlipHeader> call, Response<SlipHeader> response) {
                if (response.isSuccessful()) {
                    SlipHeader modifiedSlipHeader = response.body();
                    getBasePresenter().onSuccessGetSlipHeaderByModifyingSlipHeaderAndSlipDetail(modifiedSlipHeader);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<SlipHeader> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }
}
