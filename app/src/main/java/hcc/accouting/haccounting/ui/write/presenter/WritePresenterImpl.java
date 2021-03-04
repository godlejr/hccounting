package hcc.accouting.haccounting.ui.write.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import hcc.accouting.haccounting.common.dto.SearchListDialogDto;
import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Budget;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.SlipDetail;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.common.flag.ActivityRequestResultFlag;
import hcc.accouting.haccounting.common.flag.CardHistoryFlag;
import hcc.accouting.haccounting.common.flag.DialogFlag;
import hcc.accouting.haccounting.common.utils.StringUtil;
import hcc.accouting.haccounting.ui.base.presenter.BasePresenterImpl;
import hcc.accouting.haccounting.ui.write.interactor.WriteInteractor;
import hcc.accouting.haccounting.ui.write.interactor.WriteInteractorImpl;
import hcc.accouting.haccounting.ui.write.view.WriteView;

public class WritePresenterImpl<V extends WriteView> extends BasePresenterImpl<V> implements WritePresenter<V> {

    private WriteInteractor mInteractor;

    public WritePresenterImpl() {
    }


    @Override
    public void init(int cardHistoryId, List<CardHistory> selectedCardHistories, List<SlipHeader> slipHeaders) {
        this.mInteractor = new WriteInteractorImpl<WritePresenter>();
        this.mInteractor.onAttach(this);

        this.mInteractor.setCardHistoryId(cardHistoryId);
        this.mInteractor.setSelectedCardHistories(selectedCardHistories);
        this.mInteractor.setSlipHeaders(slipHeaders);

        getBaseView().setToolbarLayout();
        getBaseView().showToolbarTitle("전표 작성");

        getBaseView().showProgressDialog();

        User user = getBaseView().getSharedPrersUser();

        this.mInteractor.setAcntRepository();
        this.mInteractor.setTripRepository();

        CardHistory selectedCardHistory = selectedCardHistories.get(cardHistoryId);
        int selectedCardHistoriesSize = selectedCardHistories.size();

        String title = "다음";

        if (selectedCardHistoriesSize > 1) {
            int cardHistoryNo = cardHistoryId + 1; //0부터 시작하니까
            title = "다음(" + cardHistoryNo + "/" + selectedCardHistoriesSize + ")";
        }
        getBaseView().setNextBtnTitle(title);

        //모바일 관리번호 에 따라 전표 구분
        String mngNoMobile = selectedCardHistory.getMngNoMobile();


        //전표 생성 전
        if (mngNoMobile == null) {
            this.mInteractor.setFirstTransfer(true);

            String compCd = selectedCardHistory.getCompCd();
            String vendorNm = selectedCardHistory.getVendorNm();
            String currCd = selectedCardHistory.getCurrCd();
            float price = selectedCardHistory.getAmtTot();

            String date = selectedCardHistory.getTransDate();


            String amtTot = StringUtil.getWonMoneyFormatByPriceAndCurrCd(price, currCd);
            String dateWithHyphen = StringUtil.getCalculateDateFormatByDate(date); //전기일: 카드 사용일
            String time = StringUtil.getTime();

            String empDeptCd = user.getDeptCd(); // 기본값으로 본인 부서
            String empDeptNm = user.getDeptNm();
            String empNo = user.getEmpNo(); // 본인 사번
            String erpVendorId = user.getErpVendorId();
            String empNm = user.getEmpNm();

            String today = StringUtil.getToday();

            //--------background workload start--------//
            //전송할 전표헤더 생성(헤더적요랑, 출장품의ID는 계정 인터페이스 이후)
            this.mInteractor.initSlipHeader();

            SlipHeader slipHeader = mInteractor.getSlipHeader();

            slipHeader.setCompCd(compCd); //회사코드
            slipHeader.setPosDate(date); //전기일
            slipHeader.setEviDate(date); //증빙일, 전기일과 동일
            slipHeader.setCurrCd(currCd); //통화코드
            slipHeader.setStatus(CardHistoryFlag.CARD_NONE_WRITE_STATUS); //작성중
            slipHeader.setWriteUser(empNo);
            slipHeader.setWriteDate(today);
            slipHeader.setWriteTime(time);
            slipHeader.setChangeUser(empNo);
            slipHeader.setChangeDate(today);
            slipHeader.setChangeTime(time);


            //전송할 전표 디테일 생성(계정 코드랑, 상세적요는 계정 인터페이스 이후)
            SlipDetail slipDetail = new SlipDetail();
            slipDetail.setCompCd(compCd);
            slipDetail.setDeptCd(empDeptCd);
            slipDetail.setDeptNm(empDeptNm);
            slipDetail.setAmtTot(price);

            //임직원 코드 / 사용처 명
            slipDetail.setVendorCd(erpVendorId);
            slipDetail.setVendorNm(vendorNm);

            slipDetail.setWriteUser(empNo);
            slipDetail.setWriteDate(today);
            slipDetail.setWriteTime(time);

            slipDetail.setChangeUser(empNo);
            slipDetail.setChangeDate(today);
            slipDetail.setChangeTime(time);

            slipHeader.getSlipDetails().add(slipDetail); // 전표헤더에 전표디테일 연결

            this.mInteractor.setSlipHeader(slipHeader); //interactor에 전표 임시저장
            //--------background workload end--------//


            //--------foreground workload start--------//
            //헤더적요, 상세적요, 출장품의는 계정 인터페이스 이후
            getBaseView().setVendorNmContent(vendorNm);
            getBaseView().setPriceContent(amtTot + "원");
            getBaseView().setDateContent(dateWithHyphen);
            getBaseView().setDeptContent(empDeptNm);
            //--------foreground workload end--------//

            //출장품의 삽입(first)
            this.mInteractor.getTripsByCompCdAndEmpNoAndDate(compCd, empNo, date);


            //전표 생성 후
        } else {
            this.mInteractor.setFirstTransfer(false);
            this.mInteractor.setSlipHeaderRepository();
            this.mInteractor.getSlipHeaderByMngNoMobile(mngNoMobile);

        }

        getBaseView().goneProgressDialog();

    }

