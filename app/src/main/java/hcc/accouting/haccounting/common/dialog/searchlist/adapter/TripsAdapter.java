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
import hcc.accouting.haccounting.common.entity.Trip;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripsViewHolder> {
    private SearchListDialogPresenter mPresenter;
    private List<Trip> mTrips;
    private Context context;
    private int layout;

    public TripsAdapter(SearchListDialogPresenter searchListDialogPresenter, List<Trip> trips, Context context, int layout) {
        this.mPresenter = searchListDialogPresenter;
        this.mTrips = trips;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public TripsAdapter.TripsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TripsAdapter.TripsViewHolder tripsViewHolder = new TripsAdapter.TripsViewHolder(this.mPresenter, this.mTrips, LayoutInflater.from(context).inflate(layout, parent, false));
        return tripsViewHolder;
    }

    @Override
    public void onBindViewHolder(TripsAdapter.TripsViewHolder holder, int position) {
        Trip trip = this.mTrips.get(position);
        String tripNm = trip.getTripNm();
        holder.mTripContent.setText(tripNm);

    }

    @Override
    public int getItemCount() {
        return this.mTrips.size();
    }

    public static class TripsViewHolder extends RecyclerView.ViewHolder {
        private SearchListDialogPresenter mPresenter;
        private List<Trip> mTrips;

        @BindView(R.id.tv_dialogtrip_content)
        TextView mTripContent;


        public TripsViewHolder(SearchListDialogPresenter searchListDialogPresenter, List<Trip> trips, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mPresenter = searchListDialogPresenter;
            this.mTrips = trips;
        }

        @OnClick({R.id.ll_dialogtrip_content,R.id.tv_dialogtrip_content})
        public void onClickTripContent() {
            int position = getAdapterPosition();
            this.mPresenter.onClickTripContent(position);
        }

    }
}
