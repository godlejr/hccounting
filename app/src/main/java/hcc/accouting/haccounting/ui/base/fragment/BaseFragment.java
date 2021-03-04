package hcc.accouting.haccounting.ui.base.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.utils.ToastUtil;
import hcc.accouting.haccounting.repository.local.SharedPreferenceManager;
import hcc.accouting.haccounting.ui.base.view.BaseView;

public abstract class BaseFragment extends Fragment implements BaseView {

    protected Context mContext;

    protected ToastUtil toastUtil;

    protected SharedPreferenceManager mSharedPreferenceManager;

    protected ProgressDialog mProgressDialog;

    protected Handler mProgressDialogHandler;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
        //toast
        this.toastUtil = new ToastUtil(mContext);

        //sharedPreference
        this.mSharedPreferenceManager = new SharedPreferenceManager(mContext);

        //ProgressDialog
        this.mProgressDialogHandler = new Handler();
        this.mProgressDialog = new ProgressDialog(mContext);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void showMessage(String message) {
        toastUtil.showMessage(message);
    }

    @Override
    public void setToolbarLayout() {

    }

    @Override
    public void showToolbarTitle(String message) {

    }

    @Override
    public void setSharedPrersUser(User user) {
        this.mSharedPreferenceManager.setUser(user);
    }

    @Override
    public User getSharedPrersUser() {
        return this.mSharedPreferenceManager.getUser();
    }

    @Override
    public void removeSharedPrersUser() {
        this.mSharedPreferenceManager.removeUser();
    }

    @Override
    public void setActivityFinish(){
        //fragment는 적용 대상 아님.
    }

    @Override
    public void showProgressDialog() {
        this.mProgressDialog.show();
        if (this.mProgressDialog.getWindow() != null) {
            this.mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        this.mProgressDialog.getWindow().getDecorView().setBackgroundResource(R.color.transparent);
        this.mProgressDialog.setContentView(R.layout.progress_dialog);
    }

    @Override
    public void goneProgressDialog() {
        this.mProgressDialogHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        }, 400);

    }

    protected abstract void init();

    protected abstract void initView();

    @Override
    public Context getContext() {
        return this.mContext;
    }
}