    @Override
    public void onSuccessGetAcntsWithSuggestionsByCompNoAndEmpNoAndVendorNm(List<Acnt> acnts) {
        //--------background workload start--------//
        // 첫번째 실행
        String acntCd = acnts.get(0).getAcntCd(); //계정 코드
        String acntNm = acnts.get(0).getAcntNm(); //계정 이름

        SlipHeader slipHeader = this.mInteractor.getSlipHeader();
        slipHeader.setTextH(acntNm);//헤더 적요

        String posDate = slipHeader.getPosDate();

        SlipDetail slipDetail = slipHeader.getSlipDetails().get(0);
        slipDetail.setTextD(acntNm); //상세 적요
        slipDetail.setAcntCd(acntCd); //계정 코드
        slipDetail.setAcntNm(acntNm); //계정 이름

        String textH = slipHeader.getTextH();
        String textD = slipDetail.getTextD();

        //--------background workload end--------//


        //--------foreground workload start--------//
        getBaseView().setHtextContent(textH);
        getBaseView().setDtextContent(textD);
        getBaseView().setAcntContent(acntNm);
        //--------foreground workload end--------//


        //계정에 따라 예산 조회

        User user = getBaseView().getSharedPrersUser();
        String deptCd = slipDetail.getDeptCd(); //전표기반
        String compCd = user.getCompCd();

        this.mInteractor.setBudgetRepository();

        this.mInteractor.getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(compCd, deptCd, acntCd, posDate);

    }

