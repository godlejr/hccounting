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
import hcc.accouting.haccounting.common.entity.Acnt;

public class AcntsAdapter  extends RecyclerView.Adapter<AcntsAdapter.AcntsViewHolder> {
    private SearchListDialogPresenter mPresenter;
    private List<Acnt> mAcnts;
    private Context context;
    private int layout;

    public AcntsAdapter(SearchListDialogPresenter searchListDialogPresenter, List<Acnt> acnts, Context context, int layout) {
        this.mPresenter = searchListDialogPresenter;
        this.mAcnts = acnts;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public AcntsAdapter.AcntsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AcntsAdapter.AcntsViewHolder AcntsViewHolder = new AcntsAdapter.AcntsViewHolder(this.mPresenter, this.mAcnts, LayoutInflater.from(context).inflate(layout, parent, false));
        return AcntsViewHolder;
    }

    @Override
    public void onBindViewHolder(AcntsAdapter.AcntsViewHolder holder, int position) {
        Acnt acnt = this.mAcnts.get(position);
        String acntNm = acnt.getAcntNm();
        String acntRate = acnt.getAcntRate();

        if(acntRate != null){
            holder.mAcntRate.setVisibility(View.VISIBLE);
            holder.mAcntRate.setText(acntRate + "%");
        }else{
            holder.mAcntRate.setVisibility(View.GONE);
        }

        holder.mAcntContent.setText(acntNm);

    }

    @Override
    public int getItemCount() {
        return this.mAcnts.size();
    }

    public static class AcntsViewHolder extends RecyclerView.ViewHolder {
        private SearchListDialogPresenter mPresenter;
        private List<Acnt> mAcnts;

        @BindView(R.id.tv_dialogacnt_content)
        TextView mAcntContent;

        @BindView(R.id.tv_dialogacnt_rate)
        TextView mAcntRate;


        public AcntsViewHolder(SearchListDialogPresenter searchListDialogPresenter, List<Acnt> acnts, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mPresenter = searchListDialogPresenter;
            this.mAcnts = acnts;
        }

        @OnClick({R.id.ll_dialogacnt_content,R.id.tv_dialogacnt_content})
        public void onClickAcntContent() {
            int position = getAdapterPosition();
            this.mPresenter.onClickAcntContent(position);
        }

    }
}
