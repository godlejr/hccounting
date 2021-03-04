package hcc.accouting.haccounting.ui.base.interactor;

import android.content.Context;
import android.util.Log;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.utils.HttpErrorUtil;
import hcc.accouting.haccounting.repository.remote.async.AcntRepository;
import hcc.accouting.haccounting.repository.remote.async.BudgetRepository;
import hcc.accouting.haccounting.repository.remote.async.CardHistoryRepository;
import hcc.accouting.haccounting.repository.remote.async.DeptRepository;
import hcc.accouting.haccounting.repository.remote.async.SlipHeaderRepository;
import hcc.accouting.haccounting.repository.remote.async.TripRepository;
import hcc.accouting.haccounting.repository.remote.async.UserRepository;
import hcc.accouting.haccounting.repository.remote.interceptor.HttpInterceptor;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseInteractorImpl<V extends BasePresenter> implements BaseInteractor<V> {
    private V mBasePresenter;

    //Reposiltory
    private UserRepository mUserRepository;

    private CardHistoryRepository mCardHistoryRepository;

    private AcntRepository mAcntRepository;

    private BudgetRepository mBudgetRepository;

    private TripRepository mTripRepository;

    private DeptRepository mDeptRepository;

    private SlipHeaderRepository mSlipHeaderRepository;

    private Context mContext;

    public BaseInteractorImpl() {

    }


    @Override
    public void onAttach(V basePresenter) {
        this.mBasePresenter = basePresenter;
        this.mContext = mBasePresenter.getContext();
    }

    @Override
    public void init() {

    }

    @Override
    public void showThrowableLog(Throwable throwable) {
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        String className = stackTraceElements[0].getClassName();
        String methodName = stackTraceElements[0].getMethodName();
        int lineNumber = stackTraceElements[0].getLineNumber();
        String fileName = stackTraceElements[0].getFileName();

        Log.e("Exception 발생 :: ", throwable.getMessage());
        Log.e("Exception 정보 :: ", className + "." + methodName + " " + fileName + " " + lineNumber + " " + "번째 line");

    }

    public V getBasePresenter() {
        return this.mBasePresenter;
    }


    /**
     * repository getter/setter
     *
     * @Author 김동주 사원
     */



    @Override
    public void setUserRepository() {
        this.mUserRepository = new HttpInterceptor(this.mContext).getRetrofitForUserRepository().create(UserRepository.class);
    }

    @Override
    public UserRepository getUserRepository() {
        return this.mUserRepository;
    }

    @Override
    public void clearUserRepository() {
        if (this.mUserRepository != null) {
            this.mUserRepository = null;
        }
    }

    @Override
    public void setCardHistoryRepository() {
        this.mCardHistoryRepository = new HttpInterceptor(this.mContext).getRetrofitForCardHistoryRepository().create(CardHistoryRepository.class);
    }

    @Override
    public CardHistoryRepository getCardHistoryRepository() {
        return this.mCardHistoryRepository;
    }

    @Override
    public void clearCardHistoryRepository() {
        if (this.mCardHistoryRepository != null) {
            this.mCardHistoryRepository = null;
        }
    }

    @Override
    public void setAcntRepository() {
        this.mAcntRepository = new HttpInterceptor(this.mContext).getRetrofitForAcntRepository().create(AcntRepository.class);
    }

    @Override
    public AcntRepository getAcntRepository() {
        return this.mAcntRepository;
    }

    @Override
    public void clearAcntRepository() {
        if (this.mAcntRepository != null) {
            this.mAcntRepository = null;
        }
    }

    @Override
    public void setBudgetRepository() {
        this.mBudgetRepository = new HttpInterceptor(this.mContext).getRetrofitForBudgetRepository().create(BudgetRepository.class);
    }

    @Override
    public BudgetRepository getBudgetRepository() {
        return this.mBudgetRepository;
    }

    @Override
    public void clearBudgetRepository() {
        if (this.mBudgetRepository != null) {
            this.mBudgetRepository = null;
        }
    }


    @Override
    public void setTripRepository() {
        this.mTripRepository = new HttpInterceptor(this.mContext).getRetrofitForTripRepository().create(TripRepository.class);
    }

    @Override
    public TripRepository getTripRepository() {
        return this.mTripRepository;
    }

    @Override
    public void clearTripRepository() {
        if (this.mTripRepository != null) {
            this.mTripRepository = null;
        }
    }

    @Override
    public void setDeptRepository() {
        this.mDeptRepository = new HttpInterceptor(this.mContext).getRetrofitForDeptRepository().create(DeptRepository.class);
    }

    @Override
    public DeptRepository getDeptRepository() {
        return this.mDeptRepository;
    }

    @Override
    public void clearDeptRepository() {
        if (this.mDeptRepository != null) {
            this.mDeptRepository = null;
        }
    }


    @Override
    public void setSlipHeaderRepository() {
        this.mSlipHeaderRepository = new HttpInterceptor(this.mContext).getRetrofitForSlipHeaderRepository().create(SlipHeaderRepository.class);
    }

    @Override
    public SlipHeaderRepository getSlipHeaderRepository() {
        return this.mSlipHeaderRepository;
    }

    @Override
    public void clearSlipHeaderRepository() {
        if (this.mSlipHeaderRepository != null) {
            this.mSlipHeaderRepository = null;
        }
    }



}
