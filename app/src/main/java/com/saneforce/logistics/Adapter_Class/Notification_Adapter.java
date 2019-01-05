package com.saneforce.logistics.Adapter_Class;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.saneforce.logistics.Activity_Class.Notificationyour_order;
import com.saneforce.logistics.Pojo_Class.Notification_Pojo;
import com.saneforce.logistics.R;

import java.util.List;

/**
 * Created by nnandroid03user on 20/10/16.
 */

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.MyViewHolder> {

    private List<Notification_Pojo> moviesList;
    Context context;

    public Notification_Adapter(List<Notification_Pojo> moviesList) {
        this.moviesList = moviesList;
    }


    @Override
    public Notification_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_listview, parent, false);

        return new Notification_Adapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(Notification_Adapter.MyViewHolder holder, int position) {
        Notification_Pojo Notification_Pojo = moviesList.get(position);
        holder.notificationdate.setText(Notification_Pojo.getNotificationdate());
        holder.Notificationmsg.setText(Notification_Pojo.getGenre());
        holder.checkyourbill.setText(Notification_Pojo.getYear());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView notificationdate, Notificationmsg, checkyourbill;
        ImageView notificationback;

        public MyViewHolder(View view) {
            super(view);
            context = itemView.getContext();
            notificationdate = (TextView) view.findViewById(R.id.notificationdate);
            Notificationmsg = (TextView) view.findViewById(R.id.Notificationmsg);
            checkyourbill = (TextView) view.findViewById(R.id.checkyourbill);
            notificationback = (ImageView) view.findViewById(R.id.notificationback);
            notificationback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Notificationyour_order.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}