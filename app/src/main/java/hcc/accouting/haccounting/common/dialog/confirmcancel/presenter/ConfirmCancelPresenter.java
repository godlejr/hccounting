package hcc.accouting.haccounting.common.dialog.confirmcancel.presenter;

import hcc.accouting.haccounting.common.dialog.confirmcancel.view.ConfirmCancelView;
import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;

public interface ConfirmCancelPresenter<V extends ConfirmCancelView> extends BasePresenter<V> {
    void init(ConfirmCancelDialogDto confirmCancelDialogDto, int flag);

    void onClickConfirm();

    void onClickCancel();

    void onSuccessGetIsSuccessByDeletingCardHistoriesAndSlipHeaders(Boolean isSuccess);

    void onSuccessGetIsSuccessByDeletingSlipHeadersAndModifyingCardHistories(Boolean isSuccess);
}