    @Override
    public void onSuccessGetBudgetByCompCdAndDeptCdAndAcntCd(Budget budget) {
        //--------background workload start--------//
        this.mInteractor.setBudget(budget);

        //출장품의 예산 체크
        Trip trip = this.mInteractor.getTrip();

        Integer tripAmt = null;
        if (trip != null) {
            tripAmt = trip.getTripAmt();

            if (tripAmt == null) {

                Integer dailyAmt = trip.getDailyAmt();
                Integer transAmt = trip.getTransAmt();
                Integer roomAmt = trip.getRoomAmt();
                Integer etcAmt = trip.getEtcAmt();

                tripAmt = dailyAmt + transAmt + etcAmt + roomAmt;
            }
        }

        String leftPrice = "";

        try {

            Float remBdget = budget.getRemBdget();

            if (tripAmt != null && tripAmt > 0) {
                remBdget += tripAmt;
            }

            leftPrice = StringUtil.getWonMoneyFormatByPriceAndCurrCd(remBdget, "KRW");// 예산의 금액통화는 KRW

        } catch (Exception e) {
            leftPrice = "0";
        }

        //--------background workload end--------//

        //--------foreground workload start--------//
        getBaseView().setLeftPriceContent(leftPrice + "원");
        getBaseView().goneProgressDialog();
        // --------foreground workload end--------//
    }

    @Override
    public void onClickNext(String textH, String textD) {

        getBaseView().showProgressDialog();
        SlipHeader slipHeader = this.mInteractor.getSlipHeader();

        //헤더 적요 및 상세 적요 업데이트

        slipHeader.setTextH(textH);
        SlipDetail slipDetail = slipHeader.getSlipDetails().get(0);
        slipDetail.setTextD(textD);

        String mngNoMobile = slipHeader.getMngNoMobile();

        User user = getBaseView().getSharedPrersUser();


        this.mInteractor.setSlipHeaderRepository();

        //전표 모바일 관리번호 유무 체크
        if (mngNoMobile == null) {
            //insert 전표 헤더, 디테일
            this.mInteractor.getSlipHeaderBySavingSlipHeaderAndSlipDetail(slipHeader);

        } else {
            //update 전표 헤더, 디테일

            String empNo = user.getEmpNo();
            String changeDate = StringUtil.getToday();
            String time = StringUtil.getTime();

            Trip trip = this.mInteractor.getTrip();

            if (trip != null) {
                String tripCd = trip.getTripCd();
                slipHeader.setBusiTripId(tripCd);
            } else {
                slipHeader.setBusiTripId(null);
            }

            slipHeader.setChangeDate(changeDate);
            slipHeader.setChangeUser(empNo);
            slipHeader.setChangeTime(time);
            slipDetail.setChangeDate(changeDate);
            slipDetail.setChangeUser(empNo);
            slipDetail.setChangeTime(time);

            this.mInteractor.getSlipHeaderByModifyingSlipHeaderAndSlipDetail(slipHeader);
        }
    }

    @Override
    public void onClickTripContent() {
        //전기일
        SlipHeader slipHeader = this.mInteractor.getSlipHeader();
        String date = slipHeader.getPosDate();

        SearchListDialogDto searchListDialogDto = new SearchListDialogDto();
        searchListDialogDto.setDate(date);

        int flag = DialogFlag.TRIP_SEARCH_LIST;
        int requestCode = ActivityRequestResultFlag.SEARCH_LIST_DIALOG_TRIP_REQUEST;
        getBaseView().goneProgressDialog();

        getBaseView().navigateToSearchListDialogActivity(searchListDialogDto, flag, requestCode);
    }

    @Override
    public void onClickAcntContent() {
        //가맹점이름
        List<CardHistory> selectedCardHistories = this.mInteractor.getSelectedCardHistories();
        int cardHistoryId = this.mInteractor.getCardHistoryId();
        CardHistory selectedCardHistory = selectedCardHistories.get(cardHistoryId);
        String vendorNm = selectedCardHistory.getVendorNm();

        SearchListDialogDto searchListDialogDto = new SearchListDialogDto();
        searchListDialogDto.setVendorNm(vendorNm);

        int flag = DialogFlag.ACNT_SEARCH_LIST;
        int requestCode = ActivityRequestResultFlag.SEARCH_LIST_DIALOG_ACNT_REQUEST;
        getBaseView().goneProgressDialog();

        getBaseView().navigateToSearchListDialogActivity(searchListDialogDto, flag, requestCode);

    }

