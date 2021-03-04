package hcc.accouting.haccounting.ui.login.presenter;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;
import hcc.accouting.haccounting.ui.login.view.LoginView;

public interface LoginPresenter<V extends LoginView> extends BasePresenter<V> {
    void onClickLogin(User user);

    void onSuccessGetUserByIdAndPassword(User user);
}
