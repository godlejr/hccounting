package hcc.accouting.haccounting.common.dialog.searchlist.presenter;

import java.util.List;

import hcc.accouting.haccounting.common.dialog.searchlist.interactor.SearchListDialogInteractor;
import hcc.accouting.haccounting.common.dialog.searchlist.interactor.SearchListDialogInteractorImpl;
import hcc.accouting.haccounting.common.dialog.searchlist.view.SearchListDialogView;
import hcc.accouting.haccounting.common.dto.SearchListDialogDto;
import hcc.accouting.haccounting.common.dto.SearchListDialogResultOkDto;
import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.flag.DialogFlag;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenterImpl;

public class SearchListDialogPresenterImpl<V extends SearchListDialogView> extends BasePresenterImpl<V> implements SearchListDialogPresenter<V> {

    private SearchListDialogInteractor mInteractor;


    public SearchListDialogPresenterImpl() {

    }

    @Override
    public void init(SearchListDialogDto searchListDialogDto, int flag) {
        getBaseView().setSearchListEditTextChangedListener(); //검색 와처
        getBaseView().setScrollViewOnScrollChangeListener(); //인피니티 스크롤
        getBaseView().showProgressDialog();

        this.mInteractor = new SearchListDialogInteractorImpl();
        this.mInteractor.onAttach(this);

        this.mInteractor.setFlag(flag);
        this.mInteractor.setSearchListDialogDto(searchListDialogDto);

        User user = getBaseView().getSharedPrersUser();
        String compCd = user.getCompCd();
        String empNo = user.getEmpNo();


        if (flag == DialogFlag.ACNT_SEARCH_LIST) {
            getBaseView().setTitleContent(DialogFlag.ACNT_SEARCH_LIST_TITLE);
            this.mInteractor.setAcntRepository();
            String vendorNm = searchListDialogDto.getVendorNm(); //가맹점

            this.mInteractor.getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm(compCd, empNo, vendorNm);
        }

        if (flag == DialogFlag.DEPT_SEARCH_LIST) {
            getBaseView().setTitleContent(DialogFlag.DEPT_SEARCH_LIST_TITLE);
            this.mInteractor.setDeptRepository();

            this.mInteractor.getDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(compCd, empNo, "%%", DialogFlag.SEARCH_LIST_FRIST_OFFSET, DialogFlag.SEARCH_LIST_LIMIT, DialogFlag.SEARCH_LIST_STATUS_CHANGED);
        }

        if (flag == DialogFlag.TRIP_SEARCH_LIST) {
            getBaseView().setTitleContent(DialogFlag.TRIP_SEARCH_LIST_TITLE);
            this.mInteractor.setTripRepository();

            String date = searchListDialogDto.getDate(); //전기일
            //전기일 기준, 출장 초기 리스트 호출
            this.mInteractor.getTripsByCompNoAndEmpNoAndDate(compCd, empNo, date);

            //test
//            this.mInteractor.getTripsByCompNoAndEmpNoAndDate(compCd, "201600077", "20190405");

        }

    }

    @Override
    public void onScrollChange(int difference, String toString) {
        if (difference <= 0) {
            User user = getBaseView().getSharedPrersUser();
            String compCd = user.getCompCd();
            int flag = this.mInteractor.getFlag();

            getBaseView().showProgressDialog();

            if (flag == DialogFlag.ACNT_SEARCH_LIST) {
                List<Acnt> acnts = this.mInteractor.getmAcnts();
                int acntsSize = acnts.size();

//                long lastRownum = acnts.get(acntsSize - 1).getRownum();
//                long offset = lastRownum + 1;

                long lastRownum = acntsSize;
                long offset = lastRownum + 1;

                long limit = DialogFlag.SEARCH_LIST_LIMIT;


                this.mInteractor.getAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(compCd, toString, toString, offset, limit, DialogFlag.SEARCH_LIST_STATUS_STATELESS);

            }
            if (flag == DialogFlag.DEPT_SEARCH_LIST) {
                String empNo = user.getEmpNo();
                List<Dept> depts = this.mInteractor.getmDepts();
                int deptsSize = depts.size();

//                long lastRownum = acnts.get(acntsSize - 1).getRownum();
//                long offset = lastRownum + 1;

                long lastRownum = deptsSize;
                long offset = lastRownum + 1;

                long limit = DialogFlag.SEARCH_LIST_LIMIT;

                this.mInteractor.getDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(compCd, empNo, toString, offset, limit, DialogFlag.SEARCH_LIST_STATUS_STATELESS);

            }

            if (flag == DialogFlag.TRIP_SEARCH_LIST) {
                List<Trip> trips = this.mInteractor.getmTrips();
                int tripsSize = trips.size();
                String empNo = user.getEmpNo();

                // long lastRownum = trips.get(tripsSize - 1).getRownum();
                //   long offset = lastRownum + 1;

                long lastRownum = tripsSize - 1; //없음 제외
                long offset = lastRownum + 1;

                long limit = DialogFlag.SEARCH_LIST_LIMIT;

                this.mInteractor.getTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimitAndListStatus(compCd, empNo, toString, offset, limit, DialogFlag.SEARCH_LIST_STATUS_STATELESS);
                //this.mInteractor.getTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimitAndListStatus(compCd, "201600077", toString, offset, limit, DialogFlag.SEARCH_LIST_STATUS_STATELESS);

            }
        }
    }

