package hcc.accouting.haccounting.ui.password.presenter;

import android.util.Log;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.flag.UserFlag;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenterImpl;
import hcc.accouting.haccounting.ui.password.interactor.PasswordInteractor;
import hcc.accouting.haccounting.ui.password.interactor.PasswordInteractorImpl;
import hcc.accouting.haccounting.ui.password.view.PasswordView;

public class PasswordPresenterImpl<V extends PasswordView> extends BasePresenterImpl<V> implements PasswordPresenter<V> {

    private PasswordInteractor mPasswordInteractor;

    public PasswordPresenterImpl() {
    }

    @Override
    public void init() {
        this.mPasswordInteractor = new PasswordInteractorImpl<PasswordPresenter>();
        this.mPasswordInteractor.onAttach(this);
        this.mPasswordInteractor.setUserRepository();
    }

    @Override
    public void onClickConfirm(String password1, String password2) {
        if(password1.length() > 5 && password2.length() > 5 ){

            if(password1.equals(password2)){


                //기존 유저
                User user = getBaseView().getSharedPrersUser();
                String compCde = user.getCompCd();
                String empNo = user.getEmpNo();

                //수정될 유저
                User modifiedUser = new User();
                modifiedUser.setCompCd(compCde);
                modifiedUser.setEmpNo(empNo);

                //later 비밀번호 암호화..
                modifiedUser.setLoginPw(password1);
                modifiedUser.setLoginStatus(UserFlag.STABLE_LOGIN_STATUS); // 1: 안정된 상태(초기화 이후)

                //Progress Dialog 시작
                getBaseView().showProgressDialog();

                //Data Fetch 시작
                this.mPasswordInteractor.editAndGetUser(modifiedUser);


            }else{
                getBaseView().showMessage("비밀번호가 일치하지 않습니다");
            }

        }else{
            getBaseView().showMessage("비밀번호 길이는 6자 이상으로 입력하세요");
        }
    }

    @Override
    public void onSuccessEditAndGetUser(User user) {
        getBaseView().showMessage("비밀번호가 변경되었습니다");

        Log.e(user.getAccessToken(), user.toString());
        getBaseView().setSharedPrersUser(user);

        //Progress Dialog 끝
        getBaseView().goneProgressDialog();
        getBaseView().navigateToMainActivity();
    }
}
