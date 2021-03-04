package hcc.accouting.haccounting.ui.history.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.common.dto.SearchListDialogDto;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.flag.ActivityRequestResultFlag;
import hcc.accouting.haccounting.common.flag.CardHistoryFlag;
import hcc.accouting.haccounting.common.flag.DialogFlag;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenterImpl;
import hcc.accouting.haccounting.ui.history.interactor.HistoryFragmentInterator;
import hcc.accouting.haccounting.ui.history.interactor.HistoryFragmentInteratorImpl;
import hcc.accouting.haccounting.ui.history.view.HistoryFragmentView;

public class HistoryFragmentPresenterImpl<V extends HistoryFragmentView> extends BasePresenterImpl<V> implements HistoryFragmentPresenter<V> {

    private HistoryFragmentInterator mInteractor;

    public HistoryFragmentPresenterImpl() {
    }


    @Override
    public void init() {
        this.mInteractor = new HistoryFragmentInteratorImpl<HistoryFragmentPresenter>();
        this.mInteractor.onAttach(this);
        this.mInteractor.initCardHistories();
        this.mInteractor.setInit(true);
        // 레파지토리 셋팅
        User user = getBaseView().getSharedPrersUser();
        String accessToken = user.getAccessToken();
        if (accessToken != null) {
            this.mInteractor.setCardHistoryRepository();
        } else {
            getBaseView().showMessage("로그인 기한 만료, 재 로그인 부탁드립니다");
        }
    }

    @Override
    public void initView() {
        getBaseView().setScrollViewOnScrollChangeListener();

        getBaseView().showProgressDialog();

        User user = getBaseView().getSharedPrersUser();

        String accessToken = user.getAccessToken();

        Log.d("ddddddddddddddd;::", user.toString());

        if (accessToken != null) {
            this.mInteractor.setCardHistoryRepository();
        } else {
            getBaseView().showMessage("로그인 기한 만료, 재 로그인 부탁드립니다");
        }

        String compCd = user.getCompCd();
        String empNo = user.getEmpNo();


        //초기:: 미작성 및 offest = 0;
        String status = CardHistoryFlag.CARD_NONE_WRITE_STATUS;

        long offset = CardHistoryFlag.CARD_HISTORY_FRIST_OFFSET;
        long limit = CardHistoryFlag.CARD_HISTORY_LIMIT;

        this.mInteractor.setCardHistoryStatus(CardHistoryFlag.CARD_NONE_WRITE_STATUS); //상태 값 저장
        this.mInteractor.getCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(status, compCd, empNo, offset, limit);

    }

    @Override
    public void onSuccessGetCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(List<CardHistory> cardHistories, String status) {
        List<CardHistory> prevCardHistories = this.mInteractor.getCardHistories();
        int prevCardHistoriesSize = prevCardHistories.size();
        int newCardHistoriesSize = cardHistories.size();

        String prevStatus = this.mInteractor.getCardHistoryStatus();

        if (prevCardHistoriesSize > 0 && prevStatus.equals(status)) {

            this.mInteractor.setCardHistoriesAddAll(cardHistories);
            getBaseView().cardHistoriesAdapterNotifyItemRangeInserted(prevCardHistoriesSize, newCardHistoriesSize);

        } else {

            //최초 List
            if (!prevStatus.equals(status)) {//상태값이 변경 되었을 때

                this.mInteractor.setCardHistoryStatus(status);

                //재 초기화
                this.mInteractor.setCardHistories(null);
                this.mInteractor.initCardHistories();
                getBaseView().clearHistoriesAdapter(); //adapter clear

                this.mInteractor.setCardHistories(cardHistories);
                getBaseView().setCardHistoriesViewByItem(cardHistories);
            }

            if (prevCardHistoriesSize == 0) { //첫 초기화
                this.mInteractor.setCardHistories(cardHistories);
                getBaseView().setCardHistoriesViewByItem(cardHistories);
            }

            boolean isInit = this.mInteractor.isInit();

            if(isInit) {
                this.mInteractor.setInit(false);
            }
        }

        //버튼 변경

        if (status.equals(CardHistoryFlag.CARD_NONE_WRITE_STATUS)) {

            getBaseView().hideCancelBtn();
            getBaseView().showWriteBtn();

        } else if (status.equals(CardHistoryFlag.CARD_TRANSFER_STATUS)) {

            getBaseView().hideWriteBtn();
            getBaseView().showCancelBtn();

        } else { //결재중, 결제완료

            getBaseView().hideCancelBtn();
            getBaseView().hideWriteBtn();

        }

        getBaseView().goneProgressDialog();

    }

