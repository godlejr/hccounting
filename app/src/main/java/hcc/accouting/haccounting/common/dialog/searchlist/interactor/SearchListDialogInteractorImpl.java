package hcc.accouting.haccounting.common.dialog.searchlist.interactor;

import java.util.ArrayList;
import java.util.List;

import hcc.accouting.haccounting.common.dialog.searchlist.presenter.SearchListDialogPresenter;
import hcc.accouting.haccounting.common.dto.SearchListDialogDto;
import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.common.utils.HttpErrorUtil;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractorImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchListDialogInteractorImpl<V extends SearchListDialogPresenter> extends BaseInteractorImpl<V> implements SearchListDialogInteractor<V> {
    private int flag;
    private SearchListDialogDto searchListDialogDto;

    private List<Trip> mTrips;
    private List<Acnt> mAcnts;
    private List<Dept> mDepts;

    public SearchListDialogInteractorImpl() {
    }

    @Override
    public List<Dept> getmDepts() {
        return mDepts;
    }

    @Override
    public void setmDepts(List<Dept> mDepts) {
        this.mDepts = mDepts;
    }

    @Override
    public void initDepts() {
        this.mDepts = new ArrayList<>();
    }

    @Override
    public void setDeptsAddAll(List<Dept> depts) {
        this.mDepts.addAll(depts);
    }

    @Override
    public List<Acnt> getmAcnts() {
        return mAcnts;
    }

    @Override
    public void setmAcnts(List<Acnt> mAcnts) {
        this.mAcnts = mAcnts;
    }

    @Override
    public void initAcnts() {
        this.mAcnts = new ArrayList<>();
    }

    @Override
    public void setAcntsAddAll(List<Acnt> acnts) {
        this.mAcnts.addAll(acnts);
    }


    @Override
    public void initTrips() {
        this.mTrips = new ArrayList<>();
        Trip trip = new Trip();
        trip.setTripNm("없음");
        trip.setTripCd(null);
        this.mTrips.add(trip);
    }

    @Override
    public void setTripsAddAll(List<Trip> trips) {
        this.mTrips.addAll(trips);
    }

    @Override
    public List<Trip> getmTrips() {
        return mTrips;
    }

    @Override
    public void setmTrips(List<Trip> mTrips) {
        this.mTrips = mTrips;
    }

    @Override
    public int getFlag() {
        return flag;
    }

    @Override
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public SearchListDialogDto getSearchListDialogDto() {
        return searchListDialogDto;
    }

    @Override
    public void setSearchListDialogDto(SearchListDialogDto searchListDialogDto) {
        this.searchListDialogDto = searchListDialogDto;
    }

    @Override
    public void getTripsByCompNoAndEmpNoAndDate(String compCd, String empNo, String date) {
        Call<List<Trip>> call = getTripRepository().getTripsByCompCdAndEmpNoAndDate(compCd, empNo, date);
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (response.isSuccessful()) {
                    List<Trip> trips = response.body();
                    getBasePresenter().onSuccessGetTripsByCompNoAndEmpNoAndDate(trips);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimitAndListStatus(String compCd, String empNo, String toString, long searchListFristOffset, long searchListLimit, final int listStatus) {
        Call<List<Trip>> call = getTripRepository().getTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(compCd, empNo, toString, searchListFristOffset, searchListLimit);
        call.enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (response.isSuccessful()) {
                    List<Trip> trips = response.body();
                    getBasePresenter().onSuccessGetTripsByCompCdAndEmpNoAndTripNmAndOffsetAndLimit(trips, listStatus);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm(String compCd, String empNo, String vendorNm) {
        Call<List<Acnt>> call = getAcntRepository().getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm(compCd, empNo, vendorNm);
        call.enqueue(new Callback<List<Acnt>>() {
            @Override
            public void onResponse(Call<List<Acnt>> call, Response<List<Acnt>> response) {
                if (response.isSuccessful()) {
                    List<Acnt> acnts = response.body();
                    getBasePresenter().onSuccessGetAcntsWithSuggestionsByCompNoAndEmpNoAndVendorNm(acnts);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<List<Acnt>> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(String compCd, String toString, String toString1, long searchListFristOffset, long searchListLimit, final int listStatus) {
        Call<List<Acnt>> call = getAcntRepository().getAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(compCd, toString1, toString, searchListFristOffset, searchListLimit);
        call.enqueue(new Callback<List<Acnt>>() {
            @Override
            public void onResponse(Call<List<Acnt>> call, Response<List<Acnt>> response) {
                if (response.isSuccessful()) {
                    List<Acnt> acnts = response.body();
                    getBasePresenter().onSuccessGetAcntsByCompCdAndAcntCdOrAcntNmAndOffsetAndLimit(acnts, listStatus);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<List<Acnt>> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }

    @Override
    public void getDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(String compCd, String empNo, String deptNm, long searchListFristOffset, long searchListLimit, final int listStatus) {
        Call<List<Dept>> call = getDeptRepository().getDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(compCd, empNo, deptNm, searchListFristOffset, searchListLimit);
        call.enqueue(new Callback<List<Dept>>() {
            @Override
            public void onResponse(Call<List<Dept>> call, Response<List<Dept>> response) {
                if (response.isSuccessful()) {
                    List<Dept> depts = response.body();
                    getBasePresenter().onSuccessGetDeptsByCompCdAndEmpNoAndDeptNmAndOffsetAndLimit(depts, listStatus);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<List<Dept>> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }
}
