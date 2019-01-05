package com.saneforce.logistics.Fragment_Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.saneforce.logistics.Activity_Class.Enquiry_Activity;
import com.saneforce.logistics.Activity_Class.Profile_Activity;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.R;

public class Home_Fragment_Activity extends Fragment implements View.OnClickListener {
    View v;
    Common_Class common_class;
    Shared_Common_Pref shared_common_pref;
    Button customerprofilebutton, enquirybutton, RateFinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frgment_home_activity, container, false);

        Declaration_View(v);
        return v;
    }

    private void Declaration_View(View v) {
        //swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        shared_common_pref = new Shared_Common_Pref(getActivity());
        common_class = new Common_Class(getActivity());
        customerprofilebutton = (Button) v.findViewById(R.id.customerprofilebutton);
        enquirybutton = (Button) v.findViewById(R.id.enquirybutton);
        RateFinder = (Button) v.findViewById(R.id.RateFinder);
        customerprofilebutton.setOnClickListener(this);
        enquirybutton.setOnClickListener(this);
        RateFinder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customerprofilebutton:

                common_class.CommonIntentwithNEwTask(Profile_Activity.class);
                break;
            case R.id.enquirybutton:
                common_class.CommonIntentwithNEwTask(Enquiry_Activity.class);
                break;
            case R.id.RateFinder:
                getFragmentManager().beginTransaction().replace(R.id.homepage, new Fragment_Rate_Finder()).commit();

                break;

        }
    }
}