    @Override
    public void onScrollChange(int difference) {

        boolean isInit = this.mInteractor.isInit();

        if ((!isInit) && difference <= 50) {
            List<CardHistory> cardHistories = mInteractor.getCardHistories();

            int cardHistoriesSize = cardHistories.size();

            if (cardHistoriesSize > 0) {

                User user = getBaseView().getSharedPrersUser();
                String compCd = user.getCompCd();
                String empNo = user.getEmpNo();

                long lastRownum = cardHistories.get(cardHistoriesSize - 1).getRownum();
                long offset = lastRownum + 1;
                long limit = CardHistoryFlag.CARD_HISTORY_LIMIT;

                String status = getCardHistoryStatusByRbtnChecked(); //init

                Log.e("ddddstatuscall::", status);

                getBaseView().showProgressDialog();

                this.mInteractor.getCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(status, compCd, empNo, offset, limit);

            }
        }
    }

    @Override
    public String getCardHistoryStatusByRbtnChecked() {
        String status = CardHistoryFlag.CARD_NONE_WRITE_STATUS; //init

        if (getBaseView().isNoneWriteRbtnChecked()) {
            status = CardHistoryFlag.CARD_NONE_WRITE_STATUS;

        } else if (getBaseView().isTransferRbtnChecked()) {
            status = CardHistoryFlag.CARD_TRANSFER_STATUS;

        } else if (getBaseView().isApprovingRbtnChecked()) {
            status = CardHistoryFlag.CARD_APPROVING_STATUS;

        } else if (getBaseView().isApprovedRbtnChecked()) {
            status = CardHistoryFlag.CARD_APPROVED_STATUS;

        }
        return status;
    }

    @Override
    public void onClickCardHistoryStatus() {
        User user = getBaseView().getSharedPrersUser();
        String compCd = user.getCompCd();
        String empNo = user.getEmpNo();

        //항상 초기화가 된다.
        this.mInteractor.setCardHistories(null);
        this.mInteractor.initCardHistories();

        long offset = CardHistoryFlag.CARD_HISTORY_FRIST_OFFSET;
        long limit = CardHistoryFlag.CARD_HISTORY_LIMIT;

        String status = getCardHistoryStatusByRbtnChecked();

        //삭제 버튼 show or gone
        if (status.equals(CardHistoryFlag.CARD_NONE_WRITE_STATUS)) {
            getBaseView().showDeleteBtn();
        } else {
            getBaseView().goneDeleteBtn();
        }

        getBaseView().showProgressDialog();

        this.mInteractor.getCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(status, compCd, empNo, offset, limit);

    }

    @Override
    public void onClickCardHistoryContent(int position) {
        String status = getCardHistoryStatusByRbtnChecked();

        if (status.equals(CardHistoryFlag.CARD_NONE_WRITE_STATUS) || status.equals(CardHistoryFlag.CARD_TRANSFER_STATUS)) {

            List<CardHistory> cardHistories = this.mInteractor.getCardHistories();
            int cardHistoriesSize = cardHistories.size();

            CardHistory cardHistory = cardHistories.get(position);
            boolean isChecked = cardHistory.isChecked();

            if (isChecked) {
                cardHistory.setChecked(false);
            } else {
                cardHistory.setChecked(true);
            }

            getBaseView().cardHistoriesAdapterNotifyItemChanged(position);
        }
    }

    @Override
    public void onClickAllSelection() {
        getBaseView().showProgressDialog();

        List<CardHistory> cardHistories = this.mInteractor.getCardHistories();
        int cardHistoriesSize = cardHistories.size();

        boolean isChecked = this.mInteractor.isCardHistoriesChecked();

        for (CardHistory cardHistory : cardHistories) {

            if (isChecked) {
                cardHistory.setChecked(false);
                this.mInteractor.setCardHistoriesChecked(false);
            } else {
                cardHistory.setChecked(true);
                this.mInteractor.setCardHistoriesChecked(true);
            }

        }
        getBaseView().cardHistoriesAdapterNotifyItemRangeChanged(0, cardHistoriesSize);

        getBaseView().goneProgressDialog();
    }

