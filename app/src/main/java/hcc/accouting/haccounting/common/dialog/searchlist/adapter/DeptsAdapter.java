package hcc.accouting.haccounting.common.dialog.searchlist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.dialog.searchlist.presenter.SearchListDialogPresenter;
import hcc.accouting.haccounting.common.entity.Dept;

public class DeptsAdapter extends RecyclerView.Adapter<DeptsAdapter.DeptsViewHolder> {
    private SearchListDialogPresenter mPresenter;
    private List<Dept> mDepts;
    private Context context;
    private int layout;

    public DeptsAdapter(SearchListDialogPresenter searchListDialogPresenter, List<Dept> depts, Context context, int layout) {
        this.mPresenter = searchListDialogPresenter;
        this.mDepts = depts;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public DeptsAdapter.DeptsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DeptsAdapter.DeptsViewHolder deptsViewHolder = new DeptsAdapter.DeptsViewHolder(this.mPresenter, this.mDepts, LayoutInflater.from(context).inflate(layout, parent, false));
        return deptsViewHolder;
    }

    @Override
    public void onBindViewHolder(DeptsAdapter.DeptsViewHolder holder, int position) {
        Dept dept = this.mDepts.get(position);
        String deptNm = dept.getObjmNm();
        holder.mDeptContent.setText(deptNm);

    }

    @Override
    public int getItemCount() {
        return this.mDepts.size();
    }

    public static class DeptsViewHolder extends RecyclerView.ViewHolder {
        private SearchListDialogPresenter mPresenter;
        private List<Dept> mDepts;

        @BindView(R.id.tv_dialogdept_content)
        TextView mDeptContent;


        public DeptsViewHolder(SearchListDialogPresenter searchListDialogPresenter, List<Dept> depts, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mPresenter = searchListDialogPresenter;
            this.mDepts = depts;
        }

        @OnClick({R.id.ll_dialogdept_content,R.id.tv_dialogdept_content})
        public void onClickDeptContent() {
            int position = getAdapterPosition();
            this.mPresenter.onClickDeptContent(position);
        }

    }
}
