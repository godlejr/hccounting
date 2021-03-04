package hcc.accouting.haccounting.ui.password.interactor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.utils.AES256Util;
import hcc.accouting.haccounting.common.utils.HttpErrorUtil;
import hcc.accouting.haccounting.repository.remote.interceptor.HttpInterceptor;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractorImpl;
import hcc.accouting.haccounting.ui.password.presenter.PasswordPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordInteractorImpl<V extends PasswordPresenter> extends BaseInteractorImpl<V> implements PasswordInteractor<V> {

    public PasswordInteractorImpl() {
    }

    @Override
    public void editAndGetUser(User modifiedUser) {

        try {
            modifiedUser.encrypt(new AES256Util());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Call<User> call = getUserRepository().editAndGetUser(modifiedUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    getBasePresenter().onSuccessEditAndGetUser(user);
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
