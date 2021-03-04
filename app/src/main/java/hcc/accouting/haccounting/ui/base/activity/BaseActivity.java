package hcc.accouting.haccounting.ui.base.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.utils.ToastUtil;
import hcc.accouting.haccounting.repository.local.SharedPreferenceManager;
import hcc.accouting.haccounting.ui.base.view.BaseView;

/**
 * BaseActivity
 *
 * @author 김동주
 * @since 2019.02.13
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected Context mContext;

    protected ToastUtil toastUtil;

    protected SharedPreferenceManager mSharedPreferenceManager;

    protected ProgressDialog mProgressDialog;

    protected Handler mProgressDialogHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = BaseActivity.this;

        //toast
        this.toastUtil = new ToastUtil(this);

        //sharedPreference
        this.mSharedPreferenceManager = new SharedPreferenceManager(this);

        //ProgressDialog
        this.mProgressDialog = new ProgressDialog(this);

        this.mProgressDialogHandler = new Handler();


    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void showMessage(String message) {
        toastUtil.showMessage(message);
    }


    @Override
    public void setToolbarLayout() {

    }

    @Override
    public void setActivityFinish() {
        finish();
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
    public void showProgressDialog() {
        this.mProgressDialog.show();
        if (this.mProgressDialog.getWindow() != null) {
            this.mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        this.mProgressDialog.getWindow().getDecorView().setBackgroundResource(R.color.transparent);
        this.mProgressDialog.setCanceledOnTouchOutside(false);
        this.mProgressDialog.setContentView(R.layout.progress_dialog);

    }

    @Override
    public void goneProgressDialog() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }




    protected abstract void init();

    @Override
    public Context getContext() {
        return this.mContext;
    }
}