    @Override
    public void onClickDeptContent() {
        int flag = DialogFlag.DEPT_SEARCH_LIST;
        int requestCode = ActivityRequestResultFlag.SEARCH_LIST_DIALOG_DEPT_REQUEST;
        getBaseView().goneProgressDialog();

        getBaseView().navigateToSearchListDialogActivity(null, flag, requestCode);

    }

    @Override
    public void onSuccessGetTripsByCompCdAndEmpNoAndDate(List<Trip> trips) {

        //출장품의가 전기일기준으로 있는 경우
        if (trips != null && trips.size() > 0) {
            Trip trip = trips.get(0); //가장 우선순위

            this.mInteractor.setTrip(trip);

            String tripCd = trip.getTripCd();
            String tripNm = trip.getTripNm();
            String acntCd = trip.getAcntCd();
            String acntNm = trip.getAcntNm();
            Integer tripAmt = trip.getTripAmt();

            SlipHeader slipHeader = this.mInteractor.getSlipHeader();
            String posDate = slipHeader.getPosDate();
            slipHeader.setBusiTripId(tripCd);
            slipHeader.setTextH(acntNm);
            SlipDetail slipDetail = slipHeader.getSlipDetails().get(0);

            slipDetail.setAcntCd(acntCd); //계정 코드
            slipDetail.setAcntNm(acntNm); //계정 이름
            slipDetail.setTextD(acntNm);

            getBaseView().setTripContent(tripNm);
            getBaseView().setAcntContent(acntNm);
            getBaseView().setDtextContent(acntNm);
            getBaseView().setHtextContent(acntNm);

            //출장계정에 따라 예산 조회(부서 금액 + 출장 금액 == 예산)
            User user = getBaseView().getSharedPrersUser();
            String deptCd = slipDetail.getDeptCd(); //전표기반
            String compCd = user.getCompCd();

            this.mInteractor.setBudgetRepository();

            this.mInteractor.getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(compCd, deptCd, acntCd, posDate);

            //출장품의가 없는 경우 계정조회
        } else {
            this.mInteractor.setTrip(null);

            User user = getBaseView().getSharedPrersUser();
            String compCd = user.getCompCd();
            String empNo = user.getEmpNo();

            int cardHistoryId = this.mInteractor.getCardHistoryId();
            List<CardHistory> selectedCardHistories = this.mInteractor.getSelectedCardHistories();
            CardHistory selectedCardHistory = selectedCardHistories.get(cardHistoryId);
            String vendorNm = selectedCardHistory.getVendorNm();

            //계정 삽입
            this.mInteractor.getAcntsWithSuggestionsByCompCdAndEmpNoAndVendorNm(compCd, empNo, vendorNm);

        }
    }

    @Override
    public void onActivityResultForTripDialogResultOk(Trip trip) {
        String tripCd = trip.getTripCd();

        SlipHeader slipHeader = this.mInteractor.getSlipHeader();

        slipHeader.setBusiTripId(tripCd);

        if (tripCd != null) { //출장 품의가 없으면 코드가 null
            this.mInteractor.setTrip(trip);
            String tripNm = trip.getTripNm();
            String acntNm = trip.getAcntNm();
            String acntCd = trip.getAcntCd().trim();

            slipHeader.setTextH(acntNm);
            String posDate = slipHeader.getPosDate();

            SlipDetail slipDetail = slipHeader.getSlipDetails().get(0);

            slipDetail.setTextD(acntNm);
            slipDetail.setAcntCd(acntCd);
            slipDetail.setAcntNm(acntNm);

            getBaseView().setTripContent(tripNm);
            getBaseView().setAcntContent(acntNm);
            getBaseView().setDtextContent(acntNm);
            getBaseView().setHtextContent(acntNm);

            //출장계정에 따라 예산 조회(부서 금액 + 출장 금액 == 예산)
            User user = getBaseView().getSharedPrersUser();
            String deptCd = slipDetail.getDeptCd(); //전표기반
            String compCd = user.getCompCd();

            this.mInteractor.setBudgetRepository();

            this.mInteractor.getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(compCd, deptCd, acntCd, posDate);

        } else {

            this.mInteractor.setTrip(null);
            getBaseView().setTripContent("");
        }
    }

