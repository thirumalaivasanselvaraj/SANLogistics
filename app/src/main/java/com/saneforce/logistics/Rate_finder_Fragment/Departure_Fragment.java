package com.saneforce.logistics.Rate_finder_Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saneforce.logistics.Adapter_Class.Departure_Adapter;
import com.saneforce.logistics.Adapter_Class.POL_Adapter;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.Enquiry_Me.CountryName;
import com.saneforce.logistics.Enquiry_Me.PortName;
import com.saneforce.logistics.Fragment_Activity.Fragment_Rate_Finder;
import com.saneforce.logistics.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Departure_Fragment extends Fragment implements View.OnClickListener {
    RecyclerView.LayoutManager recylerViewLayoutManager;
    RecyclerView mRecyclerview;
    CountryName params_Data;
    Gson gson;
    List<RatefilnderResponse> PortPojoList;
    Shared_Common_Pref shared_common_pref;
    List<String> maker_list;
    String fragname, selected_value, Select_POD;
    Departure_Adapter makerAdapter;
    ImageView back_arrow;
    TextView titlename, Empty_text;
    RelativeLayout Empty_screen;
    ImageView Empty_imag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.departure_fragment, container, false);
        Declaration_View(v);
        return v;
    }

    private void Declaration_View(View v) {
        mRecyclerview = (RecyclerView) v.findViewById(R.id.countrynamerecyclerview);
        shared_common_pref = new Shared_Common_Pref(getActivity());
        recylerViewLayoutManager = new LinearLayoutManager(getActivity());
        params_Data = new CountryName();
        titlename = (TextView) v.findViewById(R.id.car);
        back_arrow = (ImageView) v.findViewById(R.id.back_arrow);
        titlename.setText("Select Departure");
        Empty_imag = (ImageView) v.findViewById(R.id.Empty_imag);
        Empty_text = (TextView) v.findViewById(R.id.Empty_text);
        Empty_screen = (RelativeLayout) v.findViewById(R.id.Empty_Screen);  //couldn't create view referece obj for empy_screen by Butterknife. so here

        maker_list = new ArrayList<>();
        gson = new Gson();
        if (getArguments() != null) {

            // fragname = getArguments().getString("vehicle_edit");
            selected_value = getArguments().getString("selected_value");
            Select_POD = getArguments().getString("Select_POD");


        }
        back_arrow.setOnClickListener(this);
        GetDataFromPref();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back_arrow:

                getActivity().getFragmentManager().beginTransaction().replace(R.id.homepage, new Fragment_Rate_Finder()).commit();
                break;
        }

    }

    public void GetDataFromPref() {
        maker_list.clear();
        String param_list = shared_common_pref.getvalue(Shared_Common_Pref.PortShared);

        Type port = new TypeToken<List<RatefilnderResponse>>() {
        }.getType();
        PortPojoList = gson.fromJson(shared_common_pref.getvalue(Shared_Common_Pref.Ratefnder), port);
        System.out.println("SELECTED_VALUES" + Select_POD);
        if (!(Common_Class.isRatefinderEmpty(PortPojoList))) {
            for (int i = 0; i < PortPojoList.size(); i++) {

                if ((PortPojoList.get(i).getFINALPOD())
                        .equals(Select_POD)) {

                    if (!maker_list.contains(PortPojoList.get(i).getDEP())) {
                        maker_list.add(PortPojoList.get(i).getDEP());
                    }

                    System.out.println("+++params_data" + PortPojoList.get(i).getCountryName());
                }
            }
        }


        if (maker_list != null && maker_list.size() > 0) {

            System.out.println("+++Maker_model" + maker_list.toString());

            makerAdapter = new Departure_Adapter(maker_list, getActivity(), "Departure", selected_value);
            mRecyclerview.setLayoutManager(recylerViewLayoutManager);
            mRecyclerview.setAdapter(makerAdapter);


        } else {
            LisitngMessage(R.drawable.ic_empty_location, "Departure Is Not Available");

        }

    }

    private void LisitngMessage(int empty_deals, String string) {

        System.out.println("EMPTY_SCREEN_IS_NOT_WORKING");
        Empty_screen.setVisibility(View.VISIBLE);//empty screen
        Empty_imag.setImageResource(empty_deals);
        Empty_text.setText(string);


    }

}


