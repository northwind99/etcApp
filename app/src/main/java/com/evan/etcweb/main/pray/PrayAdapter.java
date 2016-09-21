package com.evan.etcweb.main.pray;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evan.etcweb.R;
import com.evan.etcweb.entities.PrayDetail;

import java.util.List;

public class PrayAdapter extends RecyclerView.Adapter<PrayAdapter.MyViewHolder> {

    private static List<PrayDetail> item;
    private Activity context;

    public PrayAdapter(List<PrayDetail> item, Activity context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pray_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PrayDetail prayDetail = item.get(position);
        holder.pray_name.setText(prayDetail.getName());
        holder.pray_date.setText(prayDetail.getDate());
        holder.pray_msg.setText(prayDetail.getMessage());
        holder.totalPrayers.setText(prayDetail.getTotalPrayers() + "");

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView pray_name;
        private TextView pray_date;
        private TextView pray_msg;
        private TextView totalPrayers;
        public MyViewHolder(View itemView) {
            super(itemView);
            pray_name = (TextView)itemView.findViewById(R.id.pray_name);
            pray_date = (TextView)itemView.findViewById(R.id.pray_date);
            pray_msg = (TextView)itemView.findViewById(R.id.pray_msg);
            totalPrayers = (TextView)itemView.findViewById(R.id.number_of_prayers);
        }
    }
}
