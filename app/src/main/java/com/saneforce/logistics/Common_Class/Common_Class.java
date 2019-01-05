package com.saneforce.logistics.Common_Class;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saneforce.logistics.API_interface.ApiClient;
import com.saneforce.logistics.API_interface.ApiInterface;
import com.saneforce.logistics.Activity_Class.Add_Customer_Activity;
import com.saneforce.logistics.Activity_Class.Login_Activity;
import com.saneforce.logistics.Activity_Class.MainActivity;
import com.saneforce.logistics.Customer_Me.CustomerMe;
import com.saneforce.logistics.Customer_Me.Customer_Me_Success;
import com.saneforce.logistics.Customer_Profile_Pojo.CustomerProfileNameOftheCompany;
import com.saneforce.logistics.Enquiry_Me.CountryName;
import com.saneforce.logistics.Enquiry_Me.PortName;
import com.saneforce.logistics.Pojo_Class.Notification_Pojo;
import com.saneforce.logistics.R;
import com.saneforce.logistics.Rate_finder_Fragment.RatefilnderResponse;
import com.saneforce.logistics.Spinner_Response.Spinner_Pojo;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Admin on 1/31/2017.
 */

public  class Common_Class {


    Dialog dialog_invitation = null;
    Intent intent;
    Activity activity;
    public Context context;
    Shared_Common_Pref shared_common_pref;
    Gson gson;
    public static String value_true = null;
    Typeface typeface_regular, typeface_light, typeface_bold, typeface_medium;
    SharedPreferences addvehiclesharedprefrence;
    ArrayList<String> ListNameofthecompany, MonthlyVolumeArrrayList, SectorArrrayList, SegmentArrrayList, TypeofcallArrayList, ModeofCallArrayList, MultipleSelectedSectorList, DescriptionArraylist, CustomerTypeArrayList, EnquiryArrayList, NextFollowupArrayList;
    public int mYear, mMonth, mDay, mHour, mMinute;
    String ReturnDate = null;
    Spinner_Pojo spojo;
    DatePickerDialog datePickerDialog = null;
    List<CustomerMe> CustomerMeList;
    ApiInterface apiService;
    List<CustomerMe> PojoCustomerMe;

    public void CommonIntentwithFinish(Class classname) {
        intent = new Intent(activity, classname);
        activity.startActivity(intent);
        activity.finish();
    }

    public void CommonIntentwithoutFinish(Class classname) {
        intent = new Intent(activity, classname);
        activity.startActivity(intent);

    }

    public void CommonIntentwithNEwTask(Class classname) {
        intent = new Intent(activity, classname);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public void CommonIntentwithoutFinishputextra(Class classname, String title) {
        intent = new Intent(activity, classname);

        intent.putExtra("title", title);

        activity.startActivity(intent);

    }

    public void CommonGoodstype(Class classname, String title) {
        intent = new Intent(activity, classname);

        intent.putExtra("goodstype", title);
        activity.startActivity(intent);


    }

    public void commonSnackBar(String s, View act) {
        Snackbar snackbar = Snackbar.make(act, s, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public void showToastMSG(Activity Ac, String MSg, int s) {
        TastyToast.makeText(Ac, MSg,
                TastyToast.LENGTH_SHORT, s);
    }

    public Common_Class(Context context) {
        this.context = context;
        addvehiclesharedprefrence = context.getSharedPreferences("logingetaccesstoken", MODE_PRIVATE);
        shared_common_pref = new Shared_Common_Pref(context);
        typeface_regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        typeface_light = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
        typeface_bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
        typeface_medium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
    }
    public boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    public Common_Class(Activity activity) {
        this.activity = activity;
        addvehiclesharedprefrence = activity.getSharedPreferences("logingetaccesstoken", MODE_PRIVATE);
        shared_common_pref = new Shared_Common_Pref(activity);
        typeface_regular = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Regular.ttf");
        typeface_light = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        typeface_bold = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Bold.ttf");
        typeface_medium = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Medium.ttf");

    }

    public void hidekeyboard(Activity activity) {
        this.activity = activity;
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void Showkeyboard(Activity activity) {
        this.activity = activity;
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public Typeface setFontStyle(String style) {
        Typeface result = null;
        if (style.equals("light")) {
            result = typeface_light;
        } else if (style.equals("bold")) {
            result = typeface_bold;
        } else if (style.equals("regular")) {
            result = typeface_regular;
        } else if (style.equals("medium")) {
            result = typeface_medium;
        }
        return result;
    }

    public boolean isNetworkAvailable(final Context context) {
        this.context = context;

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public void logoutdialog(final Context context_) {
        this.context = context_;

        dialog_invitation = new Dialog(context, R.style.DialogSlideAnim);
        Window window = dialog_invitation.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_invitation.setContentView(R.layout.fragment_logout);
        dialog_invitation.setCancelable(false);
        dialog_invitation.setTitle("Log Out");

        dialog_invitation.setCanceledOnTouchOutside(false);
        dialog_invitation.show();

        TextView logouttt = (TextView) dialog_invitation.findViewById(R.id.logout);
        TextView textmessge = (TextView) dialog_invitation.findViewById(R.id.textmessge);

        TextView logouttitle = (TextView) dialog_invitation.findViewById(R.id.logouttitle);
        textmessge.setGravity(View.FOCUS_LEFT);
        textmessge.setTypeface(setFontStyle("light"));
        logouttitle.setTypeface(setFontStyle("light"));
        TextView cancelalerbox = (TextView) dialog_invitation.findViewById(R.id.cancel);

        cancelalerbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_invitation.cancel();
            }
        });

        logouttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_common_pref.clear_pref(Shared_Common_Pref.loggedIn);
                shared_common_pref.clear_pref(Shared_Common_Pref.cards_pref);

                dialog_invitation.cancel();
                CommonIntentwithNEwTask(Login_Activity.class);

               /* ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

                acesstokennupdate = addvehiclesharedprefrence.getString("customermeloginaccesstoken", null);
                authorize_token = "JWT " + acesstokennupdate;
                System.out.println("logout acesstoken " + authorize_token);
                Call<ResponseBody> Callto = apiService.logout(authorize_token);
                Callto.enqueue(logoutcalmain);*/
            }
        });


    }


