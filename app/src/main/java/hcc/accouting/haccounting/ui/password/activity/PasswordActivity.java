package hcc.accouting.haccounting.ui.password.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.ui.base.activity.BaseActivity;
import hcc.accouting.haccounting.ui.main.activity.MainActivity;
import hcc.accouting.haccounting.ui.password.presenter.PasswordPresenter;
import hcc.accouting.haccounting.ui.password.presenter.PasswordPresenterImpl;
import hcc.accouting.haccounting.ui.password.view.PasswordView;

public class PasswordActivity extends BaseActivity implements PasswordView {

    private PasswordPresenter mPresenter;

    @BindView(R.id.et_password_pw1)
    EditText mPassword1;

    @BindView(R.id.et_password_pw2)
    EditText mPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        ButterKnife.bind(this);
        this.mPresenter = new PasswordPresenterImpl<PasswordView>();
        this.mPresenter.onAttach(this);
        init();
    }

    protected void init() {
        this.mPresenter.init();
    }

    @Override
    public void setToolbarLayout() {

    }

    @Override
    public void showToolbarTitle(String message) {

    }

    @OnClick(R.id.btn_password_confirm)
    @Override
    public void onClickConfirm() {
        String password1 = this.mPassword1.getText().toString();
        String password2 = this.mPassword2.getText().toString();

        this.mPresenter.onClickConfirm(password1,password2);
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
