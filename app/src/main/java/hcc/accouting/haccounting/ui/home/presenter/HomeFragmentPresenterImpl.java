package hcc.accouting.haccounting.ui.home.presenter;

import java.util.List;

import hcc.accouting.haccounting.common.entity.Budget;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.flag.CardHistoryFlag;
import hcc.accouting.haccounting.common.utils.StringUtil;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenterImpl;
import hcc.accouting.haccounting.ui.home.interactor.HomeFragmentInteractor;
import hcc.accouting.haccounting.ui.home.interactor.HomeFragmentInteractorImpl;
import hcc.accouting.haccounting.ui.home.view.HomeFragmentView;
import hcc.accouting.haccounting.ui.transfer.activity.TransferActivity;

public class HomeFragmentPresenterImpl<V extends HomeFragmentView> extends BasePresenterImpl<V> implements HomeFragmentPresenter<V> {

    private HomeFragmentInteractor mInteractor;

    public HomeFragmentPresenterImpl() {
    }


    @Override
    public void init() {
        this.mInteractor = new HomeFragmentInteractorImpl<HomeFragmentPresenter>();
        this.mInteractor.onAttach(this);

        this.mInteractor.setCardHistoryRepository();
        this.mInteractor.setTripRepository();
        this.mInteractor.setBudgetRepository();
    }

    @Override
    public void initView() {

        User user = getBaseView().getSharedPrersUser();
        String compCd = user.getCompCd();
        String deptCd = user.getDeptCd();
        String deptNm = user.getDeptNm();
        String empNo = user.getEmpNo();

        getBaseView().setBudgetTitleContent(deptNm + " 남은 예산");

        this.mInteractor.getCardHistoryCountByStatusAndEmpNo(CardHistoryFlag.CARD_NONE_WRITE_STATUS, empNo);

        this.mInteractor.getBudgetsByCompCdAndDeptCdAndAcntCd(compCd, deptCd, "all"); //전체

        this.mInteractor.getRecentOneTripByCompCdAndEmpNo(compCd, empNo);


    }

    @Override
    public void onSuccessGetCardHistoryCountByStatus(Integer count) {
        String notice = "작성할 내역이 없습니다.";
        if (count != null) {
            notice = "작성하지 않은 " + count + "건의 법인카드 내역이 있습니다.";
        }

        getBaseView().setNoticeContent(notice);
    }

    @Override
    public void onSuccessGetBudgetsByCompCdAndDeptCdAndAcntCd(List<Budget> budgets) {
        int budgetsSize = budgets.size();
        if (budgetsSize > 0) {
            getBaseView().goneBudgetEmpty();
            getBaseView().showBudgetContent();
            String acnt1 = budgets.get(0).getAcntNm();
            Float budget1 = budgets.get(0).getRemBdget();
            String bugetStr1 = StringUtil.getWonMoneyFormatByPriceAndCurrCd(budget1, "KRW");


            String acnt2 = budgets.get(1).getAcntNm();
            Float budget2 = budgets.get(1).getRemBdget();
            String bugetStr2 = StringUtil.getWonMoneyFormatByPriceAndCurrCd(budget2, "KRW");


            String acnt3 = budgets.get(2).getAcntNm();
            Float budget3 = budgets.get(2).getRemBdget();
            String bugetStr3 = StringUtil.getWonMoneyFormatByPriceAndCurrCd(budget3, "KRW");


            getBaseView().setBudgetAcnt1Content(acnt1);
            getBaseView().setBudgetPrice1Content(bugetStr1 + "원");

            getBaseView().setBudgetAcnt2Content(acnt2);
            getBaseView().setBudgetPrice2Content(bugetStr2 + "원");

            getBaseView().setBudgetAcnt3Content(acnt3);
            getBaseView().setBudgetPrice3Content(bugetStr3 + "원");
        }else{
            getBaseView().goneBudgetContent();
            getBaseView().showBudgetEmpty();
        }
    }

    @Override
    public void onSuccessGetRecentOneTripByCompCdAndEmpNo(Trip trip) {
        if (trip == null) {
            getBaseView().goneTripContent();
            getBaseView().showTripEmpty();

        } else {
            getBaseView().goneTripEmpty();
            getBaseView().showTripContent();

            String tripNm = trip.getTripNm();
            Integer transAmt = trip.getTransAmt();
            Integer roomAmt = trip.getRoomAmt();
            Integer dailyAmt = trip.getDailyAmt();

            String transAmtStr = StringUtil.getWonMoneyFormatByIntegerPriceAndCurrCd(transAmt, "KRW");
            String roomAmtStr = StringUtil.getWonMoneyFormatByIntegerPriceAndCurrCd(roomAmt, "KRW");
            String dailyAmtStr = StringUtil.getWonMoneyFormatByIntegerPriceAndCurrCd(dailyAmt, "KRW");

            getBaseView().setTripTitleContent(tripNm+ " 출장비");
            getBaseView().setTripHouseCostContent(roomAmtStr + "원");
            getBaseView().setTripNormalCostContent(dailyAmtStr + "원");
            getBaseView().setTripTravelCostContent(transAmtStr + "원");

        }
    }

    @Override
    public void onPause() {
        //fragment pause flag setting
        boolean isHomeFragmentPause = this.mInteractor.isHomeFragmentPause();

        if (!isHomeFragmentPause) {
            this.mInteractor.setHomeFragmentPause(true);
        }
    }

    @Override
    public void onResume() {
        boolean isHomeFragmentPause = this.mInteractor.isHomeFragmentPause();

        if (isHomeFragmentPause) {
            User user = getBaseView().getSharedPrersUser();
            String compCd = user.getCompCd();
            String deptCd = user.getDeptCd();
            String deptNm = user.getDeptNm();
            String empNo = user.getEmpNo();
            this.mInteractor.getCardHistoryCountByStatusAndEmpNo(CardHistoryFlag.CARD_NONE_WRITE_STATUS, empNo);

            this.mInteractor.getBudgetsByCompCdAndDeptCdAndAcntCd(compCd, deptCd, "all"); //전체
        }
    }
}
