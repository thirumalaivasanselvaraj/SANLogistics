package com.saneforce.logistics.Fragment_Activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.gson.Gson;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.R;


import static android.content.Context.MODE_PRIVATE;


public class Settings_Fragment extends Fragment implements View.OnClickListener {
    TextView password;
    Common_Class common_class;
    LinearLayout sharewithfrnds, lay_abt_leuf, lay_termsof_use, lay_privacypolicy, lay_myaccount, deletelayout, layoutversion;
    public static Boolean abt_leuf, termsof_use, privacypolicy = false;
    TextView settingusername, settingemail;
    SharedPreferences getdataloginstored;

    Gson gson;
    public static CircularImageView getimage;
    Shared_Common_Pref shared_prefrence;
    Typeface face;
    int i = 0;
    LinearLayout linear_pass;
    public static Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings_, container, false);
        Declaration_View(v);
        common_class = new Common_Class(getActivity());

        sharewithfrnds.setOnClickListener(this);
        lay_abt_leuf.setOnClickListener(this);
        lay_termsof_use.setOnClickListener(this);
        lay_privacypolicy.setOnClickListener(this);
        deletelayout.setOnClickListener(this);
        layoutversion.setOnClickListener(this);


        return v;

    }

    public void Declaration_View(View v) {
        face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Light.ttf");
        activity = getActivity();



        getdataloginstored = getActivity().getSharedPreferences("logingetaccesstoken", MODE_PRIVATE);
        gson = new Gson();

        sharewithfrnds = (LinearLayout) v.findViewById(R.id.lay_sharewithfrnds);
        lay_abt_leuf = (LinearLayout) v.findViewById(R.id.lay_abtleuf);
        lay_termsof_use = (LinearLayout) v.findViewById(R.id.lay_terms_of_use);
        lay_privacypolicy = (LinearLayout) v.findViewById(R.id.lay_privacy_policy);

        deletelayout = (LinearLayout) v.findViewById(R.id.deletelayot);
        layoutversion = (LinearLayout) v.findViewById(R.id.layoutversion);


        v.setFocusableInTouchMode(true);
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    //Toast.makeText(getActivity(),"Press BACK again to exit",Toast.LENGTH_SHORT).show();
                    System.out.println("INTEGE_VALUe" + i);
                    if (i == 1) {
                        getActivity().finish();
                        return true;
                    }
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("INTEGE_VALUerun" + i);

                        i = 1;
                    }
                }, 1000);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.lay_sharewithfrnds:
                //   common_class.sharedialog(getActivity());

                System.out.println("sharedialog");
                break;
            case R.id.lay_abtleuf:
                abt_leuf = true;
                // common_class.CommonIntentwithFinishputextra(Activity_aboutapp.class, getResources().getString(R.string.t_abtleuf));
                break;

            case R.id.lay_terms_of_use:
                termsof_use = true;
                // common_class.CommonIntentwithFinishputextra(Activity_aboutapp.class, getResources().getString(R.string.t_TOU));
                break;
            case R.id.lay_privacy_policy:
                privacypolicy = true;
                //  common_class.CommonIntentwithFinishputextra(Activity_aboutapp.class, getResources().getString(R.string.t_privacypolicy));
                break;

            case R.id.deletelayot:
                //common_class.deletedialog(getActivity());
                break;
            case R.id.layoutversion:
                //common_class.Alertdialogbox(getActivity());
                break;


        }

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Toast.makeText(getActivity(), "Press BACK again to exit", Toast.LENGTH_SHORT).show();
                if (i == 1) {
                    getActivity().finish();
                    return true;
                }
        }
        if (i == 1) {
            return true;

        } else {
            return false;

        }
    }

}