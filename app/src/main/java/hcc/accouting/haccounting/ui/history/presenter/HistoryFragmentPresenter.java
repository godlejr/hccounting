package hcc.accouting.haccounting.ui.history.presenter;

import java.util.List;

import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenter;
import hcc.accouting.haccounting.ui.history.view.HistoryFragmentView;

public interface HistoryFragmentPresenter<V extends HistoryFragmentView> extends BasePresenter<V> {
    void onSuccessGetCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(List<CardHistory> cardHistories, String status);

    void onScrollChange(int difference);

    String getCardHistoryStatusByRbtnChecked();

    void onClickCardHistoryStatus();

    void onClickCardHistoryContent(int position);

    void onClickAllSelection();

    void onClickWrite();


    void onPause();

    void onResume();

    void onClickTransferCancel();

    void onActivityResultForCardHistoryDeleteDialogResultOk();

    void onActivityResultForTransferCancelDialogResultOk();

    void onClickCardHistoryDelete();
}
