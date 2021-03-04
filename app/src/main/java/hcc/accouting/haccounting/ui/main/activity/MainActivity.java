package hcc.accouting.haccounting.ui.main.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.dialog.confirmcancel.activity.ConfirmCancelActivity;
import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.common.flag.ActivityRequestResultFlag;
import hcc.accouting.haccounting.common.flag.PermissionFlag;
import hcc.accouting.haccounting.ui.base.activity.BaseActivity;
import hcc.accouting.haccounting.ui.login.actvity.LoginActivity;
import hcc.accouting.haccounting.ui.main.adapter.MainTabAdapter;
import hcc.accouting.haccounting.ui.home.fragment.HomeFragment;
import hcc.accouting.haccounting.ui.history.fragment.HistoryFragment;
import hcc.accouting.haccounting.ui.main.presenter.MainPresenter;
import hcc.accouting.haccounting.ui.main.presenter.MainPresenterImpl;
import hcc.accouting.haccounting.ui.main.view.MainView;

public class MainActivity extends BaseActivity implements MainView, TabLayout.OnTabSelectedListener {

    private MainPresenter mPresenter;

    private IncludedToolbarLayout mIncludedToolbarLayout;

    private InflatedTabBudgetLayout mInflatedTabBudgetLayout;
    private InflatedTabHistoryLayout mInflatedTabHistoryLayout;

    private MainTabAdapter mTabAdapter;

    @BindView(R.id.tl_mainactivity)
    TabLayout mTabLayout;

    @BindView(R.id.vp_mainactivity)
    ViewPager mViewPager;

    @BindView(R.id.in_mainactivity_toolbar)
    View mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenterImpl<MainView>();
        this.mPresenter.onAttach(this);

        init();
    }

    protected void init() {
        this.mPresenter.init();
    }

    @Override
    public void navigateToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToConfirmCancelDialogActivity(ConfirmCancelDialogDto confirmCancelDialogDto, int flag, int requestCode) {
        Intent intent = new Intent(this.mContext, ConfirmCancelActivity.class);
        intent.putExtra("flag", flag);
        intent.putExtra("confirmCancelDialogDto", confirmCancelDialogDto);
        startActivityForResult(intent, requestCode);
    }


    @Override
    public void setToolbarLayout() {
        mIncludedToolbarLayout = new IncludedToolbarLayout();
        ButterKnife.bind(mIncludedToolbarLayout, mToolbar);
    }

    @Override
    public void showToolbarTitle(String message) {
        mIncludedToolbarLayout.mToolbarTitle.setText(message);
    }

    @OnClick(R.id.ib_toolbarmain_logout)
    public void onClickLogoutBtn(){
        this.mPresenter.onClickLogout();
    }

    @Override
    public void setTabLayout() {
        View tabBudget = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_tab_text, null, false);
        View tabHistory = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_tab_text, null, false);

        mInflatedTabBudgetLayout = new InflatedTabBudgetLayout();
        mInflatedTabHistoryLayout = new InflatedTabHistoryLayout();

        ButterKnife.bind(mInflatedTabBudgetLayout, tabBudget);
        ButterKnife.bind(mInflatedTabHistoryLayout, tabHistory);

        mInflatedTabBudgetLayout.mTabTitle.setText("홈");
        mInflatedTabHistoryLayout.mTabTitle.setText("내역");

        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());

        mTabLayout.getTabAt(0).setCustomView(tabBudget);
        mTabLayout.getTabAt(1).setCustomView(tabHistory);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(this);

    }

    @Override
    public void setTabAdapter() {
        mTabAdapter = new MainTabAdapter(getSupportFragmentManager(), this);

        mTabAdapter.addFragment(new HomeFragment());
        mTabAdapter.addFragment(new HistoryFragment());

        mViewPager.setAdapter(mTabAdapter);

    }

    @Override
    public void showRequestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS},
                PermissionFlag.PERMISSION_REQUEST_SMS_RECEIVE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PermissionFlag.PERMISSION_REQUEST_SMS_RECEIVE:
                this.mPresenter.onRequestPermissionsResultForSMSReceive(grantResults);
                break;
        }

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    static class IncludedToolbarLayout {

        @BindView(R.id.tv_toolbarmain_logo)
        TextView mToolbarTitle;

        @BindView(R.id.ib_toolbarmain_setting)
        ImageButton mToolbarSetting;

        @BindView(R.id.ib_toolbarmain_logout)
        ImageButton mToolbarLogout;


    }

    static class InflatedTabBudgetLayout {
        @BindView(R.id.tv_all_tab)
        TextView mTabTitle;
    }

    static class InflatedTabHistoryLayout {
        @BindView(R.id.tv_all_tab)
        TextView mTabTitle;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ActivityRequestResultFlag.CONFIRM_CANCEL_DIALOG_LOGOUT_REQUEST:
                switch (resultCode) {
                    case ActivityRequestResultFlag.RESULT_OK:
                        this.mPresenter.onActivityResultForLogoutResultOk();
                        break;

                }
                break;
        }
    }


}
