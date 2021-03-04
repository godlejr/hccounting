package hcc.accouting.haccounting.common.dialog.searchlist.presenter;

import java.util.List;

import hcc.accouting.haccounting.common.dialog.searchlist.view.SearchListDialogView;
import hcc.accouting.haccounting.common.dto.SearchListDialogDto;
import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;

public interface SearchListDialogPresenter<V extends SearchListDialogView> extends BasePresenter<V> {
    void init(SearchListDialogDto searchListDialogDto, int flag);

    void onScrollChange(int difference, String toString);

    void onClickTripContent(int position);

    void onSuccessGetTripsByCompNoAndEmpNoAndDate(List<Trip> trips);

    void onTextChanged(String toString);

    void onSuccessGetTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(List<Trip> trips, int listStatus);

    void onClickAcntContent(int position);

    void onSuccessGetAcntsWithSuggestionsByCompNoAndEmpNoAndVendorNm(List<Acnt> acnts);

    void onSuccessGetAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(List<Acnt> acnts, int listStatus);

    void onSuccessGetDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(List<Dept> depts, int listStatus);

    void onClickDeptContent(int position);

    void onClickSearchClear(String searchString);
}
