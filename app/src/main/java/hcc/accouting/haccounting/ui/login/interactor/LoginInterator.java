package hcc.accouting.haccounting.ui.login.interactor;

import hcc.accouting.haccounting.ui.base.interactor.BaseInteractor;
import hcc.accouting.haccounting.ui.login.presenter.LoginPresenter;

public interface LoginInterator<V extends LoginPresenter> extends BaseInteractor<V> {

    public void getUserByIdAndPassword(String loginId, String loginPw);

}
