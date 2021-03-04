package hcc.accouting.haccounting.ui.write.view;

import java.util.List;

import butterknife.OnClick;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.dto.SearchListDialogDto;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.ui.base.view.BaseView;

public interface WriteView extends BaseView {
    void setNextBtnTitle(String title);

    void setPriceContent(String content);

    void setDateContent(String content);

    void setAcntContent(String content);

    void setDeptContent(String content);

    void setLeftPriceContent(String content);

    void setTripContent(String content);

    void setHtextContent(String content);

    void setDtextContent(String content);

    void setVendorNmContent(String content);

    void onClickNext();

    void onClickTripContent();

    void onClickAcntContent();

    void onClickDeptContent();

    void navigateToSearchListDialogActivity(SearchListDialogDto searchListDialogDto, int flag, int requestCode);

    void navigateToWriteActivity(int nextCardHistoryId, List<CardHistory> selectedCardHistories, List<SlipHeader> slipHeaders);

    void navigateToTransferActivity(int cardHistoryId, List<CardHistory> selectedCardHistories, List<SlipHeader> slipHeaders);
}
