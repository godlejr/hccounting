package hcc.accouting.haccounting.ui.intro.presenter;

import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;
import hcc.accouting.haccounting.ui.intro.view.IntroView;

public interface IntroPresenter<V extends IntroView> extends BasePresenter<V> {
    void onDestroySplash();

    void onAnimationEnd();
}
