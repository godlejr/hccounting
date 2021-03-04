package hcc.accouting.haccounting.ui.history.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.entity.CardHistory;
import hcc.accouting.haccounting.common.utils.StringUtil;
import hcc.accouting.haccounting.ui.history.presenter.HistoryFragmentPresenter;

public class HistoriesAdapter extends RecyclerView.Adapter<HistoriesAdapter.HistoriesViewHolder> {
    private HistoryFragmentPresenter mPresenter;
    private List<CardHistory> mCardHistories;
    private Context context;
    private int layout;

    public HistoriesAdapter(HistoryFragmentPresenter historyFragmentPresenter, List<CardHistory> cardHistories, Context context, int layout) {
        this.mPresenter = historyFragmentPresenter;
        this.mCardHistories = cardHistories;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public HistoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HistoriesViewHolder historiesViewHolder = new HistoriesViewHolder(this.mPresenter, this.mCardHistories, LayoutInflater.from(context).inflate(layout, parent, false));
        return historiesViewHolder;
    }

    @Override
    public void onBindViewHolder(HistoriesViewHolder holder, int position) {
        CardHistory cardHistory = this.mCardHistories.get(position);

        String vendorNm = cardHistory.getVendorNm();
        String transDate = cardHistory.getTransDate();
        String cardCompNm = cardHistory.getCardComp().getCompNm();

        float amtTot = cardHistory.getAmtTot();

        String currCd = cardHistory.getCurrCd();
        boolean isChecked = cardHistory.isChecked();

        String current = StringUtil.getCurrentFormatByCurrCd(currCd);
        String money = StringUtil.getWonMoneyFormatByPriceAndCurrCd(amtTot,currCd);
        String customDate = StringUtil.getCalculateDateFormatByDate(transDate);

        if (isChecked) {
            holder.mCardHistoryContent.setBackgroundColor(holder.mGrayColor);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.mCardHistoryContent.setBackground(holder.mBackgroundDrawable);
            }else{
                holder.mCardHistoryContent.setBackgroundDrawable(holder.mBackgroundDrawable);
            }
        }


        holder.mVendorName.setText(vendorNm);
        holder.mDateAndCard.setText(customDate + " | " + cardCompNm + "카드");
        holder.mMoney.setText(money + "" + current);


    }

    @Override
    public int getItemCount() {
        return this.mCardHistories.size();
    }

    public static class HistoriesViewHolder extends RecyclerView.ViewHolder {
        private HistoryFragmentPresenter mPresenter;
        private List<CardHistory> mCardHistories;

        @BindView(R.id.tv_history_vendor_name)
        TextView mVendorName;

        @BindView(R.id.tv_history_date_card)
        TextView mDateAndCard;

        @BindView(R.id.tv_history_money)
        TextView mMoney;

        @BindView(R.id.ll_history_content)
        LinearLayout mCardHistoryContent;

        @BindColor(R.color.gray)
        int mGrayColor;

        @BindColor(R.color.white)
        int mWhiteColor;

        @BindDrawable(R.drawable.border_gray_top_and_bottom)
        Drawable mBackgroundDrawable;


        public HistoriesViewHolder(HistoryFragmentPresenter historyFragmentPresenter, List<CardHistory> cardHistories, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mPresenter = historyFragmentPresenter;
            this.mCardHistories = cardHistories;
        }

        @OnClick(R.id.ll_history_content)
        public void onClickCardHistoryContent() {
            int position = getAdapterPosition();
            this.mPresenter.onClickCardHistoryContent(position);
        }

    }
}

