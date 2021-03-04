package hcc.accouting.haccounting.ui.base.presenter;

import android.content.Context;

import hcc.accouting.haccounting.common.dto.HttpErrorDto;
import hcc.accouting.haccounting.ui.base.view.BaseView;

public interface BasePresenter<V extends BaseView> {

    public void onAttach(V baseView);

    public void init();

    public void initView();

    public void onHttpError(HttpErrorDto httpErrorDto);

    public Context getContext();

}
