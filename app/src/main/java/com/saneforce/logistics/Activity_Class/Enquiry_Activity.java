package com.saneforce.logistics.Activity_Class;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saneforce.logistics.API_interface.ApiClient;
import com.saneforce.logistics.API_interface.ApiInterface;
import com.saneforce.logistics.Adapter_Class.CustomListAdapterDialog;
import com.saneforce.logistics.Common_Class.Common_Class;
import com.saneforce.logistics.Common_Class.CustomAdapter;
import com.saneforce.logistics.Common_Class.Shared_Common_Pref;
import com.saneforce.logistics.Common_Class.click_Listner;
import com.saneforce.logistics.Customer_Me.CustomerMe;
import com.saneforce.logistics.Customer_Me.Customer_Me_Success;
import com.saneforce.logistics.Customer_Profile_Pojo.CustomerProfileNameOftheCompany;
import com.saneforce.logistics.Enquiry_Me.Commodity;
import com.saneforce.logistics.Enquiry_Me.ContainerType;
import com.saneforce.logistics.Enquiry_Me.CountryName;
import com.saneforce.logistics.Enquiry_Me.Incoterm;
import com.saneforce.logistics.Enquiry_Me.PackageType;
import com.saneforce.logistics.Enquiry_Me.PortName;
import com.saneforce.logistics.Enquiry_Me.Priority;
import com.saneforce.logistics.Enum_Class.Edittext_drawable;
import com.saneforce.logistics.R;
import com.saneforce.logistics.Spinner_Response.Enquiry_Spinner_Pojo;
import com.saneforce.logistics.Spinner_Response.Spinner_Pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Enquiry_Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, click_Listner {


    ImageView enquiry_activity_back_arrow;
    Common_Class common_class;
    Spinner spinnerselectfll, truckloadspinner, spinnercommodity, spinnerincoterms, spinnerpriority, spinnerport, destispinnerport, spinnersubmitpackagingtype, spinnercontainertype, spinnerfllservice;
    SwitchCompat fcltoggle, lcltoggle;
    EditText shippercompany, shipperaddress, shipperemail, shippermobile, consigcompany, consigaddress,
            consigemail, consigcontactnumber, submitnoofpackage,
            submittotalgrossweight, submitchargableweight, airfreightnoofpackage,
            airfreighttotalgrossweight, airfreightchargeableweight, submitshipmentsize,
            origioncountryname, desticountryname,
            quotebydate, Shipmentdate, originplaceofreceipt, originpickupaddress,
            destiplaceofreceipt, destipickupaddress, targetrate, paymenttermss, freightpayable, commodityvalue, shipmentreference;
    LinearLayout commonsubmitall, selefllproductlayout, fcllinearlayout, lcllinearlayout, selectairfrightlinearlayout, landfreightlinear, landfreightnooftruckloadlinear, LTLlandfreightlinear, numberoftruckloadlayout;
    Shared_Common_Pref sharedCommonPref;
    Gson gson;
    List<Commodity> Commodity;
    List<Priority> Priority;
    List<Incoterm> Incoterm;
    List<Incoterm> CustomIncoterm;
    List<CountryName> CountryNamePojoList;
    List<PackageType> PackagingTypePojoList;
    List<ContainerType> ContainerTypePojoList;
    Button enquirysavebutton;
    Enquiry_Spinner_Pojo Enquiry_Spinner_Pojo;
    SpinnerDialog origioncountryspinnerDialog, desticountryspinnerDialog;
    ArrayList<String> origioncountrynamelis, desticountrynamestring;
    ProgressBar progressBar;
    List<PortName> PortNamePojoList;
    public boolean countrycode = false;
    List<CustomerProfileNameOftheCompany> PojoNameofthecompany;
    int destiport = 0;
    int selectCountrycode, desticountrycode;
    private int mYear, mMonth, mDay, mHour, mMinute;
    ApiInterface apiService;
    TextView senddatatomail;
    int TickMark = 0;
    CustomListAdapterDialog customdialogboxadapter;
    EditText editsearch;
    String OriginPortName = null, DestiPortName = null, IncotermsString = null, CommodityString = null, PriorityString = null;
    AlertDialog alert;
    ListView lv;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_);
        common_class = new Common_Class(Enquiry_Activity.this);
        common_class.hidekeyboard(Enquiry_Activity.this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        spinnerselectfll = (Spinner) findViewById(R.id.spinnerselectfll);
        truckloadspinner = (Spinner) findViewById(R.id.truckloadspinner);
        spinnercommodity = (Spinner) findViewById(R.id.spinnercommodity);
        spinnerincoterms = (Spinner) findViewById(R.id.spinnerincoterms);
        spinnerpriority = (Spinner) findViewById(R.id.spinnerpriority);
        spinnerport = (Spinner) findViewById(R.id.spinnerport);
        destispinnerport = (Spinner) findViewById(R.id.destispinnerport);
        spinnercontainertype = (Spinner) findViewById(R.id.spinnercontainertype);
        spinnersubmitpackagingtype = (Spinner) findViewById(R.id.spinnersubmitpackagingtype);
        senddatatomail = (TextView) findViewById(R.id.senddatatomail);
        spinnerfllservice = (Spinner) findViewById(R.id.spinnerfllservice);
        fcltoggle = (SwitchCompat) findViewById(R.id.FCLtoggle);
        lcltoggle = (SwitchCompat) findViewById(R.id.LCLtoggle);
        origioncountryname = (EditText) findViewById(R.id.origioncountryname);
        desticountryname = (EditText) findViewById(R.id.desticountryname);
        quotebydate = (EditText) findViewById(R.id.quotebydate);
        Shipmentdate = (EditText) findViewById(R.id.Shipmentdate);


        shippercompany = (EditText) findViewById(R.id.shippercompany);
        shipperaddress = (EditText) findViewById(R.id.shipperaddress);
        shipperemail = (EditText) findViewById(R.id.shipperemail);
        shippermobile = (EditText) findViewById(R.id.shippermobile);
        consigcompany = (EditText) findViewById(R.id.consigcompany);
        consigaddress = (EditText) findViewById(R.id.consigaddress);
        consigemail = (EditText) findViewById(R.id.consigemail);
        consigcontactnumber = (EditText) findViewById(R.id.consigcontactnumber);
        submitnoofpackage = (EditText) findViewById(R.id.submitnoofpackage);
        submittotalgrossweight = (EditText) findViewById(R.id.submittotalgrossweight);
        submitchargableweight = (EditText) findViewById(R.id.submitchargableweight);
        airfreightnoofpackage = (EditText) findViewById(R.id.airfreightnoofpackage);
        airfreighttotalgrossweight = (EditText) findViewById(R.id.airfreighttotalgrossweight);
        airfreightchargeableweight = (EditText) findViewById(R.id.airfreightchargeableweight);
        submitshipmentsize = (EditText) findViewById(R.id.submitshipmentsize);


        originplaceofreceipt = (EditText) findViewById(R.id.originplaceofreceipt);
        originpickupaddress = (EditText) findViewById(R.id.originpickupaddress);
        destiplaceofreceipt = (EditText) findViewById(R.id.destiplaceofreceipt);
        destipickupaddress = (EditText) findViewById(R.id.destipickupaddress);

        targetrate = (EditText) findViewById(R.id.targetrate);
        paymenttermss = (EditText) findViewById(R.id.paymenttermss);
        freightpayable = (EditText) findViewById(R.id.freightpayable);
        commodityvalue = (EditText) findViewById(R.id.commodityvalue);
        shipmentreference = (EditText) findViewById(R.id.shipmentreference);
        shippercompany.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                Edittext_drawable s = Edittext_drawable.DIME;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (shippercompany.getRight() - shippercompany.getCompoundDrawables()[2].getBounds().width())) {
                        showDialog();
                        return true;
                    }
                }
                return false;
            }
        });

        enquirysavebutton = (Button) findViewById(R.id.enquirysavebutton);
        CustomIncoterm = new ArrayList<Incoterm>();
        PojoNameofthecompany = new ArrayList<CustomerProfileNameOftheCompany>();
        gson = new Gson();
        progressBar = (ProgressBar) findViewById(R.id.ProfileprogressBar);
        progressBar.setFocusableInTouchMode(false);
        sharedCommonPref = new Shared_Common_Pref(Enquiry_Activity.this);
        Enquiry_Spinner_Pojo = new Enquiry_Spinner_Pojo();
        selefllproductlayout = (LinearLayout) findViewById(R.id.selefllproductlayout);
        selectairfrightlinearlayout = (LinearLayout) findViewById(R.id.selectairfrightlinearlayout);
        fcllinearlayout = (LinearLayout) findViewById(R.id.fcllinearlayout);
        lcllinearlayout = (LinearLayout) findViewById(R.id.lcllinearlayout);
        landfreightlinear = (LinearLayout) findViewById(R.id.landfreightlinear);
        commonsubmitall = (LinearLayout) findViewById(R.id.commonsubmitall);
        landfreightnooftruckloadlinear = (LinearLayout) findViewById(R.id.landfreightnooftruckloadlinear);
        LTLlandfreightlinear = (LinearLayout) findViewById(R.id.LTLlandfreightlinear);
        numberoftruckloadlayout = (LinearLayout) findViewById(R.id.numberoftruckloadlayout);
        fcltoggle.setOnClickListener(this);
        lcltoggle.setOnClickListener(this);
        quotebydate.setOnClickListener(this);
        Shipmentdate.setOnClickListener(this);
        enquirysavebutton.setOnClickListener(this);
        senddatatomail.setOnClickListener(this);
        List<String> FLL = new ArrayList<String>();
        FLL.add("Select");
        FLL.add("Seafreight");
        FLL.add("Airfreight");
        FLL.add("Landfreight");
        FLL.add("Consol");
        FLL.add("Courier");
        FLL.add("Custom Clearance");
        FLL.add("Transportation");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_my_account, R.id.text_spinner, FLL);
        origioncountrynamelis = new ArrayList<>();

        desticountrynamestring = new ArrayList<>();
        JsonfromGson();
        spinnercommodity.setOnItemSelectedListener(this);
        spinnerincoterms.setOnItemSelectedListener(this);
        spinnerpriority.setOnItemSelectedListener(this);
        spinnerport.setOnItemSelectedListener(this);
        destispinnerport.setOnItemSelectedListener(this);
        spinnersubmitpackagingtype.setOnItemSelectedListener(this);
        spinnercontainertype.setOnItemSelectedListener(this);
        spinnerfllservice.setOnItemSelectedListener(this);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_my_account);
        // attaching data adapter to spinner
        spinnerselectfll.setAdapter(dataAdapter);
        enquiry_activity_back_arrow = (ImageView) findViewById(R.id.enquiry_activity_back_arrow);
        enquiry_activity_back_arrow.setOnClickListener(this);
        spinnerselectfll.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        if (spinnerselectfll.getSelectedItem().toString().equalsIgnoreCase("Seafreight")) {
                            lcltoggle.setChecked(false);
                            fcltoggle.setChecked(false);
                            numberoftruckloadlayout.setVisibility(View.GONE);
                            selefllproductlayout.setVisibility(View.VISIBLE);
                            //  selectairfrightlinearlayout.setVisibility(View.GONE);
                            commonsubmitall.setVisibility(View.GONE);
                            landfreightlinear.setVisibility(View.GONE);
                        } else if (spinnerselectfll.getSelectedItem().toString().equalsIgnoreCase("Airfreight")) {
                            //selectairfrightlinearlayout.setVisibility(View.VISIBLE);
                            selefllproductlayout.setVisibility(View.GONE);
                            commonsubmitall.setVisibility(View.VISIBLE);
                            fcllinearlayout.setVisibility(View.GONE);
                            numberoftruckloadlayout.setVisibility(View.GONE);
                            landfreightlinear.setVisibility(View.GONE);
                        } else if (spinnerselectfll.getSelectedItem().toString().equals("Landfreight")) {
                            selefllproductlayout.setVisibility(View.GONE);
                            // selectairfrightlinearlayout.setVisibility(View.GONE);
                            commonsubmitall.setVisibility(View.GONE);
                            landfreightlinear.setVisibility(View.VISIBLE);
                            fcllinearlayout.setVisibility(View.GONE);
                            numberoftruckloadlayout.setVisibility(View.GONE);
                        }


                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        //showToast("Spinner2: unselected");
                    }
                });

        List<String> truckload = new ArrayList<>();
        truckload.add("Select");
        truckload.add("FTL");
        truckload.add("LTL");
        ArrayAdapter<String> truckloadadapter = new ArrayAdapter<String>(this, R.layout.spinner_my_account, R.id.text_spinner, truckload);

        // Drop down layout style - list view with radio button
        truckloadadapter.setDropDownViewResource(R.layout.spinner_my_account);
        truckloadspinner.setAdapter(truckloadadapter);

        List<String> fllservice = new ArrayList<String>();
        fllservice.add("Port to Port");
        fllservice.add("Port to Door");
        fllservice.add("Door to Port");
        fllservice.add("Door to Door");
        ArrayAdapter<String> fllserviceadapter = new ArrayAdapter<String>(this, R.layout.spinner_my_account, R.id.text_spinner, fllservice);

        // Drop down layout style - list view with radio button
        fllserviceadapter.setDropDownViewResource(R.layout.spinner_my_account);
        spinnerfllservice.setAdapter(fllserviceadapter);
        Type Cppp = new TypeToken<List<CustomerProfileNameOftheCompany>>() {
        }.getType();

        List<CustomerProfileNameOftheCompany> cpa = new ArrayList<CustomerProfileNameOftheCompany>();
        cpa = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.CompanyName), Cppp);
        for (int i = 0; i < cpa.size(); i++) {
            System.out.println("TO_STRING_METHO");
            CustomerProfileNameOftheCompany item = new CustomerProfileNameOftheCompany(cpa.get(i).getEmpCode(),
                    cpa.get(i).getEmpID(), cpa.get(i).getDivisionCode(),
                    cpa.get(i).getCreatedDate(), cpa.get(i).getNameoftheCompany(),
                    cpa.get(i).getAddressoftheCompany(), cpa.get(i).getContactPersonName(), cpa.get(i).getDesignation(),
                    cpa.get(i).getMobileNumber(), cpa.get(i).getCustomerEmail(), cpa.get(i).getCustomerCategory());
            PojoNameofthecompany.add(item);
        }

        truckloadspinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        if (truckloadspinner.getSelectedItem().toString().equalsIgnoreCase("FTL")) {
                            numberoftruckloadlayout.setVisibility(View.VISIBLE);
                            commonsubmitall.setVisibility(View.GONE);
                        } else if (truckloadspinner.getSelectedItem().toString().equalsIgnoreCase("LTL")) {
                            commonsubmitall.setVisibility(View.VISIBLE);
                            numberoftruckloadlayout.setVisibility(View.GONE);
                        }


                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        //showToast("Spinner2: unselected");
                    }
                });
        origioncountryspinnerDialog = new SpinnerDialog(Enquiry_Activity.this, origioncountrynamelis, "Select Name Of the Company", "Close Button Text");// With No Animation
        origioncountryspinnerDialog = new SpinnerDialog(Enquiry_Activity.this, origioncountrynamelis, "Select or Country  Name", R.style.DialogAnimations_SmileWindow, "Close");// With 	Animation
        findViewById(R.id.origioncountryname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(Common_Class.isCountrynameEmpty(CountryNamePojoList))) {


                    origioncountryspinnerDialog.showSpinerDialog();
                }


            }
        });
        origioncountryspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                countrycode = true;
                selectCountrycode = CountryNamePojoList.get(position).getCountryCode();

                origioncountryname.setText(CountryNamePojoList.get(position).getCountryName());

                JsonfromGson();

            }
        });


        desticountryspinnerDialog = new SpinnerDialog(Enquiry_Activity.this, desticountrynamestring, "Select Country Name", "Close Button Text");// With No Animation
        desticountryspinnerDialog = new SpinnerDialog(Enquiry_Activity.this, desticountrynamestring, "Select or Search Country  Name", R.style.DialogAnimations_SmileWindow, "Close");// With 	Animation
        findViewById(R.id.desticountryname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(Common_Class.isCountrynameEmpty(CountryNamePojoList))) {


                    desticountryspinnerDialog.showSpinerDialog();
                }


            }
        });


        desticountryspinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                destiport = 1;
                desticountrycode = CountryNamePojoList.get(position).getCountryCode();

                desticountryname.setText(CountryNamePojoList.get(position).getCountryName());
                JsonfromGson();

            }
        });

    }

    private void JsonfromGson() {
        System.out.println("DESTI_CPUNTRY_CODE" + destiport);
        Enquiry_Spinner_Pojo.AllClear();
        origioncountrynamelis.clear();
        desticountrynamestring.clear();
        Enquiry_Spinner_Pojo.SetComidity("Select");

        Enquiry_Spinner_Pojo.SetPriority("Select");
        Enquiry_Spinner_Pojo.SetIncoterms("Select");
        Type COM = new TypeToken<List<Commodity>>() {
        }.getType();
        System.out.println("TYPETOKEN_LIST" + COM);
        Type PRI = new TypeToken<List<Priority>>() {
        }.getType();
        Type INC = new TypeToken<List<Incoterm>>() {
        }.getType();
        Type COnut = new TypeToken<List<CountryName>>() {
        }.getType();


        Type Port = new TypeToken<List<PortName>>() {
        }.getType();

        Type PKT = new TypeToken<List<PackageType>>() {
        }.getType();

        Type CT = new TypeToken<List<ContainerType>>() {
        }.getType();

        CustomIncoterm.clear();
        PortNamePojoList = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.PortShared), Port);

        CountryNamePojoList = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.CountryShared), COnut);

        Priority = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.Priority), PRI);
        Commodity = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.Commidity), COM);
        Incoterm = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.Incoterm), INC);


        PackagingTypePojoList = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.PackagingType), PKT);
        ContainerTypePojoList = gson.fromJson(sharedCommonPref.getvalue(Shared_Common_Pref.ContainerType), CT);
        if (!(Common_Class.isCountrynameEmpty(CountryNamePojoList))) {
            for (int i = 0; i < CountryNamePojoList.size(); i++) {
                origioncountrynamelis.add(CountryNamePojoList.get(i).getCountryName());
                desticountrynamestring.add(CountryNamePojoList.get(i).getCountryName());
            }
        }

        System.out.println("Priority_Size" + Priority.size());
        for (int i = 0; i < Commodity.size(); i++) {

            Enquiry_Spinner_Pojo.SetComidity(Commodity.get(i).getCommodityName());
        }
        if (PackagingTypePojoList.size() > 1) {
            System.out.println("Packaging Type" + Priority.size());
            for (int i = 0; i < PackagingTypePojoList.size(); i++) {

                Enquiry_Spinner_Pojo.SetPackagingType(PackagingTypePojoList.get(i).getPackageName());
            }
        }
        for (int i = 0; i < Priority.size(); i++) {

            Enquiry_Spinner_Pojo.SetPriority(Priority.get(i).getPriorityName());
        }
        for (int i = 0; i < Incoterm.size(); i++) {


            Incoterm item = new Incoterm(Incoterm.get(i).getDivisionID(), Incoterm.get(i).getIncotermsName());
            CustomIncoterm.add(item);

            Enquiry_Spinner_Pojo.SetIncoterms(Incoterm.get(i).getIncotermsName());
        }


        for (int i = 0; i < ContainerTypePojoList.size(); i++) {

            Enquiry_Spinner_Pojo.SetContainerType(ContainerTypePojoList.get(i).getContainerName());
        }
        if (!countrycode) {

            for (int i = 0; i < PortNamePojoList.size(); i++) {


                System.out.println("GET_PORT_NAme" + PortNamePojoList.get(i).getPortName());

                Enquiry_Spinner_Pojo.SetPortName(PortNamePojoList.get(i).getPortName());

                Enquiry_Spinner_Pojo.SetDestiPortName(PortNamePojoList.get(i).getPortName());
            }
        } else if (destiport == 1) {
            Enquiry_Spinner_Pojo.DestiprtClear();
            System.out.println("Desti_PORT_Size" + PortNamePojoList.size() + "DESTI_CPUNTRY_CODE" + desticountrycode);
            for (int i = 0; i < PortNamePojoList.size(); i++) {
                System.out.println("Desti_PORT_COUNTRY_CODE" + PortNamePojoList.get(i).getCountryCode());

                if (PortNamePojoList.get(i).getCountryCode() == desticountrycode) {

                    System.out.println("Desti_ELSE_PORT_NAME" + PortNamePojoList.get(i).getPortName());
                    Enquiry_Spinner_Pojo.SetDestiPortName(PortNamePojoList.get(i).getPortName());
                }
            }
            destiport = 0;
        } else {
            Enquiry_Spinner_Pojo.Clear();


            System.out.println("ELSE_CONDITION" + PortNamePojoList.size() + "SELECT_COUNTRY_CODE" + selectCountrycode);

            for (int i = 0; i < PortNamePojoList.size(); i++) {
                System.out.println("PORT_COUNTRY_CODE" + PortNamePojoList.get(i).getCountryCode());

                if (PortNamePojoList.get(i).getCountryCode() == selectCountrycode) {

                    System.out.println("ELSE_PORT_NAME" + PortNamePojoList.get(i).getPortName());
                    Enquiry_Spinner_Pojo.SetPortName(PortNamePojoList.get(i).getPortName());
                }
            }

        }
        spinnercontainertype.setAdapter(common_class.SpinnerResponseAdapter(getApplicationContext(), Enquiry_Spinner_Pojo.GetContainerType()));

        spinnersubmitpackagingtype.setAdapter(common_class.SpinnerResponseAdapter(getApplicationContext(), Enquiry_Spinner_Pojo.GetPackagingType()));
        destispinnerport.setAdapter(common_class.SpinnerResponseAdapter(getApplicationContext(), Enquiry_Spinner_Pojo.GetDestiPortName()));
        spinnerport.setAdapter(common_class.SpinnerResponseAdapter(getApplicationContext(), Enquiry_Spinner_Pojo.GetPortName()));
        System.out.println("ContainerTypePojoList" + ContainerTypePojoList);
        CustomAdapter adapter = new CustomAdapter(Enquiry_Activity.this,
                R.layout.spinner_my_account, R.id.text_spinner, CustomIncoterm, 1);
        spinnerincoterms.setAdapter(adapter);
        //    spinnerincoterms.setAdapter(common_class.SpinnerResponseAdapterIncoterms(getApplicationContext(), Incoterm));
        spinnercommodity.setAdapter(common_class.SpinnerResponseAdapter(getApplicationContext(), Enquiry_Spinner_Pojo.GetComidity()));
        spinnerpriority.setAdapter(common_class.SpinnerResponseAdapter(getApplicationContext(), Enquiry_Spinner_Pojo.GetPriority()));
    }


    private void showDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(

                Enquiry_Activity.this);


        View view = getLayoutInflater().inflate(R.layout.custom_list_view, null);
        editsearch = (EditText) view.findViewById(R.id.search);
        customdialogboxadapter = new CustomListAdapterDialog(Enquiry_Activity.this, (ArrayList<CustomerProfileNameOftheCompany>) PojoNameofthecompany, TickMark);

        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                customdialogboxadapter.getFilter().filter(arg0);
                System.out.println("TEXT_EDITTEXT" + arg0);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });
        lv = (ListView) view.findViewById(R.id.custom_list);
        alertDialog.setNegativeButton("Cancel",

                new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog,

                                        int which) {

                        // TODO Auto-generated method stub


                    }

                });
        //3000236597
        alertDialog.setView(view);
        alert = alertDialog.create();

        alert.setTitle(" Select Name"); // Title
        alert.setIcon(R.drawable.select_icon);
        System.out.println("POJO_NAME_VALUES" + PojoNameofthecompany);

        lv.setAdapter(customdialogboxadapter);

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> arg0, View arg1,

                                    int position, long arg3) {

                // TODO Auto-generated method stub
                TickMark = position;

                shippercompany.setText(PojoNameofthecompany.get(position).getNameoftheCompany());
                shipperaddress.setText(PojoNameofthecompany.get(position).getAddressoftheCompany());
                shipperemail.setText(PojoNameofthecompany.get(position).getCustomerEmail());
                shippermobile.setText(PojoNameofthecompany.get(position).getMobileNumber());
                shippercompany.setFocusable(false);
                shippercompany.setFocusableInTouchMode(false);

                shipperaddress.setFocusable(false);
                shipperaddress.setFocusableInTouchMode(false);
                shipperemail.setFocusable(false);
                shipperemail.setFocusableInTouchMode(false);
                shippermobile.setFocusable(false);
                shippermobile.setFocusableInTouchMode(false);
                //String a=(String)lv.getItemIdAtPosition(position);

                System.out.println("LIST_VIEW_EXAMPLE" + lv.getSelectedItem());

                // user touches widget on phone with touch screen


                alert.cancel();

            }

        });*/


        alert.show();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enquiry_activity_back_arrow:
                common_class.CommonIntentwithFinish(MainActivity.class);
                break;

            case R.id.FCLtoggle:
                if (fcltoggle.isChecked()) {
                    fcllinearlayout.setVisibility(View.VISIBLE);
                    lcllinearlayout.setVisibility(View.GONE);
                    lcltoggle.setChecked(false);
                    numberoftruckloadlayout.setVisibility(View.GONE);
                    commonsubmitall.setVisibility(View.GONE);
                } else {
                    fcllinearlayout.setVisibility(View.GONE);
                }
                //Button is OFF
                break;
            case R.id.LCLtoggle:

                if (lcltoggle.isChecked()) {
                    fcltoggle.setChecked(false);
                    fcllinearlayout.setVisibility(View.GONE);
                    commonsubmitall.setVisibility(View.VISIBLE);
                    lcllinearlayout.setVisibility(View.VISIBLE);
                } else {
                    lcllinearlayout.setVisibility(View.GONE);
                }
                break;
            case R.id.quotebydate:
                datepicker(1);
                break;
            case R.id.Shipmentdate:
                datepicker(2);
                break;
            case R.id.enquirysavebutton:
                if (Validation()) {
                    common_class.showToastMSG(Enquiry_Activity.this, "Successfully Quoted", 1);
                    common_class.CommonIntentwithFinish(MainActivity.class);

                    JSONObject shipperobject = new JSONObject();
                    JSONObject ConsigObject = new JSONObject();
                    JSONObject OriginLocation = new JSONObject();
                    JSONObject DestiLocation = new JSONObject();
                    JSONObject AditionalInfo = new JSONObject();
                    JSONArray ShipperArray = new JSONArray();
                    JSONArray ConsigArray = new JSONArray();
                    JSONArray OriginLocationArray = new JSONArray();
                    JSONArray DestiLocationArray = new JSONArray();
                    JSONArray AditionalInfoArray = new JSONArray();

                    try {
                        //SHIPPER
                        shipperobject.put("Companyname", shippercompany.getText().toString());
                        shipperobject.put("Address", shipperaddress.getText().toString());
                        shipperobject.put("Email", shipperemail.getText().toString());
                        shipperobject.put("Mobile", shippermobile.getText().toString());
                        //CONSIG
                        ConsigObject.put("Companyname", consigcompany.getText().toString());
                        ConsigObject.put("Address", consigaddress.getText().toString());
                        ConsigObject.put("Email", consigemail.getText().toString());
                        ConsigObject.put("Mobile", consigcontactnumber.getText().toString());
                        //ORIGIN
                        OriginLocation.put("OriginCountryName", origioncountryname.getText().toString());
                        OriginLocation.put("PortName", OriginPortName);
                        OriginLocation.put("Receipt", originplaceofreceipt.getText().toString());
                        OriginLocation.put("Address", originpickupaddress.getText().toString());
                        //DESTI
                        DestiLocation.put("DestiCountryName", desticountryname.getText().toString());
                        DestiLocation.put("PortName", OriginPortName);
                        DestiLocation.put("Receipt", originplaceofreceipt.getText().toString());
                        DestiLocation.put("Address", originpickupaddress.getText().toString());
                        //OTHERS
                        AditionalInfo.put("Incoterms", IncotermsString);
                        AditionalInfo.put("Comodity", CommodityString);
                        AditionalInfo.put("ComodityValue", commodityvalue.getText().toString());
                        AditionalInfo.put("FreightPayable", freightpayable.getText().toString());
                        AditionalInfo.put("PaymentTerms", paymenttermss.getText().toString());
                        AditionalInfo.put("shipmentreference", shipmentreference.getText().toString());
                        AditionalInfo.put("Shipmentdate", Shipmentdate.getText().toString());
                        AditionalInfo.put("Priority", PriorityString);
                        AditionalInfo.put("TargetRate", targetrate.getText().toString());
                        AditionalInfo.put("Quotebydate", quotebydate.getText().toString());


                         //END
                        ShipperArray.put(shipperobject);
                        ConsigArray.put(ConsigObject);
                        OriginLocationArray.put(OriginLocation);
                        DestiLocationArray.put(DestiLocation);
                        AditionalInfoArray.put(AditionalInfo);
                        JSONObject jsonobject = new JSONObject();
                        jsonobject.put("SHIPPERARRAY", ShipperArray);
                        jsonobject.put("CONSIGARRAY", ConsigArray);
                        jsonobject.put("ORIGINLOCATION", OriginLocationArray);
                        jsonobject.put("DESTILOCATION", DestiLocationArray);
                        jsonobject.put("AditionalInfo", AditionalInfoArray);

                    /* Call<Customer_Me_Success> Callto = apiService.LoginJSON(paramObject.toString());
                        Callto.enqueue(CheckUser);*/
                        System.out.println("JSON VALUES" + jsonobject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }


                }
              /*  progressBar.setVisibility(View.VISIBLE);

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
                CALL.enqueue(ProfileSaveResponse);*/
                break;

            case R.id.senddatatomail:
                common_class.showToastMSG(Enquiry_Activity.this, "Under Progress", 2);
                break;
        }
    }

    public boolean Validation() {
      /*  EditText shippercompany, shipperaddress, shipperemail, shippermobile, consigcompany, consigaddress,
                consigemail, consigcontactnumber, submitnoofpackage, submittotalgrossweight, submitchargableweight, airfreightnoofpackage,
                airfreighttotalgrossweight, airfreightchargeableweight, submitshipmentsize,
                origioncountryname, desticountryname, quotebydate, Shipmentdate;*/

        if (shippercompany.getText().toString().equalsIgnoreCase("")) {

           /* shippercompany.requestFocus();
            shippercompany.setError("");*/
            // common_class.seterror(v, R.id.shippercompany, "Enter Company name");
            seterror(R.id.shippercompany, "Enter Company name");
            return false;
        }
        if (shipperaddress.getText().toString().equalsIgnoreCase("")) {
            seterror(R.id.shipperaddress, "Enter Shipment Address");
            return false;

        }
        if (shipperemail.getText().toString().equalsIgnoreCase("")) {

            seterror(R.id.shipperemail, "Enter The Email");
            return false;
        }

        if (shippermobile.getText().toString().equalsIgnoreCase("")) {
            seterror(R.id.shippermobile, "Enter The Mobile Number");

            return false;
        }
        if (consigcompany.getText().toString().equalsIgnoreCase("")) {
            seterror(R.id.consigcompany, "Enter The  Company name");

            return false;
        }
        if (consigaddress.getText().toString().equalsIgnoreCase("")) {
            seterror(R.id.consigaddress, "Enter Consig Address");
            return false;

        }
        if (consigemail.getText().toString().equalsIgnoreCase("")) {
            seterror(R.id.consigemail, "Enter The Email");

            return false;
        }


        if (consigcontactnumber.getText().toString().toString().equals("")) {  seterror(R.id.consigcontactnumber, "Enter The Mobile Number");

            return false;
        }
        if (origioncountryname.getText().toString().toString().equals("")) {
            common_class.showToastMSG(Enquiry_Activity.this, "Select Origin Country", 3);
            return false;
        }
        if ((OriginPortName == null || OriginPortName.equals(""))) {
            common_class.showToastMSG(Enquiry_Activity.this, "Select PortName", 3);

        }
        if (originplaceofreceipt.getText().toString().toString().equals("")) {
            seterror(R.id.originplaceofreceipt, "Enter The Receipt");
            return false;
        }
        if (originpickupaddress.getText().toString().toString().equals("")) {
            seterror(R.id.originpickupaddress, "Enter The Pickup Address");
            return false;
        }
        if (DestiPortName == null || DestiPortName.equals("")) {
            common_class.showToastMSG(Enquiry_Activity.this, "Select PortName", 3);

        }
        if (destiplaceofreceipt.getText().toString().toString().equals("")) {
            seterror(R.id.destiplaceofreceipt, "Enter The Receipt");
            return false;
        }
        if (destipickupaddress.getText().toString().toString().equals("")) {
            seterror(R.id.destipickupaddress, "Enter The Pickup Address");
            return false;
        }


        return true;

    }


    public void seterror(int a, String enter_company_name) {

        EditText e = (EditText) findViewById(a);
        e.requestFocus();
        e.setError(enter_company_name);
    }

    public void datepicker(final int i) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (i == 1) {
                            quotebydate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        } else {
                            Shipmentdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spinnerincoterms) {
            IncotermsString = spinnerincoterms.getSelectedItem().toString();
        } else if (spinner.getId() == R.id.spinnercommodity) {
            CommodityString = spinnercommodity.getSelectedItem().toString();
        } else if (spinner.getId() == R.id.spinnerport) {
            OriginPortName = spinnerport.getSelectedItem().toString();
        } else if (spinner.getId() == R.id.destispinnerport) {
            DestiPortName = destispinnerport.getSelectedItem().toString();
        } else if (spinner.getId() == R.id.spinnerpriority) {
            PriorityString = spinnerpriority.getSelectedItem().toString();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public Callback<ResponseBody> EnquirySaveResponse = new Callback<ResponseBody>() {
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


                        common_class.showToastMSG(Enquiry_Activity.this, jsonObject.getString("msg"), 1);

                        common_class.CommonIntentwithNEwTask(MainActivity.class);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        // TastyToast.makeText(Profile_Activity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT, TastyToast.ERROR);
                        common_class.showToastMSG(Enquiry_Activity.this, jsonObject.getString("msg"), 3);
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
    public void onClick(View view, int position, String selectnam) {
        System.out.println("CLICKVALUES_DIALOG" + selectnam);
        alert.cancel();

        for (int i = 0; i < PojoNameofthecompany.size(); i++) {
            if ((PojoNameofthecompany.get(i).getNameoftheCompany())
                    .equals(selectnam)) {
                TickMark = i;
                System.out.println("POSITION_VALUES" + i);
                shippercompany.setText(PojoNameofthecompany.get(TickMark).getNameoftheCompany());
                shipperaddress.setText(PojoNameofthecompany.get(TickMark).getAddressoftheCompany());
                shipperemail.setText(PojoNameofthecompany.get(TickMark).getCustomerEmail());
                shippermobile.setText(PojoNameofthecompany.get(TickMark).getMobileNumber());
                shippercompany.setFocusable(false);
                shippercompany.setFocusableInTouchMode(false);
                // System.out.println("CLICKVALUES_DIALOG" + PojoNameofthecompany.get(position).getNameoftheCompany());
                shipperaddress.setFocusable(false);
                shipperaddress.setFocusableInTouchMode(false);
                shipperemail.setFocusable(false);
                shipperemail.setFocusableInTouchMode(false);
                shippermobile.setFocusable(false);
                shippermobile.setFocusableInTouchMode(false);

            }
        }


    }
}
