package hcc.accouting.haccounting.ui.intro.presenter;

import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.flag.UserFlag;
import hcc.accouting.haccounting.repository.remote.async.UserRepository;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenterImpl;
import hcc.accouting.haccounting.ui.base.view.BaseView;
import hcc.accouting.haccounting.ui.intro.view.IntroView;

public class IntroPresenterImpl<V extends IntroView> extends BasePresenterImpl<V> implements IntroPresenter<V> {
    public IntroPresenterImpl() {
    }

    @Override
    public void init() {
        getBaseView().setFadeInAnimationListener();
        getBaseView().setSplashFadeInAnimation();
        getBaseView().showSplash();
    }

    @Override
    public void onDestroySplash() {
        User user = getBaseView().getSharedPrersUser();
        if (user == null) {
            getBaseView().navigateToLoginActivity();
        } else {

            // 0: 초기화
            int loginStatus = user.getLoginStatus();


            if (loginStatus == UserFlag.STABLE_LOGIN_STATUS) {
                getBaseView().navigateToMainActivity();
            }else{
                getBaseView().navigateToPasswordActivity();
            }
        }
    }

    @Override
    public void onAnimationEnd() {
        getBaseView().setSplashFadeOutAnimation();
    }
}