    @Override
    public void onActivityResultForAcntDialogResultOk(Acnt acnt) {
        //--------background workload start--------//
        // 첫번째 실행
        String acntCd = acnt.getAcntCd(); //계정 코드
        String acntNm = acnt.getAcntNm(); //계정 이름

        SlipHeader slipHeader = this.mInteractor.getSlipHeader();
        slipHeader.setTextH(acntNm);//헤더 적요
        String posDate = slipHeader.getPosDate();

        SlipDetail slipDetail = slipHeader.getSlipDetails().get(0);
        slipDetail.setTextD(acntNm); //상세 적요
        slipDetail.setAcntCd(acntCd); //계정 코드
        slipDetail.setAcntNm(acntNm); //계정 이름

        String textH = slipHeader.getTextH();
        String textD = slipDetail.getTextD();

        //--------background workload end--------//


        //--------foreground workload start--------//
        getBaseView().setHtextContent(textH);
        getBaseView().setDtextContent(textD);
        getBaseView().setAcntContent(acntNm);
        //--------foreground workload end--------//


        //계정에 따라 예산 조회

        User user = getBaseView().getSharedPrersUser();
        String deptCd = slipDetail.getDeptCd(); //전표기반
        String compCd = user.getCompCd();

        this.mInteractor.setBudgetRepository();

        this.mInteractor.getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(compCd, deptCd, acntCd, posDate);
    }

    @Override
    public void onActivityResultForDeptDialogResultOk(Dept dept) {
        String deptNm = dept.getObjmNm();
        String deptCd = dept.getExpObjmCd();

        SlipHeader slipHeader = this.mInteractor.getSlipHeader();
        SlipDetail slipDetail = slipHeader.getSlipDetails().get(0);
        String posDate = slipHeader.getPosDate();
        //파라미터로 온 부서 저장
        slipDetail.setDeptCd(deptCd);
        slipDetail.setDeptNm(deptNm);

        getBaseView().setDeptContent(deptNm);
        //계정 및 부서 따라 예산 재 조회

        User user = getBaseView().getSharedPrersUser();
        String acntCd = slipDetail.getAcntCd(); //전표 기반
        String compCd = user.getCompCd();

        this.mInteractor.setBudgetRepository();

        this.mInteractor.getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(compCd, deptCd, acntCd, posDate);
    }

    @Override
    public void onSuccessGetSlipHeaderBySavingSlipHeaderAndSlipDetail(SlipHeader newSlipHeader) {
        int cardHistoryId = this.mInteractor.getCardHistoryId();
        List<CardHistory> selectedCardHistories = this.mInteractor.getSelectedCardHistories();

        CardHistory selectedCardHistory = selectedCardHistories.get(cardHistoryId);
        long seqNo = selectedCardHistory.getSeqNo();

        String mngNoMobile = newSlipHeader.getMngNoMobile();

        //update CardHistory setting
        CardHistory modifiedCardHistory = new CardHistory();
        modifiedCardHistory.setSeqNo(seqNo);
        modifiedCardHistory.setMngNoMobile(mngNoMobile);

        //repository setting
        User user = getBaseView().getSharedPrersUser();

        this.mInteractor.setCardHistoryRepository();

        this.mInteractor.setSlipHeader(newSlipHeader);
        //call
        this.mInteractor.getCardHistoryByModifyingCardHistory(modifiedCardHistory);
    }