    @Override
    public void onClickTripContent(int position) {
        List<Trip> trips = this.mInteractor.getmTrips();

        Trip trip = trips.get(position);
        SearchListDialogResultOkDto searchListDialogResultOkDto = new SearchListDialogResultOkDto();
        searchListDialogResultOkDto.setTrip(trip);
        getBaseView().goneProgressDialog();

        getBaseView().navigateToBackWithResultOk(searchListDialogResultOkDto);

    }

    @Override
    public void onClickAcntContent(int position) {
        List<Acnt> acnts = this.mInteractor.getmAcnts();

        Acnt acnt = acnts.get(position);
        SearchListDialogResultOkDto searchListDialogResultOkDto = new SearchListDialogResultOkDto();
        searchListDialogResultOkDto.setAnct(acnt);
        getBaseView().goneProgressDialog();
        getBaseView().navigateToBackWithResultOk(searchListDialogResultOkDto);
    }

    @Override
    public void onClickDeptContent(int position) {
        List<Dept> depts = this.mInteractor.getmDepts();

        Dept dept = depts.get(position);
        SearchListDialogResultOkDto searchListDialogResultOkDto = new SearchListDialogResultOkDto();
        searchListDialogResultOkDto.setDept(dept);
        getBaseView().goneProgressDialog();
        getBaseView().navigateToBackWithResultOk(searchListDialogResultOkDto);
    }

    @Override
    public void onClickSearchClear(String searchString) {
        if(searchString.length() > 0){
            getBaseView().setSearchEdit("");
        }
    }

    @Override
    public void onTextChanged(String toString) {
        int toStringLength = toString.length();
        User user = getBaseView().getSharedPrersUser();
        String compCd = user.getCompCd();


        int flag = this.mInteractor.getFlag();


        if (flag == DialogFlag.ACNT_SEARCH_LIST) {
            String empNo = user.getEmpNo();
            if (toStringLength > 0) {
                this.mInteractor.getAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(compCd, toString, toString, DialogFlag.SEARCH_LIST_FRIST_OFFSET, DialogFlag.SEARCH_LIST_LIMIT, DialogFlag.SEARCH_LIST_STATUS_CHANGED);
            } else {
                SearchListDialogDto searchListDialogDto = this.mInteractor.getSearchListDialogDto();
                String vendorNm = searchListDialogDto.getVendorNm(); //가맹점

                this.mInteractor.getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm(compCd, empNo, vendorNm);
            }
        }
        if (flag == DialogFlag.DEPT_SEARCH_LIST) {
            String empNo = user.getEmpNo();

            if (toStringLength > 0) {
                this.mInteractor.getDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(compCd, empNo, toString, DialogFlag.SEARCH_LIST_FRIST_OFFSET, DialogFlag.SEARCH_LIST_LIMIT, DialogFlag.SEARCH_LIST_STATUS_CHANGED);
            } else {
                this.mInteractor.getDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(compCd, empNo, toString, DialogFlag.SEARCH_LIST_FRIST_OFFSET, DialogFlag.SEARCH_LIST_LIMIT, DialogFlag.SEARCH_LIST_STATUS_CHANGED);

            }
        }

        if (flag == DialogFlag.TRIP_SEARCH_LIST) {
            String empNo = user.getEmpNo();
            if (toStringLength > 0) {
                this.mInteractor.getTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimitAndListStatus(compCd, empNo, toString, DialogFlag.SEARCH_LIST_FRIST_OFFSET, DialogFlag.SEARCH_LIST_LIMIT, DialogFlag.SEARCH_LIST_STATUS_CHANGED);
//                this.mInteractor.getTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimitAndListStatus(compCd, "201600077", toString, DialogFlag.SEARCH_LIST_FRIST_OFFSET, DialogFlag.SEARCH_LIST_LIMIT, DialogFlag.SEARCH_LIST_STATUS_CHANGED);

            } else {
                SearchListDialogDto searchListDialogDto = this.mInteractor.getSearchListDialogDto();
                String date = searchListDialogDto.getDate(); //전기일

                this.mInteractor.getTripsByCompNoAndEmpNoAndDate(compCd, empNo, date);
                //this.mInteractor.getTripsByCompNoAndEmpNoAndDate(compCd, "201600077", "20190405");

            }
        }
    }

