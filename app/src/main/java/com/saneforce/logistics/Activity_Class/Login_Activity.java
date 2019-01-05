package com.saneforce.logistics.Activity_Class;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.saneforce.logistics.API_interface.ApiClient;
import com.saneforce.logistics.API_interface.ApiInterface;
import com.saneforce.logistics.API_interface.Example;
import com.saneforce.logistics.API_interface.Product;
import com.saneforce.logistics.Add_New_Customer_Spinner_Pojo.Add_New_Customer_Success;
import com.saneforce.logistics.Add_New_Customer_Spinner_Pojo.CustomerCategory;
import com.saneforce.logistics.Add_New_Customer_Spinner_Pojo.Customertype;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.Common_Class.click_Listner;
import com.saneforce.logistics.Customer_Me.CustomerMe;
import com.saneforce.logistics.Customer_Me.Customer_Me_Success;
import com.saneforce.logistics.Customer_Profile_Pojo.CustomerProfileNameOftheCompany;
import com.saneforce.logistics.Enquiry_Me.Enquiry_Me_Success;
import com.saneforce.logistics.Model_Class.Login_Model_Class;
import com.saneforce.logistics.R;
import com.saneforce.logistics.Splash_Screen_Activity;
import com.sdsmdg.tastytoast.TastyToast;
import com.xwray.passwordview.PasswordView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.Result;

import okhttp3.ResponseBody;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    Button login;
    EditText loginemail;
    ProgressBar progressBar;
    Gson gson;

    Common_Class common_class;
    ApiInterface apiService;
    List<CustomerMe> PojoCustomerMe;
    Shared_Common_Pref sharedCommonPref;
    PasswordView loginpassword;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        loginemail = (EditText) findViewById(R.id.loginemail);
        //loginpassword = (EditText) findViewById(R.id.loginpassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setFocusableInTouchMode(false);
        sharedCommonPref = new Shared_Common_Pref(Login_Activity.this);
        common_class = new Common_Class(Login_Activity.this);
        gson = new Gson();
        loginpassword = (PasswordView) findViewById(R.id.loginpassword);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (vali()) {
                    apiService = ApiClient.getClient().create(ApiInterface.class);
                    if (common_class.isNetworkAvailable(getApplicationContext())) {
                        progressBar.setVisibility(View.VISIBLE);


                        JSONObject paramObject = new JSONObject();
                        try {
                            paramObject.put("name", loginemail.getText().toString());
                            paramObject.put("password", loginpassword.getText().toString());

                            Call<Customer_Me_Success> Callto = apiService.LoginJSON(paramObject.toString());
                            Callto.enqueue(CheckUser);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("JSON Expections" + paramObject.toString());

                        }
                        System.out.println("JSON VALUS" + paramObject.toString());
                        /*HashMap<String, String> map = new HashMap<String, String>();
                        map.put("name", loginemail.getText().toString());
                        map.put("password", loginpassword.getText().toString());
                        Call<Customer_Me_Success> Callto = apiService.Login(map);
                        Callto.enqueue(CheckUser);*/
                    } else {

                        common_class.showToastMSG(Login_Activity.this, getString(R.string.nointernetcnnection), 3);
                    }
                }

                break;
        }

    }

    public Callback<Customer_Me_Success> CheckUser = new Callback<Customer_Me_Success>() {
        @Override
        public void onResponse(Call<Customer_Me_Success> call, Response<Customer_Me_Success> response) {

            System.out.println("checkUser is sucessful :" + response.isSuccessful());
            if (response.isSuccessful()) {
                PojoCustomerMe = response.body().getCustomerMe();
                Gson gson = new Gson();
                String json = gson.toJson(PojoCustomerMe);
                sharedCommonPref.save(Shared_Common_Pref.cards_pref, json);


                if (response.body().getSuccess()) {
                    progressBar.setVisibility(View.GONE);
                    sharedCommonPref.save(Shared_Common_Pref.loggedIn, "loggedIn");
                    sharedCommonPref.save(Shared_Common_Pref.Password, loginpassword.getText().toString());
                    common_class.CommonIntentwithNEwTask(MainActivity.class);


                } else {
                    System.out.println("IS LOGGED IN " + sharedCommonPref.getvalue(Shared_Common_Pref.loggedIn));
                    progressBar.setVisibility(View.GONE);
                    TastyToast.makeText(getApplicationContext(), response.body().getMsg(),
                            TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                }


            } else {
                try {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    Toast.makeText(getApplicationContext(), jObjError.getString("error_msg"), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                    System.out.println("this is responsebody error" + jObjError.getString("success"));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "catchbody error " + e.toString(), Toast.LENGTH_LONG).show();
                    System.out.println("catchbody error " + e.toString());
                }
            }
        }

        @Override
        public void onFailure(Call<Customer_Me_Success> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
        }


    };


    public boolean vali() {

        if (loginemail.getText().toString().equalsIgnoreCase("")) {
            loginemail.setError("Please enter the  UserName");
            loginemail.requestFocus();
            TextInputLayout til = (TextInputLayout) findViewById(R.id.textinputloginemail);
            //til.setError("Please enter the  UserName");
            loginemail.getBackground().setColorFilter(getResources().getColor(R.color.redcolor), PorterDuff.Mode.SRC_ATOP);
            return false;
        }


        if(loginpassword.getText().toString().equalsIgnoreCase("")){

            //TextInputLayout til = (TextInputLayout) findViewById(R.id.passwordinput);

            //  loginpassword.setError("Please enter the Password");
            common_class.showToastMSG(Login_Activity.this, "Enter The Password", 3);
            loginpassword.requestFocus();
            return false;
        }
        if (!(loginpassword.getText().toString().length() >= 3)) {
            loginpassword.setError("Min length 3");
            loginpassword.getBackground().setColorFilter(getResources().getColor(R.color.redcolor), PorterDuff.Mode.SRC_ATOP);

            loginpassword.requestFocus();
            return false;
        }
        return true;

    }
}
