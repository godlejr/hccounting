package hcc.accouting.haccounting.common.dialog.searchlist.view;

import java.util.List;

import hcc.accouting.haccounting.common.dto.SearchListDialogResultOkDto;
import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.ui.base.view.BaseView;

public interface SearchListDialogView extends BaseView {
    void setTitleContent(String content);

    void setSearchListEditTextChangedListener();

    void setScrollViewOnScrollChangeListener();

    void setDeptsViewByItem(List<Dept> depts);

    void deptsAdapterNotifyItemRangeInserted(int startPosition, int itemCount);

    void deptsAdapterNotifyItemChanged(int position);

    void deptsAdapterNotifyItemRangeChanged(int startPosition, int size);

    void clearDeptsAdapter();

    void setAcntsViewByItem(List<Acnt> acnts);

    void acntsAdapterNotifyItemRangeInserted(int startPosition, int itemCount);

    void acntsAdapterNotifyItemChanged(int position);

    void acntsAdapterNotifyItemRangeChanged(int startPosition, int size);

    void clearAcntsAdapter();

    void setTripsViewByItem(List<Trip> trips);

    void tripsAdapterNotifyItemRangeInserted(int startPosition, int itemCount);

    void tripsAdapterNotifyItemChanged(int position);

    void tripsAdapterNotifyItemRangeChanged(int startPosition, int size);

    void clearTripsAdapter();

    void navigateToBackWithResultOk(SearchListDialogResultOkDto searchListDialogResultOkDto);

    void setSearchEdit(String message);
}
