package hcc.accouting.haccounting.common.dialog.confirmcancel.interactor;

import java.util.List;

import hcc.accouting.haccounting.common.dialog.confirmcancel.presenter.ConfirmCancelPresenter;
import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractor;

public interface ConfirmCancelInteractor<V extends ConfirmCancelPresenter> extends BaseInteractor<V> {
    ConfirmCancelDialogDto getConfirmCancelDialogDto();

    void setConfirmCancelDialogDto(ConfirmCancelDialogDto confirmCancelDialogDto);

    int getFlag();

    void setFlag(int flag);

    void getIsSuccessByDeletingCardHistoriesAndSlipHeaders(List<CardHistory> cardHistories);

    void getIsSuccessByDeletingSlipHeadersAndModifyingCardHistories(List<CardHistory> cardHistories);
}