    @Override
    public void onClickWrite() {
        getBaseView().showProgressDialog();
        List<CardHistory> cardHistories = this.mInteractor.getCardHistories();

        List<CardHistory> selectedCardHistories = new ArrayList<>();

        for (CardHistory cardHistory : cardHistories) {
            boolean isChecked = cardHistory.isChecked();

            if (isChecked) {
                selectedCardHistories.add(cardHistory);
            }
        }

        int selectedCardHistoriesSize = selectedCardHistories.size();

        if (selectedCardHistoriesSize > 0) { //선택되면 다음으로 넘어간다.


            getBaseView().goneProgressDialog();
            getBaseView().navigateToWriteActivity(selectedCardHistories);
        }
    }

    @Override
    public void onClickTransferCancel() {
        getBaseView().showProgressDialog();

        List<CardHistory> cardHistories = this.mInteractor.getCardHistories();

        List<CardHistory> selectedCardHistories = new ArrayList<>();

        for (CardHistory cardHistory : cardHistories) {
            boolean isChecked = cardHistory.isChecked();

            if (isChecked) {
                selectedCardHistories.add(cardHistory);
            }
        }

        int selectedCardHistoriesSize = selectedCardHistories.size();

        if (selectedCardHistoriesSize > 0) { //선택되면 다음으로 넘어간다.

            ConfirmCancelDialogDto confirmCancelDialogDto = new ConfirmCancelDialogDto();
            confirmCancelDialogDto.setCardHistories(selectedCardHistories);

            int flag = DialogFlag.SLIP_TRANS_DELETE_CONFIRM;
            int requestCode = ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_SLIP_DELETE_REQUEST;


            getBaseView().goneProgressDialog();
            getBaseView().navigateToConfirmCancelDialogActivity(confirmCancelDialogDto, flag, requestCode);
        }

    }

    @Override
    public void onActivityResultForCardHistoryDeleteDialogResultOk() {
        //resume이 자동실행 된다.
    }

    @Override
    public void onActivityResultForTransferCancelDialogResultOk() {
        //resume이 자동실행 된다.
    }

    @Override
    public void onClickCardHistoryDelete() {
        getBaseView().showProgressDialog();

        List<CardHistory> cardHistories = this.mInteractor.getCardHistories();

        List<CardHistory> selectedCardHistories = new ArrayList<>();

        for (CardHistory cardHistory : cardHistories) {
            boolean isChecked = cardHistory.isChecked();

            if (isChecked) {
                selectedCardHistories.add(cardHistory);
            }
        }

        int selectedCardHistoriesSize = selectedCardHistories.size();

        if (selectedCardHistoriesSize > 0) { //선택되면 다음으로 넘어간다.

            ConfirmCancelDialogDto confirmCancelDialogDto = new ConfirmCancelDialogDto();
            confirmCancelDialogDto.setCardHistories(selectedCardHistories);

            int flag = DialogFlag.CARD_HISTORY_DELETE_CONFIRM;
            int requestCode = ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_CARD_HISTORY_DELETE_REQUEST;



            getBaseView().goneProgressDialog();
            getBaseView().navigateToConfirmCancelDialogActivity(confirmCancelDialogDto, flag, requestCode);
        }


    }


    @Override
    public void onPause() {
    //fragment pause flag setting
        boolean isCardHistoryFragmentPause = this.mInteractor.isCardHistoryFragmentPause();

        if (!isCardHistoryFragmentPause) {
            this.mInteractor.setCardHistoryFragmentPause(true);
        }
    }

    @Override
    public void onResume() {
        boolean isCardHistoryFragmentPause = this.mInteractor.isCardHistoryFragmentPause();

        if (isCardHistoryFragmentPause) {

            this.mInteractor.setCardHistoryFragmentPause(false);

            //재 초기화
            this.mInteractor.setCardHistories(null);
            this.mInteractor.initCardHistories();

            getBaseView().showProgressDialog();

            User user = getBaseView().getSharedPrersUser();

            String compCd = user.getCompCd();
            String empNo = user.getEmpNo();

            //초기:: 미작성 및 offest = 0;
            String status = getCardHistoryStatusByRbtnChecked();

            long offset = CardHistoryFlag.CARD_HISTORY_FRIST_OFFSET;
            long limit = CardHistoryFlag.CARD_HISTORY_LIMIT;

            this.mInteractor.setCardHistoryStatus(status); //상태 값 저장
            this.mInteractor.getCardHistoriesByStatusAndCompCdAndEmpNoAndOffsetAndLimitOrderByTransDateAndTransTimeDesc(status, compCd, empNo, offset, limit);
        }
    }


}

