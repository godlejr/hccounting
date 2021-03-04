package hcc.accouting.haccounting.common.dialog.searchlist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.dialog.searchlist.adapter.AcntsAdapter;
import hcc.accouting.haccounting.common.dialog.searchlist.adapter.DeptsAdapter;
import hcc.accouting.haccounting.common.dialog.searchlist.adapter.TripsAdapter;
import hcc.accouting.haccounting.common.dialog.searchlist.presenter.SearchListDialogPresenter;
import hcc.accouting.haccounting.common.dialog.searchlist.presenter.SearchListDialogPresenterImpl;
import hcc.accouting.haccounting.common.dialog.searchlist.view.SearchListDialogView;
import hcc.accouting.haccounting.common.dto.SearchListDialogDto;
import hcc.accouting.haccounting.common.dto.SearchListDialogResultOkDto;
import hcc.accouting.haccounting.common.entity.Acnt;
import hcc.accouting.haccounting.common.entity.Dept;
import hcc.accouting.haccounting.common.entity.Trip;
import hcc.accouting.haccounting.common.flag.ActivityRequestResultFlag;
import hcc.accouting.haccounting.ui.base.activity.BaseActivity;

public class SearchListDialogActivity extends BaseActivity implements SearchListDialogView, TextWatcher, NestedScrollView.OnScrollChangeListener {

    private SearchListDialogPresenter mPresenter;

    @BindView(R.id.tv_searchlistdialog_title)
    TextView mTitleTv;

    @BindView(R.id.rv_searchlistdialog)
    RecyclerView mSearchListRv;

    @BindView(R.id.nsv_searchlistdialog)
    NestedScrollView mSearchListNsv;

    @BindView(R.id.et_searchlistdialog)
    EditText mSearchListEt;


    private TripsAdapter mTripsAdapter;
    private AcntsAdapter mAcntsAdapter;
    private DeptsAdapter mDeptsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_searchlist_dialog);
        getWindow().setLayout(android.view.WindowManager.LayoutParams.WRAP_CONTENT, android.view.WindowManager.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this);

        this.mPresenter = new SearchListDialogPresenterImpl();
        this.mPresenter.onAttach(this);
        init();
    }

    @Override
    protected void init() {
        SearchListDialogDto searchListDialogDto = (SearchListDialogDto) getIntent().getExtras().getSerializable("searchListDialogDto");
        int flag = getIntent().getIntExtra("flag", 0);

        this.mPresenter.init(searchListDialogDto, flag);
    }

    @Override
    public void setTitleContent(String content) {
        this.mTitleTv.setText(content);
    }

    @Override
    public void setSearchListEditTextChangedListener() {
        this.mSearchListEt.addTextChangedListener(this);
    }


    @Override
    public void setScrollViewOnScrollChangeListener() {
        this.mSearchListNsv.setOnScrollChangeListener(this);
    }

    @Override
    public void setDeptsViewByItem(List<Dept> depts) {
        this.mDeptsAdapter = new DeptsAdapter(this.mPresenter, depts, mContext, R.layout.item_dialog_dept);
        this.mSearchListRv.setAdapter(this.mDeptsAdapter);
        this.mSearchListRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public void deptsAdapterNotifyItemRangeInserted(int startPosition, int itemCount) {
        if (this.mDeptsAdapter != null) {
            this.mDeptsAdapter.notifyItemRangeInserted(startPosition, itemCount);
        }
    }

    @Override
    public void deptsAdapterNotifyItemChanged(int position) {
        if (this.mDeptsAdapter != null) {
            this.mDeptsAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void deptsAdapterNotifyItemRangeChanged(int startPosition, int size) {
        if (this.mDeptsAdapter != null) {
            this.mDeptsAdapter.notifyItemRangeChanged(startPosition, size);
        }
    }

    @Override
    public void clearDeptsAdapter() {
        if (this.mDeptsAdapter != null) {
            this.mDeptsAdapter = null;
        }
    }


    @Override
    public void setAcntsViewByItem(List<Acnt> acnts) {
        this.mAcntsAdapter = new AcntsAdapter(this.mPresenter, acnts, mContext, R.layout.item_dialog_acnt);
        this.mSearchListRv.setAdapter(this.mAcntsAdapter);
        this.mSearchListRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void acntsAdapterNotifyItemRangeInserted(int startPosition, int itemCount) {
        if (this.mAcntsAdapter != null) {
            this.mAcntsAdapter.notifyItemRangeInserted(startPosition, itemCount);
        }
    }

    @Override
    public void acntsAdapterNotifyItemChanged(int position) {
        if (this.mAcntsAdapter != null) {
            this.mAcntsAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void acntsAdapterNotifyItemRangeChanged(int startPosition, int size) {
        if (this.mAcntsAdapter != null) {
            this.mAcntsAdapter.notifyItemRangeChanged(startPosition, size);
        }
    }

    @Override
    public void clearAcntsAdapter() {
        if (this.mAcntsAdapter != null) {
            this.mAcntsAdapter = null;
        }
    }

    @Override
    public void setTripsViewByItem(List<Trip> trips) {
        this.mTripsAdapter = new TripsAdapter(this.mPresenter, trips, mContext, R.layout.item_dialog_trip);
        this.mSearchListRv.setAdapter(this.mTripsAdapter);
        this.mSearchListRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public void tripsAdapterNotifyItemRangeInserted(int startPosition, int itemCount) {
        if (this.mTripsAdapter != null) {
            this.mTripsAdapter.notifyItemRangeInserted(startPosition, itemCount);
        }
    }

    @Override
    public void tripsAdapterNotifyItemChanged(int position) {
        if (this.mTripsAdapter != null) {
            this.mTripsAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void tripsAdapterNotifyItemRangeChanged(int startPosition, int size) {
        if (this.mTripsAdapter != null) {
            this.mTripsAdapter.notifyItemRangeChanged(startPosition, size);
        }
    }

    @Override
    public void clearTripsAdapter() {
        if (this.mTripsAdapter != null) {
            this.mTripsAdapter = null;
        }
    }

    @Override
    public void navigateToBackWithResultOk(SearchListDialogResultOkDto searchListDialogResultOkDto) {
        Intent intent = getIntent();
        intent.putExtra("searchListDialogResultOkDto", searchListDialogResultOkDto);
        setResult(ActivityRequestResultFlag.RESULT_OK, intent);
        finish();
    }

    @Override
    public void setSearchEdit(String message){
        this.mSearchListEt.setText(message);
    }

    @OnClick(R.id.iv_searchlistdialog_close)
    public void onClickCloseBtn() {
        finish();
    }

    @OnClick(R.id.iv_searchlistdialog_clear)
    public void onClickSearchClear(){
        String searchString = this.mSearchListEt.getText().toString();
        this.mPresenter.onClickSearchClear(searchString);

    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        View view = nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);
        int difference = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()));

        String searchString = this.mSearchListEt.getText().toString();
        this.mPresenter.onScrollChange(difference, searchString);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.mPresenter.onTextChanged(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
