package hcc.accouting.haccounting.common.dialog.confirmcancel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.dialog.confirmcancel.presenter.ConfirmCancelPresenter;
import hcc.accouting.haccounting.common.dialog.confirmcancel.presenter.ConfirmCancelPresenterImpl;
import hcc.accouting.haccounting.common.dialog.confirmcancel.view.ConfirmCancelView;
import hcc.accouting.haccounting.common.dto.ConfirmCancelDialogDto;
import hcc.accouting.haccounting.common.dto.SearchListDialogResultOkDto;
import hcc.accouting.haccounting.common.flag.ActivityRequestResultFlag;
import hcc.accouting.haccounting.ui.base.activity.BaseActivity;

public class ConfirmCancelActivity extends BaseActivity implements ConfirmCancelView {

    private ConfirmCancelPresenter mPresenter;

    @BindView(R.id.tv_confirmcanceldialog_title)
    TextView mTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_confirmcancel_dialog);
        getWindow().setLayout(android.view.WindowManager.LayoutParams.WRAP_CONTENT, android.view.WindowManager.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this);

        this.mPresenter = new ConfirmCancelPresenterImpl();
        this.mPresenter.onAttach(this);
        init();
    }

    @Override
    protected void init() {
        ConfirmCancelDialogDto confirmCancelDialogDto = (ConfirmCancelDialogDto) getIntent().getExtras().getSerializable("confirmCancelDialogDto");
        int flag = getIntent().getIntExtra("flag", 0);

        this.mPresenter.init(confirmCancelDialogDto, flag);
    }

    @Override
    public void setTitleContent(String content) {
        this.mTitleTv.setText(content);
    }

    @OnClick(R.id.btn_confirmcanceldialog_confirm)
    public void onClickConfirm(){
        this.mPresenter.onClickConfirm();
    }

    @OnClick(R.id.btn_confirmcanceldialog_cancel)
    public void onClickCancel(){
        this.mPresenter.onClickCancel();
    }

    @Override
    public void navigateToBackWithResultOk() {
        Intent intent = getIntent();
        setResult(ActivityRequestResultFlag.RESULT_OK, intent);
        finish();
    }

    @Override
    public void navigateToBack() {
        finish();
    }
}