    public void CustomerMe(final Context context_) {
        this.context = context_;
        shared_common_pref = new Shared_Common_Pref(activity);


        gson = new Gson();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Type type = new TypeToken<List<CustomerMe>>() {
        }.getType();
        System.out.println("TYPETOKEN_LIST" + type);
        CustomerMeList = gson.fromJson(shared_common_pref.getvalue(Shared_Common_Pref.cards_pref), type);
        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("name", CustomerMeList.get(0).getEmpUserName());
            paramObject.put("password", CustomerMeList.get(0).getEmpPassword());

            Call<Customer_Me_Success> Callto = apiService.LoginJSON(paramObject.toString());
            Callto.enqueue(CheckUser);

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("JSON Expections" + paramObject.toString());

        }


    }

    public Callback<Customer_Me_Success> CheckUser = new Callback<Customer_Me_Success>() {
        @Override
        public void onResponse(Call<Customer_Me_Success> call, Response<Customer_Me_Success> response) {
            shared_common_pref.clear_pref(Shared_Common_Pref.loggedIn);
            shared_common_pref.clear_pref(Shared_Common_Pref.cards_pref);
            System.out.println("checkUser is sucessful :" + response.isSuccessful());
            if (response.isSuccessful()) {
                PojoCustomerMe = response.body().getCustomerMe();
                Gson gson = new Gson();
                String json = gson.toJson(PojoCustomerMe);
                shared_common_pref.save(Shared_Common_Pref.cards_pref, json);
                System.out.println("Common_Class_Customer_Me" + json);

                if (response.body().getSuccess()) {

                    shared_common_pref.save(Shared_Common_Pref.loggedIn, "loggedIn");


                } else {


                }


            } else {
                try {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());

                    System.out.println("this is responsebody error" + jObjError.getString("success"));
                } catch (Exception e) {
                    System.out.println("catchbody error " + e.toString());
                }
            }
        }

        @Override
        public void onFailure(Call<Customer_Me_Success> call, Throwable t) {
        }


    };

    public void AddNewCustomer(final Context context_) {
        this.context = context_;

        dialog_invitation = new Dialog(context, R.style.DialogSlideAnim);
        Window window = dialog_invitation.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_invitation.setContentView(R.layout.dialog_add_ne_customer);
        dialog_invitation.setCancelable(false);
        dialog_invitation.setCanceledOnTouchOutside(false);
        dialog_invitation.show();

        TextView addnewcustomer = (TextView) dialog_invitation.findViewById(R.id.addnew);
        TextView textmessge = (TextView) dialog_invitation.findViewById(R.id.textmessge);
        textmessge.setGravity(View.FOCUS_LEFT);
        textmessge.setTypeface(setFontStyle("light"));
        TextView cancelalerbox = (TextView) dialog_invitation.findViewById(R.id.cancel);

        cancelalerbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_invitation.cancel();
            }
        });

        addnewcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_common_pref.clear_pref(Shared_Common_Pref.loggedIn);
                dialog_invitation.cancel();
                CommonIntentwithNEwTask(Add_Customer_Activity.class);

               /* ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

                acesstokennupdate = addvehiclesharedprefrence.getString("customermeloginaccesstoken", null);
                authorize_token = "JWT " + acesstokennupdate;
                System.out.println("logout acesstoken " + authorize_token);
                Call<ResponseBody> Callto = apiService.logout(authorize_token);
                Callto.enqueue(logoutcalmain);*/
            }
        });


    }

    public EditText seterror(final View v, int a, String enter_company_name) {


        EditText e = (EditText) v.findViewById(a);
        e.requestFocus();
        e.setError(enter_company_name);
        return e;
    }

    public static boolean isEmpty(List<CustomerProfileNameOftheCompany> array) {
        if (array == null || array.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isCountrynameEmpty(List<CountryName> array) {
        if (array == null || array.size() == 0) {
            return true;
        }
        return false;
    }
    public static boolean isPorteEmpty(List<PortName> array) {
        if (array == null || array.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isRatefinderEmpty(List<RatefilnderResponse> array) {
        if (array == null || array.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean NAmecheck(String array) {
        if (array == null || array.equals("")) {
            return true;
        }
        return false;
    }


    public ArrayAdapter<String> SpinnerResponseAdapter(final Context context_, ArrayList<String> List) {
        spojo = new Spinner_Pojo();
        System.out.println("ARRAY_VAULES_VALIDATION" + spojo.GetMonthlyVolume());
        this.context = context_;
        ArrayAdapter<String> NextFU = null;
        List<Notification_Pojo> notificationList = new ArrayList<>();

        int a = 0;
        NextFU = new ArrayAdapter<String>(context, R.layout.spinner_my_account, R.id.text_spinner, List);
        NextFU.setDropDownViewResource(R.layout.spinner_my_account);
        return NextFU;


    }

    public String datepicker(final Context context_) {
        // Get Current Date
        this.context = context_;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        ReturnDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        System.out.println("SELECTDATE" + ReturnDate);
                        datePickerDialog.dismiss();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        return ReturnDate;
    }
   /* public CustomAdapter SpinnerResponseAdapterIncoterms(final Context context_, List<Incoterm> List) {
        spojo = new Spinner_Pojo();
        System.out.println("ARRAY_VAULES_VALIDATION" + spojo.GetMonthlyVolume());
        this.context = context_;


        CustomAdapter adapter = new CustomAdapter(context,
                R.layout.spinner_my_account, R.id.text_spinner, List);



        adapter.setDropDownViewResource(R.layout.spinner_my_account);


*//*
        ArrayAdapter<String> NextFU = null;


        NextFU = new ArrayAdapter<String>(context, R.layout.spinner_my_account, R.id.text_spinner, List);
        NextFU.setDropDownViewResource(R.layout.spinner_my_account);*//*
        return adapter;


    }*/

    public ArrayAdapter<String> ArrayAdapterNextFollowUp(final Context context_, String name) {
        spojo = new Spinner_Pojo();
        this.context = context_;
        NextFollowupArrayList = new ArrayList<>();
        ListNameofthecompany = new ArrayList<>();
        MonthlyVolumeArrrayList = new ArrayList<>();
        TypeofcallArrayList = new ArrayList<>();
        ModeofCallArrayList = new ArrayList<>();
        DescriptionArraylist = new ArrayList<>();
        CustomerTypeArrayList = new ArrayList<>();
        EnquiryArrayList = new ArrayList<>();
        ArrayAdapter<String> NextFU = null;
        if (name.equals("enquiry")) {
            EnquiryArrayList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.Enquiry)));

            NextFU = new ArrayAdapter<String>(context, R.layout.spinner_my_account, R.id.text_spinner, EnquiryArrayList);
            NextFU.setDropDownViewResource(R.layout.spinner_my_account);
            return NextFU;
        } else if (name.equals("nextfollowup")) {
            NextFollowupArrayList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.NextFollowup)));

            NextFU = new ArrayAdapter<String>(context, R.layout.spinner_my_account, R.id.text_spinner, NextFollowupArrayList);
            NextFU.setDropDownViewResource(R.layout.spinner_my_account);
            return NextFU;
        } else if (name.equals("customertype")) {
            CustomerTypeArrayList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.CustomerTypeArray)));
            NextFU = new ArrayAdapter<String>(context, R.layout.spinner_my_account, R.id.text_spinner, CustomerTypeArrayList);
            NextFU.setDropDownViewResource(R.layout.spinner_my_account);
            return NextFU;
        } else if (name.equals("description")) {
            DescriptionArraylist.addAll(Arrays.asList(context.getResources().getStringArray(R.array.DescriptionArray)));
            NextFU = new ArrayAdapter<String>(context, R.layout.spinner_my_account, R.id.text_spinner, DescriptionArraylist);
            NextFU.setDropDownViewResource(R.layout.spinner_my_account);
            return NextFU;
        } else if (name.equals("modeofcall")) {
            ModeofCallArrayList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.ModeofcallArray)));
            NextFU = new ArrayAdapter<String>(context, R.layout.spinner_my_account, R.id.text_spinner, ModeofCallArrayList);
            NextFU.setDropDownViewResource(R.layout.spinner_my_account);
            return NextFU;

        } else if (name.equals("typeofcall")) {
            TypeofcallArrayList.addAll(Arrays.asList(context.getResources().getStringArray(R.array.TypeofcallArray)));
            NextFU = new ArrayAdapter<String>(context, R.layout.spinner_my_account, R.id.text_spinner, TypeofcallArrayList);
            NextFU.setDropDownViewResource(R.layout.spinner_my_account);
            return NextFU;

        }

        return NextFU;
    }


}
