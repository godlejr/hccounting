package hcc.accouting.haccounting.ui.main.presenter;

import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;
import hcc.accouting.haccounting.ui.base.view.BaseView;
import hcc.accouting.haccounting.ui.main.view.MainView;

public interface MainPresenter<V extends MainView> extends BasePresenter<V> {
    void onRequestPermissionsResultForSMSReceive(int[] grantResults);

    void onClickLogout();

    void onActivityResultForLogoutResultOk();
}
