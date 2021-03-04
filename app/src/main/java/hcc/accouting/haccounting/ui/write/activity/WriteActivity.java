package hcc.accouting.haccounting.ui.write.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.dialog.searchlist.activity.SearchListDialogActivity;
import hcc.accouting.haccounting.common.dto.SearchListDialogDto;
import hcc.accouting.haccounting.common.dto.SearchListDialogResultOkDto;
import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.common.flag.ActivityRequestResultFlag;
import hcc.accouting.haccounting.ui.base.activity.BaseActivity;
import hcc.accouting.haccounting.ui.transfer.activity.TransferActivity;
import hcc.accouting.haccounting.ui.write.presenter.WritePresenter;
import hcc.accouting.haccounting.ui.write.presenter.WritePresenterImpl;
import hcc.accouting.haccounting.ui.write.view.WriteView;

public class WriteActivity extends BaseActivity implements WriteView {

    private WritePresenter mPresenter;

    @BindView(R.id.tv_write_usage)
    TextView mVendorNmTv;

    @BindView(R.id.btn_writeactivity_next)
    Button mNextBtn;

    @BindView(R.id.tv_write_price)
    TextView mPriceTv;

    @BindView(R.id.tv_write_date)
    TextView mDateTv;

    @BindView(R.id.tv_write_acnt)
    TextView mAcntTv;

    @BindView(R.id.tv_write_dept)
    TextView mDeptTv;

    @BindView(R.id.tv_write_leftprice)
    TextView mLeftPriceTv;

    @BindView(R.id.tv_write_trip)
    TextView mTripTv;

    @BindView(R.id.et_write_text_h)
    TextView mHtextEt;

    @BindView(R.id.et_write_text_d)
    TextView mDtextEt;

    @BindView(R.id.in_writeactivity_toolbar)
    View mToolbar;

    private IncludedToolbarLayout mIncludedToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        ButterKnife.bind(this);
        this.mPresenter = new WritePresenterImpl();
        this.mPresenter.onAttach(this);

