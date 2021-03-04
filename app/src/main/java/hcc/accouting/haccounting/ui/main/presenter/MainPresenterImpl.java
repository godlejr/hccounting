package hcc.accouting.haccounting.ui.main.presenter;

import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.flag.ActivityRequestResultFlag;
import hcc.accouting.haccounting.common.flag.DialogFlag;
import hcc.accouting.haccounting.common.flag.PermissionFlag;
import hcc.accouting.haccounting.common.utils.StringUtil;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenterImpl;
import hcc.accouting.haccounting.ui.main.view.MainView;

public class MainPresenterImpl<V extends MainView> extends BasePresenterImpl<V> implements MainPresenter<V> {

    public MainPresenterImpl() {

    }

    @Override
    public void init() {
        User user = getBaseView().getSharedPrersUser();

        String compNm = user.getCompNm();
        String deptNm = user.getDeptNm();
        String empNm = user.getEmpNm();

        String maskEmpNm = empNm.substring(0,1) +"*"+ empNm.substring(2);

        getBaseView().setToolbarLayout();

        getBaseView().showToolbarTitle(compNm + " " + deptNm + " "+maskEmpNm+"님");

        getBaseView().setTabLayout();
        getBaseView().setTabAdapter();

        getBaseView().showRequestPermissions();
    }

    @Override
    public void onRequestPermissionsResultForSMSReceive(int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PermissionFlag.PERMISSION_DENIED) {
            //권한 등록 실패
        }
    }

    @Override
    public void onClickLogout() {

        User user = getBaseView().getSharedPrersUser();

        ConfirmCancelDialogDto confirmCancelDialogDto = new ConfirmCancelDialogDto();
        confirmCancelDialogDto.setUser(user);

        int flag = DialogFlag.LOGOUT_CONFIRM;
        int requestCode = ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_LOGOUT_REQUEST;
        getBaseView().navigateToConfirmCancelDialogActivity(confirmCancelDialogDto, flag, requestCode);
    }

    @Override
    public void onActivityResultForLogoutResultOk() {
        getBaseView().navigateToLoginActivity();
    }
}
