package com.saneforce.logistics.Activity_Class;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.saneforce.logistics.R;


public class Notificationyour_order extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationyour_order);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        toolbar = (Toolbar) findViewById(R.id.customtoolbar);

        setSupportActionBar(toolbar);
        TextView title = (TextView) toolbar.findViewById(R.id.customtoolbartitle);

        ImageView customtoolbartitle = (ImageView) toolbar.findViewById(R.id.custombackbutton);

        customtoolbartitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setBackgroundColor(getResources().getColor(R.color.white));


        if (null != getSupportActionBar()) {

            title.setText(getResources().getString(R.string.notificationordertitle));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        switch (item.getItemId()) {


        }
        return super.onOptionsItemSelected(item);
    }
}