        init();
    }


    @Override
    protected void init() {
        List<CardHistory> cardHistories = (ArrayList<CardHistory>) getIntent().getExtras().getSerializable("cardHistories");
        int cardHistoryId = getIntent().getIntExtra("cardHistoryId", 0);
        List<SlipHeader> slipHeaders = (ArrayList<SlipHeader>) getIntent().getExtras().getSerializable("slipHeaders");

        this.mPresenter.init(cardHistoryId, cardHistories, slipHeaders);
    }


    @Override
    public void setToolbarLayout() {
        this.mIncludedToolbarLayout = new IncludedToolbarLayout();
        ButterKnife.bind(mIncludedToolbarLayout, mToolbar);
    }

    @Override
    public void showToolbarTitle(String message) {
        this.mIncludedToolbarLayout.mToolbarTitle.setText(message);
    }

    @Override
    public void setNextBtnTitle(String title) {
        this.mNextBtn.setText(title);
    }


    @Override
    public void setPriceContent(String content) {
        this.mPriceTv.setText(content);
    }

    @Override
    public void setDateContent(String content) {
        this.mDateTv.setText(content);
    }

    @Override
    public void setAcntContent(String content) {
        this.mAcntTv.setText(content);
    }

    @Override
    public void setDeptContent(String content) {
        this.mDeptTv.setText(content);
    }

    @Override
    public void setLeftPriceContent(String content) {
        this.mLeftPriceTv.setText(content);
    }

    @Override
    public void setTripContent(String content) {
        this.mTripTv.setText(content);
    }

    @Override
    public void setHtextContent(String content) {
        this.mHtextEt.setText(content);
    }

    @Override
    public void setDtextContent(String content) {
        this.mDtextEt.setText(content);
    }

    @Override
    public void setVendorNmContent(String content) {
        this.mVendorNmTv.setText(content);
    }

    @OnClick(R.id.btn_writeactivity_next)
    @Override
    public void onClickNext() {
        String textH = this.mHtextEt.getText().toString();
        String textD = this.mDtextEt.getText().toString();

        this.mPresenter.onClickNext(textH, textD);
    }

    @OnClick(R.id.tv_write_trip)
    @Override
    public void onClickTripContent() {
        this.mPresenter.onClickTripContent();
    }

    @OnClick(R.id.tv_write_acnt)
    @Override
    public void onClickAcntContent() {
        this.mPresenter.onClickAcntContent();
    }

    @OnClick(R.id.tv_write_dept)
    @Override
    public void onClickDeptContent() {
        this.mPresenter.onClickDeptContent();
    }

    @Override
    public void navigateToSearchListDialogActivity(SearchListDialogDto searchListDialogDto, int flag, int requestCode) {
        Intent intent = new Intent(this, SearchListDialogActivity.class);
        intent.putExtra("flag", flag);
        intent.putExtra("searchListDialogDto", searchListDialogDto);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void navigateToWriteActivity(int nextCardHistoryId, List<CardHistory> selectedCardHistories, List<SlipHeader> slipHeaders) {
        Intent intent = new Intent(this.mContext, WriteActivity.class);
        intent.putParcelableArrayListExtra("cardHistories", (ArrayList) selectedCardHistories);
        intent.putParcelableArrayListExtra("slipHeaders", (ArrayList) slipHeaders);
        intent.putExtra("cardHistoryId",nextCardHistoryId); //항상 첫 번째
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToTransferActivity(int cardHistoryId, List<CardHistory> selectedCardHistories, List<SlipHeader> slipHeaders) {
        Intent intent = new Intent(this.mContext, TransferActivity.class);
        intent.putParcelableArrayListExtra("cardHistories", (ArrayList) selectedCardHistories);
        intent.putParcelableArrayListExtra("slipHeaders", (ArrayList) slipHeaders);
        intent.putExtra("cardHistoryId",cardHistoryId); //항상 첫 번째

        startActivity(intent);
        finish();
    }


    @OnClick(R.id.ib_toolbartextback_back)
    public void onBackBtnClick(){
        this.mPresenter.onBackPressed();
    }
    @Override
    public void onBackPressed() {
        this.mPresenter.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ActivityRequestResultFlag.SEARCH_LIST_DIALOG_ACNT_REQUEST:
                switch (resultCode) {
                    case ActivityRequestResultFlag.RESULT_OK:
                        SearchListDialogResultOkDto searchListDialogResultOkDto = (SearchListDialogResultOkDto) data.getSerializableExtra("searchListDialogResultOkDto");
                        Acnt acnt = searchListDialogResultOkDto.getAnct();

                        this.mPresenter.onActivityResultForAcntDialogResultOk(acnt);
                        break;

                }
                break;

            case ActivityRequestResultFlag.SEARCH_LIST_DIALOG_DEPT_REQUEST:
                switch (resultCode) {
                    case ActivityRequestResultFlag.RESULT_OK:
                        SearchListDialogResultOkDto searchListDialogResultOkDto = (SearchListDialogResultOkDto) data.getSerializableExtra("searchListDialogResultOkDto");
                        Dept dept = searchListDialogResultOkDto.getDept();

                        this.mPresenter.onActivityResultForDeptDialogResultOk(dept);
                        break;

                }
                break;

            case ActivityRequestResultFlag.SEARCH_LIST_DIALOG_TRIP_REQUEST:
                switch (resultCode) {
                    case ActivityRequestResultFlag.RESULT_OK:
                        SearchListDialogResultOkDto searchListDialogResultOkDto = (SearchListDialogResultOkDto) data.getSerializableExtra("searchListDialogResultOkDto");
                        Trip trip = searchListDialogResultOkDto.getTrip();

                        this.mPresenter.onActivityResultForTripDialogResultOk(trip);
                        break;

                }
                break;

        }
    }

    static class IncludedToolbarLayout {

        @BindView(R.id.ib_toolbartextback_back)
        ImageButton mToolbarBackBtn;

        @BindView(R.id.tv_toolbartextback_title)
        TextView mToolbarTitle;
    }


}
