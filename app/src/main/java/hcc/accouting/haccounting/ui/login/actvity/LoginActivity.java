package hcc.accouting.haccounting.ui.login.actvity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.ui.base.activity.BaseActivity;
import hcc.accouting.haccounting.ui.login.presenter.LoginPresenter;
import hcc.accouting.haccounting.ui.login.presenter.LoginPresenterImpl;
import hcc.accouting.haccounting.ui.login.view.LoginView;
import hcc.accouting.haccounting.ui.main.activity.MainActivity;
import hcc.accouting.haccounting.ui.password.activity.PasswordActivity;

public class LoginActivity extends BaseActivity implements LoginView {

    private LoginPresenter mLoginPresenter;

    @BindView(R.id.et_login_id)
    EditText mLoginId;

    @BindView(R.id.et_login_pwd)
    EditText mLoginPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        this.mLoginPresenter = new LoginPresenterImpl<LoginView>();
        this.mLoginPresenter.onAttach(this);
        init();
    }

    protected void init() {
        this.mLoginPresenter.init();
    }

    @Override
    public void setToolbarLayout() {

    }

    @Override
    public void showToolbarTitle(String message) {

    }

    @OnClick(R.id.btn_login)
    @Override
    public void onClickLogin() {
        String loginId = this.mLoginId.getText().toString();
        String loginPw = this.mLoginPw.getText().toString();

        User user = new User();
        user.setLoginId(loginId);
        user.setLoginPw(loginPw);

        // 추가 정보 입력

        this.mLoginPresenter.onClickLogin(user);
    }

    @Override
    public void navigateToPasswordActivity() {
        Intent intent = new Intent(this, PasswordActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
