package hcc.accouting.haccounting.ui.transfer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hcc.accouting.haccounting.R;
import hcc.accouting.haccounting.common.entity.SlipDetail;
import hcc.accouting.haccounting.common.entity.SlipHeader;
import hcc.accouting.haccounting.common.utils.StringUtil;
import hcc.accouting.haccounting.ui.transfer.presenter.TransferPresenter;

public class SlipHeadersAdapter extends RecyclerView.Adapter<SlipHeadersAdapter.SlipHeadersViewHolder> {
    private TransferPresenter mPresenter;
    private List<SlipHeader> mSlipHeaders;
    private Context context;
    private int layout;

    public SlipHeadersAdapter(TransferPresenter transferPresenter, List<SlipHeader> SlipHeaders, Context context, int layout) {
        this.mPresenter = transferPresenter;
        this.mSlipHeaders = SlipHeaders;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public SlipHeadersAdapter.SlipHeadersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SlipHeadersAdapter.SlipHeadersViewHolder SlipHeadersViewHolder = new SlipHeadersAdapter.SlipHeadersViewHolder(this.mPresenter, this.mSlipHeaders, LayoutInflater.from(context).inflate(layout, parent, false));
        return SlipHeadersViewHolder;
    }

    @Override
    public void onBindViewHolder(SlipHeadersAdapter.SlipHeadersViewHolder holder, int position) {
        SlipHeader slipHeader = this.mSlipHeaders.get(position);

        SlipDetail slipDetail = slipHeader.getSlipDetails().get(0);

        String textH = slipHeader.getTextH();
        String date = slipHeader.getPosDate();
        String dateWithCustom = StringUtil.getCalculateDateFormatByDate(date); //전기일은 항상 오늘 날짜

        String tripCd = slipHeader.getBusiTripId();

        String acntNm = slipDetail.getAcntNm();
        String deptNm = slipDetail.getDeptNm();
        String vendorNm = slipDetail.getVendorNm();
        Float amtTot = slipDetail.getAmtTot();
        String price = StringUtil.getWonMoneyFormatByPriceAndCurrCd(amtTot, "KRW");


        holder.mUsage.setText(vendorNm);
        holder.mDate.setText(dateWithCustom);
        holder.mAcnt.setText(acntNm);
        holder.mDept.setText(deptNm);
        holder.mPrice.setText(price);
        holder.mTextH.setText(textH);

        if (tripCd != null && tripCd.length() > 0) {
            if (holder.mTrip.getVisibility() == View.GONE) {
                holder.mTrip.setVisibility(View.VISIBLE);
            }
        } else {
            if (holder.mTrip.getVisibility() == View.VISIBLE) {
                holder.mTrip.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.mSlipHeaders.size();
    }

    public static class SlipHeadersViewHolder extends RecyclerView.ViewHolder {
        private TransferPresenter mPresenter;
        private List<SlipHeader> mSlipHeaders;

        @BindView(R.id.tv_transfer_usage)
        TextView mUsage;

        @BindView(R.id.tv_transfer_acnt)
        TextView mAcnt;

        @BindView(R.id.tv_transfer_date)
        TextView mDate;

        @BindView(R.id.tv_transfer_dept)
        TextView mDept;

        @BindView(R.id.tv_transfer_price)
        TextView mPrice;

        @BindView(R.id.tv_transfer_text_h)
        TextView mTextH;

        @BindView(R.id.iv_transfer_trip)
        ImageView mTrip;


        public SlipHeadersViewHolder(TransferPresenter transferPresenter, List<SlipHeader> SlipHeaders, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mPresenter = transferPresenter;
            this.mSlipHeaders = SlipHeaders;
        }


    }
}
