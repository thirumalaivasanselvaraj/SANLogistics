package com.saneforce.logistics;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.saneforce.logistics.Activity_Class.Login_Activity;
import com.saneforce.logistics.Activity_Class.MainActivity;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.ConnectionDetector;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.sdsmdg.tastytoast.TastyToast;

public class Splash_Screen_Activity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;

    Common_Class common_class;
    Shared_Common_Pref sharedCommonPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen_);
        sharedCommonPref = new Shared_Common_Pref(Splash_Screen_Activity.this);
        common_class = new Common_Class(getApplicationContext());
        if (!(common_class.isNetworkAvailable(getApplicationContext()))) {
            common_class.showToastMSG(Splash_Screen_Activity.this, getString(R.string.nointernetcnnection),3);
        }

        System.out.println("IS LOGGED IN " + sharedCommonPref.getvalue(Shared_Common_Pref.loggedIn));

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                if (sharedCommonPref.getvalue(Shared_Common_Pref.loggedIn) != null && sharedCommonPref.getvalue(Shared_Common_Pref.loggedIn).equals("loggedIn")) {
                    Intent i = new Intent(Splash_Screen_Activity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                } else {

                    Intent ii = new Intent(Splash_Screen_Activity.this, Login_Activity.class);
                    startActivity(ii);


                    finish();
                }


            }
        }, SPLASH_TIME_OUT);
    }
}
