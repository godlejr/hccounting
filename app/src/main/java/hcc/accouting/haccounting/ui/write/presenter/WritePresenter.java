package hcc.accouting.haccounting.ui.write.presenter;

import java.util.List;

import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Budget;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;
import hcc.accouting.haccounting.ui.write.view.WriteView;

public interface WritePresenter<V extends WriteView> extends BasePresenter<V> {
    void init(int cardHistoryId, List<CardHistory> cardHistories, List<SlipHeader> slipHeaders);

    void onSuccessGetAcntsWithSuggestionsByCompNoAndEmpNoAndVendorNm(List<Acnt> acnts);

    void onSuccessGetBudgetByCompCdAndDeptCdAndAcntCd(Budget budget);

    void onClickNext(String textH, String textD);

    void onClickTripContent();

    void onClickAcntContent();

    void onClickDeptContent();

    void onSuccessGetTripsByCompCdAndEmpNoAndDate(List<Trip> trips);

    void onActivityResultForTripDialogResultOk(Trip trip);

    void onActivityResultForAcntDialogResultOk(Acnt acnt);

    void onActivityResultForDeptDialogResultOk(Dept dept);

    void onSuccessGetSlipHeaderBySavingSlipHeaderAndSlipDetail(SlipHeader newSlipHeader);

    void onSuccessGetCardHistoryByModifyingCardHistory(CardHistory newCardHistory);

    void onSuccessGetSlipHeaderByMngNoMobile(SlipHeader slipHeader);

    void onSuccessGetTripByCompCdAndTripCdAndDeptCd(Trip trip);

    void onSuccessGetSlipHeaderByModifyingSlipHeaderAndSlipDetail(SlipHeader modifiedSlipHeader);

    void onBackPressed();
}
