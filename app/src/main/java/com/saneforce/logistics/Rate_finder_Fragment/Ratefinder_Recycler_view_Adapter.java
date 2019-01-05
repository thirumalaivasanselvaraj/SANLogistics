package com.saneforce.logistics.Rate_finder_Fragment;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saneforce.logistics.Activity_Class.MainActivity;
import com.saneforce.logistics.R;

import java.util.ArrayList;

public class Ratefinder_Recycler_view_Adapter extends RecyclerView.Adapter<Ratefinder_Recycler_view_Adapter.MyViewHolder> {

    private ArrayList<RatefilnderResponse> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, remarks, firstts, secondts;
        TextView textViewVersion;
        ImageView imageViewIcon;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
            this.remarks = (TextView) itemView.findViewById(R.id.remarks);
            this.firstts = (TextView) itemView.findViewById(R.id.firstts);
            this.secondts = (TextView) itemView.findViewById(R.id.secondts);
        }
    }

    public Ratefinder_Recycler_view_Adapter(ArrayList<RatefilnderResponse> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rate_finder_recycerview, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView firstts = holder.firstts;
        TextView secondts = holder.secondts;
        TextView textViewVersion = holder.textViewVersion;
        TextView remarks = holder.remarks;
        ImageView imageView = holder.imageViewIcon;
        System.out.println("VALUES_CURED_RECYCLERVIEW" + dataSet.get(listPosition).getCountryName());
        textViewName.setText(dataSet.get(listPosition).getARR());
        textViewVersion.setText(dataSet.get(listPosition).getTOTTRANSIT());
        firstts.setText(dataSet.get(listPosition).get1TS());
        secondts.setText(dataSet.get(listPosition).get2TS());
        remarks.setText(dataSet.get(listPosition).getREMARK());
        // imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}