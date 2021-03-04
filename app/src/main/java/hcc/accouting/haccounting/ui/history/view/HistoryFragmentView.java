package hcc.accouting.haccounting.ui.history.view;

import java.util.List;

import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.ui.base.view.BaseView;

public interface HistoryFragmentView extends BaseView {
    public void setCardHistoriesViewByItem(List<CardHistory> cardHistories);

    public void clearHistoriesAdapter();

    public void setScrollViewOnScrollChangeListener();

    void showDeleteBtn();

    void goneDeleteBtn();

    public Boolean isNoneWriteRbtnChecked();

    public Boolean isTransferRbtnChecked();

    public Boolean isApprovingRbtnChecked();

    public Boolean isApprovedRbtnChecked();

    public void cardHistoriesAdapterNotifyItemRangeInserted(int startPosition, int itemCount);

    public void onClickCardHistoryStatus();

    public void cardHistoriesAdapterNotifyItemChanged(int position);

    public void showCancelBtn();

    public void hideCancelBtn();

    public void showWriteBtn();

    public void hideWriteBtn();

    public void onClickAllSelection();

    public void onClickWrite();

    public void cardHistoriesAdapterNotifyItemRangeChanged(int startPosition, int size);

    public void navigateToWriteActivity(List<CardHistory> cardHistories);

    void navigateToConfirmCancelDialogActivity(ConfirmCancelDialogDto confirmCancelDialogDto, int flag, int requestCode);
}
