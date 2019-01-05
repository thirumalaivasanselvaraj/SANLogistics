package com.saneforce.logistics.Common_Class;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by nn1android01user on 15/11/16.
 */

public class Shared_Common_Pref {
    SharedPreferences Common_pref;
    SharedPreferences.Editor editor;
    Activity activity;
    Context _context;

    public static final String username = "vehicle_pref";
    public static final String Vehicle_list_pref = "vehicle_list";
    public static final String Location_list_pref = "location_list";
    public static final String Creditcard_list_pref = "creditcard_List";
    public static final String profile_image = "profile_image";
    public static final String flag_image = "flag_image";
    public static final String flag_code = "flag_code";
    public static final String loggedIn = "loggedIn";
    public static final String accesstoken = "accesstoken";
    public static final String params = "params";
    public static final String vehicle_model = "vehicle_model";
    public static final String vehicle_year = "vehicle_year";
    public static final String fcm_token = "fcm_token";
    public static final String fb_profile = "fb_profile";
    public static final String google_plus_profile = "google_plus_profile";
    public static final String logged_email = "logged_email";
    public static final String fuel_pref = "fuel_pref";
    public static final String cards_pref = "cards_pref";
    public static final String order_id = "order_id";
    public static final String get_Gas_details = "get_Gas_details";
    public static final String tamil = "tamil";
    public static final String Password = "Password";
    public static final String selectaddress = "selectaddress";

    public static final String driverlogin = "driverlogin";
    public static final String Commidity = "Commidity";
    public static final String Priority = "Priority";
    public static final String Incoterm = "Incoterm";
    public static final String CountryShared = "CountryShared";
    public static final String PortShared = "PortShared";
    public static final String PackagingType = "PackagingType";
    public static final String ContainerType = "ContainerType";
    public static final String CompanyName = "CompanyName";
    public static final String Ratefnder = "Ratefnder";
    public Shared_Common_Pref(Activity Ac) {
        activity = Ac;
        if (activity != null) {
            Common_pref = activity.getSharedPreferences("Preference_values", Context.MODE_PRIVATE);
            editor = Common_pref.edit();
        }
    }

    public Shared_Common_Pref(Context cc) {
        this._context = cc;
        Common_pref = cc.getSharedPreferences("Preference_values", Context.MODE_PRIVATE);
        editor = Common_pref.edit();
    }

    public void save(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getvalue(String key) {
        String text = null;
        text = Common_pref.getString(key, null);
        return text;
    }

    public void save_Longvalue(String key, Long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public Long getLong_value(String key) {
        Long text = null;
        text = Common_pref.getLong(key, 0);
        return text;
    }

    public void clear_pref(String key) {
        Common_pref.edit().remove(key).apply();

        //the good quality product by the end of the day worth od manual  developement in this quality regaurds minimum qu.
    }

}
