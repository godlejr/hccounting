package hcc.accouting.haccounting.ui.transfer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.ui.base.activity.BaseActivity;
import hcc.accouting.haccounting.ui.transfer.adapter.SlipHeadersAdapter;
import hcc.accouting.haccounting.ui.transfer.presenter.TransferPresenter;
import hcc.accouting.haccounting.ui.transfer.presenter.TransferPresenterImpl;
import hcc.accouting.haccounting.ui.transfer.view.TransferView;
import hcc.accouting.haccounting.ui.write.activity.WriteActivity;

public class TransferActivity extends BaseActivity implements TransferView {

    private TransferPresenter mPresenter;
    private IncludedToolbarLayout mIncludedToolbarLayout;
    private SlipHeadersAdapter mSlipHeadersAdapter;

    @BindView(R.id.in_transfer_toolbar)
    View mToolbar;

    @BindView(R.id.rv_transfer)
    RecyclerView mTransferRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        ButterKnife.bind(this);
        this.mPresenter = new TransferPresenterImpl<>();
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

    @OnClick(R.id.ib_toolbartextback_back)
    public void onBackBtnClick() {
        this.mPresenter.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        this.mPresenter.onBackPressed();
    }

    @Override
    public void setSlipHeadersViewByItem(List<SlipHeader> slipHeaders) {
        this.mSlipHeadersAdapter = new SlipHeadersAdapter(this.mPresenter, slipHeaders, mContext, R.layout.item_transfer_slip);
        this.mTransferRv.setAdapter(this.mSlipHeadersAdapter);
        this.mTransferRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public void slipHeadersAdapterNotifyItemRangeInserted(int startPosition, int itemCount) {
        if (this.mSlipHeadersAdapter != null) {
            this.mSlipHeadersAdapter.notifyItemRangeInserted(startPosition, itemCount);
        }
    }

    @Override
    public void slipHeadersAdapterNotifyItemChanged(int position) {
        if (this.mSlipHeadersAdapter != null) {
            this.mSlipHeadersAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void slipHeadersAdapterNotifyItemRangeChanged(int startPosition, int size) {
        if (this.mSlipHeadersAdapter != null) {
            this.mSlipHeadersAdapter.notifyItemRangeChanged(startPosition, size);
        }
    }

    @Override
    public void clearSlipHeadersAdapter() {
        if (this.mSlipHeadersAdapter != null) {
            this.mSlipHeadersAdapter = null;
        }
    }


    @OnClick(R.id.btn_transfer)
    public void onClickTransfer() {
        this.mPresenter.onClickTransfer();
    }

    @Override
    public void navigateToWriteActivity(int cardHistoryId, List<CardHistory> selectedCardHistories, List<SlipHeader> slipHeaders) {
        Intent intent = new Intent(this.mContext, WriteActivity.class);
        intent.putParcelableArrayListExtra("cardHistories", (ArrayList) selectedCardHistories);
        intent.putParcelableArrayListExtra("slipHeaders", (ArrayList) slipHeaders);
        intent.putExtra("cardHistoryId", cardHistoryId); //항상 첫 번째
        startActivity(intent);
        finish();
    }


    static class IncludedToolbarLayout {

        @BindView(R.id.ib_toolbartextback_back)
        ImageButton mToolbarBackBtn;

        @BindView(R.id.tv_toolbartextback_title)
        TextView mToolbarTitle;
    }

}
