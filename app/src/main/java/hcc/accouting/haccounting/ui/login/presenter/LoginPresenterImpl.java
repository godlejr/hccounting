package hcc.accouting.haccounting.ui.login.presenter;

import android.util.Log;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.flag.UserFlag;
import hcc.accouting.haccounting.common.utils.SHA256Util;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenterImpl;
import hcc.accouting.haccounting.ui.login.interactor.LoginInteractorImpl;
import hcc.accouting.haccounting.ui.login.interactor.LoginInterator;
import hcc.accouting.haccounting.ui.login.view.LoginView;

public class LoginPresenterImpl<V extends LoginView> extends BasePresenterImpl<V> implements LoginPresenter<V> {
    private LoginInterator mLoginInterator;

    public LoginPresenterImpl() {
    }

    @Override
    public void init() {
        this.mLoginInterator = new LoginInteractorImpl<LoginPresenter>();
        this.mLoginInterator.onAttach(this);

        //repository setting
        this.mLoginInterator.setUserRepository();

    }

    @Override
    public void onClickLogin(User user) {
        String loginId = user.getLoginId();
        String loginPw = user.getLoginPw();

        if (loginId.length() > 0 && loginPw.length() > 0) {

            if(!loginId.equals(loginPw)){
                loginPw = SHA256Util.encryptWithSHA256(loginPw);
            }

            this.mLoginInterator.getUserByIdAndPassword(loginId, loginPw);
        }
    }

    @Override
    public void onSuccessGetUserByIdAndPassword(User user) {


        if (user == null) {
            getBaseView().showMessage("아이디와 비밀번호를 확인하세요");
        } else {

          //  getBaseView().showMessage(user.toString());

            String accessToken = user.getAccessToken();

            if (!"".equals(accessToken) && accessToken != null) {


                //sharedPreference 저장
                getBaseView().setSharedPrersUser(user);

                this.mLoginInterator.setUserRepository();

                //추가 로직 구현
                //


                int loginStatus =  user.getLoginStatus(); // 0: 최초 로그인

                if(loginStatus == UserFlag.FIRST_LOGIN_STATUS){
                    getBaseView().navigateToPasswordActivity();
                }else{
                    getBaseView().navigateToMainActivity();
                }



            } else {
//                getBaseView().showMessage(user.getEmpNm()+ " " + accessToken.length()+ " ");
                getBaseView().showMessage("일시적인 장애입니다. 다시 시도해주세요.");

            }


        }
    }
}
