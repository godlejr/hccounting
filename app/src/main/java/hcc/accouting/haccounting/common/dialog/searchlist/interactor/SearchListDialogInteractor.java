package hcc.accouting.haccounting.common.dialog.searchlist.interactor;

import java.util.List;

import hcc.accouting.haccounting.common.dialog.searchlist.presenter.SearchListDialogPresenter;
import hcc.accouting.haccounting.common.dto.SearchListDialogDto;
import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractor;

public interface SearchListDialogInteractor<V extends SearchListDialogPresenter> extends BaseInteractor<V> {


    List<Dept> getmDepts();

    void setmDepts(List<Dept> mDepts);

    void initDepts();

    void setDeptsAddAll(List<Dept> depts);

    List<Acnt> getmAcnts();

    void setmAcnts(List<Acnt> mAcnts);

    void initAcnts();

    void setAcntsAddAll(List<Acnt> acnts);

    void initTrips();

    void setTripsAddAll(List<Trip> trips);

    List<Trip> getmTrips();

    void setmTrips(List<Trip> mTrips);

    int getFlag();

    void setFlag(int flag);

    SearchListDialogDto getSearchListDialogDto();

    void setSearchListDialogDto(SearchListDialogDto searchListDialogDto);

    void getTripsByCompNoAndEmpNoAndDate(String compCd, String empNo, String date);

    void getTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimitAndListStatus(String compCd, String empNo, String toString, long searchListFristOffset, long searchListLimit,int listStatus);

    void getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm(String compCd, String empNo, String vendorNm);

    void getAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(String compCd, String toString, String toString1, long searchListFristOffset, long searchListLimit, int searchListStatusChanged);

    void getDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(String compCd, String empNo, String deptNm, long searchListFristOffset, long searchListLimit, int listStatus);
}