    @Override
    public void onSuccessGetTripsByCompNoAndEmpNoAndDate(List<Trip> newTrips) {
        List<Trip> prevTrips = this.mInteractor.getmTrips();
        if (prevTrips != null) {
            this.mInteractor.setmTrips(null);
            getBaseView().clearTripsAdapter();
        }
        this.mInteractor.initTrips();
        this.mInteractor.setTripsAddAll(newTrips);

        List<Trip> trips = this.mInteractor.getmTrips();

        getBaseView().setTripsViewByItem(trips);

        getBaseView().goneProgressDialog();
    }

    @Override
    public void onSuccessGetTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(List<Trip> newTrips, int listStatus) {
        if (listStatus == DialogFlag.SEARCH_LIST_STATUS_STATELESS) {
            List<Trip> prevTrips = this.mInteractor.getmTrips();
            int prevTripsSize = prevTrips.size();
            int newTripsSize = newTrips.size();

            this.mInteractor.setTripsAddAll(newTrips);
            getBaseView().tripsAdapterNotifyItemRangeChanged(prevTripsSize, newTripsSize);

        }

        if (listStatus == DialogFlag.SEARCH_LIST_STATUS_CHANGED) {
            List<Trip> prevTrips = this.mInteractor.getmTrips();
            if (prevTrips != null) {
                this.mInteractor.setmTrips(null);
                getBaseView().clearTripsAdapter();
            }
            this.mInteractor.initTrips();
            this.mInteractor.setTripsAddAll(newTrips);

            List<Trip> trips = this.mInteractor.getmTrips();

            getBaseView().setTripsViewByItem(trips);
        }

        getBaseView().goneProgressDialog();
    }


    @Override
    public void onSuccessGetAcntsWithSuggestionsByCompNoAndEmpNoAndVendorNm(List<Acnt> newAcnts) {
        List<Acnt> prevAcnts = this.mInteractor.getmAcnts();
        if (prevAcnts != null) {
            this.mInteractor.setmAcnts(null);
            getBaseView().clearAcntsAdapter();
        }
        this.mInteractor.initAcnts();
        this.mInteractor.setmAcnts(newAcnts);

        if (newAcnts != null) {
            getBaseView().setAcntsViewByItem(newAcnts);
        }
        getBaseView().goneProgressDialog();
    }

    @Override
    public void onSuccessGetAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(List<Acnt> newAcnts, int listStatus) {
        if (listStatus == DialogFlag.SEARCH_LIST_STATUS_STATELESS) {
            List<Acnt> prevAcnts = this.mInteractor.getmAcnts();
            int prevAcntsSize = prevAcnts.size();
            int newAcntsSize = newAcnts.size();

            this.mInteractor.setAcntsAddAll(newAcnts);
            getBaseView().acntsAdapterNotifyItemRangeChanged(prevAcntsSize, newAcntsSize);

        }

        if (listStatus == DialogFlag.SEARCH_LIST_STATUS_CHANGED) {
            List<Acnt> prevAcnts = this.mInteractor.getmAcnts();
            if (prevAcnts != null) {
                this.mInteractor.setmAcnts(null);
                getBaseView().clearAcntsAdapter();
            }
            this.mInteractor.initAcnts();
            this.mInteractor.setmAcnts(newAcnts);
            if (newAcnts != null) {
                getBaseView().setAcntsViewByItem(newAcnts);
            }
        }

        getBaseView().goneProgressDialog();
    }

    @Override
    public void onSuccessGetDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(List<Dept> newDepts, int listStatus) {
        if (listStatus == DialogFlag.SEARCH_LIST_STATUS_STATELESS) {
            List<Dept> prevDepts = this.mInteractor.getmDepts();
            int prevDeptsSize = prevDepts.size();
            int newDeptsSize = newDepts.size();

            this.mInteractor.setDeptsAddAll(newDepts);
            getBaseView().deptsAdapterNotifyItemRangeChanged(prevDeptsSize, newDeptsSize);

        }

        if (listStatus == DialogFlag.SEARCH_LIST_STATUS_CHANGED) {
            List<Dept> prevDepts = this.mInteractor.getmDepts();
            if (prevDepts != null) {
                this.mInteractor.setmDepts(null);
                getBaseView().clearDeptsAdapter();
            }
            this.mInteractor.initDepts();
            this.mInteractor.setmDepts(newDepts);
            if (newDepts != null) {
                getBaseView().setDeptsViewByItem(newDepts);
            }
        }

        getBaseView().goneProgressDialog();
    }

}
