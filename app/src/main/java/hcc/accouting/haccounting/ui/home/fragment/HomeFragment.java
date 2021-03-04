package hcc.accouting.haccounting.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.ui.base.fragment.BaseFragment;
import hcc.accouting.haccounting.ui.home.presenter.HomeFragmentPresenter;
import hcc.accouting.haccounting.ui.home.presenter.HomeFragmentPresenterImpl;
import hcc.accouting.haccounting.ui.home.view.HomeFragmentView;

public class HomeFragment extends BaseFragment implements HomeFragmentView {

    private HomeFragmentPresenter mPresenter;

    @BindView(R.id.tv_homefragment_notice)
    TextView mNoticeTv;

    @BindView(R.id.tv_homefragment_budgettitle)
    TextView mBudgetTitleTv;

    @BindView(R.id.tv_homefragment_budgetacnt1)
    TextView mBudgetAcnt1Tv;

    @BindView(R.id.tv_homefragment_budgetprice1)
    TextView mBudgetPrice1Tv;

    @BindView(R.id.tv_homefragment_budgetacnt2)
    TextView mBudgetAcnt2Tv;

    @BindView(R.id.tv_homefragment_budgetprice2)
    TextView mBudgetPrice2Tv;

    @BindView(R.id.tv_homefragment_budgetacnt3)
    TextView mBudgetAcnt3Tv;

    @BindView(R.id.tv_homefragment_budgetprice3)
    TextView mBudgetPrice3Tv;

    @BindView(R.id.tv_homefragment_triptitle)
    TextView mTripTitleTv;

    @BindView(R.id.tv_homefragment_triphousecost)
    TextView mTripHouseCostTv;

    @BindView(R.id.tv_homefragment_tripnormalcost)
    TextView mTripNormalCostTv;

    @BindView(R.id.tv_homefragment_triptravelcost)
    TextView mTripTravelCostTv;

    @BindView(R.id.ll_homefragment_tripcontent)
    LinearLayout mTripContent;

    @BindView(R.id.ll_homefragment_tripempty)
    LinearLayout mTripEmpty;

    @BindView(R.id.ll_budget)
    LinearLayout mBudgetContent;

    @BindView(R.id.ll_homefragment_budgetempty)
    LinearLayout mBudgetEmpty;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();

        this.mPresenter = new HomeFragmentPresenterImpl<HomeFragmentView>();
        this.mPresenter.onAttach(this);

        init();
    }

    @Override
    protected void init() {
        this.mPresenter.init();
    }

    @Override
    protected void initView() {
        this.mPresenter.initView();
    }

    @Override
    public void setNoticeContent(String content) {
        this.mNoticeTv.setText(content);
    }

    @Override
    public void setBudgetTitleContent(String content) {
        this.mBudgetTitleTv.setText(content);
    }

    @Override
    public void setBudgetAcnt1Content(String content) {
        this.mBudgetAcnt1Tv.setText(content);
    }

    @Override
    public void setBudgetPrice1Content(String content) {
        this.mBudgetPrice1Tv.setText(content);
    }


    @Override
    public void setBudgetAcnt2Content(String content) {
        this.mBudgetAcnt2Tv.setText(content);
    }

    @Override
    public void setBudgetPrice2Content(String content) {
        this.mBudgetPrice2Tv.setText(content);
    }

    @Override
    public void setBudgetAcnt3Content(String content) {
        this.mBudgetAcnt3Tv.setText(content);
    }

    @Override
    public void setBudgetPrice3Content(String content) {
        this.mBudgetPrice3Tv.setText(content);
    }

    @Override
    public void setTripTitleContent(String content) {
        this.mTripTitleTv.setText(content);
    }

    @Override
    public void setTripHouseCostContent(String content) {
        this.mTripHouseCostTv.setText(content);
    }

    @Override
    public void setTripNormalCostContent(String content) {
        this.mTripNormalCostTv.setText(content);
    }

    @Override
    public void setTripTravelCostContent(String content) {
        this.mTripTravelCostTv.setText(content);
    }


    @Override
    public void showTripContent() {
        if (this.mTripContent.getVisibility() == View.GONE) {
            this.mTripContent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goneTripContent() {
        if (this.mTripContent.getVisibility() == View.VISIBLE) {
            this.mTripContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void showTripEmpty() {
        if (this.mTripEmpty.getVisibility() == View.GONE) {
            this.mTripEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goneTripEmpty() {
        if (this.mTripEmpty.getVisibility() == View.VISIBLE) {
            this.mTripEmpty.setVisibility(View.GONE);
        }
    }
    @Override
    public void showBudgetContent() {
        if (this.mBudgetContent.getVisibility() == View.GONE) {
            this.mBudgetContent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goneBudgetContent() {
        if (this.mBudgetContent.getVisibility() == View.VISIBLE) {
            this.mBudgetContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void showBudgetEmpty() {
        if (this.mBudgetEmpty.getVisibility() == View.GONE) {
            this.mBudgetEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goneBudgetEmpty() {
        if (this.mBudgetEmpty.getVisibility() == View.VISIBLE) {
            this.mBudgetEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        this.mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mPresenter.onPause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


}
