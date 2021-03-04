package hcc.accouting.haccounting.ui.main.view;

import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.ui.base.view.BaseView;

public interface MainView extends BaseView {
    void navigateToLoginActivity();

    void navigateToConfirmCancelDialogActivity(ConfirmCancelDialogDto confirmCancelDialogDto, int flag, int requestCode);

    void setTabLayout();

    void setTabAdapter();

    void showRequestPermissions();
}
