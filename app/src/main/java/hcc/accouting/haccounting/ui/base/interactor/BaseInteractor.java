package hcc.accouting.haccounting.ui.base.interactor;

import hcc.accouting.haccounting.repository.remote.async.AcntRepository;
import hcc.accouting.haccounting.repository.remote.async.BudgetRepository;
import hcc.accouting.haccounting.repository.remote.async.CardHistoryRepository;
import hcc.accouting.haccounting.repository.remote.async.DeptRepository;
import hcc.accouting.haccounting.repository.remote.async.SlipHeaderRepository;
import hcc.accouting.haccounting.repository.remote.async.TripRepository;
import hcc.accouting.haccounting.repository.remote.async.UserRepository;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;

public interface BaseInteractor<V extends BasePresenter> {
    public void onAttach(V basePresenter);

    public void init();

    public void showThrowableLog(Throwable throwable);


    public void setUserRepository();

    public UserRepository getUserRepository();

    public void clearUserRepository();

    public void setCardHistoryRepository();

    public CardHistoryRepository getCardHistoryRepository();

    public void clearCardHistoryRepository();

    void setAcntRepository();

    AcntRepository getAcntRepository();

    void clearAcntRepository();

    void setBudgetRepository();

    BudgetRepository getBudgetRepository();

    void clearBudgetRepository();

    void setTripRepository();

    TripRepository getTripRepository();

    void clearTripRepository();

    void setDeptRepository();

    DeptRepository getDeptRepository();

    void clearDeptRepository();

    void setSlipHeaderRepository();

    SlipHeaderRepository getSlipHeaderRepository();

    void clearSlipHeaderRepository();
}
