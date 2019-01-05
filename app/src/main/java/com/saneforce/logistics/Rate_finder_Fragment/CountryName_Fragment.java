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
import com.saneforce.logistics.Adapter_Class.Countryname_Adapter_Ratefinder;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.Customer_Profile_Pojo.CustomerProfileNameOftheCompany;
import com.saneforce.logistics.Enquiry_Me.CountryName;
import com.saneforce.logistics.Fragment_Activity.Fragment_Rate_Finder;

import com.saneforce.logistics.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CountryName_Fragment extends Fragment implements View.OnClickListener {
    RecyclerView.LayoutManager recylerViewLayoutManager;
    RecyclerView mRecyclerview;
    CountryName params_Data;
    Gson gson;
    List<CountryName> CountryNamePojoList;
    Shared_Common_Pref shared_common_pref;
    List<CountryName> maker_list;
    String fragname, selected_value;
    Countryname_Adapter_Ratefinder makerAdapter;
    ImageView back_arrow;
    TextView titlename,Empty_text;
    RelativeLayout Empty_screen;
    ImageView Empty_imag;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.countrynamerate_finder, container, false);
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
        Empty_imag = (ImageView) v.findViewById(R.id.Empty_imag);
        Empty_text = (TextView) v.findViewById(R.id.Empty_text);
        titlename.setText("Select Country");
        maker_list = new ArrayList<CountryName>();
        gson = new Gson();
        Empty_screen = (RelativeLayout) v.findViewById(R.id.Empty_Screen);  //couldn't create view referece obj for empy_screen by Butterknife. so here

        if (getArguments() != null) {

            // fragname = getArguments().getString("vehicle_edit");
            selected_value = getArguments().getString("selected_value");


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

        String param_list = shared_common_pref.getvalue(Shared_Common_Pref.CountryShared);

        Type COnut = new TypeToken<List<CountryName>>() {
        }.getType();
        CountryNamePojoList = gson.fromJson(shared_common_pref.getvalue(Shared_Common_Pref.CountryShared), COnut);
       /* if (!(Common_Class.isCountrynameEmpty(CountryNamePojoList))) {
            for (int i = 0; i < CountryNamePojoList.size(); i++) {
                maker_list.add(CountryNamePojoList.get(i).getCountryName());
                System.out.println("+++params_data" + CountryNamePojoList.get(i).getCountryName());
            }
        }*/



        System.out.println("Employees below the age of 30");


        if (maker_list != null) {



            makerAdapter = new Countryname_Adapter_Ratefinder(CountryNamePojoList, getActivity(), "ratefinder", selected_value);
            mRecyclerview.setLayoutManager(recylerViewLayoutManager);
            mRecyclerview.setAdapter(makerAdapter);
            System.out.println("Countryname_Adapter_List" + makerAdapter.toString());

        }

    }


    private void LisitngMessage(int empty_deals, String string, String s) {
        Empty_screen.setVisibility(View.VISIBLE);//empty screen
        Empty_imag.setImageResource(empty_deals);
        Empty_text.setText(string);


    }


}
