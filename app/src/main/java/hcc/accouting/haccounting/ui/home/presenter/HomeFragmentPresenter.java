package hcc.accouting.haccounting.ui.home.presenter;

import java.util.List;

import hcc.accouting.haccounting.common.entity.Budget;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;
import hcc.accouting.haccounting.ui.home.view.HomeFragmentView;

public interface HomeFragmentPresenter<V extends HomeFragmentView> extends BasePresenter<V> {
    void onSuccessGetCardHistoryCountByStatus(Integer count);

    void onSuccessGetBudgetsByCompCdAndDeptCdAndAcntCd(List<Budget> budgets);

    void onSuccessGetRecentOneTripByCompCdAndEmpNo(Trip trip);

    void onPause();

    void onResume();
}
