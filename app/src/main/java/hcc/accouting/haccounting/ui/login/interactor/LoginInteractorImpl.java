package hcc.accouting.haccounting.ui.login.interactor;

import java.io.IOException;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.utils.HttpErrorUtil;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractor;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractorImpl;
import hcc.accouting.haccounting.ui.login.presenter.LoginPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl<V extends LoginPresenter> extends BaseInteractorImpl<V> implements LoginInterator<V> {

    public LoginInteractorImpl() {
    }

    @Override
    public void onAttach(V basePresenter) {
        super.onAttach(basePresenter);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void getUserByIdAndPassword(String loginId, String loginPw) {
        Call<User> call = getUserRepository().getUserByIdAndPassword(loginId, loginPw);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    getBasePresenter().onSuccessGetUserByIdAndPassword(user);
                } else {
                    getBasePresenter().onHttpError(new HttpErrorUtil().responseHandler(response));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                showThrowableLog(t);
                getBasePresenter().onHttpError(null);
            }
        });
    }


}
