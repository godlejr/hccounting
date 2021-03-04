package hcc.accouting.haccounting.ui.history.interactor;

import java.util.List;

import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.ui.base.interactor.BaseInteractor;
import hcc.accouting.haccounting.ui.history.presenter.HistoryFragmentPresenter;

public interface HistoryFragmentInterator<V extends HistoryFragmentPresenter> extends BaseInteractor<V> {

    public void getCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(String status, String compCd, String empNo, long offset, long limit);

    public List<CardHistory> getCardHistories();

    public void setCardHistoriesAddAll(List<CardHistory> cardHistories);

    void setCardHistoriesRemoveAll(List<CardHistory> cardHistories);

    public void setCardHistories(List<CardHistory> cardHistories);

    public void setCardHistoryStatus(String mCardHistoryStatus);

    public String getCardHistoryStatus();

    boolean isCardHistoryFragmentPause();

    void setCardHistoryFragmentPause(boolean cardHistoryFragmentPause);

    public void initCardHistories();

    void setCardHistoriesChecked(boolean cardHistoriesChecked);

    boolean isCardHistoriesChecked();

    boolean isInit();

    void setInit(boolean init);
}
