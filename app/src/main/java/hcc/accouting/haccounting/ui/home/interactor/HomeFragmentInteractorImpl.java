package hcc.accouting.haccounting.ui.home.interactor;

import android.app.Activity;

import java.util.List;

import hcc.accouting.haccounting.common.entity.Budget;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.common.utils.HttpErrorUtil;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractorImpl;
import hcc.accouting.haccounting.ui.home.presenter.HomeFragmentPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentInteractorImpl<V extends HomeFragmentPresenter> extends BaseInteractorImpl<V> implements HomeFragmentInteractor<V>{

    private boolean isHomeFragmentPause = false;

    public HomeFragmentInteractorImpl() {
    }

    @Override
    public void init() {

    }

    @Override
    public void getCardHistoryCountByStatusAndEmpNo(String status, String empNo) {
        Call<Integer> call = getCardHistoryRepository().getCardHistoryCountByStatusAndEmpNo(status, empNo);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    Integer count = response.body();
                    getBasePresenter().onSuccessGetCardHistoryCountByStatus(count);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getBudgetsByCompCdAndDeptCdAndAcntCd(String compCd, String deptCd, String acntCd) {
        Call<List<Budget>> call = getBudgetRepository().getBudgetsByCompCdAndDeptCdAndAcntCd(compCd, deptCd, acntCd);
        call.enqueue(new Callback<List<Budget>>() {
            @Override
            public void onResponse(Call<List<Budget>> call, Response<List<Budget>> response) {
                if (response.isSuccessful()) {
                    List<Budget> budgets = response.body();
                    getBasePresenter().onSuccessGetBudgetsByCompCdAndDeptCdAndAcntCd(budgets);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<List<Budget>> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getRecentOneTripByCompCdAndEmpNo(String compCd, String empNo) {
        Call<Trip> call = getTripRepository().getRecentOneTripByCompCdAndEmpNo(compCd, empNo);
        call.enqueue(new Callback<Trip>() {
            @Override
            public void onResponse(Call<Trip> call, Response<Trip> response) {
                if (response.isSuccessful()) {
                    Trip trip = response.body();
                    getBasePresenter().onSuccessGetRecentOneTripByCompCdAndEmpNo(trip);
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
    public boolean isHomeFragmentPause() {
        return isHomeFragmentPause;
    }

    @Override
    public void setHomeFragmentPause(boolean homeFragmentPause) {
        isHomeFragmentPause = homeFragmentPause;
    }
}
