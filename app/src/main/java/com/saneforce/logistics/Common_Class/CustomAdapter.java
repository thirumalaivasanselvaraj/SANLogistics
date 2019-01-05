package com.saneforce.logistics.Common_Class;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.saneforce.logistics.Enquiry_Me.Incoterm;
import com.saneforce.logistics.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Incoterm> {

    LayoutInflater flater;
    int a;

    public CustomAdapter(Activity context, int resouceId, int textviewId, List<Incoterm> list, int b) {

        super(context, resouceId, textviewId, list);
        flater = context.getLayoutInflater();
        a = b;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Incoterm rowItem = getItem(position);
        ImageView imagTick;
        View rowview = flater.inflate(R.layout.spinner_my_account, null, true);
        TextView txtTitle = (TextView) rowview.findViewById(R.id.text_spinner);
        System.out.println("ADAPTER_THIRU" + position + "A_Values" + a);
        txtTitle.setText(rowItem.getIncotermsName());

        /*if (position == a) {
            imagTick.setVisibility(View.VISIBLE);
        } else {
            imagTick.setVisibility(View.GONE);

        }*/

        return rowview;
    }
}