    //전표 수정 후 이벤트 처리
    @Override
    public void onSuccessGetSlipHeaderByModifyingSlipHeaderAndSlipDetail(SlipHeader modifiedSlipHeader) {
        this.mInteractor.setSlipHeader(modifiedSlipHeader);

        int cardHistoryId = this.mInteractor.getCardHistoryId();
        List<CardHistory> selectedCardHistories = this.mInteractor.getSelectedCardHistories();
        int selectedCardHistoriesSize = selectedCardHistories.size();


        //결제용 전표리스트 저장 및 초기화
        //결제 전표 리스트
        if (cardHistoryId == 0) {
            this.mInteractor.initSlipHeaders();
        }

        List<SlipHeader> prevSlipHeaders = this.mInteractor.getSlipHeaders();
        int prevSlipHeadersSize = prevSlipHeaders.size();

        SlipHeader slipHeader = this.mInteractor.getSlipHeader();
        //첫 전표일 떄


        if (prevSlipHeadersSize < selectedCardHistoriesSize) {
            this.mInteractor.setSlipHeadersAdd(slipHeader);
        }
        //결제 전표 리스트
        List<SlipHeader> slipHeaders = this.mInteractor.getSlipHeaders();


        int slipHeaderSize = slipHeaders.size();

        if (slipHeaderSize  > cardHistoryId) {
            slipHeaders.set(cardHistoryId, modifiedSlipHeader);
        }

        //next 전표가 있으면
        if (cardHistoryId < selectedCardHistoriesSize - 1) {
            int nextCardHistoryId = cardHistoryId + 1;

            //다음 card history 및 결제용 전표들 전송 이관
            getBaseView().goneProgressDialog();

            getBaseView().navigateToWriteActivity(nextCardHistoryId, selectedCardHistories, slipHeaders);
        } else {
            // 전표확인 페이지 이동
            // 수정사항만 저장
            getBaseView().goneProgressDialog();

            getBaseView().navigateToTransferActivity(cardHistoryId, selectedCardHistories, slipHeaders);
        }

    }

    @Override
    public void onBackPressed() {
        getBaseView().showProgressDialog();

        int cardHistoryId = this.mInteractor.getCardHistoryId();
        List<CardHistory> selectedCardHistories = this.mInteractor.getSelectedCardHistories();
        int selectedCardHistoriesSize = selectedCardHistories.size();

        if (cardHistoryId == 0) {
            //그냥 종료하면된다.
            getBaseView().setActivityFinish();
        } else {
            int prevCardHistoryId = cardHistoryId - 1;
            //결제 전표 리스트
            List<SlipHeader> slipHeaders = this.mInteractor.getSlipHeaders();

            //이전 card history 및 결제용 전표들 전송 이관
            getBaseView().goneProgressDialog();

            getBaseView().navigateToWriteActivity(prevCardHistoryId, selectedCardHistories, slipHeaders);
        }

    }

    //전표 생성 후 이벤트 처리
    @Override
    public void onSuccessGetCardHistoryByModifyingCardHistory(CardHistory newCardHistory) {
        int cardHistoryId = this.mInteractor.getCardHistoryId();
        List<CardHistory> selectedCardHistories = this.mInteractor.getSelectedCardHistories();

        String mngNoMobile = newCardHistory.getMngNoMobile();

        //모바일 관리 번호 셋팅 (card history)
        selectedCardHistories.get(cardHistoryId).setMngNoMobile(mngNoMobile);


        //결제용 전표리스트 저장 및 초기화
        //결제 전표 리스트
        if (cardHistoryId == 0) {
            this.mInteractor.initSlipHeaders();
        }

        List<SlipHeader> prevSlipHeaders = this.mInteractor.getSlipHeaders();
        int prevSlipHeadersSize = prevSlipHeaders.size();

        int selectedCardHistoriesSize = selectedCardHistories.size();
        SlipHeader slipHeader = this.mInteractor.getSlipHeader();
        //첫 전표일 떄


        if (prevSlipHeadersSize < selectedCardHistoriesSize) {
            this.mInteractor.setSlipHeadersAdd(slipHeader);
        }

        //결제 전표 리스트
        List<SlipHeader> slipHeaders = this.mInteractor.getSlipHeaders();

        //next 전표가 있으면
        if (cardHistoryId < selectedCardHistoriesSize - 1) {
            int nextCardHistoryId = cardHistoryId + 1;

            //다음 card history 및 결제용 전표들 전송 이관
            getBaseView().goneProgressDialog();

            getBaseView().navigateToWriteActivity(nextCardHistoryId, selectedCardHistories, slipHeaders);
        } else {
            // 전표확인 페이지 이동
            //전송페이지 호출 card history 및 결제용 전표들 전송 이관
            getBaseView().goneProgressDialog();

            getBaseView().navigateToTransferActivity(cardHistoryId, selectedCardHistories, slipHeaders);

        }

    }

