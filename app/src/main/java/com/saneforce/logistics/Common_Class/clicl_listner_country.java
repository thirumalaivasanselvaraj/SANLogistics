package com.saneforce.logistics.Common_Class;

import android.view.View;

import com.saneforce.logistics.Enquiry_Me.CountryName;

import java.util.List;

public interface clicl_listner_country {
    void onClick(View view, int position, String name, String fragment_name, List<String> list);

    void onflagClick(View view, int position, String name, String fragment_name, List<CountryName> list);

}
