package hcc.accouting.haccounting.ui.home.interactor;

import hcc.accouting.haccounting.ui.base.interactor.BaseInteractor;
import hcc.accouting.haccounting.ui.home.presenter.HomeFragmentPresenter;

public interface HomeFragmentInteractor<V extends HomeFragmentPresenter> extends BaseInteractor<V> {

    void getBudgetsByCompCdAndDeptCdAndAcntCd(String compCd, String deptCd, String acntCd);

    void getRecentOneTripByCompCdAndEmpNo(String compCd, String empNo);

    void getCardHistoryCountByStatusAndEmpNo(String cardNoneWriteStatus, String empNo);

    boolean isHomeFragmentPause();

    void setHomeFragmentPause(boolean homeFragmentPause);
}
