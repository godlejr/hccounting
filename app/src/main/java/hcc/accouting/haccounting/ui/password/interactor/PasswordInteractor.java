package hcc.accouting.haccounting.ui.password.interactor;

import java.io.UnsupportedEncodingException;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractor;
import hcc.accouting.haccounting.ui.password.presenter.PasswordPresenter;

public interface PasswordInteractor<V extends PasswordPresenter> extends BaseInteractor<V> {
    public void editAndGetUser(User modifiedUser) ;
}
