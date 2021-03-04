package hcc.accouting.haccounting.ui.base.presenter;

import android.content.Context;

import hcc.accouting.haccounting.common.dto.HttpErrorDto;
import hcc.accouting.haccounting.common.entity.User;
import hcc.accouting.haccounting.ui.base.view.BaseView;

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    private V mBaseView;

    public BasePresenterImpl() {
    }

    @Override
    public void onAttach(V baseView) {
        mBaseView = baseView;
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onHttpError(HttpErrorDto httpErrorDto) {
        if (httpErrorDto == null) {
            mBaseView.showMessage("네트워크 불안정합니다. 다시 시도하세요.");
        } else {
            int responseCode = httpErrorDto.status();
            if (responseCode != 401) {
                mBaseView.showMessage(httpErrorDto.message());
            }
        }
    }

    public V getBaseView() {
        return mBaseView;
    }

    public Context getContext() {
        return mBaseView.getContext();
    }


}
