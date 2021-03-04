package hcc.accouting.haccounting.ui.transfer.view;

import java.util.List;

import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.ui.base.view.BaseView;

public interface TransferView extends BaseView {
    void setSlipHeadersViewByItem(List<SlipHeader> slipHeaders);

    void slipHeadersAdapterNotifyItemRangeInserted(int startPosition, int itemCount);

    void slipHeadersAdapterNotifyItemChanged(int position);

    void slipHeadersAdapterNotifyItemRangeChanged(int startPosition, int size);

    void clearSlipHeadersAdapter();

    void navigateToWriteActivity(int cardHistoryId, List<CardHistory> selectedCardHistories, List<SlipHeader> slipHeaders);
}
