package hcc.accouting.haccounting.ui.password.presenter;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;
import hcc.accouting.haccounting.ui.password.view.PasswordView;

public interface PasswordPresenter<V extends PasswordView> extends BasePresenter<V> {
    public void onClickConfirm(String password1, String password2);

    void onSuccessEditAndGetUser(User user);
}
