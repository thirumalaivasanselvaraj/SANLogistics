package com.saneforce.logistics.Activity_Class;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saneforce.logistics.API_interface.ApiClient;
import com.saneforce.logistics.API_interface.ApiInterface;
import com.saneforce.logistics.Add_New_Customer_Spinner_Pojo.Add_New_Customer_Success;
import com.saneforce.logistics.Add_New_Customer_Spinner_Pojo.CustomerCategory;
import com.saneforce.logistics.Add_New_Customer_Spinner_Pojo.Customertype;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.Customer_Me.CustomerMe;
import com.saneforce.logistics.R;
import com.saneforce.logistics.Splash_Screen_Activity;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Customer_Activity extends AppCompatActivity implements View.OnClickListener {
    // TextInputLayout inputname, inputaddress, inputcontactperson, inputdesignation, inputmobile, input_email, inputcategory, customertype;
    ImageView addcustomer_activity_back_arrow;
    Common_Class common_class;

    EditText nameofthecompany, addressofthecompany, contactpersonname, designation, mobilenumber, Emailid;
    TextView savecustomer;
    ProgressBar progressBar;
    ApiInterface apiService;
    SearchableSpinner category, customertype;

    ArrayList<String> customerlist, spinnercustomertype;
    String Savecategory, SaveCustomertype;
    List<CustomerMe> CustomerMeList;
    Shared_Common_Pref sharedCommonPref;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__customer_);
        nameofthecompany = (EditText) findViewById(R.id.nameofthecompany);
        addressofthecompany = (EditText) findViewById(R.id.addressofthecompany);
        contactpersonname = (EditText) findViewById(R.id.contactpersonname);
        designation = (EditText) findViewById(R.id.designation);
        mobilenumber = (EditText) findViewById(R.id.mobilenumber);
        Emailid = (EditText) findViewById(R.id.Emailid);
        ScrollView sv = (ScrollView) findViewById(R.id.scroll);
        category = (SearchableSpinner) findViewById(R.id.category);
        customertype = (SearchableSpinner) findViewById(R.id.customertype);



        savecustomer = (TextView) findViewById(R.id.savecustomer);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setFocusableInTouchMode(false);
        common_class = new Common_Class(Add_Customer_Activity.this);
        addcustomer_activity_back_arrow = (ImageView) findViewById(R.id.addcustomer_activity_back_arrow);
        savecustomer.setOnClickListener(this);
        addcustomer_activity_back_arrow.setOnClickListener(this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        customerlist = new ArrayList<>();
        spinnercustomertype = new ArrayList<>();
        sv.scrollTo(5, 10);
        common_class.hidekeyboard(Add_Customer_Activity.this);
        gson = new Gson();
        sharedCommonPref = new Shared_Common_Pref(Add_Customer_Activity.this);
        Type type = new TypeToken<List<CustomerMe>>() {
        }.getType();
        System.out.println("TYPETOKEN_LIST" + type);
        CustomerMeList = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.cards_pref), type);
        if (common_class.isNetworkAvailable(getApplicationContext())) {
            Call<Add_New_Customer_Success> Callto = apiService.Addnewcustomerspinner();
            Callto.enqueue(Getspinnervalues);
        } else {
            common_class.showToastMSG(Add_Customer_Activity.this, getString(R.string.nointernetcnnection),3);
        }

        ArrayAdapter<String> CAT = new ArrayAdapter<String>(this, R.layout.spinner_my_account, R.id.text_spinner, customerlist);
        CAT.setDropDownViewResource(R.layout.spinner_my_account);

        category.setAdapter(CAT);


        ArrayAdapter<String> CTYPE = new ArrayAdapter<String>(this, R.layout.spinner_my_account, R.id.text_spinner, spinnercustomertype);
        CTYPE.setDropDownViewResource(R.layout.spinner_my_account);

        customertype.setAdapter(CTYPE);


        category.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                Savecategory = category.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected() {

            }
        });

        customertype.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                SaveCustomertype = customertype.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addcustomer_activity_back_arrow:
                common_class.CommonIntentwithNEwTask(Profile_Activity.class);
                break;
            case R.id.savecustomer:
                if (vali()) {
                    progressBar.setVisibility(View.VISIBLE);
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("Emp_ID", CustomerMeList.get(0).getEmpID());
                    map.put("nameofthecompany", nameofthecompany.getText().toString());
                    map.put("addressofthecompany", addressofthecompany.getText().toString());
                    map.put("contactpersonname", contactpersonname.getText().toString());
                    map.put("designation", designation.getText().toString());
                    map.put("mobilenumber", mobilenumber.getText().toString());
                    map.put("Email", Emailid.getText().toString());
                    map.put("category", Savecategory);

                    map.put("customertype", SaveCustomertype);

                    System.out.println("MAP_ADD_CUSTOMER_VALUES" + map.toString());
                    Call<ResponseBody> Callto = apiService.AddcompanyDetails(map);
                    Callto.enqueue(Addnewcustomer);
                }
                break;
        }
    }

    public Callback<Add_New_Customer_Success> Getspinnervalues = new Callback<Add_New_Customer_Success>() {
        @Override
        public void onResponse(Call<Add_New_Customer_Success> call, Response<Add_New_Customer_Success> response) {

            System.out.println("checkUser is sucessful Getspinnervalues:" + response.isSuccessful());
            if (response.isSuccessful()) {

                customerlist.clear();
                spinnercustomertype.clear();
                List<CustomerCategory> CC = response.body().getCustomerCategory();
                List<Customertype> CT = response.body().getCustomertype();


                for (int i = 0; i < CT.size(); i++) {
                    customerlist.add(CT.get(i).getCusTypeName());
                }
                for (int i = 0; i < CC.size(); i++) {
                    spinnercustomertype.add(CC.get(i).getCusCatName());
                }


                System.out.println("CUSTOMERSIZE" + CT.get(0).getCusTypeName());
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
        public void onFailure(Call<Add_New_Customer_Success> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
        }


    };

    public boolean vali() {

        if (nameofthecompany.getText().toString().equalsIgnoreCase("")) {


            nameofthecompany.setError("Enter the  Name of the company");

            nameofthecompany.requestFocus();
            return false;
        }

        if (nameofthecompany.getText().toString().equalsIgnoreCase("")) {


            nameofthecompany.setError(" Enter the  Name of the company");

            nameofthecompany.requestFocus();
            return false;
        }
        if (addressofthecompany.getText().toString().equalsIgnoreCase("")) {


            addressofthecompany.setError(" Enter the  Address of the company");

            addressofthecompany.requestFocus();
            return false;
        }
        if (contactpersonname.getText().toString().equalsIgnoreCase("")) {


            contactpersonname.setError("Contact Person Name");

            contactpersonname.requestFocus();
            return false;
        }
        if (designation.getText().toString().equalsIgnoreCase("")) {


            designation.setError(" Enter the  Designation");

            designation.requestFocus();
            return false;
        }
        if (mobilenumber.getText().toString().equalsIgnoreCase("")) {


            mobilenumber.setError(" Enter the mobile No");

            mobilenumber.requestFocus();
            return false;
        }


        if (mobilenumber.getText().toString().equalsIgnoreCase("")) {


            mobilenumber.setError(" Enter the mobile No");

            mobilenumber.requestFocus();
            return false;
        }
        if (mobilenumber.getText().toString().length() != 10) {


            mobilenumber.setError("Check Your Number");

            mobilenumber.requestFocus();
            return false;
        }


        return true;

    }

    public Callback<ResponseBody> Addnewcustomer = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            System.out.println("checkUser is sucessful :" + response.isSuccessful());
            if (response.isSuccessful()) {
                JSONObject jsonObject = null;
                String jsonData = null;
                try {
                    jsonData = response.body().string();
                    jsonObject = new JSONObject(jsonData);
                    if (jsonObject.getString("success").equals("true")) {
                        progressBar.setVisibility(View.GONE);

                        common_class.showToastMSG(Add_Customer_Activity.this,jsonObject.getString("msg"),1);

                        common_class.CommonIntentwithNEwTask(MainActivity.class);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        common_class.showToastMSG(Add_Customer_Activity.this,jsonObject.getString("msg"),3);
                    }
                    Log.d("TAG", "jsonObject: " + jsonObject);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
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
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
        }


    };

}

