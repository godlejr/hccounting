package hcc.accouting.haccounting.ui.transfer.presenter;

import java.util.List;

import hcc.accouting.haccounting.common.entity.BudgetCheck;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenterImpl;
import hcc.accouting.haccounting.ui.transfer.interactor.TransferInteractor;
import hcc.accouting.haccounting.ui.transfer.interactor.TransferInteractorImpl;
import hcc.accouting.haccounting.ui.transfer.view.TransferView;

public class TransferPresenterImpl<V extends TransferView> extends BasePresenterImpl<V> implements TransferPresenter<V> {


    private TransferInteractor mInteractor;

    public TransferPresenterImpl() {
    }

    @Override
    public void init(int cardHistoryId, List<CardHistory> cardHistories, List<SlipHeader> slipHeaders) {
        this.mInteractor = new TransferInteractorImpl<TransferPresenter>();
        this.mInteractor.onAttach(this);

        this.mInteractor.setCardHistoryId(cardHistoryId);
        this.mInteractor.setSelectedCardHistories(cardHistories);
        this.mInteractor.setSlipHeaders(slipHeaders);

        getBaseView().setToolbarLayout();
        getBaseView().showToolbarTitle("전표 전송");

        getBaseView().setSlipHeadersViewByItem(slipHeaders);
    }

    @Override
    public void onBackPressed() {
        int cardHistoryId = this.mInteractor.getCardHistoryId();
        List<CardHistory> selectedCardHistories = this.mInteractor.getSelectedCardHistories();

        //결제 전표 리스트
        List<SlipHeader> slipHeaders = this.mInteractor.getSlipHeaders();

        //이전 card history 및 결제용 전표들 전송 이관
        getBaseView().goneProgressDialog();
        getBaseView().navigateToWriteActivity(cardHistoryId, selectedCardHistories, slipHeaders);
    }

    @Override
    public void onClickTransfer() {
        getBaseView().showProgressDialog();
        this.mInteractor.setSlipHeaderRepository();
        List<SlipHeader> slipHeaders = this.mInteractor.getSlipHeaders();

        this.mInteractor.getBudgetCheckByTransferringSlipHeaders(slipHeaders);
    }

    @Override
    public void onSuccessGetBudgetCheckByTransferringSlipHeaders(BudgetCheck budgetCheck) {
        if (budgetCheck != null) {
            Boolean isSuccess = budgetCheck.getIsSuccess();
            String message = "3rd party 전표 전송 실패";
            if (isSuccess != null && isSuccess) {
                message = "전표 전송 성공";
                getBaseView().setActivityFinish();
            } else {
                if (!isSuccess) {
                    message = budgetCheck.getReturnMsg();
                }
            }
            getBaseView().showMessage(message);

        }
        getBaseView().goneProgressDialog();
    }
}
