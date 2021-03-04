package hcc.accouting.haccounting.ui.home.view;

import hcc.accouting.haccounting.ui.base.view.BaseView;

public interface HomeFragmentView extends BaseView {
    void setNoticeContent(String content);

    void setBudgetTitleContent(String content);

    void setBudgetAcnt1Content(String content);

    void setBudgetPrice1Content(String content);

    void setBudgetAcnt2Content(String content);

    void setBudgetPrice2Content(String content);

    void setBudgetAcnt3Content(String content);

    void setBudgetPrice3Content(String content);

    void setTripTitleContent(String content);

    void setTripHouseCostContent(String content);

    void setTripNormalCostContent(String content);

    void setTripTravelCostContent(String content);

    void showTripContent();

    void goneTripContent();

    void showTripEmpty();

    void goneTripEmpty();

    void showBudgetContent();

    void goneBudgetContent();

    void showBudgetEmpty();

    void goneBudgetEmpty();
}
