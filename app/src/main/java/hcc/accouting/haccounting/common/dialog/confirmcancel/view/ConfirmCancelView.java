package hcc.accouting.haccounting.common.dialog.confirmcancel.view;

import hcc.accouting.haccounting.ui.base.view.BaseView;

public interface ConfirmCancelView extends BaseView {
    void setTitleContent(String content);

    void navigateToBackWithResultOk();


    void navigateToBack();
}
