package hcc.accouting.haccounting.ui.base.view;

import android.content.Context;

import java.io.UnsupportedEncodingException;

import hcc.accouting.haccounting.common.entity.User;

public interface BaseView {

    public void showMessage(String message);

    public void setToolbarLayout();

    void setActivityFinish();

    public void showToolbarTitle(String message);

    public void showProgressDialog();

    public void goneProgressDialog();


    public void setSharedPrersUser(User user);

    public User getSharedPrersUser() ;

    public void removeSharedPrersUser();

    public Context getContext();


}
