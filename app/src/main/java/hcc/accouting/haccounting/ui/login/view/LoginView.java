package hcc.accouting.haccounting.ui.login.view;

import hcc.accouting.haccounting.ui.base.view.BaseView;

public interface LoginView  extends BaseView {

    public void onClickLogin();

    void navigateToPasswordActivity();

    void navigateToMainActivity();
}
