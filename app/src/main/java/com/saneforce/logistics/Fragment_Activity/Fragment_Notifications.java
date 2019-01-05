package com.saneforce.logistics.Fragment_Activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.saneforce.logistics.Adapter_Class.Notification_Adapter;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Pojo_Class.Notification_Pojo;
import com.saneforce.logistics.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nnadmin on 12/10/16.
 */
public class Fragment_Notifications extends Fragment implements View.OnClickListener {

    public List<Notification_Pojo> notificationList = new ArrayList<>();
    private RecyclerView notificationrecyclerview;
    private Notification_Adapter mAdapter;
    Common_Class common_class;
    TextView writereview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);


        mAdapter = new Notification_Adapter(notificationList);

        Declaration_View(v);


        return v;
    }

    private void Declaration_View(View v) {
       // writereview = (TextView) v.findViewById(R.id.writereview);
        common_class = new Common_Class(getActivity());
        notificationrecyclerview = (RecyclerView) v.findViewById(R.id.notificationrecyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        notificationrecyclerview.setLayoutManager(mLayoutManager);
        notificationrecyclerview.setItemAnimator(new DefaultItemAnimator());
        notificationrecyclerview.setAdapter(mAdapter);
       // writereview.setOnClickListener(this);

      /*  notificationrecyclerview.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), notificationrecyclerview, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                common_class.CommonIntentwithoutFinish(Notificationyour_order.class);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        preparenotificationData();
    }

    private void preparenotificationData() {


        Notification_Pojo notification = new Notification_Pojo("TODAY", "Your Car is ready to hit the road again. You can now check your bill", "The truck has arrived and we are filling your tank");
        notificationList.add(notification);

        notification = new Notification_Pojo("TODAY", "Your Car is ready to hit the road again. You can now check your bill", "The truck has arrived and we are filling your tank");
        notificationList.add(notification);

        notification = new Notification_Pojo("4TH SEP", "Your Car is ready to hit the road again. You can now check your bill", "The truck has arrived and we are filling your tank");
        notificationList.add(notification);

        notification = new Notification_Pojo("29TH JULY", "Your Car is ready to hit the road again. You can now check your bill", "The truck has arrived and we are filling your tank");
        notificationList.add(notification);

        notification = new Notification_Pojo("5TH AUG", "Your Car is ready to hit the road again. You can now check your bill", "The truck has arrived and we are filling your tank");
        notificationList.add(notification);

        notification = new Notification_Pojo("9TH DEC", "Your Car is ready to hit the road again. You can now check your bill", "The truck has arrived and we are filling your tank");
        notificationList.add(notification);


        notificationList.add(notification);

        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


           /*case R.id.writereview:


                //common_class.writereviewdialog(getActivity());
                break;*/
        }
    }
    @Override
    public void onResume() {
        super.onResume();


    }

}