    @Override
    public void onSuccessGetSlipHeaderByMngNoMobile(SlipHeader slipHeader) {
        this.mInteractor.setSlipHeader(slipHeader);
        //--------background workload start--------//
        SlipDetail slipDetail = slipHeader.getSlipDetails().get(0);
        String compCd = slipDetail.getCompCd();
        String acntCd = slipDetail.getAcntCd();
        String acntNm = slipDetail.getAcntNm();
        String textD = slipDetail.getTextD();
        String deptCd = slipDetail.getDeptCd();
        String deptNm = slipDetail.getDeptNm();
        Float amtTot = slipDetail.getAmtTot();

        String price = StringUtil.getWonMoneyFormatByPriceAndCurrCd(amtTot, "KRW");
        String textH = slipHeader.getTextH();
        String tripCd = slipHeader.getBusiTripId();

        String date = slipHeader.getPosDate();
        String dateWithHyphen = StringUtil.getCalculateDateFormatByDate(date); //전기일은 항상 오늘 날짜

        slipHeader.setPosDate(date); //전기일
        slipHeader.setEviDate(date); //증빙일, 전기일과 동일

        //가맹점이름
        String vendorNm = slipDetail.getVendorNm();
        //--------background workload end--------//


        //--------foreground workload start--------//

        getBaseView().setVendorNmContent(vendorNm);
        getBaseView().setPriceContent(price + "원");
        getBaseView().setDateContent(dateWithHyphen);
        getBaseView().setDeptContent(deptNm);

        getBaseView().setDeptContent(deptNm);
        getBaseView().setHtextContent(textH);
        getBaseView().setDtextContent(textD);
        getBaseView().setAcntContent(acntNm);

        //--------foreground workload end--------//

        User user = getBaseView().getSharedPrersUser();

        //출장ID 별 출장 조회
        if (tripCd != null && tripCd.length() > 0) {

            this.mInteractor.setTripRepository();
            this.mInteractor.getTripByCompCdAndTripCdAndDeptCd(compCd, tripCd, deptCd);
        } else {
            //부서 예산 조회
            this.mInteractor.setBudgetRepository();
            String posDate = slipHeader.getPosDate();
            this.mInteractor.getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(compCd, deptCd, acntCd, posDate);
        }
    }

    @Override
    public void onSuccessGetTripByCompCdAndTripCdAndDeptCd(Trip trip) {
        this.mInteractor.setTrip(trip);
        String tripNm = trip.getTripNm();
        getBaseView().setTripContent(tripNm);


        SlipHeader slipHeader = this.mInteractor.getSlipHeader();
        SlipDetail slipDetail = slipHeader.getSlipDetails().get(0);
        String posDate = slipHeader.getPosDate();

        //출장계정에 따라 예산 조회(부서 금액 + 출장 금액 == 예산)
        User user = getBaseView().getSharedPrersUser();
        String deptCd = slipDetail.getDeptCd(); //전표기반
        String acntCd = slipDetail.getAcntCd();
        String compCd = user.getCompCd();

        this.mInteractor.setBudgetRepository();

        this.mInteractor.getBudgetByCompCdAndDeptCdAndAcntCdAndPosDate(compCd, deptCd, acntCd, posDate);
    }


}
