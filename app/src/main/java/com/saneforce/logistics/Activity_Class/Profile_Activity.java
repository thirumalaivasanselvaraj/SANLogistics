package com.saneforce.logistics.Activity_Class;

import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.saneforce.logistics.Customer_Profile_Pojo.CustomerProfileNameOftheCompany;
import com.saneforce.logistics.Customer_Profile_Pojo.MonthlyVolumeName;
import com.saneforce.logistics.Customer_Profile_Pojo.Name_Ofthe_Company_Success;
import com.saneforce.logistics.Customer_Profile_Pojo.SectorName;
import com.saneforce.logistics.Customer_Profile_Pojo.SegmentName;
import com.saneforce.logistics.R;
import com.saneforce.logistics.Spinner_Recycler_View_Adapter.Select_Sector_Adapter;
import com.saneforce.logistics.Spinner_Response.Spinner_Pojo;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, Select_Sector_Adapter.ItemClickListener {
    ImageView back_arrow;
    Common_Class common_class;
    Spinner spinnernameofthecompany, spinnervolume, spinnersegmant, spinnersector, typeofcallspinner, modeofcallspinner, descriptionspinner, customertypespinner, enquirynspinner, nextfollowuppinner;
    ImageView Addcustomer;
    ApiInterface apiService;
    ArrayList<String> ListNameofthecompany, MonthlyVolumeArrrayList, SectorArrrayList, SegmentArrrayList, TypeofcallArrayList, ModeofCallArrayList, MultipleSelectedSectorList, DescriptionArraylist, CustomerTypeArrayList, EnquiryArrayList, NextFollowupArrayList;
    List<CustomerProfileNameOftheCompany> PojoNameofthecompany;
    List<SectorName> SectornamePojo;
    List<SegmentName> SegmentPojo;
    List<MonthlyVolumeName> MonthlyVolumePojo;
    Button SaveProfile;
    EditText Address, contactpersonname,
            designation, mobilenumber, Emailid, category, decisionmakertitle, decisionmakermobile, decisionmakeremail, influensortitle, influensormobile, influensoremail;
    private boolean isSpinnerInitial = true;
    Handler handler;
    TextView timer, onlytime;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    int Seconds, Minutes, MilliSeconds;
    String CompanyName, VolumeString, SegmentString, SectorString, TypeofCallString, ModeOFCallStringString, DescriptionString, CustomerTypeString, EnquiryString, NextFollowupString;
    ProgressBar progressBar;
    List<CustomerMe> PojoCustomerMe;
    Shared_Common_Pref sharedCommonPref;
    Gson gson;
    List<CustomerMe> CustomerMeList;
    SpinnerDialog spinnerDialog;

    MultiSelectSpinner multispinnersector;
    StringBuilder MultiSelectSector;
    Select_Sector_Adapter sectoradapter;
    List<Integer> list;
    Spinner_Pojo Spinner_pojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        common_class = new Common_Class(Profile_Activity.this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        back_arrow = (ImageView) findViewById(R.id.profile_activity_back_arrow);
        back_arrow.setOnClickListener(this);
        SaveProfile = (Button) findViewById(R.id.SaveProfile);
        SaveProfile.setOnClickListener(this);
        Addcustomer = (ImageView) findViewById(R.id.Addcustomer);
        Addcustomer.setOnClickListener(this);
        Spinner_pojo = new Spinner_Pojo();
        spinnervolume = (Spinner) findViewById(R.id.spinnervolume);
        spinnersegmant = (Spinner) findViewById(R.id.spinnersegmant);
        typeofcallspinner = (Spinner) findViewById(R.id.typeofcallspinner);
        modeofcallspinner = (Spinner) findViewById(R.id.modeofcallspinner);
        descriptionspinner = (Spinner) findViewById(R.id.descriptionspinner);
        customertypespinner = (Spinner) findViewById(R.id.customertypespinner);
        enquirynspinner = (Spinner) findViewById(R.id.enquirynspinner);
        nextfollowuppinner = (Spinner) findViewById(R.id.nextfollowuppinner);
        Address = (EditText) findViewById(R.id.customerprofileAddress);
        contactpersonname = (EditText) findViewById(R.id.customerprofilecontactpersonname);
        designation = (EditText) findViewById(R.id.customerprofiledesignation);
        mobilenumber = (EditText) findViewById(R.id.customerprofilemobilenumber);
        Emailid = (EditText) findViewById(R.id.customerprofileEmailid);
        category = (EditText) findViewById(R.id.customerprofilecategory);

        decisionmakertitle = (EditText) findViewById(R.id.decisionmakertitle);
        decisionmakermobile = (EditText) findViewById(R.id.decisionmakermobile);
        decisionmakeremail = (EditText) findViewById(R.id.decisionmakermobile);
        influensortitle = (EditText) findViewById(R.id.influensortitle);
        influensormobile = (EditText) findViewById(R.id.influensormobile);
        influensoremail = (EditText) findViewById(R.id.influensoremail);

        timer = (TextView) findViewById(R.id.timer);
        progressBar = (ProgressBar) findViewById(R.id.ProfileprogressBar);
        progressBar.setFocusableInTouchMode(false);
        onlytime = (TextView) findViewById(R.id.onlystarttimer);
        gson = new Gson();
        sharedCommonPref = new Shared_Common_Pref(Profile_Activity.this);
        onlytime.setText(new SimpleDateFormat("HH:mm a", Locale.ENGLISH).format(new Date()));
        Type type = new TypeToken<List<CustomerMe>>() {
        }.getType();
        System.out.println("TYPETOKEN_LIST" + type);
        CustomerMeList = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.cards_pref), type);
        multispinnersector = (MultiSelectSpinner) findViewById(R.id.multispinnersector);
        MultiSelectSector = new StringBuilder();
        TimerStart();

        System.out.println("EMP_CODE" + CustomerMeList.get(0).getEmpID());
        if (common_class.isNetworkAvailable(getApplicationContext())) {
            if (!(common_class.NAmecheck(CustomerMeList.get(0).getEmpID()))) {

                Call<Name_Ofthe_Company_Success> Callto = apiService.SelectNameOftheCompany(CustomerMeList.get(0).getEmpID());
                Callto.enqueue(Getnameofthecompanyresponse);
            }

        } else {

            common_class.showToastMSG(Profile_Activity.this, getString(R.string.nointernetcnnection), 3);
        }
        ListNameofthecompany = new ArrayList<>();
        MonthlyVolumeArrrayList = new ArrayList<>();
        SectorArrrayList = new ArrayList<>();
        SegmentArrrayList = new ArrayList<>();


        NextFollowupArrayList = new ArrayList<>();
        MultipleSelectedSectorList = new ArrayList<>();

        spinnerDialog = new SpinnerDialog(Profile_Activity.this, ListNameofthecompany, "Select Name Of the Company", "Close Button Text");// With No Animation
        spinnerDialog = new SpinnerDialog(Profile_Activity.this, ListNameofthecompany, "Select or Search Company Name", R.style.DialogAnimations_SmileWindow, "Close");// With 	Animation


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {

                CompanyName = PojoNameofthecompany.get(position).getNameoftheCompany();
                Address.setText(PojoNameofthecompany.get(position).getAddressoftheCompany());
                contactpersonname.setText(PojoNameofthecompany.get(position).getContactPersonName());
                designation.setText(PojoNameofthecompany.get(position).getDesignation());
                mobilenumber.setText(PojoNameofthecompany.get(position).getMobileNumber());
                Emailid.setText(PojoNameofthecompany.get(position).getCustomerEmail());
                category.setText(PojoNameofthecompany.get(position).getCustomerCategory());

            }
        });
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(Common_Class.isEmpty(PojoNameofthecompany))) {
                    spinnerDialog.showSpinerDialog();

                } else {
                    common_class.AddNewCustomer(Profile_Activity.this);
                }
            }
        });


    }


    private void TimerStart() {
        StartTime = SystemClock.uptimeMillis();
        handler = new Handler();

        handler.postDelayed(runnable, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_activity_back_arrow:
                common_class.CommonIntentwithNEwTask(MainActivity.class);
                break;
            case R.id.Addcustomer:
                common_class.CommonIntentwithNEwTask(Add_Customer_Activity.class);
                break;
            case R.id.SaveProfile:
                if (Validation()) {
                    progressBar.setVisibility(View.VISIBLE);

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("Emp_Code", CustomerMeList.get(0).getEmpCode());
                    map.put("Company_Name", CompanyName);
                    map.put("Address", Address.getText().toString());
                    map.put("Contant_Person", contactpersonname.getText().toString());
                    map.put("Designation", designation.getText().toString());
                    map.put("Mobile_No", "00");
                    map.put("Email_ID", Emailid.getText().toString());
                    map.put("Category", category.getText().toString());
                    map.put("Customer_Type", CustomerTypeString);
                    map.put("Description", DescriptionString);
                    map.put("Call_Type", TypeofCallString);
                    map.put("Call_Mode", ModeOFCallStringString);
                    map.put("Decision_Make_Title", decisionmakertitle.getText().toString());
                    map.put("Decision_Make_Mobile", decisionmakermobile.getText().toString());
                    map.put("Decision_Make_Email", decisionmakeremail.getText().toString());
                    map.put("Inf_Title", influensortitle.getText().toString());
                    map.put("Inf_Mobile", influensormobile.getText().toString());
                    map.put("Inf_EmailID", influensoremail.getText().toString());
                    map.put("Monthly_Vol", VolumeString);
                    map.put("Segment", SegmentString);
                    map.put("Sector", MultiSelectSector.toString());
                    map.put("Start_Time", onlytime.getText().toString());
                    map.put("End_Time", new SimpleDateFormat("HH:mm a", Locale.ENGLISH).format(new Date()));
                    map.put("Total_Duration", timer.getText().toString());
                    System.out.println("MAP_ADD_CUSTOMER_VALUES" + map.toString());
                    Call<ResponseBody> CALL = apiService.AddProfile(map);
                    CALL.enqueue(ProfileSaveResponse);


                }
                break;
        }
    }

    public boolean Validation() {

        Log.i("BUILDER-VALUES", " : " + MultiSelectSector);
        if (Address.getText().toString().equalsIgnoreCase("")) {


            common_class.showToastMSG(Profile_Activity.this, "Add New Customer", 3);

            return false;
        }
        if (contactpersonname.getText().toString().equalsIgnoreCase("")) {

            common_class.showToastMSG(Profile_Activity.this, "Select Name Of Company", 3);
            return false;

        }
        if (VolumeString.equalsIgnoreCase("")) {

            common_class.showToastMSG(Profile_Activity.this, "Select Volume", 3);

            return false;
        }

        if (SegmentString.equalsIgnoreCase("")) {

            common_class.showToastMSG(Profile_Activity.this, "Select Segment", 3);
            return false;
        }
        if (MultiSelectSector.toString().equals("")) {

            common_class.showToastMSG(Profile_Activity.this, "Select Sector", 3);
            return false;
        }

        return true;

    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            timer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds)
            );
            /* + String.format("%03d", MilliSeconds)*/
            handler.postDelayed(this, 0);
        }

    };
    public Callback<Name_Ofthe_Company_Success> Getnameofthecompanyresponse = new Callback<Name_Ofthe_Company_Success>() {
        @Override
        public void onResponse(Call<Name_Ofthe_Company_Success> call, Response<Name_Ofthe_Company_Success> response) {

            System.out.println("checkUser is sucessful Getspinnervalues:" + response.isSuccessful());
            if (response.isSuccessful()) {

                if (response.body().getCustomerProfileNameOftheCompany().size() > 0) {
                    PojoNameofthecompany = response.body().getCustomerProfileNameOftheCompany();
                    String Comp = gson.toJson(PojoNameofthecompany);
                    sharedCommonPref.save(Shared_Common_Pref.CompanyName, Comp);


                }

                MonthlyVolumePojo = response.body().getMonthlyVolumeName();
                SegmentPojo = response.body().getSegmentName();
                SectornamePojo = response.body().getSectorName();
                ListNameofthecompany.clear();

                SpinnerSetLIst();


            } else {
                try {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    Toast.makeText(getApplicationContext(), jObjError.getString("error_msg"), Toast.LENGTH_LONG).show();

                    System.out.println("this is responsebody error" + jObjError.getString("success"));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "catchbody error " + e.toString(), Toast.LENGTH_LONG).show();
                    System.out.println("catchbody error " + e.toString());
                }
            }
        }

        @Override
        public void onFailure(Call<Name_Ofthe_Company_Success> call, Throwable t) {

        }


    };


    private void SpinnerSetLIst() {

        SegmentArrrayList.add("Select");
        // SectorArrrayList.add("Select");
        Spinner_pojo.SetMonthlyVolume("Select");
        Spinner_pojo.SetSegment("Select");
        if (!(Common_Class.isEmpty(PojoNameofthecompany))) {
            for (int i = 0; i < PojoNameofthecompany.size(); i++) {
                ListNameofthecompany.add(PojoNameofthecompany.get(i).getNameoftheCompany());
            }
        }
        for (int i = 0; i < MonthlyVolumePojo.size(); i++) {

            Spinner_pojo.SetMonthlyVolume(MonthlyVolumePojo.get(i).getVolName());
            //  MonthlyVolumeArrrayList.add(MonthlyVolumePojo.get(i).getVolName());
        }

        for (int i = 0; i < SegmentPojo.size(); i++) {
            Spinner_pojo.SetSegment(SegmentPojo.get(i).getSegName());

            // SegmentArrrayList.add(SegmentPojo.get(i).getSegName());


        }
        for (int i = 0; i < SectornamePojo.size(); i++) {
            SectorArrrayList.add(SectornamePojo.get(i).getSectorName());
            //Spinner_pojo.SetSector(SectornamePojo.get(i).getSectorName());
        }


        spinnersegmant.setAdapter(common_class.SpinnerResponseAdapter(getApplicationContext(), Spinner_pojo.GetSegment()));


        spinnervolume.setAdapter(common_class.SpinnerResponseAdapter(getApplicationContext(), Spinner_pojo.GetMonthlyVolume()));

       /* ArrayAdapter<String> SEC = new ArrayAdapter<String>(this, R.layout.spinner_my_account, R.id.text_spinner, SectorArrrayList);
        SEC.setDropDownViewResource(R.layout.spinner_my_account);
        // attaching data adapter to spinner
        spinnersector.setAdapter(SEC);*/

        list = new ArrayList<Integer>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Profile_Activity.this, R.layout.multi_spinner_select, SectorArrrayList);
        String ss = "";
        multispinnersector
                .setListAdapter(adapter)

                .setListener(new MultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {
                        for (int i = 0; i < selected.length; i++) {
                            if (selected[i]) {
                                MultiSelectSector.append(SectorArrrayList.get(i).toString() + ",");
                                // Log.i("TAG", i + " : "+ list.get(i));
                                System.out.println("ITEM SELECTED POSITION" + i);
                                list.add(i);
                            }
                        }
                        multispinnersector.setSelected(false);
                        SectorSelectRecycler();

                    }
                })
                .setAllCheckedText("All types")
                .setAllUncheckedText("Select Sector")
                .setSelectAll(false)
                .setTitle("Select Sector")
                .selectItem(0, false)

                .setMinSelectedItems(0)
                .getTitleDividerColor();


        typeofcallspinner.setAdapter(common_class.ArrayAdapterNextFollowUp(getApplicationContext(), "typeofcall"));
        modeofcallspinner.setAdapter(common_class.ArrayAdapterNextFollowUp(getApplicationContext(), "modeofcall"));
        descriptionspinner.setAdapter(common_class.ArrayAdapterNextFollowUp(getApplicationContext(), "description"));
        customertypespinner.setAdapter(common_class.ArrayAdapterNextFollowUp(getApplicationContext(), "customertype"));

        enquirynspinner.setAdapter(common_class.ArrayAdapterNextFollowUp(getApplicationContext(), "enquiry"));
        nextfollowuppinner.setAdapter(common_class.ArrayAdapterNextFollowUp(getApplicationContext(), "nextfollowup"));
        spinnervolume.setOnItemSelectedListener(this);
        spinnersegmant.setOnItemSelectedListener(this);

        typeofcallspinner.setOnItemSelectedListener(this);
        modeofcallspinner.setOnItemSelectedListener(this);
        descriptionspinner.setOnItemSelectedListener(this);
        customertypespinner.setOnItemSelectedListener(this);
        enquirynspinner.setOnItemSelectedListener(this);
        nextfollowuppinner.setOnItemSelectedListener(this);
    }

    private void SectorSelectRecycler() {


        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        MultipleSelectedSectorList.clear();

        for (int i = 0; i < list.size(); i++) {
            System.out.println("SELECTLIST_Postition" + list.get(i));
            MultipleSelectedSectorList.add(SectornamePojo.get(list.get(i)).getSectorName());
        }
        list.clear();
        sectoradapter = new Select_Sector_Adapter(getApplicationContext(), MultipleSelectedSectorList);
        recyclerView.setAdapter(sectoradapter);
        sectoradapter.setClickListener(Profile_Activity.this);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spinnervolume) {
            if (position == 0) {
                VolumeString = "";
            } else {
                position = position - 1;
                VolumeString = MonthlyVolumePojo.get(position).getVolName();
            }
        } else if (spinner.getId() == R.id.spinnersegmant) {
            if (position == 0) {
                SegmentString = "";
            } else {
                position = position - 1;
                SegmentString = SegmentPojo.get(position).getSegName();

            }
        } else if (spinner.getId() == R.id.typeofcallspinner) {
            TypeofCallString = typeofcallspinner.getSelectedItem().toString();
        } else if (spinner.getId() == R.id.modeofcallspinner) {
            ModeOFCallStringString = modeofcallspinner.getSelectedItem().toString();
        } else if (spinner.getId() == R.id.descriptionspinner) {

            DescriptionString = descriptionspinner.getSelectedItem().toString();

        } else if (spinner.getId() == R.id.customertypespinner) {
            CustomerTypeString = customertypespinner.getSelectedItem().toString();
        } else if (spinner.getId() == R.id.enquirynspinner) {
            EnquiryString = enquirynspinner.getSelectedItem().toString();
            /*common_class.showToastMSG(Profile_Activity.this, EnquiryString, 1);*/
        } else if (spinner.getId() == R.id.nextfollowuppinner) {
            NextFollowupString = nextfollowuppinner.getSelectedItem().toString();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public Callback<ResponseBody> ProfileSaveResponse = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            System.out.println("ProfileSaveResponse is sucessful :" + response.isSuccessful());
            if (response.isSuccessful()) {
                JSONObject jsonObject = null;
                String jsonData = null;
                try {
                    jsonData = response.body().string();
                    jsonObject = new JSONObject(jsonData);
                    if (jsonObject.getString("success").equals("true")) {
                        progressBar.setVisibility(View.GONE);


                        common_class.showToastMSG(Profile_Activity.this, jsonObject.getString("msg"), 1);

                        common_class.CommonIntentwithNEwTask(MainActivity.class);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        // TastyToast.makeText(Profile_Activity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT, TastyToast.ERROR);
                        common_class.showToastMSG(Profile_Activity.this, jsonObject.getString("msg"), 3);
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

    @Override
    public void onBackPressed() {
        common_class.CommonIntentwithNEwTask(MainActivity.class);

    }

    @Override
    public void onItemClick(View view, int position) {
        System.out.println("ADAPTER_POSITION" + position);
    }

}


