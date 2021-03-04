package hcc.accouting.haccounting.ui.write.interactor;

import java.util.List;

import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Budget;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractor;
import hcc.accouting.haccounting.ui.write.presenter.WritePresenter;

public interface WriteInteractor<V extends WritePresenter> extends BaseInteractor<V> {
    List<SlipHeader> getSlipHeaders();

    void setSlipHeaders(List<SlipHeader> slipHeaders);

    void initSlipHeaders();

    void setSlipHeadersAdd(SlipHeader slipHeader);

    Trip getTrip();

    void setTrip(Trip trip);

    Budget getBudget();

    void setBudget(Budget budget);

    Acnt getAnct();

    void setAnct(Acnt anct);

    void initSlipHeader();

    SlipHeader getSlipHeader();

    void setSlipHeader(SlipHeader slipHeader);

    boolean isFirstTransfer();

    void setFirstTransfer(boolean firstTransfer);

    int getCardHistoryId();

    void setCardHistoryId(int cardHistoryId);

    List<CardHistory> getSelectedCardHistories();

    void setSelectedCardHistories(List<CardHistory> selectedCardHistories);

    void getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm(String compCd, String empNo, String vendorNm);

    void getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(String compCd, String deptCd, String acntCd, String posDate);

    void getTripsByCompCdAndEmpNoAndDate(String compCd, String empNo, String date);

    void getSlipHeaderBySavingSlipHeaderAndSlipDetail(SlipHeader slipHeader);

    void getCardHistoryByModifyingCardHistory(CardHistory modifiedCardHistory);

    void getSlipHeaderByMngNoMobile(String mngNoMobile);

    void getTripByCompCdAndTripCdAndDeptCd(String compCd, String tripCd, String deptCd);

    void getSlipHeaderByModifyingSlipHeaderAndSlipDetail(SlipHeader slipHeader);
}
