package hcc.accouting.haccounting.ui.history.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.dialog.confirmcancel.activity.ConfirmCancelActivity;
import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.common.dto.SearchListDialogResultOkDto;
import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.common.flag.ActivityRequestResultFlag;
import hcc.accouting.haccounting.ui.base.fragment.BaseFragment;
import hcc.accouting.haccounting.ui.history.adapter.HistoriesAdapter;
import hcc.accouting.haccounting.ui.history.presenter.HistoryFragmentPresenter;
import hcc.accouting.haccounting.ui.history.presenter.HistoryFragmentPresenterImpl;
import hcc.accouting.haccounting.ui.history.view.HistoryFragmentView;
import hcc.accouting.haccounting.ui.password.activity.PasswordActivity;
import hcc.accouting.haccounting.ui.write.activity.WriteActivity;

public class HistoryFragment extends BaseFragment implements HistoryFragmentView, NestedScrollView.OnScrollChangeListener {

    private Context mContext;
    private HistoryFragmentPresenter mPresenter;
    private HistoriesAdapter mHistoriesAdapter;

    @BindView(R.id.rbtn_history_none_write)
    RadioButton mNoneWriteRbtn;

    @BindView(R.id.rbtn_history_transfer)
    RadioButton mTransferRbtn;

    @BindView(R.id.rbtn_history_approving)
    RadioButton mApprovingRbtn;

    @BindView(R.id.rbtn_history_approved)
    RadioButton mApprovedRbtn;

    @BindView(R.id.btn_history_cancel)
    Button mCancelBtn;

    @BindView(R.id.btn_history_write)
    Button mWriteBtn;

    @BindView(R.id.rv_history)
    RecyclerView mCardHistoriesRv;

    @BindView(R.id.nsv_history)
    NestedScrollView mCardHistoryNsv;

    @BindView(R.id.tv_history_delete)
    TextView mDeleteBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContext = getActivity();

        this.mPresenter = new HistoryFragmentPresenterImpl<HistoryFragmentView>();
        this.mPresenter.onAttach(this);

        init();
    }

    protected void init() {
        this.mPresenter.init();
    }

    @Override
    public void showDeleteBtn() {
        if (this.mDeleteBtn.getVisibility() == View.GONE) {
            this.mDeleteBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goneDeleteBtn() {
        if (this.mDeleteBtn.getVisibility() == View.VISIBLE) {
            this.mDeleteBtn.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    protected void initView() {
        this.mPresenter.initView();
    }


    @Override
    public void setToolbarLayout() {

    }

    @Override
    public void showToolbarTitle(String message) {

    }

    @Override
    public Boolean isNoneWriteRbtnChecked() {
        return mNoneWriteRbtn.isChecked();
    }

    @Override
    public Boolean isTransferRbtnChecked() {
        return mTransferRbtn.isChecked();
    }

    @Override
    public Boolean isApprovingRbtnChecked() {
        return mApprovingRbtn.isChecked();
    }

    @Override
    public Boolean isApprovedRbtnChecked() {
        return mApprovedRbtn.isChecked();
    }


    @Override
    public void setCardHistoriesViewByItem(List<CardHistory> cardHistories) {
        this.mHistoriesAdapter = new HistoriesAdapter(this.mPresenter, cardHistories, mContext, R.layout.item_fragmenthistory_cardhistory);
        this.mCardHistoriesRv.setAdapter(this.mHistoriesAdapter);
        this.mCardHistoriesRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void clearHistoriesAdapter() {
        if (this.mHistoriesAdapter != null) {
            this.mHistoriesAdapter = null;
        }
    }

    @Override
    public void setScrollViewOnScrollChangeListener() {
        this.mCardHistoryNsv.setOnScrollChangeListener(this);
    }

    @Override
    public void cardHistoriesAdapterNotifyItemRangeInserted(int startPosition, int itemCount) {
        if (this.mHistoriesAdapter != null) {
            this.mHistoriesAdapter.notifyItemRangeInserted(startPosition, itemCount);
        }
    }

    @Override
    public void cardHistoriesAdapterNotifyItemChanged(int position) {
        if (this.mHistoriesAdapter != null) {
            this.mHistoriesAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void cardHistoriesAdapterNotifyItemRangeChanged(int startPosition, int size) {
        if (this.mHistoriesAdapter != null) {
            this.mHistoriesAdapter.notifyItemRangeChanged(startPosition, size);
        }
    }

    @Override
    public void navigateToWriteActivity(List<CardHistory> cardHistories) {
        Intent intent = new Intent(this.mContext, WriteActivity.class);
        intent.putParcelableArrayListExtra("cardHistories", (ArrayList) cardHistories);
        intent.putExtra("cardHistoryId", 0); //항상 첫 번째
        startActivity(intent);
    }

    @Override
    public void navigateToConfirmCancelDialogActivity(ConfirmCancelDialogDto confirmCancelDialogDto, int flag, int requestCode) {
        Intent intent = new Intent(this.mContext, ConfirmCancelActivity.class);
        intent.putExtra("flag", flag);
        intent.putExtra("confirmCancelDialogDto", confirmCancelDialogDto);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onPause() {
        this.mPresenter.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        this.mPresenter.onResume();
        super.onResume();
    }

    @Override
    public void showCancelBtn() {
        if (this.mCancelBtn.getVisibility() == View.GONE) {
            this.mCancelBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideCancelBtn() {
        if (this.mCancelBtn.getVisibility() == View.VISIBLE) {
            this.mCancelBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void showWriteBtn() {
        if (this.mWriteBtn.getVisibility() == View.GONE) {
            this.mWriteBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideWriteBtn() {
        if (this.mWriteBtn.getVisibility() == View.VISIBLE) {
            this.mWriteBtn.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.rbtn_history_none_write, R.id.rbtn_history_transfer, R.id.rbtn_history_approving, R.id.rbtn_history_approved})
    @Override
    public void onClickCardHistoryStatus() {
        this.mPresenter.onClickCardHistoryStatus();
    }

    @OnClick(R.id.tv_history_all_selection)
    @Override
    public void onClickAllSelection() {
        this.mPresenter.onClickAllSelection();
    }


    @OnClick(R.id.btn_history_write)
    @Override
    public void onClickWrite() {
        this.mPresenter.onClickWrite();
    }

    @OnClick(R.id.btn_history_cancel)
    public void onClickTransferCancel() {
        this.mPresenter.onClickTransferCancel();
    }

    @OnClick(R.id.tv_history_delete)
    public void onClickCardHistoryDelete() {
        this.mPresenter.onClickCardHistoryDelete();
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        View view = nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
        int difference = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));
        this.mPresenter.onScrollChange(difference);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_SLIP_DELETE_REQUEST:
                switch (resultCode) {
                    case ActivityRequestResultFlag.RESULT_OK:
                        this.mPresenter.onActivityResultForTransferCancelDialogResultOk();
                        break;

                }
                break;

            case ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_CARD_HISTORY_DELETE_REQUEST:
                switch (resultCode) {
                    case ActivityRequestResultFlag.RESULT_OK:
                        this.mPresenter.onActivityResultForCardHistoryDeleteDialogResultOk();
                        break;

                }
                break;
        }
    }
}
