package hcc.accouting.haccounting.common.dialog.confirmcancel.presenter;

import java.util.List;

import hcc.accouting.haccounting.common.dialog.confirmcancel.interactor.ConfirmCancelInteractor;
import hcc.accouting.haccounting.common.dialog.confirmcancel.interactor.ConfirmCancelInteractorImpl;
import hcc.accouting.haccounting.common.dialog.confirmcancel.view.ConfirmCancelView;
import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.flag.DialogFlag;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenterImpl;

public class ConfirmCancelPresenterImpl<V extends ConfirmCancelView> extends BasePresenterImpl<V> implements ConfirmCancelPresenter<V> {

    private ConfirmCancelInteractor mInteractor;

    @Override
    public void init(ConfirmCancelDialogDto confirmCancelDialogDto, int flag) {
        this.mInteractor = new ConfirmCancelInteractorImpl();
        this.mInteractor.onAttach(this);

        this.mInteractor.setFlag(flag);
        this.mInteractor.setConfirmCancelDialogDto(confirmCancelDialogDto);

        if (flag == DialogFlag.SLIP_TRANS_DELETE_CONFIRM) {
            getBaseView().setTitleContent(DialogFlag.SLIP_TRANS_DELETE_CONFIRM_TITLE);
        }

        if (flag == DialogFlag.CARD_HISTORY_DELETE_CONFIRM) {
            getBaseView().setTitleContent(DialogFlag.CARD_HISTORY_DELETE_CONFIRM_TITLE);
        }

        if (flag == DialogFlag.LOGOUT_CONFIRM) {
            getBaseView().setTitleContent(DialogFlag.LOGOUT_CONFIRM_TITLE);
        }

    }

    @Override
    public void onClickConfirm() {
        getBaseView().showProgressDialog();

        int flag = this.mInteractor.getFlag();
        ConfirmCancelDialogDto confirmCancelDialogDto = this.mInteractor.getConfirmCancelDialogDto();


        if (flag == DialogFlag.SLIP_TRANS_DELETE_CONFIRM) {
            List<CardHistory> cardHistories = confirmCancelDialogDto.getCardHistories();

            this.mInteractor.setCardHistoryRepository();
            this.mInteractor.getIsSuccessByDeletingSlipHeadersAndModifyingCardHistories(cardHistories);

        }

        if (flag == DialogFlag.CARD_HISTORY_DELETE_CONFIRM) {
            List<CardHistory> cardHistories = confirmCancelDialogDto.getCardHistories();
            this.mInteractor.setCardHistoryRepository();
            this.mInteractor.getIsSuccessByDeletingCardHistoriesAndSlipHeaders(cardHistories);

        }

        if (flag == DialogFlag.LOGOUT_CONFIRM) {
            //User action added
            getBaseView().removeSharedPrersUser();
            getBaseView().goneProgressDialog();
            getBaseView().navigateToBackWithResultOk();
        }


    }

    @Override
    public void onClickCancel() {
        getBaseView().setActivityFinish();
    }

    @Override
    public void onSuccessGetIsSuccessByDeletingCardHistoriesAndSlipHeaders(Boolean isSuccess) {
        getBaseView().goneProgressDialog();

        if (isSuccess != null && isSuccess) {
            getBaseView().navigateToBackWithResultOk();
        }

    }

    @Override
    public void onSuccessGetIsSuccessByDeletingSlipHeadersAndModifyingCardHistories(Boolean isSuccess) {
        getBaseView().goneProgressDialog();

        if (isSuccess != null && isSuccess) {
            getBaseView().navigateToBackWithResultOk();
        }

    }
}
