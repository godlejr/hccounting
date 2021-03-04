package hcc.accouting.haccounting.ui.intro.view;

import hcc.accouting.haccounting.ui.base.view.BaseView;

public interface IntroView extends BaseView {

    void showSplash();

    void navigateToMainActivity();

    void navigateToLoginActivity();

    void navigateToPasswordActivity();

    void setSplashFadeOutAnimation();

    void setSplashFadeInAnimation();

    void setFadeInAnimationListener();
}
