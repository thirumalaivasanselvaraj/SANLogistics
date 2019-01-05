package com.saneforce.logistics.Adapter_Class;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saneforce.logistics.Activity_Class.MainActivity;
import com.saneforce.logistics.Common_Class.clicl_listner_country;
import com.saneforce.logistics.Enquiry_Me.CountryName;
import com.saneforce.logistics.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Countryname_Adapter_Ratefinder extends RecyclerView.Adapter<Countryname_Adapter_Ratefinder.MyViewHolder> {

    public List<CountryName> meker_list = new ArrayList<>();
    Context mcontext;
    View itemlayout;
    clicl_listner_country clicl_listner_fuel;
    String frag_name, selected;
    int vehicle_id;


    public Countryname_Adapter_Ratefinder(List<CountryName> fuel, Context context, String frag_name_, String selected_value) {
        this.mcontext = context;
        this.meker_list = fuel;
        this.frag_name = frag_name_;
        this.clicl_listner_fuel = (clicl_listner_country) context;
        this.selected = selected_value;
    }

    @Override
    public Countryname_Adapter_Ratefinder.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemlayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.tickmark_listview, null);
        Countryname_Adapter_Ratefinder.MyViewHolder viewHolder = new Countryname_Adapter_Ratefinder.MyViewHolder(itemlayout, mcontext);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final Countryname_Adapter_Ratefinder.MyViewHolder holder, final int position) {
        Typeface face = Typeface.createFromAsset(mcontext.getAssets(), "fonts/Roboto-Light.ttf");
        holder.mtxtpremium.setTypeface(face);
        System.out.println("Country_Flag" + meker_list.get(position).getCountry_flag());
        holder.mtxtpremium.setText(meker_list.get(position).getCountryName());

        if (meker_list.get(position).getCountry_flag() != null) {
            holder.countryflagg.setVisibility(View.VISIBLE);
            Picasso
                    .with(mcontext)
                    .load(meker_list.get(position).getCountry_flag())

                    .into(holder.countryflagg);

        }
        if (meker_list.get(position).getCountryName().equals(selected)) {
            holder.imaTick.setVisibility(View.VISIBLE);
        } else {
            holder.imaTick.setVisibility(View.GONE);

        }
        holder.linear_selecet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frag_name != null && frag_name.equals("ratefinder")) {
                    clicl_listner_fuel.onflagClick(v, position, "ratefinder", "ratefinder", meker_list);
                    System.out.println("MAKER Clicked position");
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        System.out.println("fuel_listada" + meker_list.size());

        return meker_list.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mtxtpremium;
        View view_;
        ImageView imaTick, countryflagg;
        LinearLayout linear_selecet;

        public MyViewHolder(View view, Context context) {
            super(view);
            mtxtpremium = (TextView) view.findViewById(R.id.premium);
            view_ = (View) view.findViewById(R.id.view_);
            imaTick = (ImageView) view.findViewById(R.id.imagTick);
            linear_selecet = (LinearLayout) view.findViewById(R.id.linear_selecet);

            countryflagg = (ImageView) view.findViewById(R.id.countryflagg);
        }
    }


}
