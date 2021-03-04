package hcc.accouting.haccounting.ui.transfer.presenter;

import java.util.List;

import hcc.accouting.haccounting.common.entity.BudgetCheck;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;
import hcc.accouting.haccounting.ui.transfer.view.TransferView;

public interface TransferPresenter<V extends TransferView> extends BasePresenter<V> {
    void init(int cardHistoryId, List<CardHistory> cardHistories, List<SlipHeader> slipHeaders);

    void onBackPressed();

    void onClickTransfer();

    void onSuccessGetBudgetCheckByTransferringSlipHeaders(BudgetCheck budgetCheck);
}
