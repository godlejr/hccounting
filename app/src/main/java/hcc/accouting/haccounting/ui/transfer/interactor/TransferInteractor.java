package hcc.accouting.haccounting.ui.transfer.interactor;

import java.util.List;

import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractor;
import hcc.accouting.haccounting.ui.transfer.presenter.TransferPresenter;

public interface TransferInteractor<V extends TransferPresenter> extends BaseInteractor<V> {
    List<CardHistory> getSelectedCardHistories();

    void setSelectedCardHistories(List<CardHistory> selectedCardHistories);

    List<SlipHeader> getSlipHeaders();

    void setSlipHeaders(List<SlipHeader> slipHeaders);

    int getCardHistoryId();

    void setCardHistoryId(int cardHistoryId);

    void getBudgetCheckByTransferringSlipHeaders(List<SlipHeader> slipHeaders);
}
